package hanyahunya.stock.external.kiwoom;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hanyahunya.stock.external.kiwoom.dto.ShortSellDto;
import hanyahunya.stock.external.kiwoom.dto.ShortSellListDto;
import hanyahunya.stock.external.kiwoom.dto.StockDetailDto;
import hanyahunya.stock.external.kiwoom.dto.StockDetailListDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class KiwoomService {

    @Value("${kiwoom.appkey}")
    private String appKey;
    @Value("${kiwoom.secretkey}")
    private String secretKey;

    private String accessToken;

    private static final String API_URL = "https://api.kiwoom.com";
    private final HttpHeaders headers = new HttpHeaders();
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        setToken();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    void setToken() {
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        body.put("appkey", appKey);
        body.put("secretkey", secretKey);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(API_URL + "/oauth2/token", entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.getBody());
            accessToken =  node.get("token_type").asText() + " " + node.get("token").asText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<LocalDate, StockDetailDto> getDailyStockDetails(String stockCode, LocalDate date) {
        Map<LocalDate, StockDetailDto> resultMap = new TreeMap<>();

        headers.set("Authorization", accessToken);
        headers.set("cont-yn", "Y");
        headers.set("api-id", "ka10086");

        Map<String, String> body = new HashMap<>();
        body.put("stk_cd", stockCode);
        body.put("qry_dt", date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        body.put("indc_tp", "1"); // 0: 数量、1:100万ウォン 単位

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<StockDetailListDto> response = restTemplate.exchange(API_URL + "/api/dostk/mrkcond", HttpMethod.POST, entity, StockDetailListDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            for (StockDetailDto dto : response.getBody().getStockDetails()) {
                // 絶対値処理
                dto.setOpenPrice(Math.abs(dto.getOpenPrice()));
                dto.setHighPrice(Math.abs(dto.getHighPrice()));
                dto.setLowPrice(Math.abs(dto.getLowPrice()));
                dto.setClosePrice(Math.abs(dto.getClosePrice()));
                // 文字列の数値前処理
                dto.setIndividual(rawParseInt(dto.getIndividualRaw()));
                dto.setFore(rawParseInt(dto.getForeRaw()));
                dto.setInstitution(rawParseInt(dto.getInstitutionRaw()));
                dto.setProgram(rawParseInt(dto.getProgramRaw()));

                resultMap.put(dto.getDate(), dto);
            }
        }
        return resultMap;
    }

    public Map<LocalDate, ShortSellDto> getShortSellStockDetails(String stockCode, LocalDate startDate, LocalDate endDate, boolean isSearchDate) {
        Map<LocalDate, ShortSellDto> resultMap = new TreeMap<>();

        headers.set("Authorization", accessToken);
        headers.set("cont-yn", "Y");
        headers.set("api-id", "ka10014");

        Map<String, String> body = new HashMap<>();
        body.put("stk_cd", stockCode);
        body.put("tm_tp", isSearchDate ? "1" : "0"); // 0: 期間設定　1: 最近1か月
        body.put("strt_dt", startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        body.put("end_dt", endDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<ShortSellListDto> response = restTemplate.exchange(API_URL + "/api/dostk/shsa", HttpMethod.POST, entity, ShortSellListDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            response.getBody().getShortSellDtos().forEach(dto -> {
                resultMap.put(dto.getDate(), dto);
            });
        }
        return resultMap;
    }

    private int rawParseInt(String input) {
        if (input.contains("--")) {
            String replaced = input.replace("--", "-");
            return Integer.parseInt(replaced);
        }
        return Integer.parseInt(input);
    }

}

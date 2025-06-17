package hanyahunya.stock.external.kiwoom;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class KiwoomService {

    @Value("${kiwoom.appkey}")
    private String appKey;
    @Value("${kiwoom.secretkey}")
    private String secretKey;

    private String accessToken;

    private static final String API_URL = "https://api.kiwoom.com/oauth2/token";
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        setToken();
    }

    void setToken() {
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        body.put("appkey", appKey);
        body.put("secretkey", secretKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

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
}

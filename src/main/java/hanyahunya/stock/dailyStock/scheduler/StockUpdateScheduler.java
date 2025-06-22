package hanyahunya.stock.dailyStock.scheduler;

import hanyahunya.stock.dailyStock.DailyStockService;
import hanyahunya.stock.dailyStock.Dto.SaveStockDetailDto;
import hanyahunya.stock.dailyStock.Dto.SaveStockDetailListDto;
import hanyahunya.stock.external.kiwoom.KiwoomService;
import hanyahunya.stock.external.kiwoom.dto.MergedStockDto;
import hanyahunya.stock.stock.Stock;
import hanyahunya.stock.userStocks.UserStockService;
import hanyahunya.stock.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
@RequiredArgsConstructor
public class StockUpdateScheduler {
    private final UserStockService userStockService;
    private final DailyStockService dailyStockService;
    private final KiwoomService kiwoomService;

    @Scheduled(cron = "0 0 6 * * *")
    public void updateStock() {
        List<SaveStockDetailDto> saveDtoList = new ArrayList<>();
        List<String> activatedStockCodes = userStockService.getActivatedStockCodes();
        for (String stockCode : activatedStockCodes) {
            LocalDate latestStockDate = dailyStockService.getLatestStockDate(stockCode);

            Map<LocalDate, MergedStockDto> mergedStockInfo = kiwoomService.getMergedStockInfo(stockCode, 1);
            LocalDate key = ((TreeMap<LocalDate, MergedStockDto>) mergedStockInfo).lastKey();
            if (latestStockDate != null && latestStockDate.isEqual(key)) {
                continue;
            }
            MergedStockDto stockDto = mergedStockInfo.get(key);
            saveDtoList.add(SaveStockDetailDto.builder()
                    .stock(Stock.builder().stockCode(stockCode).build())
                    .date(stockDto.getDate())
                    .openPrice(stockDto.getOpenPrice())
                    .highPrice(stockDto.getHighPrice())
                    .lowPrice(stockDto.getLowPrice())
                    .closePrice(stockDto.getClosePrice())
                    .priceChangeRate(stockDto.getPriceChangeRate())
                    .volume(stockDto.getVolume())
                    .individual(stockDto.getIndividual())
                    .fore(stockDto.getFore())
                    .institution(stockDto.getInstitution())
                    .program(stockDto.getProgram())
                    .shortSellVolume(stockDto.getShortSellVolume())
                    .cumulativeShortVolume(stockDto.getCumulativeShortVolume())
                    .shortSellRatio(stockDto.getShortSellRatio())
                    .shortSellAmount(stockDto.getShortSellAmount())
                    .shortSellAvgPrice(stockDto.getShortSellAvgPrice())
                    .build());

            ResponseDto<Void> result = dailyStockService.saveStockData(SaveStockDetailListDto.set(saveDtoList));

            if (!result.isSuccess()) {
                System.out.println(key);
            }
        }
    }
}

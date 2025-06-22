package hanyahunya.stock.dailyStock.scheduler;

import hanyahunya.stock.dailyStock.service.DailyStockAutoInsertService;
import hanyahunya.stock.dailyStock.service.DailyStockService;
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
    private final DailyStockAutoInsertService dailyStockAutoInsertService;

    @Scheduled(cron = "0 0 6 * * *")
    public void updateStock() {
        List<String> activatedStockCodes = userStockService.getActivatedStockCodes();
        for (String stockCode : activatedStockCodes) {
            dailyStockAutoInsertService.insertNewStock(stockCode);
        }
    }
}

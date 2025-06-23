package hanyahunya.stock.dailyStock.service;

import hanyahunya.stock.dailyStock.Dto.SaveStockDetailDto;
import hanyahunya.stock.dailyStock.Dto.SaveStockDetailListDto;
import hanyahunya.stock.external.kiwoom.KiwoomService;
import hanyahunya.stock.external.kiwoom.dto.MergedStockDto;
import hanyahunya.stock.stock.Stock;
import hanyahunya.stock.userStocks.UserStockService;
import hanyahunya.stock.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class DailyStockAutoInsertService {
    private final KiwoomService kiwoomService;
    private final DailyStockService dailyStockService;
    private final DailyStockLabelingService dailyStockLabelingService;

    public void insertNewStock(String stockCode) {
        LocalDate latestStockDate = dailyStockService.getLatestStockDate(stockCode);

        Map<LocalDate, MergedStockDto> mergedStockInfo = kiwoomService.getMergedStockInfo(stockCode, 1);
        LocalDate key = ((TreeMap<LocalDate, MergedStockDto>) mergedStockInfo).lastKey();

        if (latestStockDate != null && latestStockDate.isEqual(key)) {
            return;
        }

        MergedStockDto stockDto = mergedStockInfo.get(key);
        SaveStockDetailDto saveDto = toSaveDto(stockCode, stockDto);

        ResponseDto<Void> result = dailyStockService.saveStockData(
                SaveStockDetailListDto.set(List.of(saveDto))
        );

        if (!result.isSuccess()) {
            System.out.println("[保存失敗] stockCode: " + stockCode + ", date: " + key);
        }
    }

    @Async
    public void insertStock(String stockCode) {
        List<SaveStockDetailDto> saveDtoList = new ArrayList<>();

        Map<LocalDate, MergedStockDto> mergedStockInfo = kiwoomService.getMergedStockInfo(stockCode, 270);
        for (LocalDate key : mergedStockInfo.keySet()) {
            MergedStockDto stockDto = mergedStockInfo.get(key);
            saveDtoList.add(toSaveDto(stockCode, stockDto));

        }
        ResponseDto<Void> result = dailyStockService.saveStockData(SaveStockDetailListDto.set(saveDtoList));

        dailyStockLabelingService.labelDailyStock(stockCode, true);

        if (!result.isSuccess()) {
            System.out.println("[保存失敗] stockCode: " + stockCode);
        }
    }

    private SaveStockDetailDto toSaveDto(String stockCode, MergedStockDto stockDto) {
        return SaveStockDetailDto.builder()
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
                .build();
    }

}

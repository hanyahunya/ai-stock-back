package hanyahunya.stock.dailyStock.Dto;

import hanyahunya.stock.dailyStock.DailyStock;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class StockChartPointDto {
    private String stockCode;
    private String stockName;
    private LocalDate stockDate;
    private int openPrice;
    private int highPrice;
    private int lowPrice;
    private int closePrice;
    private int volume;

    public static StockChartPointDto entityToDto(DailyStock dailyStock) {
        return StockChartPointDto.builder()
                .stockCode(dailyStock.getStock().getStockCode())
                .stockName(dailyStock.getStock().getStockName())
                .stockDate(dailyStock.getDate())
                .openPrice(dailyStock.getOpenPrice())
                .highPrice(dailyStock.getHighPrice())
                .lowPrice(dailyStock.getLowPrice())
                .closePrice(dailyStock.getClosePrice())
                .volume(dailyStock.getVolume())
                .build();
    }
}

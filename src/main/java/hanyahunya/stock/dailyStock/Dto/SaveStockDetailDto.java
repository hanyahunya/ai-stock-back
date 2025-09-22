package hanyahunya.stock.dailyStock.Dto;

import hanyahunya.stock.dailyStock.DailyStock;
import hanyahunya.stock.stock.Stock;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SaveStockDetailDto {

    private Stock stock;

    private LocalDate date;
    // StockDetailDto
    private int openPrice;
    private int highPrice;
    private int lowPrice;
    private int closePrice;
    private double priceChangeRate;
    private int volume;
    //    private int volumePrice;
    private int individual;
    private int fore;
    private int institution;
    private int program;
    // ShortSellDto
    private int shortSellVolume;
    private int cumulativeShortVolume;
    private double shortSellRatio;
    private int shortSellAmount;
    private int shortSellAvgPrice;

    public DailyStock toEntity() {
        return DailyStock.builder()
                .stock(this.stock)
                .date(this.date)
                .openPrice(this.openPrice)
                .highPrice(this.highPrice)
                .lowPrice(this.lowPrice)
                .closePrice(this.closePrice)
                .priceChangeRate(this.priceChangeRate)
                .volume(this.volume)
                .individual(this.individual)
                .fore(this.fore)
                .institution(this.institution)
                .program(this.program)
                .shortSellVolume(this.shortSellVolume)
                .cumulativeShortVolume(this.cumulativeShortVolume)
                .shortSellRatio(this.shortSellRatio)
                .shortSellAmount(this.shortSellAmount)
                .shortSellAvgPrice(this.shortSellAvgPrice)
                .build();
    }
}

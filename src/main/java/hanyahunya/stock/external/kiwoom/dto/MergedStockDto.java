package hanyahunya.stock.external.kiwoom.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter
public class MergedStockDto {

    private LocalDate date;
    // StockDetailDto
    private int openPrice;
    private int highPrice;
    private int lowPrice;
    private int closePrice;
    private double priceChangeRate;
    private int volume;
    private int volumePrice;
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
}
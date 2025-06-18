package hanyahunya.stock.external.kiwoom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class StockDetailDto {
    @JsonProperty("date")
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate date;
    @JsonProperty("open_pric")
    private int openPrice;
    @JsonProperty("high_pric")
    private int highPrice;
    @JsonProperty("low_pric")
    private int lowPrice;
    @JsonProperty("close_pric")
    private int closePrice;
    @JsonProperty("flu_rt")
    private double priceChangeRate;
    @JsonProperty("trde_qty")
    private int volume;
    @JsonProperty("amt_mn")
    private int volumePrice; // * 거래대금 추가
//    @JsonProperty("crd_rt")
//    private double creditRatio;
    @JsonProperty("ind_netprps")
    private String individualRaw; // データが --58000 こんな風に来るため、Stringで受ける
    @JsonProperty("for_netprps")
    private String foreRaw;
    @JsonProperty("orgn_netprps")
    private String institutionRaw;
    @JsonProperty("prm")
    private String programRaw;
    private int individual;
    private int fore;
    private int institution;
    private int program;
//    @JsonProperty("crd_remn_rt")
//    private double creditRemainRatio;
}

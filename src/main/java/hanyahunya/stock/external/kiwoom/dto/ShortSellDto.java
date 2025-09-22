package hanyahunya.stock.external.kiwoom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ShortSellDto {
    @JsonProperty("dt")
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate date;
    @JsonProperty("shrts_qty")
    private int shortSellVolume;
    @JsonProperty("ovr_shrts_qty")
    private int cumulativeShortVolume;
    @JsonProperty("trde_wght")
    private double shortSellRatio;
    @JsonProperty("shrts_trde_prica")
    private int shortSellAmount;
    @JsonProperty("shrts_avg_pric")
    private int shortSellAvgPrice;
}

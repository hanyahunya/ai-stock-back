package hanyahunya.stock.external.kiwoom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class StockDetailListDto {
    @JsonProperty("daly_stkpc")
    private List<StockDetailDto> stockDetails;
}

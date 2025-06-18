package hanyahunya.stock.external.kiwoom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ShortSellListDto {
    @JsonProperty("shrts_trnsn")
    private List<ShortSellDto> shortSellDtos;
}

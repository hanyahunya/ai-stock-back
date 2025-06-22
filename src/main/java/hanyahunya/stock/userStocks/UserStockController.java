package hanyahunya.stock.userStocks;

import hanyahunya.stock.userStocks.dto.UserStockDto;
import hanyahunya.stock.userStocks.dto.UserStockListDto;
import hanyahunya.stock.userStocks.entity.UserStock;
import hanyahunya.stock.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hanyahunya.stock.util.ResponseUtil.toResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userstock")
public class UserStockController {
    private final UserStockService userStockService;
    @PostMapping
    public ResponseEntity<ResponseDto<Void>> addUserStock(@RequestBody @Valid UserStockDto userStockDto) {
        ResponseDto<Void> responseDto = userStockService.addUserStock(userStockDto);
        return toResponse(responseDto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<UserStockListDto>> getUserStockList(@RequestBody String userId) {
        ResponseDto<UserStockListDto> responseDto = userStockService.getUserStock(userId);
        return toResponse(responseDto);
    }
}

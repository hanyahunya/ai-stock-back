package hanyahunya.stock.userStocks;

import hanyahunya.stock.userStocks.dto.UserStockDto;
import hanyahunya.stock.userStocks.dto.UserStockListDto;
import hanyahunya.stock.util.ResponseDto;

import java.util.List;

public interface UserStockService {
    ResponseDto<Void> addUserStock(UserStockDto userStockDto);

    ResponseDto<UserStockListDto> getUserStock(String userId);
}

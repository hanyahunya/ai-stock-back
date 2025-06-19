package hanyahunya.stock.userStocks;

import hanyahunya.stock.userStocks.dto.UserStockDto;
import hanyahunya.stock.util.ResponseDto;

public interface UserStockService {
    ResponseDto<Void> addUserStock(UserStockDto userStockDto);
}

package hanyahunya.stock.userStocks;

import hanyahunya.stock.userStocks.dto.UserStockDto;
import hanyahunya.stock.userStocks.dto.UserStockListDto;
import hanyahunya.stock.userStocks.entity.UserStock;
import hanyahunya.stock.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStockServiceImpl implements UserStockService {
    private final UserStockRepository userStockRepository;

    @Override
    public ResponseDto<Void> addUserStock(UserStockDto userStockDto) {
        try {
            userStockRepository.save(UserStock.of(userStockDto.getUserId(), userStockDto.getStockCode()));
            return ResponseDto.success("保存に成功しました");
        } catch (Exception e) {
            return ResponseDto.fail("保存に失敗しました");
        }
    }

    @Override
    public ResponseDto<UserStockListDto> getUserStock(String userId) {
        List<UserStockDto> resList = new ArrayList<>();
        try {
            userStockRepository.findUserStockByUser_UserId(userId).forEach(userStock -> {
                resList.add(UserStockDto.toDto(userStock));
            });
            return ResponseDto.success("読み込みに成功しました", UserStockListDto.set(resList));
        } catch (Exception e) {
            return ResponseDto.fail("読み込みに失敗しました");
        }
    }
}

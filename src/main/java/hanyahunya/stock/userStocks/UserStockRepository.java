package hanyahunya.stock.userStocks;

import hanyahunya.stock.userStocks.entity.UserStock;
import hanyahunya.stock.userStocks.entity.UserStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserStockRepository extends JpaRepository<UserStock, UserStockId> {
    List<UserStock> findUserStockByUser_UserId(String userUserId);

    @Query("SELECT DISTINCT u.stock.stockCode FROM UserStock u")
    List<String> findDistinctStockCodes();
}

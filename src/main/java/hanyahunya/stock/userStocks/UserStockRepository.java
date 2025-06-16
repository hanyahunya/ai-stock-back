package hanyahunya.stock.userStocks;

import hanyahunya.stock.userStocks.entity.UserStock;
import hanyahunya.stock.userStocks.entity.UserStockId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStockRepository extends JpaRepository<UserStock, UserStockId> {
}

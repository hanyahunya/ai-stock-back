package hanyahunya.stock.dailyStock;

import hanyahunya.stock.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyStockRepository extends JpaRepository<DailyStock, Integer> {
    @Query("SELECT ds FROM DailyStock ds JOIN FETCH ds.stock WHERE ds.stock.stockCode = :stockCode ORDER BY ds.date DESC")
    List<DailyStock> findByStockCodeWithJoin(@Param("stockCode") String stockCode);
    /*
    JOIN -  SELECT ds.*
            FROM daily_stock ds
            JOIN stock s ON ds.stock_code = s.stock_code
            WHERE s.stock_code = ?
    위와같이 그저 daily_stock만 가져옴 -> 후에 stock 객체를 따로 가져와야 하므로 (N+!번 실행)

    JOIN FETCH -    SELECT ds.*, s.*
                    FROM daily_stock ds
                    JOIN stock s ON ds.stock_code = s.stock_code
                    WHERE s.stock_code = ?
    가져올때 조인한 stock도 같이 select 해서 DailyStock.Stock 객체에 매핑해서 반환함 (1번 실행)
    */
}

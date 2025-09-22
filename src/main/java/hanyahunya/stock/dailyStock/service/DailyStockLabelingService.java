package hanyahunya.stock.dailyStock.service;

import hanyahunya.stock.dailyStock.DailyStock;
import hanyahunya.stock.dailyStock.DailyStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DailyStockLabelingService {
    private final DailyStockRepository dailyStockRepository;

    public void labelDailyStock(String stockCode, boolean isAllDays) {
        List<DailyStock> dbDailyStocks = dailyStockRepository.findByStock_StockCodeOrderByDateAsc(stockCode);
        int size = dbDailyStocks.size();
        if (!isAllDays) {
            dbDailyStocks = dbDailyStocks.subList(size - 8, size);
        }
        Queue<Long> idQueue = new ArrayDeque<>();
        Queue<Integer> highQueue = new ArrayDeque<>();
        Queue<Integer> lowQueue = new ArrayDeque<>();
        Queue<Integer> endQueue = new ArrayDeque<>();


        for (DailyStock dailyStock : dbDailyStocks) {
            int highPrice = dailyStock.getHighPrice();
            int lowPrice = dailyStock.getLowPrice();
            int endPrice = dailyStock.getClosePrice();
            if (highPrice == 0 || lowPrice == 0 || endPrice == 0) {
                System.out.println(dailyStock.getDate() + " " + stockCode + " H:" + highPrice + " L:" + lowPrice + " E:" + endPrice);
                continue;
            }
            idQueue.offer(dailyStock.getId());
            highQueue.offer(highPrice);
            lowQueue.offer(lowPrice);
            endQueue.offer(endPrice);
//            if (endQueue.size() != 7) { // 향후6거래일을 기준으로
            if (endQueue.size() != 4) { // 향후3거래일을 기준으로
                continue;
            }
            double highestRatio = highestRatio7days(highQueue, endQueue);
            boolean isUp3Percent = highestRatio >= 3.0;

            double lowestRatio = lowestRatio7days(lowQueue, endQueue);
            boolean isDown3Percent = lowestRatio <= -3.0;

            dailyStockRepository.updateYValues(highestRatio, lowestRatio, isUp3Percent, isDown3Percent, idQueue.peek());

            idQueue.poll();
            highQueue.poll();
            lowQueue.poll();
            endQueue.poll();
        }
    }

    private double highestRatio7days(Queue<Integer> highQueue, Queue<Integer> endQueue) {
        int basePrice = endQueue.peek();

        // highQueue를 리스트로 변환
        List<Integer> highList = new ArrayList<>(highQueue);

        highList.removeFirst();
        int maxHigh = Collections.max(highList);

        return ratio(basePrice, maxHigh);
    }

    private double lowestRatio7days(Queue<Integer> lowQueue, Queue<Integer> endQueue) {
        int basePrice = endQueue.peek();

        List<Integer> lowList = new ArrayList<>(lowQueue);
        lowList.removeFirst();
        int minLow = Collections.min(lowList);
        return ratio(basePrice, minLow);
    }

    private double ratio(int currentPrice, int targetPrice) {
        return ((targetPrice - currentPrice) / (double) currentPrice) * 100;
    }
}

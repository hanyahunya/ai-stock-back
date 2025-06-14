package hanyahunya.stock.stock;

import hanyahunya.stock.util.ResponseDto;

public interface StockService {
    /**
     *　このメソッドを使ってデータベースに該当する株の情報を入れ込む
     * @param StockCode 登録したい株のコード（韓国基準）
     * @return 成功したらResponseDto.success True
     */
    ResponseDto<Void> addStock(String StockCode);

    
}

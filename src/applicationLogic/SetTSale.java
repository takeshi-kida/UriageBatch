package applicationLogic;

import java.util.List;

import businessEntity.dto.T_SALE;
import businessLogic.InsertData;
import businessLogic.SelectData;

public class SetTSale {

	public void setTSale() throws Exception {
		SelectData selectData = new SelectData();
		InsertData insertData = new InsertData();

		// 売上トランから売上データを取得する
		List<T_SALE> tSaleDatas = selectData.getTSalesData();
		
		for (T_SALE tSaleData : tSaleDatas) {
			insertData.insertTSale(tSaleData);
		}
	}
}

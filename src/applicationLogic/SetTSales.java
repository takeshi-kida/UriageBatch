package applicationLogic;

import businesslogic.SelectData;;

public class SetTSales {

	public void setTSales() throws Exception {
		SelectData selectData = new SelectData();

		// 売上トランから売上データを取得する
		selectData.getTSalesData();
	}
}

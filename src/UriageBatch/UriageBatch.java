package UriageBatch;


import applicationLogic.CopyZaiko;
import applicationLogic.SetCost;
import applicationLogic.SetTSales;
import applicationLogic.SetUriageTran;

public class UriageBatch {

	public static void main(String[] args) {
		try {

			CopyZaiko copyZaiko = new CopyZaiko();

			copyZaiko.copyZaiko();

			SetCost setCost = new SetCost();

			setCost.setCost();

			SetTSales setTSales = new SetTSales();

			// 本日の売上をトランテーブルから取得し売上に登録
			setTSales.setTSales();

			// 本日の売上をトランテーブルから取得し売上と売上明細に登録

			// 本日の在庫を確定
			//insertData.updateZaikoFromUriage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

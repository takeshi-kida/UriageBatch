package UriageBatch;


import applicationLogic.CopyZaiko;
import applicationLogic.SetCost;
import applicationLogic.SetTSale;
import applicationLogic.SetTSaleDetail;
import applicationLogic.SetUriageTran;

public class UriageBatch {

	public static void main(String[] args) {
		try {

			// 前日の在庫を本日の在庫としてコピー
			CopyZaiko copyZaiko = new CopyZaiko();

			copyZaiko.copyZaiko();

			// 仕入CSVから仕入テーブルに登録
			SetCost setCost = new SetCost();

			setCost.setCost();

			//売上CSVから売上トランテーブルに登録
			SetUriageTran setTSaleTran = new SetUriageTran();

			setTSaleTran.setUriageTran();

			// 本日の売上をトランテーブルから取得し売上に登録
			SetTSale setTSales = new SetTSale();

			setTSales.setTSale();

			// 本日の売上をトランテーブルから取得し売上明細に登録
			SetTSaleDetail setTsaleDetail = new SetTSaleDetail();

			setTsaleDetail.setTSaleDetail();

			// 本日の在庫を確定
			//insertData.updateZaikoFromUriage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

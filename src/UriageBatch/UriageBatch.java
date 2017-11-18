package UriageBatch;


import applicationLogic.CopyZaiko;
import applicationLogic.SetCost;
import applicationLogic.SetUriageTran;
import businesslogic.SelectData;

public class UriageBatch {

	public static void main(String[] args)
	{
		try {

			CopyZaiko copyZaiko = new CopyZaiko();
			
			copyZaiko.copyZaiko();
			
			SetUriageTran setUriageTran = new SetUriageTran();
			
			setUriageTran.setUriageTran();
			
			SetCost setCost = new SetCost();
			
			setCost.setCost();

			SelectData selectData = new SelectData();

			// 本日の売上をトランテーブルから取得し売上と売上明細に登録
			selectData.selectTSaleTran();

			// 本日の在庫を確定
			//insertData.updateZaikoFromUriage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

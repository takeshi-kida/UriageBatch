package applicationLogic;

import java.sql.SQLException;

import businessLogic.InsertData;

public class CopyZaiko {

	public void copyZaiko() throws SQLException {
		InsertData insertData = new InsertData();

		// 前日の在庫をコピー
		insertData.copyTStock();
	}
}

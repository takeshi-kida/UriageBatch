package businessEntity.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DaoConnectionDriverManeger {

	Connection conn = null;
	protected static Statement stmt = null;
	PreparedStatement ps = null;

	public DaoConnectionDriverManeger()
	{
		this.getConnection();
	}

	public Connection getConnection() {
		conn = null;

		try {
			// JBBCドライバクラスのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connectionの作成
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + ConnectionStrings.SERVER_NAME.getValue() + ":1521:"
							+ ConnectionStrings.SID.getValue(),
					ConnectionStrings.USER.getValue(), ConnectionStrings.PASS.getValue());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return conn;

	}
}

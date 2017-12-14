package businesslogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import businessEntity.customDao.SelectT_SALES_TRAN;
import businessEntity.dao.DaoConnectionDriverManeger;
import businessEntity.dto.T_SALE;

public class SelectData {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rset = null;

	private DaoConnectionDriverManeger dm = new DaoConnectionDriverManeger();

	public void getTSalesData() throws Exception {

		try {

			SelectT_SALES_TRAN selectTSalesTran = new SelectT_SALES_TRAN();

			List<T_SALE> tSaleData = selectTSalesTran.selectTSalesFromTSalesTran();

			rset = stmt.executeQuery(
					"select A.VOUCHER_NO, A.PRODUCT_CD, sum(A.SALES) AS SALES from T_SALE_TRAN A WHERE A.INCLUSION_YMD = (select B.SYS_BUSINESS_DAY　from T_SYSTEM_INFO B) group by A.VOUCHER_NO, A.PRODUCT_CD order by A.VOUCHER_NO");

			String voucherNo = "";
			int i = 1;

			// 取得したデータを出力する
			while (rset.next()) {

				// 伝票番号が変更した場合、カウンターを1に戻す
				if (!voucherNo.equals(rset.getString("VOUCHER_NO"))) {
					voucherNo = rset.getString("VOUCHER_NO");
					i = 1;
				}


				// カウンターを1加算
				i = i + 1;
			}

		} catch (SQLException e) {
			throw e;
		} catch (Throwable e) {
			throw e;
		} finally {
			try {
				/* クローズ処理 */
				if (rset != null) {
					rset.close();
					rset = null;
				}

				if (stmt != null) {
					stmt.close();
					stmt = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Throwable e) {
				// nop
			}
		}
	}

	public int seqTranUri() throws Exception {

		int seqTranUri = 0;

		try {
			// Connectionの作成
			conn = dm.getConnection();

			// Statementの作成
			stmt = conn.createStatement();

			// Resultsetの作成
			rset = stmt.executeQuery("select SEQ_TRAN_URI.NEXTVAL AS SEQ_TRAN_URI FROM DUAL");

			// 取得したデータを出力する
			while (rset.next()) {
				seqTranUri = Integer.parseInt(rset.getString("SEQ_TRAN_URI"));
			}

			return seqTranUri;
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				/* クローズ処理 */
				if (rset != null) {
					rset.close();
					rset = null;
				}

				if (stmt != null) {
					stmt.close();
					stmt = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Throwable e) {
				// nop
			}
		}
	}

	public int selectCostUnitPrice(String costYMD, String productCd) throws Exception {

		int costUnitPrice = 0;

		try {
			// Connectionの作成
			conn = dm.getConnection();

			// Statementの作成
			stmt = conn.createStatement();

			// Resultsetの作成
			rset = stmt.executeQuery("select A.COST_UNIT_PRICE from M_PRODUCT A where A.PRODUCT_CD = '" + productCd
					+ "' and A.UNIT_PRICE_START_YMD = (select max(UNIT_PRICE_START_YMD) from M_PRODUCT C where C.PRODUCT_CD = '"
					+ productCd + "')");

			// 取得したデータを出力する
			while (rset.next()) {
				costUnitPrice = Integer.parseInt(rset.getString("COST_UNIT_PRICE"));
			}

			return costUnitPrice;
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				/* クローズ処理 */
				if (rset != null) {
					rset.close();
					rset = null;
				}

				if (stmt != null) {
					stmt.close();
					stmt = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Throwable e) {
				// nop
			}
		}
	}
}
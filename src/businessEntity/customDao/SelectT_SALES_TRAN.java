package businessEntity.customDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import businessEntity.dao.DaoConnectionDriverManeger;
import businessEntity.dto.T_SALE;

public class SelectT_SALES_TRAN extends DaoConnectionDriverManeger {

	private static final String selectTSalesFromTSalesTran = "select VOUCHER_NO, sum(SALE_AMOUNT) AS SALE_AMOUNT from (select A.VOUCHER_NO AS VOUCHER_NO, (A.SALES * B.SALE_UNIT_PRICE) AS SALE_AMOUNT from T_SALE_TRAN A, M_PRODUCT B WHERE A.PRODUCT_CD = B.PRODUCT_CD AND B.UNIT_PRICE_START_YMD = (select max(UNIT_PRICE_START_YMD) from M_PRODUCT C where C.PRODUCT_CD = B.PRODUCT_CD) and  A.INCLUSION_YMD = (select B.SYS_BUSINESS_DAY from T_SYSTEM_INFO B))group by VOUCHER_NO";

	public List<T_SALE> selectTSalesFromTSalesTran() {
		List<T_SALE> getResult = new ArrayList<T_SALE>();

		try {
			// Resultsetの作成
			ResultSet rset = stmt.executeQuery(selectTSalesFromTSalesTran);

			// 取得したデータを出力する
			while (rset.next()) {
				T_SALE	tSale = new T_SALE();

				tSale.VOUCHER_NO = rset.getString(1);
				tSale.SHOP_CD = tSale.VOUCHER_NO.substring(0, 5);
				tSale.SALE_YMD = tSale.VOUCHER_NO.substring(6,13);
				tSale.SALE_AMOUNT = rset.getInt(2);

				getResult.add(tSale);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getResult;
	}
}

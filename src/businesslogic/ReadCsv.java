package businesslogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadCsv implements IReadCsv {
	
	ArrayList<ArrayList<String>> raw;
	
	public ReadCsv() {
		raw = new ArrayList<ArrayList<String>>();
	}

	public ArrayList<ArrayList<String>> readCsv(String fileName) {
		try {
			File csv = new File(fileName);

			BufferedReader br = new BufferedReader(new FileReader(csv));

			// 最終行まで読み込む
			String line = "";
			while ((line = br.readLine()) != null) {

				// 1行をデータの要素に分割
				StringTokenizer st = new StringTokenizer(line, ",");

				ArrayList<String> col = new ArrayList<>();
				while (st.hasMoreTokens()) {
					// 1行の各要素をカンマ区切りで表示
					col.add(st.nextToken());

				}
				raw.add(col);
			}

			br.close();
		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}

		return raw;
	}
	
	public void validation(ArrayList<ArrayList<String>> raw)
	{}

	public void readShiireCsv() throws Exception {
		try {
			File csv = new File("C:\\TEMP\\SHIIRE.csv"); // CSVデータファイル

			BufferedReader br = new BufferedReader(new FileReader(csv));

			InsertData insertOracle = new InsertData();
			SelectData selectOracle = new SelectData();

			// 最終行まで読み込む
			String line = "";
			while ((line = br.readLine()) != null) {

				// 1行をデータの要素に分割
				StringTokenizer st = new StringTokenizer(line, ",");

				String costYMD = "";
				String productCd = "";
				int costs = 0;
				int costAmount = 0;

				while (st.hasMoreTokens()) {
					// 1行の各要素をタブ区切りで表示

					costYMD = st.nextToken();
					productCd = st.nextToken();
					costs = Integer.parseInt(st.nextToken());
					costAmount = costs * selectOracle.selectCostUnitPrice(costYMD, productCd);
				}

				insertOracle.insertShiire("INSERT INTO T_COST values(?, ?, ?, ?)", costYMD, productCd, costs,
						costAmount);

				// 在庫に商品がない場合、在庫に登録する。
				if (selectOracle.selectZaikoRecord(productCd) == 0) {
					insertOracle.insertZaiko("INSERT INTO T_STOCK values(?, ?, ?)", costYMD, productCd, costs);
				} else {
					insertOracle.updateZaikoFromCost("UPDATE T_STOCK SET STOCKS = STOCKS + " + costs
							+ " WHERE STOCK_YMD = '" + costYMD + "' AND PRODUCT_CD = '" + productCd + "'");
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}
	}
}

package businessLogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return raw;
	}

	public void validation(ArrayList<ArrayList<String>> raw) {
	}
}

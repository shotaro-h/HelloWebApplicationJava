package dlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dto.BaseDTO;

public abstract class DataBaseOperator<T extends BaseDTO> {
	protected File tableFile;	//テーブルファイル

	/***
	 * コンストラクタ<br>
	 * テーブルファイルパスの定義<br>
	 * tableName： 参照テーブル名（物理名）
	 * @param tableName
	 */
	public DataBaseOperator(String tablePath) {
//		tableFile = new File("/resource/" + tableName + ".bin");
		tableFile = new File(tablePath);
	}


	/***
	 * テーブルファイルを参照し、テーブルリストを作成する。
	 *
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public abstract ArrayList<T> select() throws FileNotFoundException, IOException, ClassNotFoundException;

	/***
	 * テーブルリスト内のデータを1件更新。<br>
	 * テーブルファイルへの書き込みを行う。
	 * @param data
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public abstract int update(T data) throws FileNotFoundException, IOException;

	/***
	 * tableListへの新規追加<br>
	 * テーブルファイルへの書き込みを行う。
	 * @param data
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public abstract int insert(T data) throws FileNotFoundException, IOException, Exception;

	/***
	 * tableList内のデータを1件削除。<br>
	 * テーブルファイルへの書き込みを行う。
	 * @param data
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public abstract int delete(T data) throws FileNotFoundException, IOException;

	/***
	 * テーブルリストのシリアライズ（ファイル書出）を行う。
	 * @param tableList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeTableFile(ArrayList<T> tableList) throws FileNotFoundException, IOException {
		try (ObjectOutputStream objOutStream = new ObjectOutputStream(
				new FileOutputStream(tableFile));) {
			objOutStream.writeObject(tableList);
			objOutStream.flush();
		}
	}

	/***
	 * テーブルリストのデシリアライズ（ファイル読込）を行う。
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<T> readTableFile() throws FileNotFoundException, IOException, ClassNotFoundException{
		try (ObjectInputStream objInStream = new ObjectInputStream(
				new FileInputStream(tableFile));) {
			return (ArrayList<T>) objInStream.readObject();
		}
	}

}

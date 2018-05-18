package main3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ConnectDB {

	private String HOST;
	private String DB;
	private final String URL = "jdbc:mysql://" + HOST + "/" + DB;
	private final String USERNAME;
	private final String PASSWORD;
	
	//コンストラクタ
	ConnectDB(String HOST,String DB,String USERNAME,String PASSWORD){
		this.HOST = HOST;
		this.DB = DB;
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
	}

	// SQL文でデーベースから呼び出した行列の1列目をListにして返すメソッド
	public List<Object> selectMulti(String sql) {

		List<Object> rowElements = new ArrayList<>();

		try (	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				) {

			while (result.next()) {
				rowElements.add(result.getObject(1));
			}
			return rowElements;

		} catch (SQLException e) {
			rowElements = null;
			return rowElements;
		}

	}
	
	//SQL文でデータベースから呼び出した行列の1行目の1列目を返すメソッド
	public Object selectSingle(String sql) {
		
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				){
			
			result.absolute(1);
			return result.getObject(1);
			
		}catch(SQLException e) {
			return null;
		}
		
	}

	// 更新系のDML文およびDDL文を渡すと実行してくれるメソッド
	public void update(String sql) {

		try (	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
				) {

			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

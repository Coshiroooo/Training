package main3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DBConnecter {

	private String HOST;
	private String DB;
	private final String URL = "jdbc:mysql://" + HOST + "/" + DB;
	private final String USERNAME;
	private final String PASSWORD;
	
	//コンストラクタ
	DBConnecter(String HOST,String DB,String USERNAME,String PASSWORD){
		this.HOST = HOST;
		this.DB = DB;
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
	}
	
	//SQL文でデータベースから呼び出した行列の1行目の1列目を返すメソッド
	public Object select(String sql) {
		
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
	
	//取得した行列の1行目の各列をMapに入れて返すメソッド
	public Map<String,Object> selectMulti(String sql){
		
		Map<String,Object> elements = new HashMap<>();
		
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				){
			
			ResultSetMetaData resultMD = result.getMetaData();
			result.absolute(1);
			
			for(int i = 1; i <= resultMD.getColumnCount(); i++) {
				elements.put(resultMD.getColumnName(i), result.getObject(i));
			}
			
			return elements;
			
		}catch(SQLException e) {
			return null;
		}
		
	}

	// SQL文でデーベースから呼び出した行列の1列目をListにして返すメソッド
	public List<Object> selectRow(String sql) {

		List<Object> rowElements = new ArrayList<>();

		try (	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				) {

			while (result.next()) rowElements.add(result.getObject(1));
			return rowElements;

		} catch (SQLException e) {
			rowElements = null;
			return rowElements;
		}

	}
	
	//取得した複数列をMapに入れて返すメソッド
	public Map<String,List<Object>> selectMultiRow(String sql){
		
		Map<String,List<Object>> rowElementsMap = new HashMap<>();

		try (	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				) {
			
			ResultSetMetaData resultMD = result.getMetaData();
			
			for(int i = 1; i <= resultMD.getColumnCount(); i++) {
				List<Object> rowElements = new ArrayList<>();
				while(result.next()) rowElements.add(result.getObject(i));
				rowElementsMap.put(resultMD.getColumnName(i), rowElements);
			}
			
			return rowElementsMap;

		} catch (SQLException e) {
			rowElementsMap = null;
			return rowElementsMap;
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

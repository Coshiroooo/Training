package main;

import java.sql.*;
import java.util.*;

public class DBConnecter {

	private final String URL;
	private final String USERNAME;
	private final String PASSWORD;
	
	//コンストラクタ
	DBConnecter(String HOST,String DB,String USERNAME,String PASSWORD){
		this.URL = "jdbc:mysql://" + HOST + "/" + DB + "?useSSL=false&requireSSL=false";
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
	}
	
	//取得した行列の1行目の1列目をStringで返すメソッド
	public String selectStr(String sql){
					
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				){
						
			result.next();
			return result.getString(1);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
					
	}
	
	//取得した行列の行と列で指定された要素をStringで返すメソッド
	public String selectStr(String sql,int record,int row){
						
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				){
							
			result.absolute(record);
			return result.getString(row);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
						
 	}
	
	//取得した行列の1行目の1列目をintで返すメソッド
	public int selectInt(String sql){
				
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				){
					
			result.next();
			return (int)Math.round(result.getDouble(1));
					
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
				
	}
	
	//取得した行列の行と列で指定された要素をintで返すメソッド
	public int selectInt(String sql,int record,int row){
					
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				){
						
			result.absolute(record);
			return (int)Math.round(result.getDouble(row));
						
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
					
	}
	
	//取得した行列の指定した列を全てList<Integer>にして取得するメソッド
	public List<Integer> selectColInt(String sql,int colNumber){
						
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				){
							
			List<Integer> col = new ArrayList<>();
				
			while(result.next()) {
				col.add(result.getInt(colNumber));
			}
				
			return col;
							
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
						
	}
	
	//取得した行列の1行目の1列目をObjectで返すメソッド
	public Object selectObj(String sql){
			
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				){
				
			result.next();
			return result.getObject(1);
				
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
			
	}
	
	//取得した行列の行と列を指定した要素をObjectで返すメソッド
	public Object selectObj(String sql,int record,int row){
				
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				){
					
			result.absolute(record);
			return result.getObject(row);
					
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
				
 	}
	
	//取得した行列の1行目の各列をMapに入れて返すメソッド
	public Map<String,Object> selectRecord(String sql){
		
		Map<String,Object> elements = new HashMap<>();
		
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				){
			ResultSetMetaData resultMD = result.getMetaData();
			result.next();
			
			for(int i = 1; i <= resultMD.getColumnCount(); i++) {
				elements.put(resultMD.getColumnName(i), result.getObject(i));
			}
			
			return elements;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//取得した行列の指定した行の各列をMapに入れて返すメソッド
	public Map<String,Object> selectRecord(String sql,int record){
			
		Map<String,Object> elements = new HashMap<>();
			
		try(	Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				){
			ResultSetMetaData resultMD = result.getMetaData();
			result.absolute(record);
				
			for(int i = 1; i <= resultMD.getColumnCount(); i++) {
				elements.put(resultMD.getColumnName(i), result.getObject(i));
			}
				
			return elements;
				
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
			
	}
	
	//取得した複数行の各列をMapに入れて返すメソッド
	public Map<String,List<Object>> selectRecords(String sql){
		
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

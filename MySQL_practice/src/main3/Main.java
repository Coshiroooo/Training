package main3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class Main {
	
	private static final String DB = "mydb";
	private static final String URL = "jdbc:mysql://localhost/" + DB;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	//SQL文でデーベースから呼び出した行列の1行目をListにして返すメソッド
	public List<Object> select(String sql) {
		
		List<Object> rowElements = new ArrayList<>();
		
		try(
				Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql);
				){
			
			while(result.next()) {
				rowElements.add(result.getObject(1));
			}
			return rowElements;
			
		}catch(SQLException e) {
			rowElements = null;
			return rowElements;
		}
	}

}

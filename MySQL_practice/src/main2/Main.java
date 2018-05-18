package main2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Main {
	public static void main(String[] args) {
		
		String URL = "jdbc:mysql://localhost/mydb"; //データベースURL -> jdbc:mysql//ホスト名/データベース名
		String USERNAME = "root";
		String PASSWORD = "";
		
		String sql2 = "SELECT * FROM employer WHERE id = 1;";
		
		try(
				Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet result2 = statement.executeQuery(sql2);
				){
			
			ResultSetMetaData result2column = result2.getMetaData();
			int columnNumber = result2column.getColumnCount();
			System.out.println("カラムの数は" + columnNumber);
		
			result2.absolute(1);
				
			result2.updateString(6, "n");
			result2.updateRow();
			System.out.println(result2.getString(2) + ":" + result2.getString(6));
			
			result2.updateString(6, "y");
			result2.updateRow();
			System.out.println(result2.getString(2) + ":" + result2.getString(6));
			
		}catch(SQLException e){
			e.printStackTrace();
		
		}
	}
}
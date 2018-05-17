package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
	
	static final String URL = "jdbc:mysql://localhost/mydb"; //データベースURL -> jdbc:mysql//ホスト名/データベース名
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	public static void main(String[] args) {
		
		String sql1 = "SELECT * FROM employer;";
		
		try(	
				Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(sql1);
				){
			while(result.next()) {
				System.out.println(result.getString(3) + " " + result.getString(2));
			}
			
			for(int i = 1; i <= 3; i++) {
				result.absolute(i);
				System.out.println(result.getString(3) + " " + result.getString(2));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

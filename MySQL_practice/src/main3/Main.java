package main3;

import java.util.List;
import java.util.Map;

public class Main {

	static DBConnecter DB = new DBConnecter("localhost", "mydb", "root", "");

	public static void main(String[] args) {

		Map<String, Object> infoKoshiro = DB.selectMap("SELECT * FROM employer " + "WHERE id = 1;");
		System.out.println("私の名前は" + infoKoshiro.get("first_name") + " " + infoKoshiro.get("last_name") + "です");
		System.out.println(infoKoshiro.get("joined_year") + "年に入社しました");

		int ageAverage = DB.selectInt("SELECT AVG(age) FROM employer;");
		System.out.println("のらねこワークスの平均年齢は" + ageAverage + "才です");

		DB.update("INSERT INTO employer VALUES (4,'Nekotaro','Nora',30,2016,'n',2);");

		Map<String, List<Object>> employerNames = DB.selectObjMulti("SELECT first_name FROM employer;");
		System.out.println("社員一覧");
		employerNames.forEach((column, value) -> System.out.print(value + " "));

		DB.update("DELETE FROM employer WHERE id = 4");
		
		DB.update("UPDATE employer SET age = 24 WHERE id = 1;");
	
		int ageKoshiro1 = DB.selectInt("SELECT age FROM employer WHERE id = 1;");
		System.out.println();
		System.out.println(infoKoshiro.get("first_name") + "は" + ageKoshiro1 + "才です");
		
		DB.update("UPDATE employer SET age = 23 WHERE id = 1;");

		int ageKoshiro2 = DB.selectInt("SELECT age FROM employer WHERE id = 1;");
		System.out.println(infoKoshiro.get("first_name") + "は" + ageKoshiro2 + "才です");
	}

}

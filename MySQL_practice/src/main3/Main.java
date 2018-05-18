package main3;

import java.util.List;
import java.util.Map;

public class Main {

	static DBConnecter DB = new DBConnecter("localhost", "mydb", "root", "");

	public static void main(String[] args) {

		//自己紹介
		Map<String, Object> infoKoshiro = DB.selectRecord("SELECT * FROM employer " + "WHERE id = 1;");
		System.out.println("私の名前は" + infoKoshiro.get("first_name") + " " + infoKoshiro.get("last_name") + "です");
		System.out.println(infoKoshiro.get("joined_year") + "年に入社しました");

		//社内平均年齢
		int ageAverage = DB.selectInt("SELECT AVG(age) FROM employer;");
		System.out.println("のらねこワークスの平均年齢は" + ageAverage + "才です");

		//レコードを増やす
		DB.update("INSERT INTO employer VALUES (4,'Nekotaro','Nora',30,2016,'n',2);");

		//社員一覧
		Map<String, List<Object>> employerNames = DB.selectRecords("SELECT first_name FROM employer;");
		System.out.println("社員一覧");
		employerNames.forEach((column, value) -> System.out.print(value + " "));
		
		//レコードを消す
		DB.update("DELETE FROM employer WHERE id = 4");
		
		//年齢更新
		DB.update("UPDATE employer SET age = 24 WHERE id = 1;");
	
		//年齢表示
		int ageKoshiro1 = DB.selectInt("SELECT age FROM employer WHERE id = 1;");
		System.out.println();
		System.out.println(infoKoshiro.get("first_name") + "は" + ageKoshiro1 + "才です");
		
		//年齢更新
		DB.update("UPDATE employer SET age = 23 WHERE id = 1;");

		//年齢表示
		int ageKoshiro2 = DB.selectInt("SELECT age FROM employer WHERE id = 1;");
		System.out.println(infoKoshiro.get("first_name") + "は" + ageKoshiro2 + "才です");
		
		Map<String,Object> row1 = DB.selectRecord("SELECT first_name,favorit_language.NAME AS languageName FROM employer JOIN favorit_language ON employer.favorit_languageID = favorit_language.ID;");
		System.out.println(row1.get("first_name") + "の得意言語は" + row1.get("NAME") + "です");
	}

}

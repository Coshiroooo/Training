package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main2 {
	
	private static DBConnecter quizDB = new DBConnecter("localhost","quiz_db","root","");

	public static void main(String[] args) {
		
		printResults();
		
	}
	
	//結果を表示するメソッド
	public static void printResults() {
		
		String sqlResults = "SELECT * FROM results;";
		List<Integer> resultIds = quizDB.selectColInt(sqlResults, 1);
		List<LocalDateTime> resultDateTimes = quizDB.selectColDateTime(sqlResults, 2);
		List<Integer> resultPoints = quizDB.selectColInt(sqlResults, 3);
		
		System.out.println("【これまでの結果】");
		System.out.println();
		
		int count = 0;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		
		while(count < resultIds.size()) {
			System.out.print("第" +String.format("%02d", resultIds.get(count)) + "回");
			System.out.print("\t" + String.format("%4s",resultPoints.get(count)) + "点");
			System.out.println("\t[" + resultDateTimes.get(count).format(format) + "]　");
			count++;
		}
		
		System.out.println("\n--------------------\n");
		
		System.out.println("挑戦数：\t" + intToStr("%4s", "SELECT MAX(id) FROM results;") + "回");
		System.out.println("最高点：\t" + intToStr("%4s", "SELECT MAX(point) FROM results;") + "点");
		System.out.println("最低点：\t" + intToStr("%4s", "SELECT MIN(point) FROM results;") + "点");
		System.out.println("平均点：\t" + intToStr("%4s", "SELECT AVG(point) FROM results;") + "点\n");
		
		System.out.println("【あなたが間違えやすい問題】\n");
		List<Integer> quesWorstRankIds = quizDB.selectColInt("SELECT id FROM questions ORDER BY correct_rate ASC LIMIT 3;",1);
		
		int i = 3;
		Collections.reverse(quesWorstRankIds);
		for(int id : quesWorstRankIds) {
			String quesWHERE = "WHERE id = " + id;
			List<Integer> choiceIds = quizDB.selectColInt("SELECT * FROM choices WHERE question_id = " + id + ";",2);
			
			System.out.print("【" + i + "位】\t");
			System.out.println("正答率：" + intToStr("%3s","SELECT correct_rate FROM questions " + quesWHERE + ";") + "%");
			System.out.println("Q. " + quizDB.selectStr("SELECT question FROM questions " + quesWHERE + ";"));
			choiceIds.forEach(choiceId -> {
				System.out.print(choiceId + ". ");
				System.out.println(quizDB.selectStr("SELECT choice FROM choices WHERE choice_id = " + choiceId + " AND question_id = " + id + ";"));
			});
			System.out.println();
			i--;
		}
		
	}
	
	//int型をformatを揃えたStringで返してくれるメソッド
	public static String intToStr(String format,String sql) {
		return String.format(format, String.valueOf(quizDB.selectInt(sql)));
	}
	
}
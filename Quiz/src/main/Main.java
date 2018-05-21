package main;

import java.util.*;
import java.util.stream.*;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static DBConnecter quizDB = new DBConnecter("localhost", "quiz_db", "root", "");
	static int quesNumber = 5;

	public static void main(String[] args) {

		System.out.println("いきなりですが、クイズを始めます！！！");
		System.out.println();

		quizDB.update("INSERT INTO results DEFAULT VALUES;");
		int resultId = quizDB.selectInt("SELECT MAX(id) FROM results");

		List<Integer> quesIdList = quizDB.selectColInt("SELECT id FROM questions;", 1);
		Collections.shuffle(quesIdList);

		IntStream.rangeClosed(1, quesNumber).forEach(n -> {

			quizDB.update("INSERT INTO logs results_id VALUES (" + resultId + ");");
			int logId = quizDB.selectInt("SELECT MAX(id) FROM logs");
			int quesId = quesIdList.get(n);
			
			printQuestion(n,logId,quesId);
			
			int choiceId = choiceInput(logId);

			printJudge(logId,quesId,choiceId);

		});

		int perfectScore = quesNumber * 20;
		int sumPoint = quizDB.selectInt("SELECT point FROM results WHERE id = " + resultId);	
		int per = (int)(sumPoint / perfectScore * 100);
		quizDB.update("UPDATE results (point,per) VALUES (" + sumPoint + "," + per + ");");
		
		System.out.print("【合計得点】　　");
		System.out.println( sumPoint + " / " + perfectScore);

	}
	
	static void printQuestion(int n,int logId,int quesId) {
		System.out.print("【第" + n + "問】　");
		quizDB.update("UPDATE logs SET question_id = " + quesId + " WHERE id = " + logId + ";");
		System.out.println(quizDB.selectStr("SELECT * FROM questions;", quesId, 1));

		List<Integer> choiceIdList = quizDB.selectColInt("SELECT choice_id FROM choices;", 1);
		choiceIdList.stream()
				.map(c -> c + ". " + quizDB.selectStr("SELECT choice FROM choices WHERE choice_id = " + c + " AND question_id = " + quesId +";"))
				.forEach(System.out::println);
		System.out.println();
	}
	
	static int choiceInput(int logId) {
		System.out.print("正解を数字でお答えください：");
		int choiceId = scanner.nextInt();
		quizDB.update("UPDATE logs SET choice_id = " + choiceId + " WHERE id = " + logId + ";");
		return choiceId;
	}
	
	static void printJudge(int logId,int quesId,int choiceId) {
		if (choiceId == quizDB.selectInt("SELECT correct_choice_id FROM questions", quesId, 1)) {
			System.out.println("正解！！！！！");
			quizDB.update("UPDATE logs SET point = " + 20 + " WHERE id = " + logId + ";");
		} else {
			System.out.println("残念！！！はずれ！！！！！");
		}
		System.out.println();
	}

}

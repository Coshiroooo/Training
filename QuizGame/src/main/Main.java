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

		quizDB.update("INSERT INTO results VALUES();");
		int resultId = quizDB.selectInt("SELECT MAX(id) FROM results");

		List<Integer> quesIdList = quizDB.selectColInt("SELECT id FROM questions;", 1);
		Collections.shuffle(quesIdList);

		IntStream.rangeClosed(1, quesNumber).boxed().forEach(n -> {

			quizDB.update("INSERT INTO logs(results_id) VALUES (" + resultId + ");");
			int logId = quizDB.selectInt("SELECT MAX(id) FROM logs");
			int quesId = quesIdList.get(n);
			quizDB.update("UPDATE questions SET ques_numbers = ques_number + 1 WHERE id = " + quesId + ";");
			
			printQuestion(n,logId,quesId);
			int choiceId = choiceInput(logId);
			printJudge(logId,quesId,choiceId);
			updateCorrectRate(quesId);

		});

		updateResults(resultId);

	}
	
	//n問目の問題を出題するメソッド
	static void printQuestion(int n,int logId,int quesId) {
		System.out.print("【第" + n + "問】　");
		quizDB.update("UPDATE logs SET questions_id = " + quesId + " WHERE id = " + logId + ";");
		System.out.println(quizDB.selectStr("SELECT * FROM questions;", quesId, 2));

		List<Integer> choiceIdList = quizDB.selectColInt("SELECT choice_id FROM choices WHERE question_id = " + quesId + ";", 1);
		choiceIdList.stream()
				.map(c -> c + ". " + quizDB.selectStr("SELECT choice FROM choices WHERE choice_id = " + c + " AND question_id = " + quesId +";"))
				.forEach(System.out::println);
		System.out.println();
		System.out.println("-------------------");
	}
	
	//回答を入力するメソッド
	static int choiceInput(int logId) {
		System.out.print("正解を数字でお答えください：");
		int choiceId = scanner.nextInt();
		quizDB.update("UPDATE logs SET choice_id = " + choiceId + " WHERE id = " + logId + ";");
		return choiceId;
	}
	
	//問題の成否を判定し、点数計算するメソッド
	static void printJudge(int logId,int quesId,int choiceId) {
		System.out.println();
		if (choiceId == quizDB.selectInt("SELECT correct_choice_id FROM questions", quesId, 1)) {
			System.out.println("正解！！！！！");
			quizDB.update("UPDATE questions SET correct_numbers = correct_numbers + 1 WHERE id = " + quesId + ";");
			quizDB.update("UPDATE logs SET point = " + 20 + " WHERE id = " + logId + ";");
		} else {
			System.out.println("残念！！！はずれ！！！！！");
		}
	}
	
	//正答率を更新するメソッド
	static void updateCorrectRate(int quesId) {
		int quesNumbers = quizDB.selectInt("SELECT * FROM questions",quesId,4);
		int correctNumbers = quizDB.selectInt("SELECT * FROM questions",quesId,5);
		int correctRate = Math.round(correctNumbers / quesNumbers * 100);
		quizDB.update("UPDATE questions SET correct_rate = " + correctRate + " WHERE id = " + quesId + ";");
		System.out.println();
	}
	
	//ResultsTableを更新するメソッド
	static void updateResults(int resultId) {
		int perfectScore = quesNumber * 20;
		int sumPoint = quizDB.selectInt("SELECT SUM(point) FROM logs WHERE results_id = " + resultId);	
		int per = (int)(sumPoint * 100 / perfectScore);
		quizDB.update("UPDATE results SET point = " + sumPoint + " , per = " + per + " WHERE id = " + resultId + ";");
		
		System.out.println("【合計得点】\t" + sumPoint + " / " + perfectScore);
	}

}

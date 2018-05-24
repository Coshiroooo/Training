package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static DBConnecter quizDB = new DBConnecter("localhost", "quiz_db", "root", "");
	static Map<String,List<Object>> questions = quizDB.selectRecords("SELECT * FROM questions");
	static Map<String,List<Object>> choices = quizDB.selectRecords("SELECT * FROM choices");
	static int questionNumber = 5;

	public static void main(String[] args) {

		System.out.println("いきなりですが、クイズを始めます！！！\n");

		quizDB.update("INSERT INTO results VALUES()");
		int resultId = quizDB.selectInt("SELECT MAX(id) FROM results");

		List<Integer> questionIdList = quizDB.selectColInt("SELECT id FROM questions", 1);
		Collections.shuffle(questionIdList);

		IntStream.rangeClosed(1, questionNumber).boxed().forEach(n -> {

			quizDB.update("INSERT INTO logs(results_id) VALUES (" + resultId + ")");

			int logId = quizDB.selectInt("SELECT MAX(id) FROM logs");
			int questionId = questionIdList.get(n);

			quizDB.update("UPDATE questions SET ques_numbers = ques_numbers + 1 WHERE id = " + questionId);

			printQuestion(n, logId, questionId);
			int choiceId = choiceInput(logId);
			printJudgement(logId, questionId, choiceId);
			updateCorrectRate(questionId);

		});

		updateResults(resultId);
		printResults();

	}

	// n問目の問題を出題するメソッド
	static void printQuestion(int n, int logId, int questionId) {
		System.out.print("【第" + n + "問】　");
		quizDB.update("UPDATE logs SET questions_id = " + questionId + " WHERE id = " + logId);
		System.out.println(quizDB.selectStr("SELECT * FROM questions;", questionId, 2));

		List<String> choiceList = quizDB.selectColStr("SELECT choice FROM choices WHERE question_id = " + questionId,1);
		choiceList.stream().map(c -> choiceList.indexOf(c) + ". " + c).forEach(System.out::println);
		
		System.out.println("\n-------------------");
	}

	// 回答を入力するメソッド
	static int choiceInput(int logId) {
		System.out.print("正解を数字でお答えください：");
		int choiceId = scanner.nextInt();
		quizDB.update("UPDATE logs SET choice_id = " + choiceId + " WHERE id = " + logId);
		return choiceId;
	}

	// 問題の成否を判定し、点数計算するメソッド
	static void printJudgement(int logId, int questionId, int choiceId) {
		System.out.println();
		int correctChoiceId = quizDB.selectInt("SELECT correct_choice_id FROM questions", questionId, 1);
		if (choiceId == correctChoiceId) {
			System.out.println("正解！！！！！\n");
			quizDB.update("UPDATE questions SET correct_numbers = correct_numbers + 1 WHERE id = " + questionId);
			quizDB.update("UPDATE logs SET point = " + 20 + " WHERE id = " + logId);
		} else {
			System.out.println("残念！！！はずれ！！！！！");
			System.out.println("正解は " + correctChoiceId + "." + quizDB.selectStr(
					"SELECT choice FROM choices WHERE choice_id = " + correctChoiceId + " AND question_id = " + questionId)
					+ "\n");
		}
	}

	// 正答率を更新するメソッド
	static void updateCorrectRate(int questionId) {
		double quesNumber = quizDB.selectInt("SELECT * FROM questions", questionId, 4);
		double correctNumber = quizDB.selectInt("SELECT * FROM questions", questionId, 5);
		int correctRate = (int) (correctNumber / quesNumber * 100);
		quizDB.update("UPDATE questions SET correct_rate = " + correctRate + " WHERE id = " + questionId + ";");
	}

	// ResultsTableを更新するメソッド
	static void updateResults(int resultId) {
		int perfectScore = questionNumber * 20;
		int sumPoint = quizDB.selectInt("SELECT SUM(point) FROM logs WHERE results_id = " + resultId);
		int per = (int) (sumPoint * 100 / perfectScore);
		quizDB.update("UPDATE results SET point = " + sumPoint + " , per = " + per + " WHERE id = " + resultId + "\n");

		System.out.println("【合計得点】\t" + sumPoint + " / " + perfectScore);
	}

	// 結果を表示するメソッド
	public static void printResults() {

		String sqlResults = "SELECT * FROM results;";
		List<Integer> resultIds = quizDB.selectColInt(sqlResults, 1);
		List<LocalDateTime> resultDateTimes = quizDB.selectColDateTime(sqlResults, 2);
		List<Integer> resultPoints = quizDB.selectColInt(sqlResults, 3);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

		System.out.println("\n--------------------");
		System.out.println("【これまでの結果】\n");

		int count = 0;

		while (count < resultIds.size()) {
			System.out.print("第" + String.format("%02d", resultIds.get(count)) + "回");
			System.out.print("\t" + String.format("%4s", resultPoints.get(count)) + "点");
			System.out.println("\t[" + resultDateTimes.get(count).format(format) + "]　");
			count++;
		}

		System.out.println("\n--------------------\n");

		System.out.println("挑戦数：\t" + intToStr("%4s", "SELECT MAX(id) FROM results;") + "回");
		System.out.println("最高点：\t" + intToStr("%4s", "SELECT MAX(point) FROM results;") + "点");
		System.out.println("最低点：\t" + intToStr("%4s", "SELECT MIN(point) FROM results;") + "点");
		System.out.println("平均点：\t" + intToStr("%4s", "SELECT AVG(point) FROM results;") + "点\n");

		System.out.println("【あなたが間違えやすい問題】\n");
		Map<String,List<Object>> questionWorstRank = quizDB.selectRecords("SELECT * FROM questions WHERE ques_number > 0 ORDER BY correct_rate ASC LIMIT 3");

		
		questionWorstRank.get("id").stream().map(id -> (int)id).forEach(id -> {
			
			System.out.print("【" + questionWorstRank.get("id").indexOf(id) + "位】\t");
			System.out.println("正答率" + intToStr("%3s",questionWorstRank.get("correct_rate").get(id).toString()) + "%");
			System.out.println("Q. " + questionWorstRank.get("question").get(id).toString());
			
			Map<String,List<Object>> choices = quizDB.selectRecords("SELECT * FROM choices");
			choices.get("id").stream().map(choiceId -> (int)choiceId)
									  .map(choiceId -> choiceId + ". " + choices.get("choice").get(choiceId).toString())
									  .forEach(System.out::println);
			System.out.println();
			
		});
		

	}

	// int型をformatを揃えたStringで返してくれるメソッド
	public static String intToStr(String format, String sql) {
		return String.format(format, String.valueOf(quizDB.selectInt(sql)));
	}

}

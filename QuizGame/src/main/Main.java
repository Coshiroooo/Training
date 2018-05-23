package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static DBConnecter quizDB = new DBConnecter("localhost", "quiz_db", "root", "");
	static int quesNumber = 5;

	public static void main(String[] args) {

		System.out.println("いきなりですが、クイズを始めます！！！\n");

		quizDB.update("INSERT INTO results VALUES()");
		int resultId = quizDB.selectInt("SELECT MAX(id) FROM results");

		List<Integer> quesIdList = quizDB.selectColInt("SELECT id FROM questions", 1);
		Collections.shuffle(quesIdList);

		IntStream.rangeClosed(1, quesNumber).boxed().forEach(n -> {

			quizDB.update("INSERT INTO logs(results_id) VALUES (" + resultId + ")");

			int logId = quizDB.selectInt("SELECT MAX(id) FROM logs");
			int quesId = quesIdList.get(n);

			quizDB.update("UPDATE questions SET ques_numbers = ques_numbers + 1 WHERE id = " + quesId);

			printQuestion(n, logId, quesId);
			int choiceId = choiceInput(logId);
			printJudgement(logId, quesId, choiceId);
			updateCorrectRate(quesId);

		});

		updateResults(resultId);
		printResults();

	}

	// n問目の問題を出題するメソッド
	static void printQuestion(int n, int logId, int quesId) {
		System.out.print("【第" + n + "問】　");
		quizDB.update("UPDATE logs SET questions_id = " + quesId + " WHERE id = " + logId);
		System.out.println(quizDB.selectStr("SELECT * FROM questions;", quesId, 2));

		List<Integer> choiceIdList = quizDB.selectColInt("SELECT choice_id FROM choices WHERE question_id = " + quesId,
				1);
		choiceIdList.stream()
				.map(c -> c + ". "
						+ quizDB.selectStr(
								"SELECT choice FROM choices WHERE choice_id = " + c + " AND question_id = " + quesId))
				.forEach(System.out::println);
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
	static void printJudgement(int logId, int quesId, int choiceId) {
		System.out.println();
		int correctChoiceId = quizDB.selectInt("SELECT correct_choice_id FROM questions", quesId, 1);
		if (choiceId == correctChoiceId) {
			System.out.println("正解！！！！！\n");
			quizDB.update("UPDATE questions SET correct_numbers = correct_numbers + 1 WHERE id = " + quesId);
			quizDB.update("UPDATE logs SET point = " + 20 + " WHERE id = " + logId);
		} else {
			System.out.println("残念！！！はずれ！！！！！");
			System.out.println("正解は " + correctChoiceId + "." + quizDB.selectStr(
					"SELECT choice FROM choices WHERE choice_id = " + correctChoiceId + " AND question_id = " + quesId)
					+ "\n");
		}
	}

	// 正答率を更新するメソッド
	static void updateCorrectRate(int quesId) {
		double quesNumbers = quizDB.selectInt("SELECT * FROM questions", quesId, 4);
		double correctNumbers = quizDB.selectInt("SELECT * FROM questions", quesId, 5);
		int correctRate = (int) (correctNumbers / quesNumbers * 100);
		quizDB.update("UPDATE questions SET correct_rate = " + correctRate + " WHERE id = " + quesId + ";");
	}

	// ResultsTableを更新するメソッド
	static void updateResults(int resultId) {
		int perfectScore = quesNumber * 20;
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
		List<Integer> quesWorstRankIds = quizDB.selectColInt("SELECT id FROM questions "
														   + "WHERE ques_numbers > 0 "
														   + "ORDER BY correct_rate ASC LIMIT 3;", 1);

		int i = 3;
		Collections.reverse(quesWorstRankIds);

		for (int id : quesWorstRankIds) {
			String quesWHERE = "WHERE id = " + id;
			List<Integer> choiceIds = quizDB.selectColInt("SELECT * FROM choices WHERE question_id = " + id, 2);

			System.out.print("【" + i + "位】\t");
			System.out.println("正答率：" + intToStr("%3s", "SELECT correct_rate FROM questions " + quesWHERE) + "%");
			System.out.println("Q. " + quizDB.selectStr("SELECT question FROM questions " + quesWHERE));
			choiceIds.stream()
					.map(choiceId -> choiceId + ". " + quizDB.selectStr(
							"SELECT choice FROM choices WHERE choice_id = " + choiceId + " AND question_id = " + id))
					.forEach(System.out::println);
			System.out.println();
			i--;
		}

	}

	// int型をformatを揃えたStringで返してくれるメソッド
	public static String intToStr(String format, String sql) {
		return String.format(format, String.valueOf(quizDB.selectInt(sql)));
	}

}

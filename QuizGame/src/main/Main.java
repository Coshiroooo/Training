package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

public class Main {

	static final Scanner scanner = new Scanner(System.in);
	static final DBConnecter quizDB = new DBConnecter("localhost", "quiz_db", "root", "");
	static final Map<String,List<Object>> questions = quizDB.selectRecords("SELECT * FROM questions");
	static final Map<String,List<Object>> choices = quizDB.selectRecords("SELECT * FROM choices");
	static final Map<String,List<Object>> logs = quizDB.selectRecords("SELECT * FROM logs");
	static final Map<String,List<Object>> results = quizDB.selectRecords("SELECT * FROM results");
	static int questionNumber = 5;

	public static void main(String[] args) {

		System.out.println("いきなりですが、クイズを始めます！！！\n");

		quizDB.update("INSERT INTO results VALUES()");
		int resultId = quizDB.selectInt("SELECT MAX(id) FROM results");

		List<Integer> questionIdList = questions.get("id").stream().map(id -> (int)id).collect(Collectors.toList());
		Collections.shuffle(questionIdList);

		IntStream.range(0, questionNumber).boxed().forEach(n -> {

			quizDB.update("INSERT INTO logs(results_id) VALUES (" + resultId + ")");

			int logId = quizDB.selectInt("SELECT MAX(id) FROM logs");
			int questionId = questionIdList.get(n);

			quizDB.update("UPDATE questions SET ques_numbers = ques_numbers + 1 WHERE id = " + questionId);
			
			printQuestion(n + 1, logId, questionId);
			int choiceId = choiceInput(logId);
			printJudgement(logId, questionId, choiceId);
			updateCorrectRate(questionId);

		});

		updateResults(resultId);
		printResults();

	}

	// n問目の問題を出題するメソッド
	static void printQuestion(int n, int logId, int questionId) {
		quizDB.update(String.format("UPDATE logs SET questions_id = %s WHERE id = %s", questionId, logId));
		System.out.println("【第" + n + "問】　" + quizDB.selectStr("SELECT question FROM questions WHERE id = " + questionId));

		Map<String,List<Object>> choicesMap = choicesFilter(questionId);
		List<Object> choiceList = choicesMap.get("choice");
		choiceList.stream().map(c -> (choiceList.indexOf(c) + 1) + ". " + c.toString()).forEach(System.out::println);
		
		System.out.println("\n-------------------");
	}

	// 回答を入力するメソッド
	static int choiceInput(int logId) {
		System.out.print("正解を数字でお答えください：");
		int choiceId = scanner.nextInt();
		quizDB.update(String.format("UPDATE logs SET choice_id = %s WHERE id = %s", choiceId,logId));
		return choiceId;
	}

	// 問題の成否を判定し、点数計算するメソッド
	static void printJudgement(int logId, int questionId, int choiceId) {
		System.out.println();
		int correctChoiceId = (int)questions.get("correct_choice_id").get(questionId - 1);
		if (choiceId == correctChoiceId) {
			System.out.println("正解！！！！！\n");
			quizDB.update("UPDATE questions SET correct_numbers = correct_numbers + 1 WHERE id = " + questionId);
			quizDB.update("UPDATE logs SET point = 20 WHERE id = " + logId);
		} else {
			System.out.println("残念！！！はずれ！！！！！");
			System.out.println("正解は" + correctChoiceId + "." + choicesFilter(questionId).get("choice").get(correctChoiceId - 1) + "\n");
		}
	}

	// 正答率を更新するメソッド
	static void updateCorrectRate(int questionId) {
		int questionNumber = (int)questions.get("ques_numbers").get(questionId - 1);
		int correctNumber = (int)questions.get("correct_numbers").get(questionId - 1);
		int correctRate = (int) ((double)correctNumber / (double)questionNumber * 100);
		quizDB.update(String.format("UPDATE questions SET correct_rate = %s WHERE id = %s", correctRate,questionId));
	}

	// ResultsTableを更新するメソッド
	static void updateResults(int resultId) {
		int perfectScore = questionNumber * 20;
		int sumPoint = quizDB.selectInt("SELECT SUM(point) FROM logs WHERE results_id = " + resultId);
		int per = (int) (sumPoint * 100 / perfectScore);
		quizDB.update(String.format("UPDATE results SET point = %s , per = %s WHERE id = %s \n", sumPoint,per,resultId));

		System.out.println("【合計得点】\t" + sumPoint + " / " + perfectScore);
	}

	// 結果を表示するメソッド
	public static void printResults() {

		List<Integer> resultIds = results.get("id").stream().map(id -> (int)id).collect(Collectors.toList());
		List<Integer> resultPoints = results.get("point").stream().map(p -> (int)p).collect(Collectors.toList());
		List<LocalDateTime> resultsDateTimes = quizDB.selectColDateTime("SELECT date_time FROM results", 1);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

		System.out.println("\n--------------------");
		System.out.println("【これまでの結果】\n");
		
		resultIds.forEach(id -> {
			System.out.print(String.format("第%02d回", resultIds.get(id - 1)));
			System.out.println(String.format("\t%4s点", resultPoints.get(id - 1)));
			System.out.println("\t[" + resultsDateTimes.get(id - 1).format(format) + "]　");
		});

		System.out.println("\n--------------------\n");

		System.out.println("挑戦数：\t" + intToStr("%4s", "SELECT MAX(id) FROM results;") + "回");
		System.out.println("最高点：\t" + intToStr("%4s", "SELECT MAX(point) FROM results;") + "点");
		System.out.println("最低点：\t" + intToStr("%4s", "SELECT MIN(point) FROM results;") + "点");
		System.out.println("平均点：\t" + intToStr("%4s", "SELECT AVG(point) FROM results;") + "点\n");

		System.out.println("【あなたが間違えやすい問題】\n");
		Map<String,List<Object>> questionWorstRank = 
				quizDB.selectRecords("SELECT * FROM questions WHERE ques_numbers > 0 ORDER BY correct_rate ASC LIMIT 3");
		
		questionWorstRank.get("id").stream().map(id -> (int)id).forEach(id -> {
			
			int i = questionWorstRank.get("id").indexOf(id);
			System.out.print("【" + (i + 1) + "位】\t");
			System.out.println(String.format("正答率：%3s", questionWorstRank.get("correct_rate").get(i).toString()) + "%");
			System.out.println("Q. " + questionWorstRank.get("question").get(i).toString());
			
			List<String> choiceList = choicesFilter(id).get("choice").stream().map(c -> c.toString()).collect(Collectors.toList());
			choiceList.stream().map(c -> (choiceList.indexOf(c) + 1) + "." + c).forEach(System.out::println);
			System.out.println();
			
		});
		

	}

	// sql文で取得したint型をformatを揃えたStringで返してくれるメソッド
	public static String intToStr(String format, String sql) {
		return String.format(format, String.valueOf(quizDB.selectInt(sql)));
	}
	
	//問題ごとの選択肢Listを作成するメソッド
	public static Map<String,List<Object>> choicesFilter(int questionId){
		return quizDB.selectRecords("SELECT * FROM choices WHERE question_id = " + questionId); 
	}

}

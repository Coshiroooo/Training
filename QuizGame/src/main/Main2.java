package main;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

//問題の追加・削除、履歴の削除などを自由にやるクラス
public class Main2 {
	
	private static final DBConnecter quizDB = new DBConnecter("localhost","quiz_db","root","");
	private static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		selectDeleteQues();
		selectAddQuestion();
		selectDeleteResults();
	}
	
	//問題を追加するメソッド
	public static void selectAddQuestion() {
		System.out.println("--------------------");
		System.out.print("問題を追加しますか？(y/n)\n==> :");
		String select = scan.next();
		if("y".equals(select)) {
			
			printQuestionAll();
			quizDB.update("INSERT INTO questions VALUES()");
			int questionIdMax = quizDB.selectInt("SELECT MAX(id) FROM questions");
			
			System.out.print("設問を入力してください\n==> :");
			String question = scan.next();
			System.out.print("選択肢の数を入力してください\n==>：");
			int choiceNumber = scan.nextInt();
			IntStream.rangeClosed(1, choiceNumber).boxed().forEach(n -> {
				System.out.print(n + "番目の選択肢を入力してください\n==> :");
				String choice = scan.next();
				quizDB.update("INSERT INTO choices VALUES('" + questionIdMax + "','" + n + "','" + choice + "')");
			});
			System.out.print("正解の選択肢を入力してください\n==> :");
			int correctChoiceId = scan.nextInt();
			
			quizDB.update("UPDATE questions SET question = '" + question  + "' , correct_choice_id = " + correctChoiceId + " WHERE id = " + questionIdMax);
			System.out.println();
			System.out.println("問題の追加が完了しました\n");
			printQuestionAll();
			selectAddQuestion();
			
		}else if("n".equals(select)) {
			System.out.println("問題は追加しません");
		}else {
			System.out.println("追加するなら y を、そうでなければ n を入力してください");
			selectAddQuestion();
		}
	}
	
	//問題一覧を表示するメソッド
	public static void printQuestionAll() {
		System.out.println("--------------------");
		System.out.println("【問題一覧】\n");
		int count = 1;
		List<String> quesList = quizDB.selectColStr("SELECT * FROM questions", 2);
		for(String question : quesList) {
			System.out.println(count + ". " + question);
			count++;
		}
		System.out.println("--------------------");
	}
	
	//問題削除選択をさせるメソッド
	public static void selectDeleteQues() {
		System.out.println("--------------------");
		System.out.print("問題を削除しますか？(y/n)\n==> :");
		String select = scan.next();
		if("y".equals(select)) {
			printQuestionAll();
			System.out.print("削除する問題のidを入力してください\n==> :");
			int deleteQuestionId = scan.nextInt();
			deleteQues(deleteQuestionId);
			printQuestionAll();
			selectDeleteQues();
		}else if("n".equals(select)) {
			System.out.println("問題は削除しません");
		}else {
			System.out.println("削除するなら y を、そうでなければ n を入力してください");
			selectDeleteQues();
		}		
	}
	
	//指定されたidの問題を削除するメソッド
	public static void deleteQues(int deleteQuesId) {
		
		quizDB.update("DELETE FROM questions WHERE id = " + deleteQuesId);
		quizDB.update("DELETE FROM choices WHERE question_id = " + deleteQuesId);
		quizDB.update("ALTER TABLE questions ADD new_id int(11) NOT NULL FIRST");
		
		List<Integer> questionIdList = quizDB.selectColInt("SELECT id FROM questions", 1);
		questionIdList.forEach(id -> quizDB.update("UPDATE questions SET new_id = " + id + " WHERE id = " + id));
		
		quizDB.update("UPDATE choices RIGHT JOIN questions ON choices.question_id = questions.id SET question_id = new_id");
		quizDB.update("ALTER TABLE questions drop column id");
		quizDB.update("ALTER TABLE questions CHANGE new_id id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT FIRST");
		quizDB.update("ALTER TABLE questions AUTO_INCREMENT = 1");
		
		System.out.println("idが" + deleteQuesId + "の問題を削除しました");
		
	}
	
	//問題を全削除するメソッド
	public static void deleteQuesAll() {
		do {
			int questionIdMin = quizDB.selectInt("SELECT MIN(id) FROM questions");
			deleteQues(questionIdMin);
		}while(quizDB.selectColInt("SELECT id FROM questions", 1).size() > 1);
	}
	
	//履歴を全部消すか選択するメソッド
	public static void selectDeleteResults() {
		System.out.println("--------------------");
		System.out.print("履歴を削除しますか？(y/n)\n==> :");
		String select = scan.next();
		if("y".equals(select)) {
			deleteResultsAll();
			System.out.println("履歴を全て削除しました");
		}else if("n".equals(select)) {
			System.out.println("履歴は削除しません");
		}else {
			System.out.println("削除するなら y を、そうでなければ n を入力してください");
			selectDeleteResults();
		}
	}
	
	//Resultsを全部消すメソッド
	public static void deleteResultsAll() {
		quizDB.update("DELETE FROM results");
		quizDB.update("DELETE FROM logs");
		quizDB.update("ALTER TABLE results AUTO_INCREMENT = 1");
		quizDB.update("ALTER TABLE logs AUTO_INCREMENT = 1");
		quizDB.update("UPDATE questions SET ques_numbers = 0, correct_numbers = 0, correct_rate = 0");
		System.out.println("全てのResultsを削除しました");
	}
	
}

package main4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main4 {

	static int cardWidth = 5; // カードのマス目幅数
	static List<Integer> bingoNumbers = new ArrayList<Integer>(); // カードに配置されうる数字の候補List
	static List<List<Integer>> matrixList = new ArrayList<List<Integer>>(); // カードに配置する数字のList
	static List<Integer> winningNumbers = new ArrayList<Integer>(); // 抽選番号のList
	static List<Integer> currentWinningNumbers = new ArrayList<Integer>(); // 既出の抽選番号のList

	public static void main(String[] args) throws IOException {

		makeWinningNumbers();

		makeBingoNumberList();

		System.out.println("\r\n" + "【BINGO GAME】");
		System.out.println("\r\n" + "あなたのカードです");

		makeBingo(0);

		for (int i = 1; i <= bingoNumbers.size(); i++) {
			System.out.println("↓抽選する↓");
			enter();
			System.out.println("\r\n" + (i) + "回目の抽選" + "\r\n");
			System.out.println("当選番号は" + winningNumbers.get(i) + "です！");

			makeBingo(i);

			if (bingoJudgeSide() || bingoJudgeVertical() || bingoJudgeSlant1() || bingoJudgeSlant2()) {
				break;
			}

		}
		System.out.println("\r\n" + "ビンゴ！！！");
	}

	public static void makeWinningNumbers() { // 抽選番号のListを用意する
		if (cardWidth < 11) { // 10マス以下の時は上限100までの数字が入る
			for (int i = 1; i <= 100; i++) {
				winningNumbers.add(i);
			}
		} else { // 11マス以上は、マス数と同じ数だけの数字が入る
			for (int i = 1; i <= cardWidth * cardWidth; i++) {
				winningNumbers.add(i);
			}
		}
		Collections.shuffle(winningNumbers); // シャッフル
		winningNumbers.add(0, -1); // 先頭に0を挿入。初回の繰り返し時に当選処理が起きないようにするため
	}

	public static void makeBingo(int i) { // +++++抽選状況に合わせたビンゴを作る+++++

		int winningNumber = 0;

		for (List<Integer> matrix : matrixList) {
			for (List<Integer> n : matrixList) {
				System.out.print("______");
			}
			System.out.println();
			for (int matrixnumber : matrix) { // 取り出したリストを1つずつ処理して繰り返し
				int numberBox = matrixnumber;
				if (numberBox == winningNumbers.get(i)) {
					System.out.print("|     ");
					winningNumber = numberBox;
				} else {
					if (currentWinningNumbers.contains(numberBox)) {
						System.out.print("|     ");
					} else {
						System.out.printf("| %3d ", numberBox);
					}
				}
			}
			System.out.print("|" + "\r\n");
		}

		for (List<Integer> n : matrixList) {
			System.out.print("______");
		}
		String judge = (winningNumbers.get(i) == -1) ? "" : (winningNumber == winningNumbers.get(i)) ? "当たり！" : "残念！";
		System.out.println("\r\n" + "\r\n" + judge + "\r\n");
		currentWinningNumbers.add(winningNumbers.get(i));
	}

	public static void makeBingoNumberList() { // +++++bingoCardListをcardWidth行分に分けたリストを作り、二次元リストに追加+++++
		if (cardWidth <= 10) { // 10マス以下の時は上限100までの数字が入る
			for (int i = 1; i <= 100; i++) {
				bingoNumbers.add(i);
			}
		} else { // 11マス以上は、マス数と同じ数だけの数字が入る
			for (int i = 1; i < cardWidth * cardWidth + 1; i++) {
				bingoNumbers.add(i);
			}
		}
		Collections.shuffle(bingoNumbers);

		for (int i = 0; i < cardWidth; i++) {
			matrixList.add(new ArrayList<Integer>(bingoNumbers.subList(i * cardWidth, (i + 1) * cardWidth)));
		}
	}

	public static boolean bingoJudgeVertical() { // +++++ビンゴを判定する機能+++++

		Boolean isBingoJudge = false;
		int bingoCount = 0;

		for (List<Integer> matrix1 : matrixList) {// ***************縦がビンゴしたか判定***************
			List<Integer> exactListVer = new ArrayList<Integer>();
			int countVer = 0;
			for (List<Integer> matrix2 : matrixList) {
				exactListVer.add(matrix2.get(matrixList.indexOf(matrix1))); // matrixList.get(ループ変数).get(定数)と同じ 縦列を表す
			}
			for (int c : currentWinningNumbers) {
				if (exactListVer.contains(c)) {
					countVer++;
				}
			}
			if (countVer == cardWidth) {
				bingoCount++;
				break;
			}
		}
		if (bingoCount > 0) { // 4つの判定のどこかである行列がビンゴになっていればカウントは0以上
			isBingoJudge = true;
		}
		return isBingoJudge;
	}

	public static boolean bingoJudgeSide() { // +++++ビンゴを判定する機能+++++

		Boolean isBingoJudge = false;
		int bingoCount = 0;

		for (List<Integer> matrix1 : matrixList) {// ***************横がビンゴしたか判定***************
			List<Integer> exactListSide = new ArrayList<Integer>();
			int countSide = 0;
			exactListSide = matrix1; // 横1列分のListを作る
			for (int c : currentWinningNumbers) { // 参照した列の中の数字が既出の当選番号に含まれている分だけカウントされる
				if (exactListSide.contains(c)) {
					countSide++;
				}
			}
			if (countSide == cardWidth) { // カウントがカードの横幅と一緒になったら列の数字全てが既出の当選番号に含まれているのでビンゴになる
				bingoCount++; // trueの時にカウントし、最後にカウントされていたら、ビンゴ判定をメソッドが返す
				break; // trueになった瞬間にループを抜ける
			}
		}
		if (bingoCount > 0) { // 4つの判定のどこかである行列がビンゴになっていればカウントは0以上
			isBingoJudge = true;
		}
		return isBingoJudge;
	}

	public static boolean bingoJudgeSlant1() { // +++++ビンゴを判定する機能+++++

		Boolean isBingoJudge = false;
		int bingoCount = 0;

		for (List<Integer> matrix1 : matrixList) {// ***************斜め↘︎︎がビンゴしたか判定***************
			List<Integer> exactListSlant1 = new ArrayList<Integer>();
			int countSlant1 = 0;
			int count = 0;
			exactListSlant1.add(matrix1.get(count));
			count++;
			for (int c : currentWinningNumbers) {
				if (exactListSlant1.contains(c)) {
					countSlant1++;
				}
			}
			if (countSlant1 == cardWidth) {
				bingoCount++;
				break;
			}
		}
		if (bingoCount > 0) { // 4つの判定のどこかである行列がビンゴになっていればカウントは0以上
			isBingoJudge = true;
		}
		return isBingoJudge;
	}

	public static boolean bingoJudgeSlant2() { // +++++ビンゴを判定する機能+++++

		Boolean isBingoJudge = false;
		int bingoCount = 0;

		for (List<Integer> matrix1 : matrixList) { // ***************斜め↙︎がビンゴしたか判定***************
			List<Integer> exactListSlant2 = new ArrayList<Integer>();
			int countSlant2 = 0;
			int count = 0;
			for (List<Integer> matrix2 : matrixList) {
				exactListSlant2.add(matrix2.get(cardWidth - (count + 1)));
			}
			count++;
			for (int c : currentWinningNumbers) {
				if (exactListSlant2.contains(c)) {
					countSlant2++;
				}
			}
			if (countSlant2 == cardWidth) {
				bingoCount++;
				break;
			}
		}
		if (bingoCount > 0) { // 4つの判定のどこかである行列がビンゴになっていればカウントは0以上
			isBingoJudge = true;
		}
		return isBingoJudge;
	}

	public static void enter() throws IOException { // +++++Enter押すと次の処理が始まる+++++
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append(br);
		String enter = sb.toString();
		System.in.read();
		switch (enter) {
		case "\r\n":
			break;
		}
	}

}

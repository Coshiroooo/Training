package main6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BingoCard {

	private Lottery lottery = new Lottery();
	private Scanner scanner = new Scanner(System.in);
	private List<List<Integer>> bingoCardNumbers = new ArrayList<List<Integer>>(); // カードに配置する数字のList

	private int cardWidth; // カードの幅
	private int bingoNumbersSize; // カードに配置されうる数字の候補の上限

	// コンストラクタ
	BingoCard() {
		selectWidth();
	}

	// ビンゴのマス目の幅を入力するメソッド
	public void selectWidth() {
		System.out.print("ビンゴのマス目の幅を入力してください：");
		int cardWidth = scanner.nextInt();
		if (cardWidth > 30) {
			System.out.println("30以下の数字を入力してください");
			selectWidth();
		}
		this.cardWidth = cardWidth;
	}

	// ビンゴカード上に配置した数字のリスト
	public void makeBingoCardNumbers() {
		List<Integer> bingoNumbers = new ArrayList<Integer>();
		List<List<Integer>> bingoCardNumbers = new ArrayList<List<Integer>>();

		if (cardWidth <= 10) { // 10マス以下の時は上限100までの数字が入る
			for (int i = 1; i <= 100; i++) {
				bingoNumbers.add(i);
			}
		} else { // 11マス以上は、マス数と同じ数だけの数字が入る
			for (int i = 1; i <= cardWidth * cardWidth; i++) {
				bingoNumbers.add(i);
			}
		}
		Collections.shuffle(bingoNumbers); // シャッフルして順番にカードに配置していく
		this.bingoNumbersSize = bingoNumbers.size();

		for (int i = 0; i < cardWidth; i++) { // bingoNumbersの要素を前からcardWidth分に切り分けたListを二次元リストに格納していく
			bingoCardNumbers.add(new ArrayList<Integer>(bingoNumbers.subList(i * cardWidth, (i + 1) * cardWidth)));
		}
		this.bingoCardNumbers = bingoCardNumbers;
	}

	// lotteryからランダムに数字を出し、既出もしくは範囲外ならもう一度引く
	public void pickWinningNumber() {
		int winningNumber = lottery.pickNumber();
		if (winningNumber > bingoNumbersSize) {
			pickWinningNumber();
		} else {
			if (lottery.getCurrentWinningNumbers().contains(winningNumber)) {
				pickWinningNumber();
			} else {
				lottery.getCurrentWinningNumbers().add(winningNumber);
			}
		}
	}

	// 抽選状況に合わせたビンゴカードを作る
	public void updateBingo(Player player, int i) {

		pickWinningNumber();

		System.out.println();
		System.out.println((i + 1) + "回目の抽選");
		System.out.println();
		System.out.println("当選番号は" + lottery.getCurrentWinningNumbers().get(i) + "です！");

		for (List<List<Integer>> myBCNumbers : player.getMyBingoCardNumbers()) {

			System.out.println("【" + (player.getMyBingoCardNumbers().indexOf(myBCNumbers) + 1) + "人目のビンゴ】");

			int winningNumber = 0;

			for (List<Integer> bcNumbers : myBCNumbers) {
				for (List<Integer> loop : myBCNumbers) {
					System.out.print("______");
				}
				System.out.println();
				for (int bcNumber : bcNumbers) { // 取り出したリストを1つずつ処理して繰り返し
					if (bcNumber == lottery.getCurrentWinningNumbers().get(i)) {
						System.out.print("|     ");
						winningNumber = bcNumber;
					} else {
						if (lottery.getCurrentWinningNumbers().contains(bcNumber)) {
							System.out.print("|     ");
						} else {
							System.out.printf("| %3d ", bcNumber);
						}
					}
				}
				System.out.print("|");
				System.out.println();
			}

			for (List<Integer> loop : myBCNumbers) {
				System.out.print("______");
			}
			System.out.println();
			System.out.println();
			String judge = (winningNumber == lottery.getCurrentWinningNumbers().get(i)) ? "当たり！" : "残念！";
			System.out.println(judge);
			System.out.println();
		}
	}

	// 初期ビンゴを作る
	public void makeBingo(List<List<Integer>> myBCNumbers, int count) {
		System.out.println();
		System.out.println((count + 1) + "人目のカードです");

		for (List<Integer> bcNumbers : myBCNumbers) {
			for (List<Integer> loop : myBCNumbers) {
				System.out.print("______");
			}
			System.out.println();
			for (int bcNumber : bcNumbers) { // 取り出したリストを1つずつ処理して繰り返し
				System.out.printf("| %3d ", bcNumber);
			}
			System.out.print("|");
			System.out.println();
		}

		for (List<Integer> loop : bingoCardNumbers) {
			System.out.print("______");
		}
		System.out.println();
		System.out.println();
	}

	// 縦ビンゴを判定する機能
	public boolean isBingoJudgeVertical(List<List<Integer>> myBCNumbers) {

		Boolean isBingoJudge = false;

		for (List<Integer> bcNumbers : myBCNumbers) {
			List<Integer> exactListVer = new ArrayList<Integer>();
			int countVer = 0;
			for (List<Integer> bcNumbers2 : myBCNumbers) {
				exactListVer.add(bcNumbers2.get(myBCNumbers.indexOf(bcNumbers))); // bingoCardNumbers.get(ループ変数).get(定数)と同じ
																					// 縦列を表す
			}
			for (int c : lottery.getCurrentWinningNumbers()) {
				if (exactListVer.contains(c)) {
					countVer++;
				}
			}
			if (countVer == cardWidth) {
				isBingoJudge = true;
				break;
			}
		}
		return isBingoJudge;
	}

	// 横ビンゴを判定する機能
	public boolean isBingoJudgeSide(List<List<Integer>> myBCNumbers) {

		Boolean isBingoJudge = false;

		for (List<Integer> bcNumbers : myBCNumbers) {
			List<Integer> exactListSide = new ArrayList<Integer>();
			int countSide = 0;
			exactListSide = bcNumbers; // 横1列分のListを作る
			for (int c : lottery.getCurrentWinningNumbers()) { // 参照した列の中の数字が既出の当選番号に含まれている分だけカウントされる
				if (exactListSide.contains(c)) {
					countSide++;
				}
			}
			if (countSide == cardWidth) { // カウントがカードの横幅と一緒になったら列の数字全てが既出の当選番号に含まれているのでビンゴになる
				isBingoJudge = true;
				break; // trueになった瞬間にループを抜ける
			}
		}
		return isBingoJudge;
	}

	// 斜め↘︎ビンゴを判定する機能
	public boolean isBingoJudgeSlant1(List<List<Integer>> myBCNumbers) {

		Boolean isBingoJudge = false;

		int countSlant1 = 0;
		List<Integer> exactListSlant1 = new ArrayList<Integer>();

		for (List<Integer> bcNumbers : myBCNumbers) {
			exactListSlant1.add(bcNumbers.get(myBCNumbers.indexOf(bcNumbers)));
		}
		for (int c : lottery.getCurrentWinningNumbers()) {
			if (exactListSlant1.contains(c)) {
				countSlant1++;
			}
		}
		if (countSlant1 == cardWidth) {
			isBingoJudge = true;
		}
		return isBingoJudge;
	}

	// 斜め↙ビンゴを判定する機能
	public boolean isBingoJudgeSlant2(List<List<Integer>> myBCNumbers) {

		Boolean isBingoJudge = false;

		int countSlant2 = 0;
		List<Integer> exactListSlant2 = new ArrayList<Integer>();

		for (List<Integer> bcNumbers : myBCNumbers) {
			exactListSlant2.add(bcNumbers.get(cardWidth - (myBCNumbers.indexOf(bcNumbers) + 1)));
		}
		for (int c : lottery.getCurrentWinningNumbers()) {
			if (exactListSlant2.contains(c)) {
				countSlant2++;
			}
		}
		if (countSlant2 == cardWidth) {
			isBingoJudge = true;
		}
		return isBingoJudge;
	}

	// ビンゴ判定をまとめた
	public List<Boolean> isBingoJudge(Player player) {
		List<Boolean> isBingoList = new ArrayList<>();
		for (List<List<Integer>> myBCNumbers : player.getMyBingoCardNumbers()) {
			if (isBingoJudgeVertical(myBCNumbers) || isBingoJudgeSide(myBCNumbers) || isBingoJudgeSlant1(myBCNumbers)
					|| isBingoJudgeSlant2(myBCNumbers)) {
				isBingoList.add(true);
			} else {
				isBingoList.add(false);
			}
		}
		return isBingoList;
	}

	// ゲッター//

	public int getBingoNumbersSize() {
		return bingoNumbersSize;
	}

	public List<List<Integer>> getBingoCardNumbers() {
		return bingoCardNumbers;
	}
}

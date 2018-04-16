package main7;

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
	public List<List<Integer>> makeBingoCardNumbers() {
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
		return bingoCardNumbers;
	}

	// lotteryからランダムに数字を出し、既出もしくは範囲外ならもう一度引く
	public void pickWinningNumber() {
		int winningNumber = lottery.pickNumber();
		if (winningNumber < bingoNumbersSize && !lottery.getCurrentWinningNumbers().contains(winningNumber)) {
			lottery.getCurrentWinningNumbers().add(winningNumber);
		} else {
			pickWinningNumber();
		}
	}

	// 抽選状況に合わせたそのプレイヤーのビンゴカードを作る
	public void updateBingo(Player player, int count) {

		System.out.println("【" + player.getPlayerName() + "のビンゴ】");

		int winningNumber = 0;

		for (List<Integer> bcNumbers : player.getMyBingoCard()) {
			for (int i = 0; i < cardWidth; i++) {
				System.out.print("______");
			}
			System.out.println();
			for (int bcNumber : bcNumbers) { // 取り出したリストを1つずつ処理して繰り返し
				if (bcNumber == lottery.getCurrentWinningNumbers().get(count)) {
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

		for (int i = 0; i < cardWidth; i++) {
			System.out.print("______");
		}
		System.out.println();
		System.out.println();
		String judge = (winningNumber == lottery.getCurrentWinningNumbers().get(count)) ? "当たり！" : "残念！";
		System.out.println(judge);
		System.out.println();
	}

	// 初期ビンゴを作る
	public void makeBingo(Player player) {
		System.out.println();
		System.out.println(player.getPlayerName() + "のカードです");

		for (List<Integer> bcNumbers : player.getMyBingoCard()) {
			for (int i = 0; i < cardWidth; i++) {
				System.out.print("______");
			}
			System.out.println();
			for (int bcNumber : bcNumbers) { // 取り出したリストを1つずつ処理して繰り返し
				System.out.printf("| %3d ", bcNumber);
			}
			System.out.print("|");
			System.out.println();
		}

		for (int i = 0; i < cardWidth; i++) {
			System.out.print("______");
		}
		System.out.println();
		System.out.println();
	}

	// 縦ビンゴを判定する機能
	public boolean isBingoJudgeVertical(Player player) {

		Boolean isBingoJudge = false;

		for (List<Integer> bcNumbers : player.getMyBingoCard()) {
			List<Integer> exactListVer = new ArrayList<Integer>();
			int countVer = 0;
			for (List<Integer> bcNumbers2 : player.getMyBingoCard()) {
				exactListVer.add(bcNumbers2.get(player.getMyBingoCard().indexOf(bcNumbers))); // bingoCardNumbers.get(ループ変数).get(定数)と同じ
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
	public boolean isBingoJudgeSide(Player player) {

		Boolean isBingoJudge = false;

		for (List<Integer> bcNumbers : player.getMyBingoCard()) {
			int countSide = 0;
			for (int c : lottery.getCurrentWinningNumbers()) { // 参照した列の中の数字が既出の当選番号に含まれている分だけカウントされる
				if (bcNumbers.contains(c)) {
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
	public boolean isBingoJudgeSlant1(Player player) {

		Boolean isBingoJudge = false;

		int countSlant1 = 0;
		List<Integer> exactListSlant1 = new ArrayList<Integer>();

		for (List<Integer> bcNumbers : player.getMyBingoCard()) {
			exactListSlant1.add(bcNumbers.get(player.getMyBingoCard().indexOf(bcNumbers)));
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
	public boolean isBingoJudgeSlant2(Player player) {

		Boolean isBingoJudge = false;

		int countSlant2 = 0;
		List<Integer> exactListSlant2 = new ArrayList<Integer>();

		for (List<Integer> bcNumbers : player.getMyBingoCard()) {
			exactListSlant2.add(bcNumbers.get(cardWidth - (player.getMyBingoCard().indexOf(bcNumbers) + 1)));
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
	public boolean isBingoJudge(Player player) {
		Boolean isBingo;
		if (isBingoJudgeVertical(player) || isBingoJudgeSide(player) || isBingoJudgeSlant1(player)
				|| isBingoJudgeSlant2(player)) {
			isBingo = true;
		} else {
			isBingo = false;
		}
		return isBingo;
	}

	// ゲッター//

	public int getBingoNumbersSize() {
		return bingoNumbersSize;
	}

	public List<List<Integer>> getBingoCardNumbers() {
		return bingoCardNumbers;
	}

	public Lottery getLottery() {
		return this.lottery;
	}

}

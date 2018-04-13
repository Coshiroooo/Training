package main5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BingoCard {
	
	private Lottery lottery = new Lottery();
	
	private int cardWidth; //カードの幅
	
	private List<Integer> bingoNumbers = new ArrayList<Integer>(); // カードに配置されうる数字の候補List
	private List<List<Integer>> bingoCardNumbers = new ArrayList<List<Integer>>(); // カードに配置する数字のList
	
	BingoCard(){
		selectWidth();
	}
	
	public void selectWidth() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("ビンゴのマス目の幅を入力してください：");
		int cardWidth = scanner.nextInt();
		if(cardWidth > 30) {
			System.out.println("30以下の数字を入力してください");
			selectWidth();
		}
		this.cardWidth = cardWidth;
	}
	
	public void makeBingoCardNumbers() { // +++++ビンゴカード上に配置した数字のリスト+++++
		if (cardWidth <= 10) { // 10マス以下の時は上限100までの数字が入る
			for (int i = 1; i <= 100; i++) {
				bingoNumbers.add(i);
			}
		} else { // 11マス以上は、マス数と同じ数だけの数字が入る
			for (int i = 1; i <= cardWidth * cardWidth; i++) {
				bingoNumbers.add(i);
			}
		}
		Collections.shuffle(bingoNumbers); //シャッフルして順番にカードに配置していく

		for (int i = 0; i < cardWidth; i++) { //bingoNumbersの要素を前からcardWidth分に切り分けたListを二次元リストに格納していく
			bingoCardNumbers.add(new ArrayList<Integer>(bingoNumbers.subList(i * cardWidth, (i + 1) * cardWidth)));
		}
	}
	
	public void pickWinningNumber() { //lotteryからランダムに数字を出し、既出もしくは範囲外ならもう一度引く
		int winningNumber = lottery.pickNumber();
		if(lottery.getCurrentWinningNumbers().contains(winningNumber) || !(bingoNumbers.contains(winningNumber))) {
			pickWinningNumber();
		}else {
			lottery.getCurrentWinningNumbers().add(winningNumber);
		}
	}
	
	public void updateBingo(int i) { // +++++抽選状況に合わせたビンゴを作る+++++
		
		pickWinningNumber();
		
		System.out.println();
		System.out.println((i + 1) + "回目の抽選");
		System.out.println();
		System.out.println("当選番号は" + lottery.getCurrentWinningNumbers().get(i) + "です！");

		int winningNumber = 0;

		for (List<Integer> bcNumbers : bingoCardNumbers) {
			for (List<Integer> loop : bingoCardNumbers) {
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

		for (List<Integer> loop : bingoCardNumbers) {
			System.out.print("______");
		}
		System.out.println();
		System.out.println();
		String judge = (winningNumber == lottery.getCurrentWinningNumbers().get(i)) ? "当たり！" : "残念！";
		System.out.println(judge);
		System.out.println();
	}
	
	public void makeBingo() { // +++++初期ビンゴを作る+++++
		System.out.println();
		System.out.println("あなたのカードです");
		
		for (List<Integer> bcNumbers : bingoCardNumbers) {
			for (List<Integer> loop : bingoCardNumbers) {
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
		System.out.println();
	}
	
	public boolean isBingoJudgeVertical() { // +++++縦ビンゴを判定する機能+++++

		Boolean isBingoJudge = false;

		for (List<Integer> bcNumbers : bingoCardNumbers) {
			List<Integer> exactListVer = new ArrayList<Integer>();
			int countVer = 0;
			for (List<Integer> bcNumbers2 : bingoCardNumbers) {
				exactListVer.add(bcNumbers2.get(bingoCardNumbers.indexOf(bcNumbers))); // bingoCardNumbers.get(ループ変数).get(定数)と同じ 縦列を表す
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

	public boolean isBingoJudgeSide() { // +++++横ビンゴを判定する機能+++++

		Boolean isBingoJudge = false;

		for (List<Integer> bcNumbers : bingoCardNumbers) {
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

	public boolean isBingoJudgeSlant1() { // +++++斜め↘ビンゴを判定する機能+++++

		Boolean isBingoJudge = false;
		int countSlant1 = 0;
		
		List<Integer> exactListSlant1 = new ArrayList<Integer>();
		
		for (List<Integer> bcNumbers : bingoCardNumbers) {
				exactListSlant1.add(bcNumbers.get(bingoCardNumbers.indexOf(bcNumbers)));
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

	public boolean isBingoJudgeSlant2() { // +++++斜め↙ビンゴを判定する機能+++++

		Boolean isBingoJudge = false;
		int countSlant2 = 0;
		
		List<Integer> exactListSlant2 = new ArrayList<Integer>();

		for (List<Integer> bcNumbers : bingoCardNumbers) {
			exactListSlant2.add(bcNumbers.get(cardWidth - (bingoCardNumbers.indexOf(bcNumbers) + 1)));
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
	
	public boolean isBingoJudge() { //全部の判定をまとめた
		Boolean isBingoJudge = (isBingoJudgeVertical() || isBingoJudgeSide() || isBingoJudgeSlant1() || isBingoJudgeSlant2()) ? true : false;
		return isBingoJudge;
	}
	
	//ゲッター//
	
	public List<Integer> getBingoNumbers(){
		return bingoNumbers;
	}
}

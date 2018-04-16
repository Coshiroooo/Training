package main8;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String playerName;
	private BingoCard bingoCard = new BingoCard();
	private List<List<Integer>> myBingoCardNumbers = new ArrayList<List<Integer>>();

	// コンストラクタ
	Player(int i,int cardWidth) {
		this.myBingoCardNumbers = this.bingoCard.makeBingoCardNumbers(cardWidth);
		this.playerName = "Player" + i + "さん";
	}
	
	//プレイヤーのビンゴカードに配置する数字のリストを作るメソッド
	public void makeMyBingoCardNumbers(int cardWidth) {
		this.myBingoCardNumbers = bingoCard.makeBingoCardNumbers(cardWidth);
	}

	// プレイヤーのビンゴカードを作るメソッド
	public void makeMyBingoCard(int cardWidth) {
		System.out.println();
		System.out.println(playerName + "のカードです");
		this.bingoCard.makeBingo(this.myBingoCardNumbers,cardWidth);
	}
	
	//プレイヤーのビンゴカードを抽選回数に合わせてアップデートするメソッド
	public void updateMyBingoCard(Lottery lottery,int cardWidth,int count) {
		System.out.println("【" + playerName + "のビンゴ】");
		this.bingoCard.updateBingo(this.myBingoCardNumbers,lottery,cardWidth,count);
	}
	
	// ビンゴ判定をまとめた
	public boolean isBingo(Lottery lottery,int cardWidth) {
		Boolean isBingo;
		if (isBingoVertical(lottery,cardWidth) || isBingoSide(lottery,cardWidth) || isBingoSlant1(lottery,cardWidth)
				|| isBingoSlant2(lottery,cardWidth)) {
			isBingo = true;
		} else {
			isBingo = false;
		}
		return isBingo;
	}

	// 縦ビンゴを判定する機能
	public boolean isBingoVertical(Lottery lottery, int cardWidth) {

		Boolean isBingoJudge = false;

		for (List<Integer> bcNumbers : myBingoCardNumbers) {
			List<Integer> exactListVer = new ArrayList<Integer>();
			int countVer = 0;
			for (List<Integer> bcNumbers2 : myBingoCardNumbers) {
				exactListVer.add(bcNumbers2.get(myBingoCardNumbers.indexOf(bcNumbers))); // bingoCardNumbers.get(ループ変数).get(定数)と同じ
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
	public boolean isBingoSide(Lottery lottery,int cardWidth) {

		Boolean isBingoJudge = false;

		for (List<Integer> bcNumbers : myBingoCardNumbers) {
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
	public boolean isBingoSlant1(Lottery lottery,int cardWidth) {

		Boolean isBingoJudge = false;

		int countSlant1 = 0;
		List<Integer> exactListSlant1 = new ArrayList<Integer>();

		for (List<Integer> bcNumbers : myBingoCardNumbers) {
			exactListSlant1.add(bcNumbers.get(myBingoCardNumbers.indexOf(bcNumbers)));
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
	public boolean isBingoSlant2(Lottery lottery,int cardWidth) {

		Boolean isBingoJudge = false;

		int countSlant2 = 0;
		List<Integer> exactListSlant2 = new ArrayList<Integer>();

		for (List<Integer> bcNumbers : myBingoCardNumbers) {
			exactListSlant2.add(bcNumbers.get(cardWidth - (myBingoCardNumbers.indexOf(bcNumbers) + 1)));
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

	// ゲッター

	public String getPlayerName() {
		return this.playerName;
	}

	public List<List<Integer>> getMyBingoCardNumbers() {
		return this.myBingoCardNumbers;
	}
	
	public BingoCard bingoCard() {
		return this.bingoCard;
	}

}

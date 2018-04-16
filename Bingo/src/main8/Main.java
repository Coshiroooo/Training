package main8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static Lottery lottery = new Lottery();
	static Scanner scanner = new Scanner(System.in);
	static int playerNumber;
	static int cardWidth;
	
	public static void main(String[] args) throws IOException {

		List<Player> allBingoCards = new ArrayList<Player>();

		System.out.println();
		System.out.println("【BINGO GAME】");
		
		selectWidth();
		selectMember();

		for (int i = 1; i <= playerNumber; i++) {
			allBingoCards.add(new Player(i,cardWidth));
		}

		System.out.println("Game Start!!");

		for (Player player : allBingoCards) {
			player.makeMyBingoCard(cardWidth);
		}

		loop: for (int i = 0; i <= bingoNumberMax(cardWidth); i++) {
			System.out.println("↓抽選する↓");
			enter();
			
			pickWinningNumber();
			printWinning(i);

			for (Player player : allBingoCards) {
				player.updateMyBingoCard(lottery,cardWidth,i);
			}
			for (Player player : allBingoCards) {
				if (player.isBingo(lottery,cardWidth) == true) {
					break loop;
				}
			}
		}

		for (Player player : allBingoCards) {
			if (player.isBingo(lottery,cardWidth)) {
				System.out.println(player.getPlayerName() + "のビンゴ！！！");
			}
		}
	}
	
	//ビンゴに入りうる数の最大値を求める
	public static int bingoNumberMax(int cardWidth) {
		int bingoNumberMax;
		if (cardWidth <= 10) { // 10マス以下の時は上限100までの数字が入る
			bingoNumberMax = 100;
		} else { // 11マス以上は、マス数と同じ数だけの数字が入る
			bingoNumberMax = (int) Math.pow(cardWidth, 2);
		}
		return bingoNumberMax;
	}
	
	//ゲーム参加人数を決めるメソッド
	public static void selectMember() {
		System.out.println();
		System.out.print("何人でビンゴしますか？：");
		playerNumber = scanner.nextInt();
	}
	
	// ビンゴのマス目の幅を入力するメソッド
		public static void selectWidth() {
			System.out.print("ビンゴのマス目の幅を入力してください：");
			cardWidth = scanner.nextInt();
			if (cardWidth > 30) {
				System.out.println("30以下の数字を入力してください");
				selectWidth();
			}
		}
	
	// lotteryからランダムに数字を出し、既出もしくは範囲外ならもう一度引く
	public static void pickWinningNumber() {
		int winningNumber = lottery.pickNumber();
		if (winningNumber <= bingoNumberMax(cardWidth) && !lottery.getCurrentWinningNumbers().contains(winningNumber)) {
			lottery.getCurrentWinningNumbers().add(winningNumber);
		} else {
			pickWinningNumber();
		}
	}

	// Enterを押すと次の処理が始まる
	public static void enter() throws IOException {
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

	// 当選番号の表示など
	public static void printWinning(int i) {
		System.out.println();
		System.out.println((i + 1) + "回目の抽選");
		System.out.println();
		System.out.println("当選番号は" + lottery.getCurrentWinningNumbers().get(i) + "です！");
	}


}

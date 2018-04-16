package main7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		List<Player> allBingoCards = new ArrayList<Player>();

		System.out.println();
		System.out.println("【BINGO GAME】");

		BingoCard bingoCard = new BingoCard();

		System.out.println();
		System.out.print("何人でビンゴしますか？：");
		int playerNumber = scanner.nextInt();

		for (int i = 1; i <= playerNumber; i++) {
			allBingoCards.add(new Player(i, bingoCard));
		}

		System.out.println("Game Start!!");

		for (Player player : allBingoCards) {
			bingoCard.makeBingo(player);
		}

		loop: for (int i = 0; i <= bingoCard.getBingoNumbersSize(); i++) {
			System.out.println("↓抽選する↓");
			enter();
			
			bingoCard.pickWinningNumber();
			printWinning(i, bingoCard.getLottery());

			for (Player player : allBingoCards) {
				bingoCard.updateBingo(player, i);
			}
			for (Player player : allBingoCards) {
				if (bingoCard.isBingoJudge(player) == true) {
					break loop;
				}
			}
		}

		for (Player player : allBingoCards) {
			if (bingoCard.isBingoJudge(player)) {
				System.out.println(player.getPlayerName() + "のビンゴ！！！");
			}
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
	public static void printWinning(int i, Lottery lottery) {
		System.out.println();
		System.out.println((i + 1) + "回目の抽選");
		System.out.println();
		System.out.println("当選番号は" + lottery.getCurrentWinningNumbers().get(i) + "です！");
	}

}

package main6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		System.out.println();
		System.out.println("【BINGO GAME】");

		BingoCard bingoCard = new BingoCard();
		Player player = new Player();

		System.out.println("Game Start!!");

		for (int p = 0; p < player.getPlayerNumber(); p++) {
			bingoCard.makeBingoCardNumbers();
			player.getMyBingoCardNumbers().add(bingoCard.getBingoCardNumbers());
		}

		for (List<List<Integer>> myBCNumbers : player.getMyBingoCardNumbers()) {
			bingoCard.makeBingo(myBCNumbers, player.getMyBingoCardNumbers().indexOf(myBCNumbers));
		}

		for (int i = 0; i <= bingoCard.getBingoNumbersSize(); i++) {
			System.out.println("↓抽選する↓");
			enter();

			bingoCard.updateBingo(player, i);

			if (bingoCard.isBingoJudge(player).contains(true)) {
				break;
			}
		}

		for (List<List<Integer>> myBCNumbers : player.getMyBingoCardNumbers()) {
			int number = player.getMyBingoCardNumbers().indexOf(myBCNumbers);
			if (bingoCard.isBingoJudge(player).get(number)) {
				System.out.println((number + 1) + "人目のビンゴ！！！");
			}
		}
	}

	//Enterを押すと次の処理が始まる
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

}

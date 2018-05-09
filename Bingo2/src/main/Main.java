package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;

public class Main {

	static Lottery lottery = new Lottery();
	static Scanner scanner = new Scanner(System.in);
	static int playerNumber;
	static int cardWidth;

	public static void main(String[] args) throws IOException {

		List<Player> allPlayers = new ArrayList<Player>();

		System.out.println();
		System.out.println("【BINGO GAME】");

		selectWidth();
		selectMember();
		
		System.out.println("Game Start!!");
		
		allPlayers = IntStream.rangeClosed(1, playerNumber).mapToObj(n -> new Player(n,cardWidth)).collect(Collectors.toList());

		int count = 0;

		do {
			System.out.println("↓抽選する↓");
			enter();

			pickWinningNumber();
			printWinning(count);

			for (Player player : allPlayers)
				player.updateMyBingoCard(lottery, cardWidth, count);

			count++;

		} while (allPlayers.stream().noneMatch(p -> p.isBingo(lottery, cardWidth)));

		allPlayers.stream().filter(p -> p.isBingo(lottery, cardWidth)).map(p -> p.getPlayerName() + "のビンゴ！！！")
				.forEach(System.out::println);
	}

	// ゲーム参加人数を決めるメソッド
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
		if (winningNumber <= BingoCard.numberMax && !lottery.getCurrentWinningNumbers().contains(winningNumber)) {
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
	public static void printWinning(int count) {
		System.out.println();
		System.out.println((count + 1) + "回目の抽選");
		System.out.println();
		System.out.println("当選番号は" + lottery.getCurrentWinningNumbers().get(count) + "です！");
	}

}

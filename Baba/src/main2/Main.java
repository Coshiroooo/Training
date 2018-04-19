package main2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Trump trump = new Trump();
	private static Scanner scanner = new Scanner(System.in);
	private static List<Player> allPlayer = new ArrayList<Player>();
	private static List<Card> deadSpace = new ArrayList<Card>();
	private static int playerNumber;

	public static void main(String[] args) {

		System.out.print("何人でババ抜きしますか？：");
		playerNumber = scanner.nextInt();

		System.out.println("ババ抜きを始めます");

		for (int i = 0; i < playerNumber; i++) {
			allPlayer.add(new Player(i));
		}

		dealCards(); //カードを配る
		printAllHand(); //全員の手札を表示

		allPlayer.forEach(p -> deadSpace.addAll(p.pickupDuplicateCards())); //全員手札で重複しているペアを墓地に捨てる

		printAllHand(); //全員の手札を表示

		do {
			for (Player player : allPlayer) {

				Player pulledPlayer = toPulledPlayer(player); // カードを引かれる人

				if (player.isWinner()) { // すでにあがっている場合、次のプレイヤーへパス
					continue;
				}

				player.addCard(pulledPlayer, pulledPlayer.pulledCard()); //カードを引いて手札に加える

				deadSpace.addAll(player.pickupDuplicateCards()); //数字が重複したペアがあれば墓地に捨てる

				printAllHand(); //全員の手札を表示
				allPlayer.forEach(p -> printWinner(p)); //あがった人を表示

			}
		} while (deadSpace.size() < trump.getAllCards().size() - 1); //場にjokerの1枚のみ残っていたらゲーム終了

		end(); //ゲーム終了

	}

	// カードをプレイヤーに配るメソッド
	public static void dealCards() {

		int allCardsNumber = trump.getAllCards().size();
		int myHandNumber = (int) (allCardsNumber / playerNumber);
		int remainCards = allCardsNumber % playerNumber;

		Collections.shuffle(trump.getAllCards());

		for (Player player : allPlayer) { // 余りは無視して均等に配る

			List<Card> playerMyHand = trump.getAllCards().subList(myHandNumber * allPlayer.indexOf(player),
					myHandNumber * (allPlayer.indexOf(player) + 1));

			player.addCard(playerMyHand);

		}

		if (remainCards != 0) { // 余ったカードを再分配
			int count = 0;
			for (int i = allCardsNumber - 1; i > (allCardsNumber - 1) - remainCards; i--) { // Listの最後尾から順番に分配
				Card remainCard = trump.getAllCards().get(i);
				allPlayer.get(count).addCard(remainCard);

				count++;
			}
		}
		System.out.println();
		System.out.println("カードを配ります");
	}

	// プレイヤーが次にカードをとる人を決めるメソッド
	public static Player toPulledPlayer(Player player) {

		int playerIndex = allPlayer.indexOf(player);
		int pulledPlayerIndex = (playerIndex == playerNumber - 1) ? 0 : playerIndex + 1;
		Player pulledPlayer = allPlayer.get(pulledPlayerIndex);

		if (pulledPlayer.isWinner()) {
			pulledPlayer = toPulledPlayer(pulledPlayer);
		}

		return pulledPlayer;

	}

	// 全員の手札を表示するメソッド
	public static void printAllHand() {
		allPlayer.forEach(p -> p.printMyHand());
		System.out.println();
		System.out.println("---------------------------------------");
	}

	// 勝利状態を表示するメソッド
	public static void printWinner(Player player) {
		if (player.isWinner()) {
			System.out.println(player.getName() + "はあがり！");
		}
	}

	// 負け状態を表示するメソッド
	public static void printLoser(Player player) {
		if (!player.isWinner()) {
			System.out.println(player.getName() + "の負け！！！");
		}
	}

	// ゲーム終了
	public static void end() {
		System.out.println("---------------------------------------");
		System.out.println();
		allPlayer.forEach(p -> printLoser(p));
		System.out.println("ババ抜き終了！！！");
	}

}

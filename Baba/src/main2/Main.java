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

		dealCards();
		printMyHand();

		for (Player player : allPlayer) {

			List<Card> duplicateCards = player.pickupDuplicateCards();
			deadSpace.addAll(duplicateCards);

			printThrowCards(player, duplicateCards);

		}

		printMyHand();

		do {
			for (Player player : allPlayer) {

				Player pulledPlayer = player.pulledPlayer(allPlayer, player, playerNumber); // カードを引かれる人

				if (player.isWinner()) { // すでにあがっている場合、次のプレイヤーへパス
					player = pulledPlayer;
					continue;
				}

				if (pulledPlayer == player) {
					break; // 次にカードを引く人が自分になってしまったら、ゲーム終了
				}

				Card pulledCard = pulledPlayer.pulledCard();
				player.addCard(pulledCard);

				printPullCard(player, pulledPlayer, pulledCard);

				List<Card> duplicateCards = player.pickupDuplicateCards();
				deadSpace.addAll(duplicateCards);

				printThrowCards(player, duplicateCards);
				printMyHand();
				allPlayer.forEach(p -> printWinner(p));

			}
		} while (deadSpace.size() < trump.getAllCards().size() - 1);

		end();

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

	// プレイヤーの手札の状態を表示するメソッド
	public static void printMyHand() {
		for (Player player : allPlayer) {
			System.out.print("【" + player.getName() + "】：残り" + player.getMyHandList().size() + "枚 ");
			System.out.print("[ ");
			player.getMyHandList().forEach(c -> System.out.print(c.toString() + " "));
			System.out.println("]");
		}
		System.out.println();
		System.out.println("---------------------------------------");
	}

	// 捨てるカードを表示するメソッド
	public static void printThrowCards(Player player, List<Card> duplicateCards) {
		for (int i = 0; i < duplicateCards.size() / 2; i++) {
			System.out.println("【" + player.getName() + "】" + duplicateCards.get(i * 2).toString() + ","
					+ duplicateCards.get(i * 2 + 1).toString() + "を捨てました");
		}
		System.out.println();
	}

	// カードのやり取りを表示するメソッド
	public static void printPullCard(Player player, Player pulledPlayer, Card pulledCard) {
		System.out.print("【" + player.getName() + "】");
		System.out.print(pulledPlayer.getName() + "さんから");
		System.out.println(pulledCard.toString() + "を引きました");
	}

	// 勝利したプレイヤーを表示するメソッド
	public static void printWinner(Player player) {
		if (player.isWinner())
			System.out.println(player.getName() + "はあがり！");
	}

	// ゲーム終了
	public static void end() {
		System.out.println();
		for (Player player : allPlayer) {
			if (!player.isWinner())
				System.out.println(player.getName() + "の負け！！！");
		}
		System.out.println("ババ抜き終了！！！");
	}

}

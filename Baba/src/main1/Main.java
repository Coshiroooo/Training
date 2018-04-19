package main1;

import java.util.*;

public class Main {

	private static Trump trump = new Trump();
	private static Scanner scanner = new Scanner(System.in);
	private static List<Player> allPlayer = new ArrayList<Player>();
	private static List<Card> deadCards = new ArrayList<Card>();
	private static int playerNumber;

	public static void main(String[] args) {

		System.out.print("何人でババ抜きしますか？：");
		playerNumber = scanner.nextInt();

		System.out.println("ババ抜きを始めます");

		for (int i = 0; i < playerNumber; i++) allPlayer.add(new Player(i));

		dealCards();
		printMyHand();

		allPlayer.forEach(p -> p.throwCards(deadCards));

		printMyHand();

		do {
			for (Player player : allPlayer) {

				Player nextPlayer = player.nextPlayer(allPlayer, player, playerNumber);

				if (player.isWinner()) { // すでにあがっている場合、次のプレイヤーへパス
					player = nextPlayer;
					continue;
				}

				if (nextPlayer == player) break; //次にカードを引く人が自分になってしまったら、ゲーム終了

				player.pullCard(nextPlayer);
				player.throwCards(deadCards);
				printMyHand();
				allPlayer.forEach(p -> printWinner(p));
			}
		} while (deadCards.size() < trump.getAllCards().size() - 1);

		end();

	}

	// ゲーム終了
	public static void end() {
		System.out.println();
		for (Player player : allPlayer) {
			if (!player.isWinner()) System.out.println(player.getName() + "の負け！！！");
		}
		System.out.println("ババ抜き終了！！！");
	}

	// プレイヤーの勝利判定を出力するメソッド
	public static void printWinner(Player player) {
		if (player.isWinner()) System.out.println(player.getName() + "はあがり！");
	}

	// プレイヤーの手札の状態を表示するメソッド
	public static void printMyHand() {
		for (Player player : allPlayer) {
			System.out.print("【" + player.getName() + "】：残り" + player.getMyHandList().size() + "枚 ");
			System.out.print("[ ");
			player.getMyHandList().forEach(c -> System.out.print(c.printCard() + " "));
			System.out.print("]");
			System.out.println();
		}
		System.out.println();
		System.out.println("---------------------------------------");
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

			playerMyHand.forEach(c -> player.getMyHandList().add(c));

		}

		if (remainCards != 0) { // 余ったカードを再分配
			int count = 0;
			for (int i = allCardsNumber - 1; i > (allCardsNumber - 1) - remainCards; i--) { // Listの最後尾から順番に分配

				List<Card> myHand = allPlayer.get(count).getMyHandList();
				Card remainCard = trump.getAllCards().get(i);

				myHand.add(remainCard);

				count++;
			}
		}
		System.out.println();
		System.out.println("カードを配ります");
	}
}

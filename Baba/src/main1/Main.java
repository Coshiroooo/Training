package main1;

import java.util.*;

public class Main {

	private static Card card = new Card();
	private static List<Player> allPlayer = new ArrayList<Player>();
	private static int playerNumber = 2;

	public static void main(String[] args) {

		System.out.println("ババ抜きを始めます");

		for (int i = 0; i < playerNumber; i++) {
			allPlayer.add(new Player(i));
		}

		dealCards();
		printMyHand();

		allPlayer.forEach(p -> p.throwCards());

		printMyHand();
		
		List<String> remainAllCards = new ArrayList<String>();
		allPlayer.forEach(p -> p.getMyHand().getList().forEach(c -> remainAllCards.add(c)));
		
			for (Player player : allPlayer) {
				int playerIndex = allPlayer.indexOf(player);
				int nextPlayerIndex = (playerIndex == playerNumber - 1) ? 0 : playerIndex + 1;
				player.pullCard(allPlayer.get(nextPlayerIndex));
				player.throwCards();
				printMyHand();
				player.judge();
			}

			for (Player player : allPlayer) {
				int playerIndex = allPlayer.indexOf(player);
				int nextPlayerIndex = (playerIndex == playerNumber - 1) ? 0 : playerIndex + 1;
				player.pullCard(allPlayer.get(nextPlayerIndex));
				player.throwCards();
				printMyHand();
				player.judge();
			}

	}

	// ゲーム終了判定
	public static Boolean isEnd() {
		Boolean isEnd = false;
		List<String> remainAllCards = new ArrayList<String>();
		allPlayer.forEach(p -> p.getMyHand().getList().forEach(c -> remainAllCards.add(c)));
		if (remainAllCards.size() == 1) {
			isEnd = true;
		}
		return isEnd;
	}

	// プレイヤーの手札の状態を表示するメソッド
	public static void printMyHand() {
		for (Player player : allPlayer) {
			System.out.print("【" + player.getName() + "】：残り" + player.getMyHand().getList().size() + "枚");
			System.out.print(player.getMyHand().getList());
			System.out.println();
		}
		System.out.println();
	}

	// カードをプレイヤーに配るメソッド
	public static void dealCards() {

		int allCardsNumber = card.getAllCards().size();
		int myHandNumber = (int) (allCardsNumber / playerNumber);
		int remainCards = allCardsNumber % playerNumber;

		Collections.shuffle(card.getAllCards());

		for (Player player : allPlayer) { // 余りは無視して均等に配る
			List<String> playerMyHand = card.getAllCards().subList(myHandNumber * allPlayer.indexOf(player),
					myHandNumber * (allPlayer.indexOf(player) + 1));
			for (String myCard : playerMyHand) {
				player.getMyHand().getList().add(myCard);
			}
		}
		if (remainCards != 0) { // 余ったカードを再分配
			for (int i = allCardsNumber - 1; i > (allCardsNumber - 1) - remainCards; i--) {
				int count = 0;
				allPlayer.get(count).getMyHand().getList().add(card.getAllCards().get(i));
				count++;
			}
		}
		System.out.println();
		System.out.println("カードを配ります");
	}
}

package main1;

import java.util.*;

public class Player {

	private List<Card> myHandList = new ArrayList<Card>();
	private String name;

	// コンストラクタ
	Player(int i) {
		this.name = "Player" + (i + 1);
	}

	// 重複している手札を捨てるメソッド
	public void throwCards(List<Card> deadCards) {
		
		List<Card> myHand = new ArrayList<Card>();
		myHand = this.myHandList;
		
		for (Card card1 : myHand) {
			for (Card card2 : myHand) {
				if (!card1.equals(card2) && card2.isSameNumber(card1) && card1.getException() != "null" && card2.getException() != "null") {
					deadCards.addAll(Arrays.asList(card1, card2));
					myHand.set(myHand.indexOf(card1), new Card("null"));
					myHand.set(myHand.indexOf(card2), new Card("null"));
					System.out.println("【" + name + "】" + card1.printCard() + "," + card2.printCard() + "を捨てました");
					break;
				}
			}
		}

		Iterator<Card> it = myHand.iterator();
		while (it.hasNext()) {
			Card card = it.next();
			if (card.getException() == "null") it.remove();
		}
		
		this.myHandList = myHand;
		System.out.println();
	}

	// カードをとる人を決めるメソッド
	public Player nextPlayer(List<Player> allPlayer, Player player, int playerNumber) {
		
		int playerIndex = allPlayer.indexOf(player);
		int nextPlayerIndex = (playerIndex == playerNumber - 1) ? 0 : playerIndex + 1;
		Player nextPlayer = allPlayer.get(nextPlayerIndex);

		if (nextPlayer.isWinner()) {
			nextPlayer = nextPlayer.nextPlayer(allPlayer, nextPlayer, playerNumber);
		}

		return nextPlayer;
	}

	// 他のプレイヤーのカードを一枚引いて自分の手札に入れるメソッド
	public void pullCard(Player nextPlayer) {

		Collections.shuffle(nextPlayer.getMyHandList());

		System.out.println();
		System.out.println(
				"【" + name + "】" + nextPlayer.getName() + "さんから" + nextPlayer.getMyHandList().get(0).printCard() + "を引きました");
		this.myHandList.add(nextPlayer.getMyHandList().get(0));
		nextPlayer.getMyHandList().remove(0);
	}

	// 勝利判定
	public Boolean isWinner() {
		return (this.myHandList.size() == 0);
	}

	// ゲッター

	public String getName() {
		return this.name;
	}

	public List<Card> getMyHandList() {
		return this.myHandList;
	}

}

package main2;

import java.util.*;

public class Player {

	private List<Card> myHandList = new ArrayList<Card>();
	private String name;

	// コンストラクタ
	public Player(int i) {
		this.name = "Player" + (i + 1);
	}

	// 数字が重複している手札ペアを探すメソッド
	public List<Card> pickupDuplicateCards() {

		List<Card> deadCards = new ArrayList<Card>();

		for (Card card1 : this.myHandList) {
			for (Card card2 : this.myHandList) {
				if (!card1.equals(card2) && card2.isSameNumber(card1) && card1.getException() != "null"
						&& card2.getException() != "null") {
					deadCards.add(card1);
					deadCards.add(card2);
					this.myHandList.set(this.myHandList.indexOf(card1), new Card("null"));
					this.myHandList.set(this.myHandList.indexOf(card2), new Card("null"));
					break;
				}
			}
		}
		deleteNullCard(this.myHandList);
		return deadCards;
	}

	// Listからnull要素のあるcardを取り除くメソッド
	public void deleteNullCard(List<Card> list) {
		Iterator<Card> it = list.iterator();
		while (it.hasNext()) {
			Card card = it.next();
			if (card.getException() == "null") {
				it.remove();
			}
		}
	}

	// カードをとる人を決めるメソッド
	public Player pulledPlayer(List<Player> allPlayer, Player player, int playerNumber) {

		int playerIndex = allPlayer.indexOf(player);
		int pulledPlayerIndex = (playerIndex == playerNumber - 1) ? 0 : playerIndex + 1;
		Player pulledPlayer = allPlayer.get(pulledPlayerIndex);

		if (pulledPlayer.isWinner()) {
			pulledPlayer = pulledPlayer.pulledPlayer(allPlayer, pulledPlayer, playerNumber);
		}

		return pulledPlayer;

	}

	// カードを引かれ、渡す時のメソッド
	public Card pulledCard() {
		Collections.shuffle(myHandList);
		Card pulledCard = this.myHandList.get(0);
		this.myHandList.remove(0);
		return pulledCard;
	}

	// 渡されたカードを自分の手札に入れるメソッド
	public void addCard(Card card) {
		myHandList.add(card);
	}

	// 渡されたカードのListを自分の手札に入れるメソッド（オーバーロード）
	public void addCard(List<Card> cards) {
		myHandList.addAll(cards);
	}

	// 勝利判定
	public Boolean isWinner() {
		return this.myHandList.isEmpty();
	}

	// ゲッター

	public String getName() {
		return this.name;
	}

	public List<Card> getMyHandList() {
		return this.myHandList;
	}

}

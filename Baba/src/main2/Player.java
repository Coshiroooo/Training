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
		printThrowCards(deadCards);

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

	// カードを引かれ、渡す時のメソッド
	public Card pulledCard() {
		Collections.shuffle(myHandList);
		Card pulledCard = this.myHandList.get(0);
		this.myHandList.remove(0);
		return pulledCard;
	}

	// もらったカードを自分の手札に入れるメソッド
	public void addCard(Card card) {
		myHandList.add(card);
	}

	// とったカードを自分の手札に入れるメソッド（オーバーロード）
	public void addCard(Player pulledPlayer, Card pulledCard) {
		myHandList.add(pulledCard);
		printPullCard(pulledPlayer, pulledCard);
	}

	// 最初に配られる複数のカードを自分の手札に入れるメソッド（オーバーロード）
	public void addCard(List<Card> cards) {
		myHandList.addAll(cards);
	}

	// 勝利判定
	public Boolean isWinner() {
		return this.myHandList.isEmpty();
	}

	// プレイヤーの手札の状態を表示するメソッド
	public void printMyHand() {
		System.out.print("【" + name + "】：残り" + myHandList.size() + "枚 ");
		System.out.print("[ ");
		myHandList.forEach(c -> System.out.print(c.toString() + " "));
		System.out.println("]");
	}

	// 捨てるカードを表示するメソッド
	public void printThrowCards(List<Card> duplicateCards) {
		for (int i = 0; i < duplicateCards.size(); i += 2) {
			System.out.print("【" + name + "】");
			System.out
					.println(duplicateCards.get(i).toString() + "," + duplicateCards.get(i + 1).toString() + "を捨てました");
		}
		System.out.println();
	}

	// 誰からどのカードをとったかを表示するメソッド
	public void printPullCard(Player pulledPlayer, Card pulledCard) {
		System.out.println("---------------------------------------");
		System.out.println();
		System.out.print("【" + name + "】");
		System.out.print(pulledPlayer.getName() + "さんから");
		System.out.println(pulledCard.toString() + "を引きました");
	}

	// ゲッター

	public String getName() {
		return this.name;
	}

}

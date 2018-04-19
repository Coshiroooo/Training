package main2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trump {

	private List<Card> allCards = new ArrayList<Card>();

	// コンストラクタ
	public Trump() {
		List<String> markList = Arrays.asList("♡", "♤", "♧", "♢");
		List<Integer> numberList = new ArrayList<Integer>();
		for (int i = 1; i <= 13; i++)
			numberList.add(i);

		markList.forEach(m -> numberList.forEach(n -> allCards.add(new Card(m, n))));
		allCards.add(new Card("joker"));
	}

	// ゲッター

	public List<Card> getAllCards() {
		return this.allCards;
	}

}

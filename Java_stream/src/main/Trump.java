package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Trump {

	private List<Card> allCards = new ArrayList<Card>();

	// コンストラクタ
	public Trump() {
		List<String> markList = Arrays.asList("♡", "♤", "♧", "♢");
		List<Integer> numberList = new ArrayList<Integer>();
		
		IntStream.rangeClosed(1, 13).forEach(numberList::add);

		markList.forEach(m -> numberList.forEach(n -> allCards.add(new Card(m, n))));
		allCards.add(new Card("joker"));

		Collections.shuffle(allCards);
	}

	// ゲッター

	public List<Card> getAllCards() {
		return this.allCards;
	}

}

package main2;

import java.util.ArrayList;
import java.util.List;

public class Card {
	
	private List<String> allCards = new ArrayList<String>();
	private List<List<String>> sameNumbersList = new ArrayList<List<String>>();
	
	//コンストラクタ
	Card(){
		for(int i = 1; i <= 13; i++) {
			if(i < 10) {
				allCards.add("♡0" + i);
				allCards.add("♤0" + i);
				allCards.add("♧0" + i);
				allCards.add("♢0" + i);
			}else {
				allCards.add("♡" + i);
				allCards.add("♤" + i);
				allCards.add("♧" + i);
				allCards.add("♢" + i);
			}
		}
		for(int i = 0; i < allCards.size() / 4; i++) {
			sameNumbersList.add(allCards.subList(i*4, (i + 1)*4));
		}
		allCards.add("joker");
	}
	
	//ゲッター
	
	public List<String> getAllCards(){
		return this.allCards;
	}
	
	public List<List<String>> getSameNumbersList(){
		return this.sameNumbersList;
	}

}

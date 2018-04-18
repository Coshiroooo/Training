package main1;

import java.util.*;

public class Card {
	
	private List<String> allCards = new ArrayList<String>();
	
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
		allCards.add("joker");
	}
	
	public List<String> getAllCards(){
		return this.allCards;
	}
}

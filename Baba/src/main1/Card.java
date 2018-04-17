package main1;

import java.util.*;

public class Card {
	
	private List<String> allCards = new ArrayList<String>();
	private List<String> heartCards = new ArrayList<String>();
	private List<String> spadeCards = new ArrayList<String>();
	private List<String> cloverCards = new ArrayList<String>();
	private List<String> diamondCards = new ArrayList<String>();
	private String joker = "joker";
	
	Card(){
		for(int i = 1; i <= 13; i++) {
			if(i < 10) {
				heartCards.add("♡0" + i);
				spadeCards.add("♤0" + i);
				cloverCards.add("♧0" + i);
				diamondCards.add("♢0" + i);
			}else {
				heartCards.add("♡" + i);
				spadeCards.add("♤" + i);
				cloverCards.add("♧" + i);
				diamondCards.add("♢" + i);
			}
		}
		heartCards.forEach(h -> allCards.add(h));
		spadeCards.forEach(s -> allCards.add(s));
		cloverCards.forEach(c -> allCards.add(c));
		diamondCards.forEach(d -> allCards.add(d));
		allCards.add(joker);
	}
	
	public List<String> getAllCards(){
		return this.allCards;
	}
}

package main;

import java.util.*;

public class Bingo {

	private int squareLength;
	private List<Integer> winningNumbers;
	
	Bingo(int squareLength){ //コンストラクタ
		
		this.squareLength = squareLength;
		
		List<Integer> winningNumbers = new ArrayList<Integer>();
		
		for(int i = 1; i < 101; i++){
			winningNumbers.add(i);
		}
		Collections.shuffle(winningNumbers);
		this.winningNumbers = winningNumbers;
	}
	
	public int comeNumber() {
		return this.winningNumbers.get(0);
//		winningNumbers.remove(0);
	}

}

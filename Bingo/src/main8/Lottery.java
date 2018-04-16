package main8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lottery {
	
	private List<Integer> currentWinningNumbers = new ArrayList<Integer>(); // 既出の抽選番号のList
	
	//ランダムで数字を排出する
	public int pickNumber() {
		Random random = new Random();
		int winningNumber = random.nextInt(999) + 1;
		return winningNumber;
	}
	
	//ゲッター
	
	public List<Integer> getCurrentWinningNumbers(){
		return this.currentWinningNumbers;
	}

}

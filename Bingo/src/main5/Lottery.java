package main5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lottery { //当選番号を吐くだけのクラス　どこのクラスでも使えるように
	
	private List<Integer> currentWinningNumbers = new ArrayList<Integer>(); // 既出の抽選番号のList
	
	public int pickNumber() {
		Random random = new Random();
		return random.nextInt(999) + 1;
	}
	
	public List<Integer> getCurrentWinningNumbers(){
		return this.currentWinningNumbers;
	}
}

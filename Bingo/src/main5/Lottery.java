package main5;

import java.util.Random;

public class Lottery { //当選番号を吐くだけのクラス　どこのクラスでも使えるように
	
	public int pickNumber() {
		Random random = new Random();
		return random.nextInt(1000) + 1;
	}
}

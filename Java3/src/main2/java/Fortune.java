package main2.java;
import java.util.Random;

public class Fortune {
	public static String tellFortune() {
		Random random = new Random();
		int fortuneNum = random.nextInt(11);
		String[] fortuneBox = {"大吉","中吉","中吉","小吉","小吉","小吉","吉","吉","吉","凶","凶","大凶"};
//		int fortuneNum = random.nextInt(5);
//		String[] fortuneBox = {"大吉","中吉","小吉","吉","凶"."大凶"};
		return fortuneBox[fortuneNum];
	}
}

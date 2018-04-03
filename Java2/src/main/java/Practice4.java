package main.java;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

//適当に作った整数型の配列の中から、全ての2つの組み合わせの和の配列を作り、かつ素数である配列を作成する
public class Practice4 {
	public static void main(String[] args) {
		int[] numbers = {0,1,2,4,7,13,16,17,19,24,25,27,31,32,33,38,45,47,50};
		ArrayList<Integer>twoSums = new ArrayList<Integer>();
		ArrayList<Integer>primeNums = new ArrayList<Integer>();
		
		for(int i = 0; i < numbers.length - 1; i++) { //2つの組み合わせの和の配列を作成＝＞ twoSums
			for(int j = i + 1; j < numbers.length; j++) {
				twoSums.add(numbers[i] + numbers[j]);
			}
		}
		
		int twoSumsMax = twoSums.get(0);
		
		for(int k: twoSums) { //twoSumsの最大値を計算
			if(k >= twoSumsMax) {
				twoSumsMax = k;
			}
		}
		
		boolean result;
		
		for(int twoSum: twoSums) { //towSumsをそれぞれ素数判定する
			if(twoSum < 1) { //何もない
			}else if(twoSum == 2) {
				primeNums.add(twoSum);
			}else if(twoSum > 2){
				result = true; //結果が素数だと仮定
				for(int l = 2; l < twoSum; l++) {
					if(twoSum % l == 0) {
						result = false;
						break;
					}
				}
				if(result) {
					primeNums.add(twoSum);
				}
			}
		}
		
		ArrayList<Integer>primeNumsA = new ArrayList<Integer>(new HashSet<>(primeNums));
		Collections.sort(primeNumsA);
		
		//System.out.println("配列；" + numbers + "の各数字をそれぞれ2つずつ足し合わせる");
		System.out.println("配列；" + Arrays.toString(numbers) + "の各数字をそれぞれ2つずつ足し合わせる");
		System.out.println("そのうち、素数であるものの配列は" + primeNumsA + "です");
	}
 }

package main;

import java.util.*;

public class Main {
	
	static int squareLength = 5;
	
	public static void main(String[] args) {
		
		System.out.println("        〜BINGO GAME〜");
		
		makeBingo();
		
	}
	
	public static void makeBingo() {
		
		List<List<Integer>> matrixList = new ArrayList<List<Integer>>(); //マス目List
		List<Integer> sideList = new ArrayList<Integer>(); //マス目の横列のList
		List<List<Integer>> verticalList = new ArrayList<List<Integer>>();
		Random random = new Random();
		
		String numberBox = "  ";
		
//		for(int j = 0; j < squareLength; j++) { //Listに1~100の数字をランダムに入れていく
//			
//			for(int i = 0; i < squareLength; i++) {
//				int randomNumber = random.nextInt(100) + 1;
//				sideList.add(randomNumber); 
//			}
//			matrixList.add(sideList);
//		}
		for(int i = 0; i < squareLength; i++) {
			for(int j = 0; j < squareLength; j++) {
				int randomNumber = random.nextInt(100) + 1;
				matrixList.get(i).set(j,randomNumber);
			}
			matrixList.set(i, matrixList.get(i));
		}
		
		System.out.println(matrixList.get(0).get(0));
		
		for(int l = 0; l < squareLength; l++) { //マス目を作る
			for(int i = 0; i < squareLength; i++) {
				System.out.print("______");
			}
			System.out.println();
			for(int j = 0; j < squareLength; j++) {
				System.out.print("|  ");
				System.out.print(matrixList.get(l).get(j));
				System.out.print(" ");
			}
			System.out.print("|");
			System.out.println();
		}
		for(int i = 0; i < squareLength; i++) {
			System.out.print("______");
		}
	}
	
}

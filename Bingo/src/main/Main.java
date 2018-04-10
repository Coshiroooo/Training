package main;

import java.util.*;

public class Main {
	
	static int squareLength = 5;
	
	public static void main(String[] args) {
		
		Bingo bingo = new Bingo(squareLength); //Bingoクラスをインスタンス化
		
		System.out.println("        〜BINGO GAME〜");
		System.out.println();
		
		System.out.println("　　あなたのビンゴカードはこれです！");
		makeBingo();
		
		System.out.println();
		System.out.println("     抽選番号は " + bingo.comeNumber() + " です！！");
		
	}
	
	public static void makeBingo() { //Bingoのマス目を作る
		
		List<Integer> bingoCardList = new ArrayList<Integer>(); //マス目に入る数字のList
		Random random = new Random();
		
		String numberBox = "  ";
		
		for(int i =1; i < 101; i++){ //1~100の数字を格納し、shuffleでランダムに並び替えて順番に取り出させる
			bingoCardList.add(i);
		}
		Collections.shuffle(bingoCardList);
		
//		for(int i = squareLength; i < 101; i++) { //マス目にいれない数字を取り除く
//			bingoCardList.remove(i);
//		}
		
		for(int l = 0; l < squareLength; l++) { //マス目を作る
			for(int i = 0; i < squareLength; i++) {
				System.out.print("______");
			}
			System.out.println();
			for(int j = 1; j < squareLength + 1; j++) {
				if(9 < bingoCardList.get((j + squareLength*l) - 1)  && bingoCardList.get((j + squareLength*l) - 1) < 100) {
					System.out.print("|  ");
					System.out.print(bingoCardList.get((j + squareLength*l) - 1)); //1列埋めたら改行して数字を埋めていく
					System.out.print(" ");
				}else if(bingoCardList.get((j + squareLength*l) - 1) < 10) {
					System.out.print("|  " + 0); //マス目がずれないように、1桁の数字を2桁にする
					System.out.print(bingoCardList.get((j + squareLength*l) - 1)); //1列埋めたら改行して数字を埋めていく
					System.out.print(" ");
				}else if(bingoCardList.get((j + squareLength*l) - 1) == 100){ //100の時は空白を一つ使う
					System.out.print("| " + 100 + " ");
				}
			}
			System.out.print("|");
			System.out.println();
		}
		for(int i = 0; i < squareLength; i++) {
			System.out.print("______");
		}
	}
	
}

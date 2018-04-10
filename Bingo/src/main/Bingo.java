package main;

import java.util.*;

public class Bingo {

	private int squareLength = 5;
	private List<Integer> winningNumbers; //当選番号のList
//	private int winningNumber;
	private List<Integer> bingoCardList; //ビンゴカードに書かれている番号
//	private int numberBox;
	
	Bingo(){ //コンストラクタ
		
		List<Integer> winningNumbers = new ArrayList<Integer>();
		
		for(int i = 1; i < 101; i++){
			winningNumbers.add(i);
		}
		Collections.shuffle(winningNumbers);
		this.winningNumbers = winningNumbers;
	}
	
	public void arrangeNumber(List<Integer> bingoCardNumbers,int numberBox) {
		
	}
	
	public int comeNumber() { //**************************当選番号を返すメソッド
		return this.winningNumbers.get(0);
//		winningNumbers.remove(0);
	}
	
public void makeBingo() { //*******************************Bingoのマス目を作る
		
		List<Integer> bingoCardList = new ArrayList<Integer>(); //マス目に入る数字のList
		int numberBox;
		
		for(int i =1; i < 101; i++){ //1~100の数字を格納し、shuffleでランダムに並び替えて順番に取り出させる
			bingoCardList.add(i);
		}
		Collections.shuffle(bingoCardList);
		this.bingoCardList = bingoCardList;
		
//		for(int i = squareLength; i < 101; i++) { //マス目にいれない数字を取り除く
//			bingoCardList.remove(i);
//			bingoCardList = bingoCardList;
//		}
		
		for(int l = 0; l < squareLength; l++) { //マス目を作る
			for(int i = 0; i < squareLength; i++) {
				System.out.print("______");
			}
			System.out.println();
			for(int j = 1; j < squareLength + 1; j++) {
				
				numberBox = bingoCardList.get((j + squareLength*l) - 1);
//				this.numberBox = numberBox;
				
				if(9 < numberBox  && numberBox < 100) {
					System.out.print("|  ");
					System.out.print(numberBox); //1列埋めたら改行して数字を埋めていく
					System.out.print(" ");
				}else if(numberBox < 10) {
					System.out.print("|  " + 0); //マス目がずれないように、1桁の数字を2桁にする
					System.out.print(numberBox); //1列埋めたら改行して数字を埋めていく
					System.out.print(" ");
				}else if(numberBox == 100){ //100の時は空白を一つ使う
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

	public void fixBingo() { //********************空白があるビンゴカードを作るメソッド
		
//		for(int i = squareLength; i < 101; i++) { //マス目にいれない数字を取り除く
//			bingoCardList.remove(i);
//		}
		
		for(int l = 0; l < squareLength; l++) { //マス目を作る
			for(int i = 0; i < squareLength; i++) {
				System.out.print("______");
			}
			System.out.println();
			for(int j = 1; j < squareLength + 1; j++) {
				
				int numberBox = bingoCardList.get((j + squareLength*l) - 1);
				
				if(numberBox == winningNumbers.get(0)) {
					System.out.print("|     ");
				}else {
					if(9 < numberBox  && numberBox < 100) {
						System.out.print("|  ");
						System.out.print(numberBox); //1列埋めたら改行して数字を埋めていく
						System.out.print(" ");
					}else if(numberBox < 10) {
						System.out.print("|  " + 0); //マス目がずれないように、1桁の数字を2桁にする
						System.out.print(numberBox); //1列埋めたら改行して数字を埋めていく
						System.out.print(" ");
					}else if(numberBox == 100){ //100の時は空白を一つ使う
						System.out.print("| " + 100 + " ");
					}
				}
				
//				if(9 < numberBox  && numberBox < 100) {
//					System.out.print("|  ");
//					System.out.print(numberBox); //1列埋めたら改行して数字を埋めていく
//					System.out.print(" ");
//				}else if(numberBox < 10) {
//					System.out.print("|  "); //マス目がずれないように、1桁の数字を2桁にする
//					System.out.print(0 + numberBox); //1列埋めたら改行して数字を埋めていく
//					System.out.print(" ");
//				}else if(numberBox == 100){ //100の時は空白を一つ使う
//					System.out.print("| " + 100 + " ");
//				}
			}
			System.out.print("|");
			System.out.println();
		}
		for(int i = 0; i < squareLength; i++) {
			System.out.print("______");
		}
	}
	
//	public String hit() {
//		for(int k = 0; k < squareLength; k++) { //カード内の数字と当選番号が一致するかどうか　検索対象はマス目分のindex
//			if(bingoCardList.get(k) == this.winningNumbers.get(0)) { //もし当選番号とビンゴマスListのk番目の数字が一致していたら
//				
//			}
//		}
//	}

}

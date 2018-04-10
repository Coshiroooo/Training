package main;

import java.util.*;

public class Bingo {

	private int squareLength = 5;
	private List<Integer> winningNumbers = new ArrayList<Integer>(); //当選番号のList
	private List<Integer> currentWinningNumbers = new ArrayList<Integer>(); //現在出ている当選番号のリスト
	private List<Integer> bingoCardList = new ArrayList<Integer>(); //ビンゴカードに書かれている番号
	private int winningNumbersIndex = 0;
	
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
		return this.winningNumbers.get(winningNumbersIndex);
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
		
//		List<Integer> currentWinningNumbers = new ArrayList<Integer>();
		
		int winningNumber = 0;
		
		for(int l = 0; l < squareLength; l++) { //マス目を作る
			for(int i = 0; i < squareLength; i++) {
				System.out.print("______");
			}
			System.out.println();
			for(int j = 1; j < squareLength + 1; j++) {
				
				int numberBox = bingoCardList.get((j + squareLength*l) - 1);
		
				if(numberBox == winningNumbers.get(winningNumbersIndex)) { //当選番号と合致していたら空白を返す
					System.out.print("|     ");
					winningNumber = numberBox;
				}else {
					
					Boolean isBeforeNumber = this.currentWinningNumbers.contains(numberBox); //これまでの当選番号に該当するか判断する
					
					if(isBeforeNumber) { //これまでの当選番号が出てきたら空白で出力
						System.out.print("|     ");
					}else{
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
				}
			}
			System.out.print("|");
			System.out.println();
		}
		for(int i = 0; i < squareLength; i++) {
			System.out.print("______");
		}
		System.out.println();
		System.out.println();
		if(winningNumber == winningNumbers.get(winningNumbersIndex)) {
			System.out.println("　　　　　　　当たり！");
		}else {
			System.out.println("　　　　　　　残念！");
		}
		this.currentWinningNumbers.add(winningNumbers.get(winningNumbersIndex));
		winningNumbersIndex++;
	}
	
	public int getSquareLength() {
		return this.squareLength;
	}
	
}

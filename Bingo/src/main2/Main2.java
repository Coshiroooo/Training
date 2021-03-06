package main2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
	
	static int squareLength = 5;
	static List<Integer> bingoCardList = new ArrayList<Integer>();
	static List<List<Integer>> matrixList = new ArrayList<List<Integer>>();
	static List<Integer> winningNumbers = new ArrayList<Integer>();
	static List<Integer> currentWinningNumbers = new ArrayList<Integer>();
 	static List<Integer> squareList = new ArrayList<Integer>();
 	static List<Integer> squareSideList = new ArrayList<Integer>();
	static int winningNumbersIndex = 0;
	
	Main2(){
		for(int i = 1; i < 101; i++) {
			winningNumbers.add(i);
			bingoCardList.add(i);
		}
		Collections.shuffle(winningNumbers);
		Collections.shuffle(bingoCardList);
		winningNumbers.add(0,-1); //先頭に0を挿入。初回の繰り返し時に当選処理が起きないようにするため
		matrixList = makeNumberList();
		
	}
	
	public static void main(String[] args)throws IOException{
		
		Main2 main = new Main2();
		
		System.out.println();
		System.out.println("【BINGO GAME】");
		System.out.println();
		System.out.println("あなたのカードです");
		
		makeBingo();
		
		for(int i = 0; i < 100; i++) {
			System.out.println("　　　　  ↓抽選する↓");
			enter();
			System.out.println();
			System.out.println("         " + (i + 1) +"回目の抽選" );
			System.out.println();
			System.out.println("当選番号は" + winningNumbers.get(winningNumbersIndex) + "です！");
			makeBingo();
			System.out.println();
			
			if(bingoJudge()) {break;}
		}
		System.out.println();
		System.out.println("        ビンゴ！！！");
	}
	
	public static void makeBingo() { //ビンゴを作る
		
		int winningNumber = 0;
		
		for(int l = 0; l < squareLength; l++) { //マス目を作る
			for(int i = 0; i < squareLength; i++) {
				System.out.print("______");
			}
			System.out.println();
			for(int j = 1; j < squareLength + 1; j++) {
		
				int numberBox = matrixList.get(l).get(j-1);
				
				if(numberBox == winningNumbers.get(winningNumbersIndex)) {
					System.out.print("|     ");
					winningNumber = numberBox;
				}else{
					if(currentWinningNumbers.contains(numberBox)) {
						System.out.print("|     ");
					}else {
						if(9 < numberBox  && numberBox < 100) {
							System.out.print("|  " + numberBox + " ");
						}else if(numberBox < 10) {
							System.out.print("|  " + 0 + numberBox + " "); //マス目がずれないように、1桁の数字を2桁にする
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
		String judge = (winningNumbers.get(winningNumbersIndex) == -1) ? "" : (winningNumber == winningNumbers.get(winningNumbersIndex)) ? "当たり！" : "残念！";
		System.out.println(judge);
		currentWinningNumbers.add(winningNumbers.get(winningNumbersIndex));
		winningNumbersIndex++;
	}
	
	public static List<List<Integer>> makeNumberList(){ //bingoCardListをsquareLength行分に分けたリストを作り、二次元リストに追加
		for(int i = 0; i < squareLength; i++) {
		matrixList.add(new ArrayList<Integer>(bingoCardList.subList(i*squareLength, (i + 1)*squareLength)));
		}
		return matrixList;
	}
	
	public static boolean bingoJudge() { //ビンゴを判定する機能
		Boolean isBingoJudge = false;
		
		for(int i = 0; i < squareLength; i++) {
			List<Integer> exactListVer = new ArrayList<Integer>();
			List<Integer> exactListSide = new ArrayList<Integer>();
			List<Integer> exactListSlant1 = new ArrayList<Integer>();
			List<Integer> exactListSlant2 = new ArrayList<Integer>();
			
			for(int j = 0; j < squareLength; j++) {
				exactListVer.add(matrixList.get(j).get(i));
				exactListSide.add(matrixList.get(i).get(j));
				exactListSlant1.add(matrixList.get(j).get(j));
				exactListSlant2.add(matrixList.get(j).get(squareLength - (j+1)));	
			}
			int countVer = 0;
			int countSide = 0;
			int countSlant1 = 0;
			int countSlant2 = 0;
			for(int c : currentWinningNumbers) {
				if(exactListVer.contains(c)) {countVer++;}
				if(exactListSide.contains(c)) {countSide++;}
				if(exactListSlant1.contains(c)) {countSlant1++;}
				if(exactListSlant2.contains(c)) {countSlant2++;}
			}	
			if(countVer == squareLength || countSide == squareLength || countSlant1 == squareLength || countSlant2 == squareLength) {
				isBingoJudge = true;
				break;
			}
		}
		return isBingoJudge;
	}
	
	public static void enter() throws IOException{ //Enter押すと次の処理が始まる
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append(br);
		String enter = sb.toString();
		System.in.read();
		switch(enter){
			case "\r\n":
				break;
		}
	}
}

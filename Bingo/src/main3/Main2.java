package main3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
	
	static int squareLength = 5;
	static List<Integer> bingoNumbers = new ArrayList<Integer>();
	static List<List<Integer>> matrixList = new ArrayList<List<Integer>>();
	static List<Integer> winningNumbers = new ArrayList<Integer>();
	static List<Integer> currentWinningNumbers = new ArrayList<Integer>();
	static int winningNumbersIndex = 0;
	
	Main2(){
		for(int i = 1; i < 101; i++) {
			winningNumbers.add(i);
			bingoNumbers.add(i);
		}
		Collections.shuffle(winningNumbers);
		Collections.shuffle(bingoNumbers);
		winningNumbers.add(0,-1); //先頭に0を挿入。初回の繰り返し時に当選処理が起きないようにするため
		matrixList = makeNumberList();
	}
	
	public static void main(String[] args)throws IOException{
		
		Main2 main = new Main2();

		System.out.println("\r\n" + "【BINGO GAME】");
		System.out.println("\r\n" + "あなたのカードです");
		
		makeBingo();
		
		for(int i = 0; i < bingoNumbers.size(); i++) {
			System.out.println("↓抽選する↓");
			enter();
			System.out.println("\r\n" + (i + 1) +"回目の抽選" + "\r\n" );
			System.out.println("当選番号は" + winningNumbers.get(winningNumbersIndex) + "です！");
			makeBingo();
			
			if(bingoJudge()) {break;}

		}
		System.out.println("\r\n" + "ビンゴ！！！");
	}
	
	public static void makeBingo() { //ビンゴを作る
		
		int winningNumber = 0;
		
		for(List<Integer> matrix : matrixList) {
			for(List<Integer> n : matrixList) {System.out.print("______");}
			System.out.println();
			for(int matrixnumber : matrix) { //取り出したリストを1つずつ処理して繰り返し
				int numberBox = matrixnumber;
				if(numberBox == winningNumbers.get(winningNumbersIndex)) {
					System.out.print("|     ");
					winningNumber = numberBox;
				}else {
					if(currentWinningNumbers.contains(numberBox)) {
						System.out.print("|     ");
					}else {
						System.out.printf("| %3d ", numberBox);
					}
				}
			}
			System.out.print("|" + "\r\n");
		}

		for(List<Integer> n : matrixList) {System.out.print("______");}
		String judge = (winningNumbers.get(winningNumbersIndex) == -1) ? "" : (winningNumber == winningNumbers.get(winningNumbersIndex)) ? "当たり！" : "残念！";
		System.out.println("\r\n" + "\r\n" + judge + "\r\n");
		currentWinningNumbers.add(winningNumbers.get(winningNumbersIndex));
		winningNumbersIndex++;
	}
	
	public static List<List<Integer>> makeNumberList(){ //bingoCardListをsquareLength行分に分けたリストを作り、二次元リストに追加
		for(int i = 0; i < squareLength; i++) {
		matrixList.add(new ArrayList<Integer>(bingoNumbers.subList(i*squareLength, (i + 1)*squareLength)));
		}
		return matrixList;
	}
	
	public static boolean bingoJudge() { //ビンゴを判定する機能
		
		Boolean isBingoJudge = false;
		
		for(List<Integer> matrix1 : matrixList) { //ビンゴ列になりうるリストを作り、その中に既出の当選番号がカードの幅数と同じだけあるかどうかを繰り返しで検証する
			List<Integer> exactListSide = new ArrayList<Integer>(); //横
			List<Integer> exactListVer = new ArrayList<Integer>(); //縦
			List<Integer> exactListSlant1 = new ArrayList<Integer>(); //斜め1
			List<Integer> exactListSlant2 = new ArrayList<Integer>(); //斜め2
			
			int count = 0;
			
			exactListSide = matrix1;
			for(List<Integer> matrix2 : matrixList) {
				exactListVer.add(matrix2.get(matrixList.indexOf(matrix1)));
				exactListSlant1.add(matrix2.get(count));
				exactListSlant2.add(matrix2.get(squareLength - (count + 1)));
			}
			
			count++;
			
			int countSide = 0; int countVer = 0; int countSlant1 = 0; int countSlant2 = 0;
			
			for(int c : currentWinningNumbers) { //既出の当選番号で各列で何個出揃っているか
				if(exactListSide.contains(c)) {countSide++;}
				if(exactListVer.contains(c)) {countVer++;}
				if(exactListSlant1.contains(c)) {countSlant1++;}
				if(exactListSlant2.contains(c)) {countSlant2++;}
			}
			if(countSide == squareLength || countVer == squareLength || countSlant1 == squareLength || countSlant2 == squareLength) { //マス目の幅と同じ数あったらビンゴ
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

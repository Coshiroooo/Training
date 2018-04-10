package main;

import java.util.*;

public class Main {
	
//	static int squareLength = 5;
	
	public static void main(String[] args) {
		
		Bingo bingo = new Bingo(); //Bingoクラスをインスタンス化
		
		System.out.println("        〜BINGO GAME〜");
		System.out.println();
		
		System.out.println("　　あなたのビンゴカードはこれです！");
		bingo.makeBingo();
		System.out.println();
		System.out.println("==============================");
		
		System.out.println();
		System.out.println("     当選番号は " + bingo.comeNumber() + " です！！");
		System.out.println();
		
		bingo.fixBingo();
		
		
	}
	
}

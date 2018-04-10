package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		Bingo bingo = new Bingo(); //Bingoクラスをインスタンス化
		
		System.out.println("        〜BINGO GAME〜");
		System.out.println();
		
		System.out.println("　　あなたのビンゴカードはこれです！");
		bingo.makeBingo();
		System.out.println();
		System.out.println("==============================");
		
		System.out.println("        Game Start");
		System.out.println();
		
		for(int i = 0; i < 100; i++) {
			System.out.println();
			System.out.println("　　　　  ↓抽選する↓");
			enter();
			System.out.println();
			System.out.println("         " + (i + 1) +"回目の抽選" );
			System.out.println("     当選番号は " + bingo.comeNumber() + " です！！");
			System.out.println();
			
			bingo.fixBingo();
		}
		
		
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

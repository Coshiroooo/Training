package main5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main5 {
	
	static Scanner scanner = new Scanner(System.in);
	static int cardWidth;

	public static void main(String[] args) throws IOException {

		System.out.println();
		System.out.println("【BINGO GAME】");
		
		BingoCard bingoCard = new BingoCard();
		
		bingoCard.makeBingoCardNumbers();

		bingoCard.makeBingo();

		for (int i = 0; i <= bingoCard.getBingoNumbers().size(); i++) {
			System.out.println("↓抽選する↓");
			enter();

			bingoCard.updateBingo(i);

			if (bingoCard.isBingoJudge()) {
				break;
			}
		}
		System.out.println();
		System.out.println("ビンゴ！！！");
		
	}

	public static void enter() throws IOException { // +++++Enter押すと次の処理が始まる+++++
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append(br);
		String enter = sb.toString();
		System.in.read();
		switch (enter) {
		case "\r\n":
			break;
		}
	}

}

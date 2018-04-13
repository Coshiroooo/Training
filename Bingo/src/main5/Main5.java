package main5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main5 {

	public static void main(String[] args) throws IOException {
		
		BingoCard bingoCard = new BingoCard(12);
		
		bingoCard.makeBingoCardNumbers();

		System.out.println();
		System.out.println("【BINGO GAME】");
		System.out.println();
		System.out.println("あなたのカードです");

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

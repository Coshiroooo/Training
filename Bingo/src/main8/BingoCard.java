package main8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BingoCard {

	private Scanner scanner = new Scanner(System.in);
	private List<List<Integer>> bingoCardNumbers = new ArrayList<List<Integer>>(); // カードに配置する数字のList

	// ビンゴカード上に配置した数字のリスト
	public List<List<Integer>> makeBingoCardNumbers(int cardWidth) {
		List<Integer> bingoNumbers = new ArrayList<Integer>();
		List<List<Integer>> bingoCardNumbers = new ArrayList<List<Integer>>();

		if (cardWidth <= 10) { // 10マス以下の時は上限100までの数字が入る
			for (int i = 1; i <= 100; i++) {
				bingoNumbers.add(i);
			}
		} else { // 11マス以上は、マス数と同じ数だけの数字が入る
			for (int i = 1; i <= cardWidth * cardWidth; i++) {
				bingoNumbers.add(i);
			}
		}
		
		Collections.shuffle(bingoNumbers); // シャッフルして順番にカードに配置していく

		for (int i = 0; i < cardWidth; i++) { // bingoNumbersの要素を前からcardWidth分に切り分けたListを二次元リストに格納していく
			bingoCardNumbers.add(new ArrayList<Integer>(bingoNumbers.subList(i * cardWidth, (i + 1) * cardWidth)));
		}
		return bingoCardNumbers;
	}

	// 抽選状況に合わせたビンゴカードを作る
	public void updateBingo(List<List<Integer>> myBingoCardNumbers,Lottery lottery,int cardWidth,int count) {

		int winningNumber = 0;

		for (List<Integer> bcNumbers : myBingoCardNumbers) {
			for (int i = 0; i < cardWidth; i++) {
				System.out.print("______");
			}
			System.out.println();
			for (int bcNumber : bcNumbers) { // 取り出したリストを1つずつ処理して繰り返し
				if (bcNumber == lottery.getCurrentWinningNumbers().get(count)) {
					System.out.print("|     ");
					winningNumber = bcNumber;
				} else {
					if (lottery.getCurrentWinningNumbers().contains(bcNumber)) {
						System.out.print("|     ");
					} else {
						System.out.printf("| %3d ", bcNumber);
					}
				}
			}
			System.out.print("|");
			System.out.println();
		}

		for (int i = 0; i < cardWidth; i++) {
			System.out.print("______");
		}
		System.out.println();
		System.out.println();
		String judge = (winningNumber == lottery.getCurrentWinningNumbers().get(count)) ? "当たり！" : "残念！";
		System.out.println(judge);
		System.out.println();
	}

	// 初期ビンゴを作る
	public void makeBingo(List<List<Integer>> myBingoCardNumbers,int cardWidth) {

		for (List<Integer> bcNumbers : myBingoCardNumbers) {
			for (int i = 0; i < cardWidth; i++) {
				System.out.print("______");
			}
			System.out.println();
			for (int bcNumber : bcNumbers) { // 取り出したリストを1つずつ処理して繰り返し
				System.out.printf("| %3d ", bcNumber);
			}
			System.out.print("|");
			System.out.println();
		}

		for (int i = 0; i < cardWidth; i++) {
			System.out.print("______");
		}
		System.out.println();
		System.out.println();
	}

	// ゲッター//

	public List<List<Integer>> getBingoCardNumbers() {
		return bingoCardNumbers;
	}

}

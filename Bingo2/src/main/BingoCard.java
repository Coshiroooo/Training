package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BingoCard {

	private Scanner scanner = new Scanner(System.in);
	private List<List<Integer>> bingoCardNumbers = new ArrayList<List<Integer>>(); // カードに配置する数字のList
	public static List<Integer> cardWidthIndex; // カードの幅のインデックスを並べたList
	public static int numberMax; // ビンゴカードに書かれうる数字の最大値

	// コンストラクタ
	BingoCard(int cardWidth) {
		this.bingoCardNumbers = makeBingoCardNumbers(cardWidth);
		cardWidthIndex = new ArrayList<Integer>();
		for (int i = 0; i < cardWidth; i++)
			cardWidthIndex.add(i);
	}

	// ビンゴカード上に配置した数字のリストを作るメソッド
	public static List<List<Integer>> makeBingoCardNumbers(int cardWidth) {

		List<Integer> bingoNumbers = new ArrayList<Integer>();
		List<List<Integer>> bingoCardNumbers = new ArrayList<List<Integer>>();

		if (cardWidth <= 10) {
			for (int i = 1; i <= 100; i++)
				bingoNumbers.add(i);
		} else {
			for (int i = 1; i <= cardWidth * cardWidth; i++)
				bingoNumbers.add(i);
		}

		numberMax = bingoNumbers.stream().max((a, b) -> a.compareTo(b)).get();

		Collections.shuffle(bingoNumbers);

		for (int i = 0; i < cardWidth; i++) {
			bingoCardNumbers.add(new ArrayList<Integer>(bingoNumbers.subList(i * cardWidth, (i + 1) * cardWidth)));
		}

		return bingoCardNumbers;
	}

	// 抽選状況に合わせたビンゴカードを作るメソッド
	public void updateBingo(Lottery lottery, int cardWidth, int count) {

		bingoCardNumbers.forEach(bcNumbers -> {
			for (int i = 0; i < cardWidth; i++)
				System.out.print("______");
			System.out.println();

			bcNumbers.stream()
					.map(b -> lottery.getCurrentWinningNumbers().contains(b) ? "|     " : String.format("| %3d ", b))
					.forEach(System.out::print);
			
			System.out.print("|");
			System.out.println();
		});
		for (int i = 0; i < cardWidth; i++)
			System.out.print("______");
		System.out.println();
		System.out.println();

		System.out.println(
				bingoCardNumbers.stream().anyMatch(b -> b.contains(lottery.getCurrentWinningNumbers().get(count)))
						? "当たり！"
						: "残念！");

		System.out.println();
		System.out.println();
	}

	// 初期ビンゴを作る
	public void makeBingo(int cardWidth) {

		bingoCardNumbers.forEach(bcNumbers -> {
			for (int i = 0; i < cardWidth; i++)
				System.out.print("______");
			System.out.println();

			bcNumbers.stream().map(b -> String.format("| %3d ", b)).forEach(System.out::print);

			System.out.print("|");
			System.out.println();
		});
		for (int i = 0; i < cardWidth; i++)
			System.out.print("______");
		System.out.println();
		System.out.println();
	}

	// ゲッター//
	public List<List<Integer>> getBingoCardNumbers() {
		return bingoCardNumbers;
	}

}

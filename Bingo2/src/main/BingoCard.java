package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;;

public class BingoCard {

	private Scanner scanner = new Scanner(System.in);
	private List<List<Integer>> bingoCardNumbers = new ArrayList<List<Integer>>(); // カードに配置する数字のList
	public static List<Integer> cardWidthIndexes; // カードの幅のインデックスを並べたList
	public static int numberMax; // ビンゴカードに書かれうる数字の最大値

	// コンストラクタ
	BingoCard(int cardWidth) {
		this.bingoCardNumbers = makeBingoCardNumbers(cardWidth);
		cardWidthIndexes = new ArrayList<Integer>();
		cardWidthIndexes.addAll(IntStream.range(0, cardWidth).boxed().collect(Collectors.toList()));
	}

	// ビンゴカード上に配置した数字のリストを作るメソッド
	public static List<List<Integer>> makeBingoCardNumbers(int cardWidth) {

		List<Integer> bingoNumbers = new ArrayList<Integer>();
		List<List<Integer>> bingoCardNumbers = new ArrayList<List<Integer>>();

		if (cardWidth <= 10) {
			bingoNumbers = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
		} else {
			bingoNumbers = IntStream.rangeClosed(1, (int)Math.pow(cardWidth, 2)).boxed().collect(Collectors.toList());
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
			cardWidthIndexes.forEach(c -> System.out.print("______"));
			System.out.println();
			bcNumbers.stream()
					.map(b -> lottery.getCurrentWinningNumbers().contains(b) ? "|     " : String.format("| %3d ", b))
					.forEach(System.out::print);
			System.out.print("|");
			System.out.println();
		});
		cardWidthIndexes.forEach(c -> System.out.print("______"));
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
			cardWidthIndexes.forEach(c -> System.out.print("______"));
			System.out.println();
			bcNumbers.stream().map(b -> String.format("| %3d ", b)).forEach(System.out::print);
			System.out.print("|");
			System.out.println();
		});
		cardWidthIndexes.forEach(c -> System.out.print("______"));
		System.out.println();
		System.out.println();
	}

	// ゲッター//
	public List<List<Integer>> getBingoCardNumbers() {
		return bingoCardNumbers;
	}

}

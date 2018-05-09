package main;

public class Player {

	private String playerName;
	private BingoCard bingoCard;

	// コンストラクタ
	Player(int i, int cardWidth) {
		bingoCard = new BingoCard(cardWidth);
		this.playerName = "Player" + i + "さん";
	}

	// プレイヤーのビンゴカードを作るメソッド
	public void makeMyBingoCard(int cardWidth) {
		System.out.println();
		System.out.println(playerName + "のカードです");
		this.bingoCard.makeBingo(cardWidth);
	}

	// プレイヤーのビンゴカードを抽選回数に合わせてアップデートするメソッド
	public void updateMyBingoCard(Lottery lottery, int cardWidth, int count) {
		System.out.println("【" + playerName + "のビンゴ】");
		this.bingoCard.updateBingo(lottery, cardWidth, count);
	}

	// ビンゴ判定をするメソッド
	public boolean isBingo(Lottery lottery, int cardWidth) {

		boolean isBingoVertical = BingoCard.cardWidthIndexes.stream().anyMatch(c -> bingoCard.getBingoCardNumbers()
				.stream().allMatch(b -> lottery.getCurrentWinningNumbers().contains(b.get(c))));
		boolean isBingoSide = bingoCard.getBingoCardNumbers().stream()
				.anyMatch(b -> b.stream().allMatch(n -> lottery.getCurrentWinningNumbers().contains(n)));
		boolean isBingoSlant1 = bingoCard.getBingoCardNumbers().stream().allMatch(
				b -> lottery.getCurrentWinningNumbers().contains(b.get(bingoCard.getBingoCardNumbers().indexOf(b))));
		boolean isBingoSlant2 = bingoCard.getBingoCardNumbers().stream().allMatch(
				b -> lottery.getCurrentWinningNumbers().contains(b.get(bingoCard.getBingoCardNumbers().indexOf(b))));

		return isBingoVertical || isBingoSide || isBingoSlant1 || isBingoSlant2;

	}

	// ゲッター
	public String getPlayerName() {
		return this.playerName;
	}

}

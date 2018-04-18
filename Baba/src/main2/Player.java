package main2;

import java.util.*;

public class Player {

	private String name;
	private MyHand myHand;

	// コンストラクタ
	Player(int i) {
		this.name = "Player" + (i + 1);
		MyHand myHand = new MyHand();
		this.myHand = myHand;
	}

	// 重複したカードを墓地に送るメソッド
	public void throwCards(Card card, BoneYard boneYard) {

		Iterator<String> itFirst = this.myHand.getList().iterator();
		while (itFirst.hasNext()) {

			String firstStr = itFirst.next();

			if (firstStr == "joker") {
				continue;
			}

			int firstStrNumber = 0;
			for (List<String> sameNumbers : card.getSameNumbersList()) {
				if (sameNumbers.contains(firstStr)) {
					firstStrNumber = card.getSameNumbersList().indexOf(sameNumbers) + 1;
				}
			}

			Iterator<String> itSecond = this.myHand.getList().iterator();
			int count = 0;
			loop:while (itSecond.hasNext()) {
				String secondStr = itSecond.next();
				for (List<String> sameNumbers : card.getSameNumbersList()) {
					if (sameNumbers.get(firstStrNumber - 1).contains(secondStr)) {
						boneYard.getDeadCards().addAll(Arrays.asList(firstStr, secondStr));
						itSecond.remove();
						count++;
						break loop;
					}
				}
			}
			if(count == 1) {
				itFirst.remove();
			}
		}
	}

	// カードをとる人を決めるメソッド
	public Player nextPlayer(List<Player> allPlayer, Player player, int playerNumber) {
		int playerIndex = allPlayer.indexOf(player);
		int nextPlayerIndex = (playerIndex == playerNumber - 1) ? 0 : playerIndex + 1;
		Player nextPlayer = allPlayer.get(nextPlayerIndex);

		if (nextPlayer.isWinner()) {
			nextPlayer = nextPlayer.nextPlayer(allPlayer, nextPlayer, playerNumber);
		}

		return nextPlayer;
	}

	// 他のプレイヤーのカードを一枚引いて自分の手札に入れるメソッド
	public void pullCard(Player nextPlayer) {

		Collections.shuffle(nextPlayer.getMyHand().getList());

		System.out.println();
		System.out.println(
				"【" + name + "】" + nextPlayer.getName() + "さんから" + nextPlayer.getMyHand().getList().get(0) + "を引きました");
		this.myHand.getList().add(nextPlayer.getMyHand().getList().get(0));
		nextPlayer.getMyHand().getList().remove(0);
	}

	// 勝利判定
	public Boolean isWinner() {
		Boolean isWinner = (this.myHand.getList().size() == 0) ? true : false;
		return isWinner;
	}

	// ゲッター

	public String getName() {
		return this.name;
	}

	public MyHand getMyHand() {
		return this.myHand;
	}

}

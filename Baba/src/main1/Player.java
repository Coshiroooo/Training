package main1;

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

	// 重複している手札を捨てるメソッド
	public void throwCards(BoneYard boneYard) {
		List<String> myHand = new ArrayList<String>();
		myHand = this.myHand.getList();
		for (String firstStr : myHand) {
			loop: for (String secondStr : myHand) {
				for (int j = 1; j <= 13; j++) {
					if (j < 10) {
						if (firstStr.contains(0 + String.valueOf(j)) && secondStr.contains(0 + String.valueOf(j))
								&& firstStr != secondStr) {
							toBoneYard(boneYard, myHand, firstStr, secondStr);
							break loop;
						}
					} else {
						if (firstStr.contains(String.valueOf(j)) && secondStr.contains(String.valueOf(j))
								&& firstStr != secondStr) {
							toBoneYard(boneYard, myHand, firstStr, secondStr);
							break loop;
						}
					}
				}
			}
		}
		List<String> hashMyHand = new ArrayList<String>(new HashSet<>(myHand));
		if (hashMyHand.contains("null")) {
			hashMyHand.remove(hashMyHand.indexOf("null"));
		}
		this.myHand.setList(hashMyHand);
		System.out.println();
	}

	// 墓地にカードを送るメソッド
	public void toBoneYard(BoneYard boneYard, List<String> myHand, String firstStr, String secondStr) {
		boneYard.getDeadCards().add(firstStr);
		boneYard.getDeadCards().add(secondStr);
		myHand.set(myHand.indexOf(firstStr), "null");
		myHand.set(myHand.indexOf(secondStr), "null");
		System.out.print("【" + name + "】");
		System.out.println(firstStr + "," + secondStr + "を捨てました");
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
		System.out.print("【" + name + "】");
		System.out.println(nextPlayer.getName() + "さんから" + nextPlayer.getMyHand().getList().get(0) + "を引きました");
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

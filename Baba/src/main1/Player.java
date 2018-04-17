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
		public void throwCards() {
			List<String> myHand = new ArrayList<String>();
			myHand = this.myHand.getList();
			for (String firstStr : myHand) {
				loop: for (String secondStr : myHand) {
					for (int j = 1; j <= 13; j++) {
						if (j < 10) {
							if (firstStr.contains(0 + String.valueOf(j)) && secondStr.contains(0 + String.valueOf(j))
									&& firstStr != secondStr) {
								myHand.set(myHand.indexOf(firstStr), "null");
								myHand.set(myHand.indexOf(secondStr), "null");
								System.out.print("【" + name + "】");
								System.out.println(firstStr + "," + secondStr + "を捨てました");
								break loop;
							}
						} else {
							if (firstStr.contains(String.valueOf(j)) && secondStr.contains(String.valueOf(j))
									&& firstStr != secondStr) {
								myHand.set(myHand.indexOf(firstStr), "null");
								myHand.set(myHand.indexOf(secondStr), "null");
								System.out.print("【" + name + "】");
								System.out.println(firstStr + "," + secondStr + "を捨てました");
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
		
	// 他のプレイヤーのカードを一枚引いて自分の手札に入れるメソッド
	public void pullCard(Player player) {
		Collections.shuffle(player.getMyHand().getList());
		System.out.println();
		System.out.print("【" + name + "】");
		System.out.println(player.getMyHand().getList().get(0) + "を引きました");
		this.myHand.getList().add(player.getMyHand().getList().get(0));
		player.getMyHand().getList().remove(0);
	}
	
	//勝利判定
	public void judge() {
		System.out.println();
		if(isWinner()) {
			System.out.println(name + "はあがり！");
		}
	} 
	
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

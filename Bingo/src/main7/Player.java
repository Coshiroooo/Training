package main7;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String playerName;
	private List<List<Integer>> myBingoCard = new ArrayList<List<Integer>>();
	
	//コンストラクタ
	Player(int i,BingoCard bingoCard){
		this.myBingoCard = bingoCard.makeBingoCardNumbers();
		this.playerName = "Player" + i + "さん";
	}
	
	//ゲッター
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public List<List<Integer>> getMyBingoCard(){
		return this.myBingoCard;
	}

}

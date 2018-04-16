package main6;

import java.util.*;

public class Player {
	
	private int playerNumber;
	private List<List<List<Integer>>> myBingoCardNumbers = new ArrayList<List<List<Integer>>>();
	private Scanner scanner = new Scanner(System.in);
	
	//コンストラクタ
	Player(){
		System.out.println();
		System.out.print("何人でビンゴしますか？：");
		int playerNumber = scanner.nextInt();
		this.playerNumber = playerNumber;
		System.out.println();
	}
	
	//ゲッター
	
	public int getPlayerNumber() {
		return this.playerNumber;
	}
	
	public List<List<List<Integer>>> getMyBingoCardNumbers(){
		return this.myBingoCardNumbers;
	}
	
}

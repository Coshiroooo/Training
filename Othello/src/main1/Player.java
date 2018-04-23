package main1;

import java.util.*;

public class Player {

	private String name;
	private String playerColor;
	private Scanner scanner = new Scanner(System.in);

	// コンストラクタ
	Player(String name, String color) {
		this.name = name;
		Stone stone = new Stone(color);
		this.playerColor = stone.getFrontColor();
	}

	// Profileを表示するメソッド
	public void printProfile() {
		System.out.println(name + ":" + this.playerColor);
	}

	//石を置く場所を決めるメソッド
	public int inputSquareNumber() {
		System.out.println();
		System.out.println("【" + name + "のターン】");
		System.out.print(playerColor + "を置くマス目の数字を入力してください：");
		int squareNumber = scanner.nextInt();
		System.out.println(squareNumber + "のマスに" + playerColor+ "を置きます");
		System.out.println();
		return squareNumber;
	}
	
	//石を置くメソッド
	public Stone putStone() {
		Stone stone = new Stone(playerColor);
		return stone;
	}

	// ゲッター
	public String getName() {
		return this.name;
	}
	
	public String getPlayerColor() {
		return this.playerColor;
	}

}

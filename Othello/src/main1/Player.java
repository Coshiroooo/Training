package main1;

import java.util.*;

public class Player {

	private String name;
	private String stone;
	private final String white = "◎";
	private final String black = "◉";
	private Scanner scanner = new Scanner(System.in);

	// コンストラクタ
	Player(String name, String stone) {
		this.name = name;
		this.stone = stone;
	}

	// Profileを表示するメソッド
	public void printProfile() {
		System.out.println(name + ":" + stone);
	}

	public int inputSquareNumber() {
		System.out.println();
		System.out.println("【" + name + "のターン】");
		System.out.print(stone + "を置くマス目の数字を入力してください：");
		int squareNumber = scanner.nextInt();
		System.out.println(squareNumber + "のマスに" + stone + "を置きます");
		System.out.println();
		return squareNumber;
	}

	// ゲッター
	public String getName() {
		return this.name;
	}
	
	public String getStone() {
		return this.stone;
	}

}

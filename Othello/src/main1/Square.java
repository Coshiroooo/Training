package main1;

import java.util.*;

public class Square {

	private String box;
	private int number;
	private String numberS;
	private final static String white = "◎";
	private final static String black = "◉";
	private Map<String, Square> aroundSquares = new HashMap<String, Square>();

	// コンストラクタ
	Square(int number) {
		this.number = number;
		this.numberS = String.format("%02d", this.number);
		this.box = this.numberS;
	}

	// コンストラクタのオーバーロード
	Square(String state) {
		this.box = state;
	}

	// 石を置くメソッド
	public void putStone(String stone) {
		this.box = stone;
	}

	// 色をひっくり返すメソッド
	public String changeColor(String color) {
		if (color.equals(white)) {
			color = black;
		} else if (color.equals(black)) {
			color = white;
		}
		return color;
	}

	// 隣のマスが違う色の石かを判定するメソッド
	public Boolean isDifferentColor(String key) {
		Boolean isDifferentColor = false;
		if ((this.box.equals(white) || this.box.equals(black))
				&& aroundSquares.get(key).getBox().equals(changeColor(this.box))) {
			isDifferentColor = true;
		}
		return isDifferentColor;
	}

	public void printSquare() {
		System.out.print(" " + box + " ");
	}

	public void inputAroundSquares(Map<String, Square> aroundSquares) {
		this.aroundSquares = aroundSquares;
	}

	public int getNumber() {
		return this.number;
	}
	
	public String getNumberS() {
		return this.numberS;
	}

	public String getBox() {
		return this.box;
	}

	public Map<String, Square> getAroundSquares() {
		return this.aroundSquares;
	}

}

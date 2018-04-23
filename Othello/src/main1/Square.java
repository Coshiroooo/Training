package main1;

import java.util.*;

public class Square {

	private String box;
	private int number;
	private String numberS;
	private Stone stone;
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

	// マス目に石が格納されるメソッド
	public void strageStone(Stone stone) {
		this.stone = stone;
		this.box = this.stone.getFrontColor();
	}

	// 隣のマスが違う色の石かを判定するメソッド
	public Boolean isDifferentColor(String key) {
		Boolean isDifferentColor = false;
		if (this.stone != null && aroundSquares.get(key).getBox().equals(this.stone.getBackColor())) {
			isDifferentColor = true;
		}
		return isDifferentColor;
	}

	//石をひっくり返すメソッド
	public void turnOverStone() {
		this.stone.turnOver();
		this.box = this.stone.getFrontColor();
	}

	// マス目を表示する
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

	public Stone getStone() {
		return this.stone;
	}

	public String getBox() {
		return this.box;
	}

	public Map<String, Square> getAroundSquares() {
		return this.aroundSquares;
	}

}

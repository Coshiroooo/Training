package main1;

import java.util.*;

public class Square {

	private int number;
	private String numberS;
	private Stone stone = new Stone();
	private boolean isValid = true;
	private boolean isShown = true;
	private Map<String, Square> aroundSquares = new HashMap<String, Square>();

	// コンストラクタ
	Square(int number) {
		this.number = number;
		this.numberS = String.format("%02d", this.number);
	}

	// コンストラクタのオーバーロード
	Square() {
		this.isValid = false;
	}

	// マス目に石が格納されるメソッド
	public void strageStone(Stone stone) {
		this.stone = stone;
	}

	// 隣のマスが違う色の石かを判定するメソッド
	public boolean isDifferentColor(String key) {
		Square nextSquare = aroundSquares.get(key);
		if(this.stone.getIsExist() && nextSquare.getStone().getIsExist()) {
			return nextSquare.getStone().getFrontColor().equals(this.stone.getBackColor());
		}else {
			return false;
		}
	}
	
	public void switchIsShown() {
		this.isShown = !this.isShown;
	}

	// 石をひっくり返すメソッド
	public void turnOverStone() {
		this.stone.turnOver();
	}

	// マス目を表示する
	public void printSquare() {
		if(this.isValid) {
			if(this.stone.getIsExist()) {
				System.out.print(" " + this.stone.getFrontColor() + " ");
			}else {
				if(this.isShown) {
				System.out.print(" " + this.numberS + " ");
				}else {
					System.out.print(" -- ");
				}
			}
		}
	}

	//セッター
	
	public void setAroundSquares(Map<String, Square> aroundSquares) {
		this.aroundSquares = aroundSquares;
	}

	//ゲッター
	
	public int getNumber() {
		return this.number;
	}

	public String getNumberS() {
		return this.numberS;
	}

	public Stone getStone() {
		return this.stone;
	}
	
	public boolean getIsShown() {
		return this.isShown;
	}

	public Map<String, Square> getAroundSquares() {
		return this.aroundSquares;
	}

}

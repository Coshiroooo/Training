package main1;

import java.util.*;

public class Square {

	private String box;
	private int number;
	private final static String white = "◎";
	private final static String black = "◉";
	private String state = "valid";
	private Map<String,Square> aroundSquares = new HashMap<String,Square>();
	
	//コンストラクタ
	Square(int number){
		this.number = number;
		this.box = String.format("%02d", this.number);
	}
	
	//コンストラクタのオーバーロード
	Square(String state){
		this.state = state;
	}
	
	//石を置くメソッド
	public void putStone(String stone) {
		this.box = stone;
	}
	
	//隣のマスが違う色の石かを判定するメソッド
	public Boolean isDiffirentColor(String key) {
		Boolean isDiffirentColor = false;
		if(this.box == white) {
			if(aroundSquares.get(key).getBox() == black) {
				isDiffirentColor = true;
			}
		}else if(this.box == black) {
			if(aroundSquares.get(key).getBox() == white) {
				isDiffirentColor = true;
			}
		}
		return isDiffirentColor;
	}
	
	public void printSquare() {
		System.out.print(" " + box + " ");
	}
	
	public void inputAroundSquares(Map<String,Square> aroundSquares){
		this.aroundSquares = aroundSquares;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getBox() {
		return this.box;
	}
	
}

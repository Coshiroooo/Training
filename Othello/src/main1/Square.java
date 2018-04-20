package main1;

import java.util.*;

public class Square {

	private String box;
	private int number;
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
	
	public void printSquare() {
		System.out.print(" " + box + " ");
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getBox() {
		return this.box;
	}
	
	public void inputAroundSquares(Map<String,Square> aroundSquares){
		this.aroundSquares = aroundSquares;
	}
	
}

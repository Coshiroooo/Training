package main1;

public class Square {

	private String box;
	private int number;
	
	//コンストラクタ
	Square(int number){
		this.number = number;
		this.box = String.format("%02d", this.number);
	}
	
	//石を置くメソッド
	public void putStone(String stone) {
		this.box = stone;
	}
	
	public void printSquare() {
		System.out.print(" " + box + " ");
	}
	
	public String getBox() {
		return this.box;
	}
	
}

package main1;

public class Square {

	private String box;
	private int number;
	
	Square(int number){
		this.number = number;
		this.box = String.format("%02d", this.number);
	}
	
	public void putStone(String stone) {
		this.box = stone;
	}
	
	public void printSquare() {
		System.out.print(" " + box + " ");
	}
	
}

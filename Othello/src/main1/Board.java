package main1;

import java.util.*;

public class Board {

	private int width = 8;
	private String white = "◎️";
	private String black = "◉";
	private List<List<Square>> boardSquares = new ArrayList<List<Square>>();

	// コンストラクタ
	Board() {
		List<Square> squares = new ArrayList<Square>();
		for (int i = 1; i <= Math.pow(width, 2); i++) {
			squares.add(new Square(i));
		}
		for (int i = 0; i < Math.pow(width, 2); i += width) {
			boardSquares.add(squares.subList(i, i + width));
		}
		changeSquare(3,3,black);
		changeSquare(3,4,white);
		changeSquare(4,3,white);
		changeSquare(4,4,black);
	}

	// ボードの状態を表示する
	public void printBoard() {
		for (List<Square> squareLine : boardSquares) {
			printLine();
			for (Square square : squareLine) {
				System.out.print("|");
				square.printSquare();
			}
			System.out.print("|");
			System.out.println();
		}
		printLine();
	}

	//ボードの行線を表示する
	public void printLine() {
		for (int i = 0; i < width; i++) {
			System.out.print("-----");
		}
		System.out.println();
	}
	
	public void changeSquare(int x,int y,String stone) {
		boardSquares.get(y).get(x).putStone(stone);
	}

}

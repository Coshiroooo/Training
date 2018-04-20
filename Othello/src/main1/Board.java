package main1;

import java.util.*;

public class Board {

	private int width = 8;
	private String white = "◎";
	private String black = "◉";
	private List<List<Square>> boardSquares = new ArrayList<List<Square>>();
//	private Map<String, Square> aroundStone = new HashMap<String, Square>();

	// コンストラクタ
	Board() {
		List<Square> squares = new ArrayList<Square>();
		for (int i = 1; i <= Math.pow(width, 2); i++) {
			squares.add(new Square(i));
		}
		for (int i = 0; i < Math.pow(width, 2); i += width) {
			boardSquares.add(squares.subList(i, i + width));
		}
		
		for(List<Square> squareLine : boardSquares) {
			for(Square square : squareLine) {
				square.inputAroundSquares(creatAroundSquares(square.getNumber()));
			}
		}
		
		changeSquare(width / 2 - 1, width / 2 - 1, black);
		changeSquare(width / 2, width / 2 - 1, white);
		changeSquare(width / 2 - 1, width / 2, white);
		changeSquare(width / 2, width / 2, black);
	}

	// ボードの状態を表示する
	public void printBoard() {
		for (List<Square> squareLine : boardSquares) {
			printLine();
			for (Square square : squareLine) {
				System.out.print("|");
				square.printSquare();
			}
			System.out.println("|");
		}
		printLine();
	}

	// ボードの行線を表示する
	public void printLine() {
		for (int i = 0; i < width; i++) {
			System.out.print("-----");
		}
		System.out.println();
	}

	// 指定した座標に石を置くメソッド
	public void changeSquare(int x, int y, String stone) {
		boardSquares.get(y).get(x).putStone(stone);
	}

	// マス目の番号入力するとそこに石を置いてくれるメソッド
	public void changeSquare(int squareNumber, String stone) {
		int y = (int) (squareNumber / width);
		if (squareNumber % 8 == 0) {
			y = y - 1;
		}
		int x = (squareNumber % width) - 1;
		if (x == 0) {
			x = width - 1;
		}
		changeSquare(x, y, stone);
	}
	
	//マス目の数字を入れるとそのマス目を返してくれるメソッド
	public Square getSquare(int squareNumber) {
		int y = (int) (squareNumber / width);
		if (squareNumber % 8 == 0) {
			y = y - 1;
		}
		int x = (squareNumber % width) - 1;
		if (x == 0) {
			x = width - 1;
		}
		return boardSquares.get(y).get(x);
	}

	// 盤上の石の数を数えるメソッド
	public int countStone(String stone) {
		int count = 0;
		for (List<Square> squareLine : boardSquares) {
			for (Square square : squareLine) {
				if (square.getBox().equals(stone)) {
					count++;
				}
			}
		}
		return count;
	}
	
	//Squareの周りのSquareを取得したListを生成するメソッド
	public Map<String,Square> creatAroundSquares(int squareNumber){
		Map<String,Square> aroundSquares = new HashMap<String,Square>();
		if(squareNumber > width) {
			aroundSquares.put("north", getSquare(squareNumber-width-1));
		}else {
			aroundSquares.put("north", new Square("null"));
		}
		if(squareNumber > width && squareNumber % width != 0) {
			aroundSquares.put("northEast",getSquare(squareNumber-width));	
		}else {
			aroundSquares.put("northEast", new Square("null"));
		}
		if(squareNumber % width != 0) {
			aroundSquares.put("east",getSquare(squareNumber));
		}else {
			aroundSquares.put("east", new Square("null"));
		}
		if(squareNumber <= width*(width-1) && squareNumber % width != 0) {
			aroundSquares.put("southEast",getSquare(squareNumber+width));	
		}else {
			aroundSquares.put("southEast", new Square("null"));
		}
		if(squareNumber <= width*(width-1)) {
			aroundSquares.put("south",getSquare(squareNumber+width-1));	
		}else {
			aroundSquares.put("south", new Square("null"));
		}
		if(squareNumber <= width*(width-1) && squareNumber % width != 1) {
			aroundSquares.put("southWest",getSquare(squareNumber+width-2));
		}else {
			aroundSquares.put("southWest", new Square("null"));
		}
		if(squareNumber % width != 1) {
			aroundSquares.put("west",getSquare(squareNumber-2));
		}else {
			aroundSquares.put("west", new Square("null"));
		}
		if(squareNumber > width && squareNumber % width != 1) {
			aroundSquares.put("northWest",getSquare(squareNumber-width-2));	
		}else {
			aroundSquares.put("northWest", new Square("null"));
		}
		return aroundSquares;
	}

}

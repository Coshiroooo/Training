package main1;

import java.util.*;

public class Board {

	private int width = 8;
	private final String white = "◎";
	private final String black = "◉";
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

		for (List<Square> squareLine : boardSquares) {
			for (Square square : squareLine) {
				square.inputAroundSquares(creatAroundSquares(square));
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
		if (squareNumber % width == 0) {
			y = y - 1;
		}
		int x = (squareNumber % width) - 1;
		if (x == -1) {
			x = width - 1;
		}
		changeSquare(x, y, stone);
	}

	// マス目の数字を入れるとそのマス目を返してくれるメソッド
	public Square getSquare(int squareNumber) {
		int y = (int) (squareNumber / width);
		if (squareNumber % width == 0) {
			y = y - 1;
		}
		int x = (squareNumber % width) - 1;
		if (x == -1) {
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
	
	//ゲーム続行条件を示すメソッド
	public Boolean isContinue(Player player1,Player player2) {
		int allStone = countStone(player1.getStone()) + countStone(player2.getStone());
		if(allStone < Math.pow(width, 2)) {
			return true;
		}else {
			return false;
		}
	}
	
	//勝利判定をするメソッド
	public void printJudge(Player player1,Player player2) {
		int count1 = countStone(player1.getStone());
		int count2 = countStone(player2.getStone());
		System.out.println();
		
		if(count1 > count2) {
			System.out.println(player1.getName() + "の勝利！！！");
		}else if(count1 < count2) {
			System.out.println(player2.getName() + "の勝利！！！");
		}else if(count1 == count2) {
			System.out.println("引き分け！！！");
		}
	}

	// 全方位に石ひっくり返しメソッド
	public void changeAmongStoneAll(int squareNumber) {
		for (String key : getSquare(squareNumber).getAroundSquares().keySet()) {
			changeAmongStone(squareNumber, key);
		}
		System.out.println();
	}

	// 石を置いたら挟まれた違う色の石をひっくり返すメソッド
	public void changeAmongStone(int squareNumber, String key) {
		Square square = getSquare(squareNumber); // 置いた石を呼び出す
		int count = 0; // ひっくり返す石の数

		if (square.isDifferentColor(key)) { // 隣のマスが違う色だったら
			count++; // 1つひっくり返るかも
			Square nextSquare = square.getAroundSquares().get(key); // 隣の石を呼び出す
			count = countAmongStone(nextSquare, count, key); // 同じ色が何個続くか //count =を忘れてた
		}
		if (count == 0) { // 1つもひっくり返す石がなかったらおしまい
			return;
		}
		change(square, count, key); // 間にある個数だけひっくり返す
	}

	// 隣の石の色をひっくり返したらその隣の石もひっくり返すのをcount回繰り返すメソッド
	public void change(Square square, int count, String key) {
		Square nextSquare = square.getAroundSquares().get(key);
		if (count == 0) {
			return;
		}
		nextSquare.putStone(nextSquare.changeColor(nextSquare.getBox()));
		count--;
		change(nextSquare, count, key);
	}

	// 挟まれている石が何個あるか数えるメソッド
	public int countAmongStone(Square square, int count, String key) {
		Square nextSquare = square.getAroundSquares().get(key);
		if (!square.isDifferentColor(key) && !nextSquare.getBox().equals(nextSquare.getNumberS())
				&& !"null".equals(nextSquare.getBox())) { // 隣が同じ色だったら
			count++;
			count = countAmongStone(nextSquare, count, key); // カウントを1増やして次のマスで同じ処理
			return count;
		} else { // 隣が違う色
			if (nextSquare.getBox().equals(nextSquare.getNumberS()) || "null".equals(nextSquare.getBox())) {
				count = 0;
				return count;
			}
			return count;
		}
	}

	// Squareの周りのSquareを取得したListを生成するメソッド
	public Map<String, Square> creatAroundSquares(Square square) {
		int number = square.getNumber();
		Map<String, Square> aroundSquares = new HashMap<String, Square>();

		if (number > width) {
			aroundSquares.put("north", getSquare(number - width));
		} else {
			aroundSquares.put("north", new Square("null"));
		}
		if (number > width && number % width != 0) {
			aroundSquares.put("northEast", getSquare(number - width + 1));
		} else {
			aroundSquares.put("northEast", new Square("null"));
		}
		if (number % width != 0) {
			aroundSquares.put("east", getSquare(number + 1));
		} else {
			aroundSquares.put("east", new Square("null"));
		}
		if (number <= width * (width - 1) && number % width != 0) {
			aroundSquares.put("southEast", getSquare(number + width + 1));
		} else {
			aroundSquares.put("southEast", new Square("null"));
		}
		if (number <= width * (width - 1)) {
			aroundSquares.put("south", getSquare(number + width));
		} else {
			aroundSquares.put("south", new Square("null"));
		}
		if (number <= width * (width - 1) && number % width != 1) {
			aroundSquares.put("southWest", getSquare(number + width - 1));
		} else {
			aroundSquares.put("southWest", new Square("null"));
		}
		if (number % width != 1) {
			aroundSquares.put("west", getSquare(number - 1));
		} else {
			aroundSquares.put("west", new Square("null"));
		}
		if (number > width && number % width != 1) {
			aroundSquares.put("northWest", getSquare(number - width - 1));
		} else {
			aroundSquares.put("northWest", new Square("null"));
		}
		return aroundSquares;
	}

}

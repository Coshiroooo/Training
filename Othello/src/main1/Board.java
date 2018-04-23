package main1;

import java.util.*;

public class Board {

	private int width = 8;
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
	}

	public void setUp(Player player1, Player player2) {
		installStone(width / 2 - 1, width / 2 - 1, player1.putStone());
		installStone(width / 2, width / 2 - 1, player2.putStone());
		installStone(width / 2 - 1, width / 2, player2.putStone());
		installStone(width / 2, width / 2, player1.putStone());
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
	public void installStone(int x, int y, Stone stone) {
		boardSquares.get(y).get(x).strageStone(stone);
	}

	// マス目の番号入力するとそこに石を置いてくれるメソッド //オーバーロード
	public void installStone(int squareNumber, Stone stone) {
		int y = (int) (squareNumber / width);
		if (squareNumber % width == 0) {
			y = y - 1;
		}
		int x = (squareNumber % width) - 1;
		if (x == -1) {
			x = width - 1;
		}
		installStone(x, y, stone);
	}

	// マス目の数字を入れるとそのマス目を返してくれるメソッド
	public Square getSquare(int squareNumber) {

		int x = (squareNumber % width) - 1;
		int y = (int) (squareNumber / width);

		if (x == -1) {
			x = width - 1;
		}
		if (squareNumber % width == 0) {
			y = y - 1;
		}

		return boardSquares.get(y).get(x);
	}

	// 盤上の石の数を数えるメソッド
	public int countStone(Player player) {
		int count = 0;
		for (List<Square> squareLine : boardSquares) {
			for (Square square : squareLine) {
				Stone stone = square.getStone();
				if (stone != null && stone.getFrontColor() == player.getPlayerColor()) {
					count++;
				}
			}
		}
		return count;
	}

	// ゲーム続行条件を示すメソッド
	public Boolean isContinue(Player player1, Player player2) {
		int allStone = countStone(player1) + countStone(player2);
		if (allStone < Math.pow(width, 2)) {
			return true;
		} else {
			return false;
		}
	}

	// 勝利判定をするメソッド
	public void printJudge(Player player1, Player player2) {
		int count1 = countStone(player1);
		int count2 = countStone(player2);
		System.out.println();

		if (count1 > count2) {
			System.out.println(player1.getName() + "の勝利！！！");
		} else if (count1 < count2) {
			System.out.println(player2.getName() + "の勝利！！！");
		} else if (count1 == count2) {
			System.out.println("引き分け！！！");
		}
	}

	// 全方位に石をひっくり返す処理をするメソッド
	public void turnOverAllAmongStone(int squareNumber) {
		for (String key : getSquare(squareNumber).getAroundSquares().keySet()) {
			turnOverAmongStone(squareNumber, key);
		}
		System.out.println();
	}

	// 8方位のうち1方位、石を置いたら挟まれた違う色の石をひっくり返すメソッド
	public void turnOverAmongStone(int squareNumber, String key) {
		
		Square square = getSquare(squareNumber); // 置いた石を呼び出す
		int count = 0; // ひっくり返す石の数
		
		count = countAmongStone(square,count,key);
		
		if (count == 0) { // 1つもひっくり返す石がなかったらおしまい
			return;
		}

		do { //カウントの数だけ、隣の石をひっくり返す
			Square nextSquare = square.getAroundSquares().get(key);
			nextSquare.turnOverStone();
			square = nextSquare;
			count--;
		} while (count > 0);
	}
	
	//挟まれている石が何個あるか数えるメソッド
	public int countAmongStone(Square square,int count,String key) {
		if (square.isDifferentColor(key)) { // 隣のマスが違う色だったら
			count++; // 1つひっくり返る
			Square nextSquare = square.getAroundSquares().get(key); // 隣の石を呼び出す
			count = countSameStone(nextSquare, count, key); // 同じ色が何個続くか
		}
		return count;
	}

	// 同じ色の石が何個並ぶか数えるメソッド
	public int countSameStone(Square square, int count, String key) {

		Square nextSquare = square.getAroundSquares().get(key);

		if (!square.isDifferentColor(key) && nextSquare.getStone() != null) { // 隣が同じ色だったら
			count++;
			count = countSameStone(nextSquare, count, key); // カウントを1増やして次のマスで同じ処理
			return count;
		} else { // 隣が違う色
			if (nextSquare.getStone() == null) {
				count = 0;
				return count;
			}
			return count;
		}
	}
	
	public Boolean isTurnOver(int squareNumber,Player player,String key) {
		Square square = getSquare(squareNumber);
		square.strageStone(new Stone(player.getPlayerColor()));
		int count = 0; // ひっくり返す石の数
		
		count = countAmongStone(square,count,key);
		
		square.strageStone(null);
		square.setBox(square.getNumberS());
		
		if(count > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//そのマス目に石を置いたら色が変わるかを判定するメソッド
	public Boolean isPutStone(int squareNumber,Player player) {
		List<Boolean> isPutStone = new ArrayList<Boolean>();
		for(String key : getSquare(squareNumber).getAroundSquares().keySet()) {
			isPutStone.add(isTurnOver(squareNumber,player,key));
		}
		return isPutStone.contains(true);
	}
	
	public void notPutStone(Player player) {
		System.out.println();
		for(List<Square> squareList : boardSquares) {
			for(Square square : squareList) {
				if(square.getStone() == null && !isPutStone(square.getNumber(),player)) {
					square.setBox("--");
				}
			}
		}
	}
	
	public void returnBoard() {
		for(List<Square> squareList : boardSquares) {
			for(Square square : squareList) {
				if(square.getStone() == null) {
					square.setBox(square.getNumberS());
				}
			}
		}
	}
	
	// Squareの周りのSquareを取得したMapを生成するメソッド
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

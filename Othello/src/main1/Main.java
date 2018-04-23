package main1;

import java.util.*;

public class Main {

	private final static String white = "◎";
	private final static String black = "◉";
	private static Player player1 = new Player("Player1", white);
	private static Player player2 = new Player("Player2", black);
	private static Board board = new Board(player1,player2);
	private static List<Player> allPlayer = Arrays.asList(player1, player2);

	public static void main(String[] args) {

		System.out.println("オセロを始めます");

		printPlayerProfile();

		board.printBoard();

		do {
			for (Player player : allPlayer) {
				int squareNumber = player.inputSquareNumber();
				board.changeSquare(squareNumber, player.getPlayerColor());
				board.changeAmongStoneAll(squareNumber);
				board.printBoard();
				printCountStone();
			}
		} while (board.isContinue(player1, player2));
		
		board.printJudge(player1, player2);

	}

	// PlayerのProfileを表示するメソッド
	public static void printPlayerProfile() {
		System.out.println();
		player1.printProfile();
		player2.printProfile();
		System.out.println();
	}

	public static void printCountStone() {
		System.out.println(
				"【" + player1.getName() + "】" + player1.getPlayerColor() + "：" + board.countStone(player1.getPlayerColor()));
		System.out.println(
				"【" + player2.getName() + "】" + player2.getPlayerColor() + "：" + board.countStone(player2.getPlayerColor()));
	}

}

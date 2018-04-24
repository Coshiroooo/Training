package main1;

import java.util.*;

public class Main {

	public static final String white = "◯";
	public static final String black = "★";
	private static Board board = new Board();
	private static Player player1 = new Player("Player1", white);
	private static Player player2 = new Player("Player2", black);
	private static List<Player> allPlayer = Arrays.asList(player1, player2);

	public static void main(String[] args) {

		System.out.println("オセロを始めます");

		printPlayerProfile();

		board.setUp(player1, player2);
		board.printBoard();

		do {
			for (Player player : allPlayer) {
				
				board.onlyPutStone(player);
				board.printBoard();
				
				int count = 0;
				if (!board.isPutable()) {
					count++;
					if (count > 1) {
						break;
					}
					System.out.println("石を置く場所がないのでスキップします");
					continue;
				}
				
				int squareNumber = player.inputSquareNumber();
				board.installStone(squareNumber, player.putStone());
				board.turnOverAllAmongStone(squareNumber);
				
				board.returnBoard();
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

	//各プレイヤーの石の数を数えるメソッド
	public static void printCountStone() {
		System.out.println("【" + player1.getName() + "】" + player1.getPlayerColor() + "：" + board.countStone(player1));
		System.out.println("【" + player2.getName() + "】" + player2.getPlayerColor() + "：" + board.countStone(player2));
	}

}

package main1;

import java.util.*;

public class Main {
	
	private final static String white = "◎";
	private final static String black = "◉";
//	private static Scanner scanner = new Scanner(System.in);
	private static Board board = new Board();
	private static Player player1 = new Player("Player1",white);
	private static Player player2 = new Player("Player2",black);
//	private static List<Player> allPlayer = Arrays.asList(player1,player2);

	public static void main(String[] args) {

		System.out.println("オセロを始めます");
		
		printPlayerProfile();
		
		board.printBoard();
		
		board.changeSquare(player1.inputSquareNumber(), player1.getStone());
		board.printBoard();
		printCountStone();
		
		
		board.changeSquare(player2.inputSquareNumber(), player2.getStone());
		board.printBoard();
		printCountStone();
	}
	
	//PlayerのProfileを表示するメソッド
	public static void printPlayerProfile() {
		System.out.println();
		player1.printProfile();
		player2.printProfile();
		System.out.println();
	}
	
	public static void printCountStone() {
		System.out.println("【" + player1.getName() + "】" + player1.getStone() + "：" + board.countStone(player1.getStone()));
		System.out.println("【" + player2.getName() + "】" + player2.getStone() + "：" + board.countStone(player2.getStone()));
	}
	
}

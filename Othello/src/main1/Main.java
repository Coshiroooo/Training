package main1;

import java.util.*;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	private static Board board = new Board();
	private static Player player1 = new Player("Player1","◎️");
	private static Player player2 = new Player("Player2","◉");
	private static List<Player> allPlayer = Arrays.asList(player1,player2);

	public static void main(String[] args) {

		board.printBoard();
	}
	
	
}

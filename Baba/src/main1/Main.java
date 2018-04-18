package main1;

import java.util.*;

public class Main {

	private static Card card = new Card();
	private static BoneYard boneYard = new BoneYard();
	private static Scanner scanner = new Scanner(System.in);
	private static List<Player> allPlayer = new ArrayList<Player>();
	private static int playerNumber;

	public static void main(String[] args) {
		
		System.out.print("何人でババ抜きしますか？：");
		playerNumber = scanner.nextInt();

		System.out.println("ババ抜きを始めます");

		for (int i = 0; i < playerNumber; i++) {
			allPlayer.add(new Player(i));
		}

		dealCards();
		printMyHand();

		allPlayer.forEach(p -> p.throwCards(boneYard));

		printMyHand();

		do {
			for (Player player : allPlayer) {

				Player nextPlayer = player.nextPlayer(allPlayer, player, playerNumber);

				if (player.isWinner()) { // カードを引かれてあがった場合、次のプレイヤーへパス
					player = nextPlayer;
					continue;
				}

				if (nextPlayer == player) { // 次にカードを引く人が自分になってしまったら、ゲーム終了
					break;
				}

				player.pullCard(nextPlayer);
				player.throwCards(boneYard);
				printMyHand();
				allPlayer.forEach(p -> printWinner(p));
			}
		} while (boneYard.getDeadCards().size() < card.getAllCards().size() - 1);

		end();

	}

	// ゲーム終了
	public static void end() {
		System.out.println();
		for (Player player : allPlayer) {
			if (!player.isWinner()) {
				System.out.println(player.getName() + "の負け！！！");
			}
		}
		System.out.println("ババ抜き終了！！！");
	}

	// プレイヤーの勝利判定を出力するメソッド
	public static void printWinner(Player player) {
		if (player.isWinner()) {
			System.out.println(player.getName() + "はあがり！");
		}
	}

	// プレイヤーの手札の状態を表示するメソッド
	public static void printMyHand() {
		for (Player player : allPlayer) {
			System.out.print("【" + player.getName() + "】：残り" + player.getMyHand().getList().size() + "枚");
			System.out.print(player.getMyHand().getList());
			System.out.println();
		}
		System.out.println();
		System.out.println("---------------------------------------");
	}

	// カードをプレイヤーに配るメソッド
	public static void dealCards() {

		int allCardsNumber = card.getAllCards().size();
		int myHandNumber = (int) (allCardsNumber / playerNumber);
		int remainCards = allCardsNumber % playerNumber;

		Collections.shuffle(card.getAllCards());

		for (Player player : allPlayer) { // 余りは無視して均等に配る

			List<String> playerMyHand = card.getAllCards().subList(myHandNumber * allPlayer.indexOf(player),
					myHandNumber * (allPlayer.indexOf(player) + 1));

			playerMyHand.forEach(c -> player.getMyHand().getList().add(c));

		}

		if (remainCards != 0) { // 余ったカードを再分配
			int count = 0;
			for (int i = allCardsNumber - 1; i > (allCardsNumber - 1) - remainCards; i--) { // Listの最後尾から順番に分配

				List<String> myHand = allPlayer.get(count).getMyHand().getList();
				String remainCard = card.getAllCards().get(i);

				myHand.add(remainCard);

				count++;
			}
		}
		System.out.println();
		System.out.println("カードを配ります");
	}
}

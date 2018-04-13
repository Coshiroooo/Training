package main5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottery {
	
	private List<Integer> winningNumbers = new ArrayList<Integer>(); // 抽選番号のList
	private BingoCard bingoCard; //コンストラクタで持ってきたbingoCardインスタンス
	
	Lottery(BingoCard bingoCard){ //コンストラクタ
		this.bingoCard = bingoCard;
	}
	
	public void makeWinningNumbers(BingoCard bingocard) { // 抽選番号のListを用意する
		if (bingoCard.getCardWidth() <= 10) { // 10マス以下の時は上限100までの数字が入る
			for (int i = 1; i <= 100; i++) {
				winningNumbers.add(i);
			}
		} else { // 11マス以上は、マス数と同じ数だけの数字が入る
			for (int i = 1; i <= Math.pow(bingoCard.getCardWidth(), 2); i++) {
				winningNumbers.add(i);
			}
		}
		Collections.shuffle(winningNumbers); // シャッフル
	}
	
	//ゲッター//
	
	public List<Integer> getWinningNumbers(){
		return winningNumbers;
	}
}

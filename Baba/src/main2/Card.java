package main2;

public class Card {

	private String mark;
	private int number;
	private String exception;

	// コンストラクタ マーク＋数字があるカード
	Card(String mark, int number) {
		this.mark = mark;
		this.number = number;
	}

	// コンストラクタのオーバーロード Joker or nullCardの生成
	Card(String exception) {
		this.exception = exception;
	}

	// カードの数字が等しいか真偽を判定するメソッド
	public Boolean isSameNumber(Card card) {
		return card.getNumber() == this.number;
	}

	// 出力時にカードの情報をStringにするメソッド
	public String toString() {
		if (exception == "joker") {
			return exception;
		} else {
			return mark + String.valueOf(number);
		}
	}

	// ゲッター

	public int getNumber() {
		return this.number;
	}

	public String getException() {
		return this.exception;
	}

}

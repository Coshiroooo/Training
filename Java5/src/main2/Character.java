package main2;

abstract public class Character {
	
	private String name;
	private String job;
	
	Character(String name){ //コンストラクタ
		this.name = name;
	}
	
	abstract public void attack(); //抽象メソッド
	
	public void printCharaData() {
		System.out.println();
		System.out.println("《キャラクター情報》");
		System.out.println("　〈名前〉：" + this.name);
	}
	
	public String getName() {
		return this.name;
	}
}

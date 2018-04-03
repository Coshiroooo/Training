package main.java;
import java.util.ArrayList;

public class Practice3 {
	public static void main(String[] args) {
		String[] animals = {"なんじゃもんじゃ","ねこ","プエルトリコヒメエメラルドハチドリ","わんこ","ツキノワグマ"," ホッキョクグマ","ニューギニアヒメテングフルーツコウモリ"};
		
		//String[] longName;
		ArrayList<String> longName = new ArrayList<String>(0);
		//String[] shortName;
		ArrayList<String> shortName = new ArrayList<String>(0);
		
		for(String animal: animals) {
			if(animal.length() > 8) {
				longName.add(animal);
			}else {
				shortName.add(animal);
			}
		}
		
		System.out.println("長い名前の動物は"+ longName + "です");
		System.out.println("短い名前の動物は"+ shortName + "です");
 }
}

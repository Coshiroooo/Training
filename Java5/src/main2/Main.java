//のらねこクエスト

package main2;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//import java.util.EventObject;
//import java.awt.event.KeyEvent;

public class Main {
	public static void main(String[] args)throws IOException{ //例外処理
		
		Scanner scanner = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("【のらねこクエスト】");
		System.out.println("　　Game Start");
		System.out.println("Enterを押してください");
			enter();
		System.out.println("キャラクターを選んでください");
			CharaM charaM = new CharaM("もっちゃん");
			CharaB charaB = new CharaB("べっちゃん");
			CharaN charaN = new CharaN("ノザキさん");
			System.out.println("　1." + charaM.getName());
			System.out.println("　2." + charaB.getName());
			System.out.println("　3." + charaN.getName());
			System.out.println();
		System.out.print("どのキャラクターでプレイしますか？「1」「2」「3」のいずれかを入力してください：");		
			playCharaSelect(charaM,charaB,charaN);
			
		System.out.println("このキャラクターでOKですか？");
			System.out.println("OKなら「1」を、変更するなら「2」を入力してください");
			
				int playCharaConfirm = scanner.nextInt();
				if(playCharaConfirm == 1) {
					System.out.print("");
				}else if(playCharaConfirm == 2) {
					
				}
			
		}
	
	public static void enter() throws IOException{ //Enter押すと次の処理が始まる
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append(br);
		String enter = sb.toString();
		System.in.read();
		switch(enter){
			case "\r\n":
				break;
		}
	}
	
	public static void playCharaSelect(CharaM charaM,CharaB charaB,CharaN charaN) {
		
		Scanner scanner = new Scanner(System.in);
		
		int playChara = scanner.nextInt();
		
		while(!(playChara == 1 || playChara == 2 || playChara == 3)) { //1,2,3以外を選択した場合は正しく入力されるまで繰り返す
			System.out.println("「1」「2」「3」のいずれかを入力してください：");
			playChara = scanner.nextInt(); //ループで使うときはデータ型定義をしない
		}
		
		switch(playChara){
			case 1:
				charaM.printCharaData();
				break;
			case 2:
				charaB.printCharaData();
				break;
			case 3:
				charaN.printCharaData();
				break;
		}
	}
	
}

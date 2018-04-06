//Java道場2のカスタマイズ
//どんどん燃費を悪くする
//走行距離や給油量をランダムにする
//どれだけ長く走れるか

package main4;
import java.util.Scanner;
import java.util.Random;

public class Practice {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Practice practice = new Practice();
		Bicycle bicycle = new Bicycle("ビアンキ","緑");
		Car car = new Car("フェラーリ","赤");
		
		bicycle.printData();
		System.out.println("---------------");
		System.out.print("走る距離を入力してください：");
		int disBicycle = scanner.nextInt();
		System.out.println(disBicycle + "km走ります");
		bicycle.run(disBicycle);
		System.out.println("===============");
		
		car.printData();
		System.out.println("---------------");
		practice.carRun(car); //Practiceをインスタンス化し、別メソッドを呼び出し
		
	}
	
	public void carRun(Car car) { //車が走ってガソリン減らしての一連の動作をメソッド化
		Random random = new Random();
		int disCar = random.nextInt(50) + 10; //10~60のランダム整数値をとる

		car.run(disCar);
		if(car.getFuel() == 0) {
			car.goal();
			return; //ループを終了させる、処理を中断させる
		}
		System.out.println("---------------");
		System.out.println("給油タイム");
		int oil = random.nextInt(30); //0~30Lのランダム給油量
		car.charge(oil,car);
	}
}

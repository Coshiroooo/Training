//Java道場2の再現

package main3.java;
import java.util.Scanner;

public class Practice {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
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
		System.out.print("走る距離を入力してください：");
		int disCar = scanner.nextInt();
		System.out.println(disCar + "km走ります");
		car.run(disCar);
		System.out.println("---------------");
		System.out.print("給油する量を入力してください：");
		int oil = scanner.nextInt();
		car.charge(oil);
		
		
	}
}

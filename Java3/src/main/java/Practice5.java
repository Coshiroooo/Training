//Java3で学んだこと
//メソッドの使い方/クラスの使い方/Mathクラス/Scannerクラス/真偽値で返すメソッド

package main.java;
import java.util.Scanner;

public class Practice5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Person person = new Person();
		
		System.out.print("何人分の情報を入力しますか？：");
		int member = scanner.nextInt();
		
		int maxAge = 0;
		int totalAge = 0;
		
		for(int i = 1; i <= member; i++) {
			
			System.out.println(i + "人目");
			System.out.println();
		
			System.out.print("名前：");
			String firstName = scanner.next();
			System.out.print("名字：");
			String lastName = scanner.next();
			System.out.print("年齢：");
			int age = scanner.nextInt();
			System.out.print("身長(m)：");
			double height = scanner.nextDouble();
			System.out.print("体重(kg)：");
			double weight = scanner.nextDouble();
			
			printData(person.fullName(firstName,lastName),age,height,weight);
			
			if(age >= maxAge) {
				maxAge = age;
			}
			
			totalAge += age;
			
		}
		
		System.out.println("最高年齢は" + maxAge + "歳です");
		System.out.println("平均年齢は" + totalAge/member + "歳です");
	}
	
	public static void printData(String fullName,int age,double height,double weight) {
		Person person = new Person();
		
		System.out.println("名前は" + fullName + "です");
		System.out.println("年齢は" + age + "歳です");
		if(age >= 20) {
			System.out.println("成年者です");
		}else {
			System.out.println("未成年者です");
		}
		System.out.println("身長は" + height + "mです");
		System.out.println("体重は" + weight + "kgです");
		
		double bmi = person.bmi(height, weight); 
		
		System.out.println("BMIは" + Math.round(bmi) + "です");
		if(person.isHealthy(bmi)) {
			System.out.println("健康です");
		}else {
			System.out.println("健康ではありません");
		}
	}
}

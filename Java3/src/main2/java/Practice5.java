//Java3で学んだこと
//メソッドの使い方/クラスの使い方/Mathクラス/Scannerクラス/真偽値で返すメソッド

package main2.java;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Random;

public class Practice5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Person person = new Person();
		
		System.out.print("何人分の情報を入力しますか？：");
		int member = scanner.nextInt();
		System.out.println();
		
		int maxAge = 0;
		int totalAge = 0;
		
		int nextNearBirthDay = 365;
		int nearM = 0;
		int nearD = 0;
		String nearPerson = null;
		
		for(int i = 1; i <= member; i++) {
			
			System.out.println("---------------");
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
			System.out.print("誕生日(年)：");
			int year = scanner.nextInt();
			System.out.print("誕生日(月)：");
			int month = scanner.nextInt();
			System.out.print("誕生日(日)");
			int day = scanner.nextInt();
			
			
			printData(person.fullName(firstName,lastName),age,height,weight,year,month,day);
			
			if(age >= maxAge) {
				maxAge = age;
			}
			
			totalAge += age;
			
			if(nextNearBirthDay >= Person.nextBirth(year, month, day)) {
				nextNearBirthDay = Person.nextBirth(year, month, day);
				nearM = month;
				nearD = day;
				nearPerson = person.fullName(firstName, lastName);
			}
		}
		
		System.out.println();
		System.out.println("---------------");
		System.out.println(member + "人の最高年齢は" + maxAge + "歳です");
		System.out.println(member + "人の平均年齢は" + totalAge/member + "歳です");System.out.println();
		System.out.println("一番誕生日が近い人は" + nextNearBirthDay + "日後の" + nearM + "月" + nearD + "日の" + nearPerson + "さんです！みんなでお祝いしましょう！"  );
	}
	
	public static void printData(String fullName,int age,double height,double weight,int year,int month, int day) {
		Person person = new Person();
		//名前
		System.out.println("名前は" + fullName + "です");
		//年齢
		System.out.println("年齢は" + age + "歳です");
		if(age >= 20) {
			System.out.println("成年者です");
		}else {
			System.out.println("未成年者です");
		}
		//身長体重BMI
		System.out.println("身長は" + height + "mです");
		System.out.println("体重は" + weight + "kgです");
		
		double bmi = person.bmi(height, weight); 
		
		System.out.println("BMIは" + Math.round(bmi) + "です");
		if(person.isHealthy(bmi)) {
			System.out.println("健康です");
		}else {
			System.out.println("健康ではありません");
		}
		System.out.println();
		System.out.println("誕生日は" + year + "年" + month + "月" + day + "日です");
		System.out.println("次の誕生日まであと" + person.nextBirth(year,month,day) + "日です");
		System.out.println();
		
		System.out.println("今日の運勢は" + Fortune.tellFortune() + "です");
	}
}

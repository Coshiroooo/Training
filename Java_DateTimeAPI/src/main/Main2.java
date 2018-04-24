package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Main2 {
	
	public static void main(String[] args) throws IOException{
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("生まれた年は？：");
		int birthdayY = scanner.nextInt();
		System.out.print("生まれた月は？：");
		int birthdayM = scanner.nextInt();
		System.out.print("生まれた日は？：");
		int birthdayD = scanner.nextInt();
		
		String birthday = String.valueOf(birthdayY) + "-" + String.format("%02d",birthdayM) + "-" + String.format("%02d",birthdayD);
		
		birthdayCalc(birthday);
		
		System.out.println();
		System.out.println("--------------------------------------------");
		
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime localDateTime2 = localDateTime.plusHours(5);
		
		localDateTime.truncatedTo(ChronoUnit.DAYS);
		localDateTime2.truncatedTo(ChronoUnit.DAYS);
		
		if(localDateTime.isEqual(localDateTime)) {
			System.out.println("まだ日付は変わっとらんよ");
		}else {
			System.out.println("明日になってもうた、、、");
		}
		
	}
	
	public static void birthdayCalc(String birthday) {
		System.out.println();
		LocalDate localDate = LocalDate.now();
		LocalDate myBirthDay = LocalDate.parse(birthday);
		System.out.println("今日は" + localDate.getYear() + "年" + localDate.getMonthValue()+ "月" + localDate.getDayOfMonth() + "日");
		System.out.println("誕生日は" + myBirthDay.getYear() + "年" + myBirthDay.getMonthValue() + "月" + myBirthDay.getDayOfMonth() + "日");
		
		System.out.println();
		
		long localDiffDay1 = ChronoUnit.DAYS.between(myBirthDay,localDate);
		System.out.println("生まれてから" + localDiffDay1 + "日やで");
	}
	
}

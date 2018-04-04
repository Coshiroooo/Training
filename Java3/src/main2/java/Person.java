package main2.java;
//import java.util.Date; //日付を扱うクラス
//import java.util.Calendar; //日付の計算、計算などを扱うクラス
//import java.time.LocalDateTime; //現在日時を取得するクラス
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class Person {
	
	public static String fullName(String firstName,String lastName) { //名前を連結させるメソッド
		return firstName + " " + lastName;
	}
	
	public static double bmi(double height,double weight) { //BMI値を計算するメソッド
		return weight / height / height;
	}
	
	public static boolean isHealthy(double bmi) {
		if(18.5 <= bmi && bmi < 25.0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static int nextBirth(int year,int month,int day) { //次の誕生日まであと何日かを計算してint型で返すメソッド
		//次の誕生日を算出
		LocalDate today = LocalDate.now(); //今日の日付を取得
		int nextY = today.getYear(); //月や日の大きさによって+1するかしないか変わる
		int nextM = month; //不変
		int nextD = day; //不変
		if(month < today.getMonthValue()) { //条件分岐 
			nextY++;
		}else if(month == today.getMonthValue()) {
			if(day <= today.getDayOfMonth()) {
				nextY++;
			}
		}
		
		//今日から次の誕生日までの日にち
		LocalDate nextBirthDay = LocalDate.of(nextY,nextM,nextD);
		
		return (int)(ChronoUnit.DAYS.between(today,nextBirthDay));
		
	}
}

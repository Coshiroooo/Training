package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Main2 {
	
	public static void main(String[] args) throws IOException{
		
		hello();
		
		System.out.println();
		System.out.println("--------------------------------------------");
		
		getPremiumFriday();
		
		System.out.println();
		System.out.println("--------------------------------------------");
		
		birthdayCalc();
		
		System.out.println();
		System.out.println("--------------------------------------------");
		
		hoursAgo(5);
		
		System.out.println();
		System.out.println("--------------------------------------------");
		
		lunchTimeCalc();
		
		System.out.println();
		System.out.println("--------------------------------------------");
		
		timeZoneNow();
		
		System.out.println();
		System.out.println("--------------------------------------------");
		
		System.out.println("生まれた日の曜日は" + getBirthDayOfWeek() + "です！！！");
		
	}
	
	//年齢とか生まれてからの日数とか次の誕生日まであと何日かとかを計算するメソッド
	public static void birthdayCalc() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("生まれた年は？：");
		int birthdayY = scanner.nextInt();
		System.out.print("生まれた月は？：");
		int birthdayM = scanner.nextInt();
		System.out.print("生まれた日は？：");
		int birthdayD = scanner.nextInt();
		
		String birthday = String.valueOf(birthdayY) + "-" + String.format("%02d",birthdayM) + "-" + String.format("%02d",birthdayD);
		
		System.out.println();
		LocalDate localDate = LocalDate.now();
		LocalDate myBirthDay = LocalDate.parse(birthday);
		System.out.println("今日は" + localDate.getYear() + "年" + localDate.getMonthValue()+ "月" + localDate.getDayOfMonth() + "日");
		System.out.println("誕生日は" + myBirthDay.getYear() + "年" + myBirthDay.getMonthValue() + "月" + myBirthDay.getDayOfMonth() + "日");
		
		System.out.println();
		
		long localDiffDay1 = ChronoUnit.YEARS.between(myBirthDay, localDate);
		System.out.println("今、" + localDiffDay1 + "歳やで");
		
		long localDiffDay2 = ChronoUnit.DAYS.between(myBirthDay,localDate);
		System.out.println("生まれてから" + localDiffDay2 + "日やで");
		
		if(localDate.getMonthValue() == myBirthDay.getMonthValue() && localDate.getDayOfMonth() == localDate.getDayOfMonth()) {
			System.out.println("今日は誕生日やでおめでとう");
		}else {
			System.out.println("今日は誕生日じゃないよ");
		}
		
		LocalDate nextBirthDay = myBirthDay.plusYears(localDiffDay1 + 1);
		
		long localDiffDay3 = ChronoUnit.DAYS.between(localDate, nextBirthDay);
		System.out.println("次の誕生日まで" + localDiffDay3 + "日");
		
	}
	
	//入力した時間後は日付が変わっているかを調べるメソッド
	public static void hoursAgo(int hours) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime localDateTime2 = localDateTime.plusHours(hours);
		
		System.out.println("今、" + localDateTime.truncatedTo(ChronoUnit.MINUTES).format(formatter) + "です");
		System.out.println(hours + "時間後は" + localDateTime2.truncatedTo(ChronoUnit.MINUTES).format(formatter) + "です");
		
		localDateTime.truncatedTo(ChronoUnit.DAYS);
		localDateTime2.truncatedTo(ChronoUnit.DAYS);
		
		if(localDateTime.isEqual(localDateTime)) {
			System.out.println("まだ日付は変わっとらんよ");
		}else {
			System.out.println("明日になってもうた、、、");
		}
	}
	
	//お昼ご飯まであと何分か教えてくれるメソッド
	public static void lunchTimeCalc() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime lunchTime = LocalDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(), 13,0,0);
		
		long lunchDiff = ChronoUnit.MINUTES.between(now, lunchTime);
		if(lunchDiff > 0) {
			System.out.println("お昼ご飯まであと" + lunchDiff + "分");
		}else {
			System.out.println("ランチタイム始まってるよ！早く食べなきゃ！");
		}
	}
	
	public static void timeZoneNow() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		
		ZonedDateTime tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		
		ZoneId zoneIdHawaii = ZoneId.of("US/Hawaii");
		ZoneId zoneIdNewYork = ZoneId.of("America/New_York");
		
		ZonedDateTime hawaiiTime = tokyoTime.withZoneSameInstant(zoneIdHawaii);
		ZonedDateTime newYorkTime = tokyoTime.withZoneSameInstant(zoneIdNewYork);
		
		System.out.println("東京は今、" + tokyoTime + "です");
		System.out.println("ハワイは今、" + hawaiiTime + "です");
		System.out.println("ニューヨークは今、" + newYorkTime + "です");
		
		System.out.println("東京は今、" + tokyoTime.format(formatter) + "です");
		System.out.println("ハワイは今、" + hawaiiTime.format(formatter) + "です");
		System.out.println("ニューヨークは今、" + newYorkTime.format(formatter) + "です");
	}
	
	public static DayOfWeek getBirthDayOfWeek() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("生まれた年は？：");
		int birthdayY = scanner.nextInt();
		System.out.print("生まれた月は？：");
		int birthdayM = scanner.nextInt();
		System.out.print("生まれた日は？：");
		int birthdayD = scanner.nextInt();
		
		String birthday = String.valueOf(birthdayY) + "-" + String.format("%02d",birthdayM) + "-" + String.format("%02d",birthdayD);
		
		LocalDate myBirthDay = LocalDate.parse(birthday);
		
		return myBirthDay.getDayOfWeek();
	}
	
	public static void hello() {
		LocalDateTime now = LocalDateTime.now();
		
		LocalTime Morning = LocalTime.of(6, 0);
		LocalTime AfterNoon = LocalTime.of(12, 0);
		LocalTime Night = LocalTime.of(18, 0);
		LocalTime GoodNight = LocalTime.of(0, 0);
		
		if(now.toLocalTime().isBefore(AfterNoon) && now.toLocalTime().isAfter(Morning)) {
			System.out.println("おはようございます！！！！！！！！！！！！");
		}else if(now.toLocalTime().isBefore(Night) && now.toLocalTime().isAfter(AfterNoon)) {
			System.out.println("こんにちはーーーーーー！！！！！！！！！！！！！");
		}else if(now.isBefore(LocalDateTime.of(now.toLocalDate().plusDays(1), GoodNight)) && now.toLocalTime().isAfter(Night)) {
			System.out.println("こんばんはーーーーーーーーーーーーーー！！！！！！！！！！");
		}else {
			System.out.println("おやすみなさーーーい！！！！！！！！！！！！！");
		}
	}
	
	//来月のプレミアムフライデーを教えてくれるメソッド
	public static void getPremiumFriday() {
		LocalDate today = LocalDate.now();
		LocalDate nextMonth = today.plusMonths(1);
		List<Integer> nextMonthFriday = new ArrayList<Integer>();
		for(int i = 1; i <= nextMonth.lengthOfMonth(); i++) {
			LocalDate nextMonthDay = LocalDate.of(nextMonth.getYear(), nextMonth.getMonthValue(), i);
			if(nextMonthDay.getDayOfWeek() == DayOfWeek.FRIDAY) {
				nextMonthFriday.add(i);
			}
		}
		Optional<Integer> max = nextMonthFriday.stream().max((a,b) -> a.compareTo(b));
		LocalDate nextMonthPremiumFriday = LocalDate.of(nextMonth.getYear(), nextMonth.getMonthValue(), max.get());
		System.out.println("来月のプレミアムフライデーは" + nextMonthPremiumFriday.getMonthValue() + "月" + nextMonthPremiumFriday.getDayOfMonth() + "日だ！！！");
	}
}

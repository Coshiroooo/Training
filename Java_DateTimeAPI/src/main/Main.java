package main;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		String msg =
				"LocalDate:" + LocalDate.now() + "\n" +
				"LocalTime:" + LocalTime.now() + "\n" +
				"LocalDateTime:" + LocalDateTime.now() + "\n" +
				"OffsetTime:" + OffsetTime.now() + "\n" +
				"OffsetDateTime:" + OffsetDateTime.now() + "\n" +
				"ZonedDateTime:" + ZonedDateTime.now()
				;
		System.out.println(msg);
		
		LocalDate localDate = LocalDate.parse("2018-04-24");
		System.out.println(localDate);
		
		OffsetDateTime offsetDateTime = OffsetDateTime.parse("2018-04-24T17:00:00+09:00");
		System.out.println(offsetDateTime);
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
//		System.out.println(LocalDate.parse("2018-04-24",formatter));
		
		//フォーマットを指定して文字列に変換する
		DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy/MM/dd");
		System.out.println(LocalDate.now().format(formatter));
		
		//Date->LocalDateTIme
//		Date date = new Date();
//		Instant instant = date.toInstant();
//		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(9));
//		System.out.println(localDateTime);
		
		//LocalDateTime->Date
		LocalDateTime localDateTime = LocalDateTime.now();
		Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(9));
		Date date = Date.from(instant);
		System.out.println(date);
		
		test("SystemDefaultZone()",Clock.systemDefaultZone());
		test("fixed()",Clock.fixed(OffsetDateTime.now().toInstant(), ZoneOffset.ofHours(9)));
	}
	
	public static void test(String tag,Clock clock) {
		System.out.println(tag + ": clock.class = " + clock.getClass().getSimpleName());
		System.out.println(LocalDateTime.now(clock));
		sleep(1000);
		System.out.println(LocalDateTime.now(clock));
		sleep(1000);
		System.out.println(LocalDateTime.now(clock));
	}
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch(InterruptedException e) {}
	}
}

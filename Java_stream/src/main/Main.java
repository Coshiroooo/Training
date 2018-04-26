package main;

import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	private static Trump trump = new Trump();

	public static void main(String[] args) {
		
		animalPrint();
		
		getMark("♡");
		
		System.out.println();
		System.out.println("----------------------------");
		
		changeStar("♡");
		
		System.out.println();
		System.out.println("----------------------------");
		
		getFive();
		
		System.out.println();
		System.out.println("----------------------------");
		
		reduce();
	}
	
	public static void animalPrint() {
		List<String> animals = Arrays.asList("ねこ","いぬ","らいおん","ぶた","しか","ぞう","たぬき","きつね","さる","きじ","いぬ");
		
		for(String animal : animals) {
			if(animal.contains("き")) {
				System.out.println(animal);
			}
		}
		
		//「き」が含まれている文字列だけを取り出して、出力する
		animals.stream().filter(a -> a.contains("き")).forEach(System.out::print);
		
		System.out.println();
		System.out.println("----------------------------");
		
		//animalsの要素の個数を数える //count()は終端操作
		System.out.println(animals.stream().count());
		
		System.out.println("----------------------------");
		
		//文字数順に並べる //sorted -> Comparator
		animals.stream().sorted((l,r) -> l.length() - r.length()).forEach(System.out::print);
		
		System.out.println();
		System.out.println("----------------------------");
		
		//全部にさんづけする //map -> Function //forEach -> Consumer
		animals.stream().map(a -> a + "さん").forEach(System.out::print);
		
		System.out.println();
		System.out.println("----------------------------");
		
		//最初から3つ分しか取ってこない
		animals.stream().limit(3).forEach(System.out::print);
		
		System.out.println();
		System.out.println("----------------------------");
		
		//重複したものは表示させない
		animals.stream().distinct().forEach(System.out::print);
		
		System.out.println();
		System.out.println("----------------------------");
		
		//データ型をList型に戻して受け渡す
		List<String> animals2 = animals.stream().filter(a -> a.length() == 2).collect(Collectors.toList());
		animals2.forEach(System.out::print);
		
		System.out.println();
		System.out.println("----------------------------");
		
		//要素をつなぎ合わせる
		String animal = animals.stream().limit(3).collect(Collectors.joining("-"));
		System.out.println(animal);
		
		System.out.println("----------------------------");
		
		//文字数が2の文字列があればtrueを返す
		System.out.print(animals.stream().anyMatch(a -> a.length() == 2));
		
		System.out.println();
		System.out.println("----------------------------");
		
		//全部の文字列の文字数が1より長かったらtrueを返す
		System.out.print(animals.stream().allMatch(a -> a.length() > 1));
		
		System.out.println();
		System.out.println("----------------------------");
		
		//Listの最初の値を返す //戻り値はOptional<T>なので注意
		System.out.print(animals.stream().findFirst().get());
		
		System.out.println();
		System.out.println("----------------------------");
		
		//flatMap 戻り値がStreamになるMap
		animals.stream().flatMap(a -> Stream.of(a, a.length())).collect(Collectors.toList()).forEach(System.out::print);
		
		System.out.println();
		System.out.println("----------------------------");
		
		//skip 読み飛ばす
		animals.stream().skip(2).limit(6).forEach(System.out::print);
		
		System.out.println();
		System.out.println("----------------------------");
		
		//文字数の最大 //Optionalで出力されるので注意
		System.out.print(animals.stream().max((a1,a2) -> a1.length() - a2.length()).get());
		
		System.out.println();
		System.out.println("----------------------------");
		
		
	}

	public static void getMark(String mark) {
		trump.getAllCards().stream()
							.sorted((m1, m2) -> m1.getNumber() - m2.getNumber())
							.filter(m -> m.getMark().equals(mark))
							.map(m -> m + " ")
							.forEach(System.out::print);
	}

	public static void changeStar(String mark) {
		trump.getAllCards().stream()
							.sorted((m1,m2) -> m1.getNumber() - m2.getNumber())
							.filter(m -> m.getMark().equals(mark))
							.forEach(m -> {
									m.setMark("☆");
									System.out.print(m);
									});
	}
	
	public static void getFive() {
		trump.getAllCards().stream()
							.limit(5)
							.forEach(System.out::print);
	}
	
	public static void reduce() {
		List<Integer> numbers = Stream.of(1,2,3,4,5).collect(Collectors.toList());
		int number = numbers.stream().reduce(0,(number1,number2) -> number1 + number2);
		System.out.println(number);
	}
	
	public static void reduce2() {
		int number1 = IntStream.rangeClosed(1,15).reduce(1,(n1,n2) -> n1*n2);
		System.out.print(number1);
	}

}

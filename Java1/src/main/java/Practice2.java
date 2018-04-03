package main.java;

public class Practice2 {
	public static void main(String[] args) {
		String name = "Koshiro";
		int age = 22;
		double height = 1.77;
		double weight = 80.0;
		double bmi = weight/height/height;
		
		System.out.println("私の名前は" + name + "です");
		System.out.println("私の年齢は" + age + "歳です");
		System.out.println("私の身長は" + height + "mです");
		System.out.println("私の体重は" + weight + "kgです");
		System.out.println("私のBMIは" + bmi + "です");
	}
}

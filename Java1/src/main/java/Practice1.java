package main.java;
import java.math.BigDecimal;

public class Practice1 {
	public static void main(String[] args) {
		String name = "Koshiro";
		int age = 22;
		double height = 1.77;
		double weight = 80.0;
		double bmi = weight/height/height;
		boolean truth = true;
		char m = '本';
		char y = '山';
		char k = '幸';
		char s = '志';
		char r = '郎';
		int m2 = (int)m;
		int y2 = (int)y;
		String m3 = String.valueOf(m);
		String y3 = String.valueOf(y);
		double t1 = 3.4;
		double t2 = 3.2;
		double t3 = 3.1;
		double t4 = 3.0;
		double t5 = 2.0;
		BigDecimal bt1 = new BigDecimal(String.valueOf(t1));
		BigDecimal bt2 = new BigDecimal(String.valueOf(t2));
		
		System.out.println("私の名前は" + name + "です");
		System.out.println("私の年齢は" + age + "歳です");
		System.out.println("私の身長は" + height + "mです");
		System.out.println("私の体重は" + weight + "kgです");
		System.out.println("私のBMIは" + bmi + "です");
		System.out.println(truth);
		System.out.println(m);
		System.out.println(y);
		System.out.println(k);
		System.out.println(s);
		System.out.println(r);
		System.out.println(m + y);
		System.out.println(m2);
		System.out.println(y2);
		System.out.println(m3 + y3);
		System.out.println(t2);
		System.out.println(t1-t2);
		System.out.println(t2-t3);
		System.out.println(t1+t2);
		System.out.println(t2+t3);
		System.out.println(t4/t5);
		System.out.println(t4-t5);
		System.out.println(t3-t5);
		BigDecimal b1 = (bt1.subtract(bt2));
		System.out.println(b1.toPlainString());
		System.out.println(b1.doubleValue());
	}
}

package main.java;

public class Person {
	
	public static String fullName(String firstName,String lastName) {
		return firstName + " " + lastName;
	}
	
	public static double bmi(double height,double weight) {
		return weight / height / height;
	}
	
	public static boolean isHealthy(double bmi) {
		if(18.5 <= bmi && bmi < 25.0) {
			return true;
		}else {
			return false;
		}
	}
}

package main1.java;

public class Person {
	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	private double height;
	private double weight;
	
	Person(String firstName,String lastName,int age,double height,double weight){ //コンストラクタ
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	Person(String firstName,String middleName,String lastName,int age,double height,double weight){ //コンストラクタのオーバーロード
		this(firstName,lastName,age,height,weight);
		this.middleName = middleName;
	}
	
	public void printData() { 
		System.out.println("名前は" + fullName() +"です");
		System.out.println("年齢は" + this.age + "です");
		System.out.println("身長は" + this.height + "です");
		System.out.println("体重は" + this.weight + "です");
		System.out.println("BMIは" + Math.round(bmi(this.height,this.weight)) + "です");
	}
	
	public void buy(Vehicle vehicle) {
		vehicle.setOwner(this);
	}
	
	public String fullName() {
		if(this.middleName == null) {
			return this.firstName + " " + this.lastName;
		}else {
			return this.firstName + " " + this.middleName + " " + this.lastName;
		}
	}
	
	public double bmi(double height,double weight) {
		return weight / height / height;
	}
	
}

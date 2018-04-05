package main1.java;

public class Person {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	private double height;
	private double weight;
	private String job;
	
	Person(String firstName,String lastName,int age,double height,double weight,String job){ //コンストラクタ
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.job = job;
	}
	
	Person(String firstName,String middleName,String lastName,int age,double height,double weight,String job){
		this(firstName,lastName,age,height,weight,job);
		this.middleName = middleName;
	}
	
	public void printData() {
		System.out.println("私の名前は" + this.fullName() + "です");
		System.out.println("年齢は" + this.age + "歳です");
		System.out.println("BMIは" + Math.round(bmi(this.height,this.weight)) + "です");
		System.out.println("仕事は" + this.job + "です");
	}
	
	public String fullName() {
		if(this.middleName == null) {
			return firstName + " " + lastName; 
		}else {
			return firstName + " " + middleName + " " + lastName;
		}
	}
	
	public double bmi(double height,double weight) {
		return weight / height /height;
	}
	
	public String getJob() {
		return this.job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
}

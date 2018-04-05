package main2;

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
	
	//コンストラクタのオーバーロード
	Person(String firstName,String middleName,String lastName,int age,double height,double weight,String job){
		this(firstName,lastName,age,height,weight,job);
		this.middleName = middleName;
	}
	
	public void printData() { //出力するメソッド
		System.out.println("私の名前は" + this.fullName() + "です");
		System.out.println("年齢は" + this.age + "歳です");
		System.out.println("BMIは" + Math.round(bmi(this.height,this.weight)) + "です");
		System.out.println("仕事は" + this.job + "です");
	}
	
	public String fullName() { //名前を連結させるメソッド
		if(this.middleName == null) {
			return firstName + " " + lastName; 
		}else {
			return firstName + " " + middleName + " " + lastName;
		}
	}
	
	public double bmi(double height,double weight) { //bmiを計算するメソッド
		return weight / height /height;
	}
	
	//ゲッター一覧
	public String getFirstName() {
		return this.firstName;
	}
	public String getMiddleName() {
		return this.middleName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public int getAge() {
		return this.age;
	}
	public double getHeight() {
		return this.height;
	}
	public double getWeight() {
		return this.weight;
	}
	public String getJob() {
		return this.job;
	}
	
	//セッター一覧
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setHight(double height) {
		this.height = height;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setJob(String job) {
		this.job = job;
	}
}

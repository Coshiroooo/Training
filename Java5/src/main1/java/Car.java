package main1.java;

public class Car extends Vehicle{
	
	private int fuel = 50;
	
	Car(String name,String color){
		super(name,color);
	}
	
	public void printData() {
		super.printData();
		System.out.println("ガソリン量：" + this.fuel + "L");
	}
	
	public void run(int distance) {
		System.out.println(distance + "km走ります");
		if(distance <= this.fuel) {
			this.distance += distance;
			this.fuel -= distance;
		}else {
			System.out.println("ガソリンが足りません");
		}
		System.out.println("走行距離：" + this.distance + "km");
		System.out.println("ガソリン量：" + this.fuel + "L");
	}
	
	public void charge(int oil) {
		System.out.println(oil + "L給油します");
		if(oil <= 0) {
			System.out.println("給油できません");
		}else if(oil + this.fuel >= 100) {
			System.out.println("満タンまで給油します");
			this.fuel = 100;
		}else {
			this.fuel += oil;
		}
		System.out.println("ガソリン量：" + this.fuel + "L");
	}
}

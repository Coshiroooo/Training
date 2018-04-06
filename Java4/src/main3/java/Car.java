package main3.java;

public class Car {
	
	private String name;
	private String color;
	private int distance = 0;
	private int fuel = 100;
	
	Car(String name,String color){
		this.name = name;
		this.color = color;
	}
	
	public void printData() {
		System.out.println("【車の情報】");
		System.out.println("名前：" + this.name);
		System.out.println("色：" + this.color);
		System.out.println("走行距離：" + this.distance + "km");
	}
	
	public void run(int disCar) {
		if(disCar <= this.fuel) {
			this.distance += disCar;
			this.fuel -= disCar;
		}else if(disCar > this.fuel) {
			System.out.println("ガソリンが足りません");
		}
		System.out.println("走行距離：" + this.distance + "km");
		System.out.println("ガソリン量：" + this.fuel + "L");
	}
	
	public void charge(int oil) {
		if(oil + this.fuel >= 100) {
			System.out.println(oil + "L給油します");
			System.out.println("満タンまで給油します");
			this.fuel = 100;
		}else if(0 <oil + this.fuel && oil + this.fuel < 100) {
			System.out.println(oil + "L給油します");
			this.fuel += oil;
		}else if(oil <= 0) {
			System.out.println("給油できません");
		}
		System.out.println("ガソリン量：" + this.fuel + "L");
	}
}

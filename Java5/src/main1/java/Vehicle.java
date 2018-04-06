package main1.java;

abstract public class Vehicle {
	
	private String name;
	private String color;
	protected int distance = 0;
	private Person owner;
	
	Vehicle(String name,String color){
		this.name = name;
		this.color = color;
	}
	
	public void printData() { //車の情報を出力
		System.out.println("名前：" + this.name);
		System.out.println("色：" + this.color);
		System.out.println("走行距離：" + this.distance + "km");
	}
	
	abstract public void run(int distance);
	
	public void setOwner(Person person) {
		this.owner = person;
	}
	
	public Person getOwner() {
		return this.owner;
	}
}

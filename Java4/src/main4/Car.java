package main4;
import java.util.Random; //乱数を出す
import java.math.BigDecimal; //四捨五入用
import java.math.*;

public class Car {
	
	private String name;
	private String color;
	private int distance = 0;
	private int fuel = 100;
	private double fuelCon = 2.0; //燃費　初期値は2.0km/L 30km走ったら0.9倍になる
	
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
		
		double lastDistanceD = this.fuel * this.fuelCon;
		double oilCon = disCar / this.fuelCon;
		BigDecimal bd = new BigDecimal(String.valueOf(lastDistanceD));
		BigDecimal bd1 = bd.setScale(0,RoundingMode.HALF_UP); //小数点第１位を四捨五入
		int lastDistanceI = bd1.intValue(); //BigDecimal → int への変換
		BigDecimal bdo = new BigDecimal(String.valueOf(oilCon));
		BigDecimal bdo1 = bdo.setScale(0,RoundingMode.HALF_UP);
		int oilConI = bdo1.intValue();
		BigDecimal bdf = new BigDecimal(String.valueOf(this.fuelCon));
		BigDecimal fuelConNow = bdf.setScale(1,RoundingMode.HALF_UP);
		
		if(disCar / this.fuelCon < this.fuel) { //消費ガソリン量 < ガソリン残量
			System.out.println(disCar + "km走ります");
			this.distance += disCar;
			this.fuel -= disCar / this.fuelCon;
			System.out.println("これまでの走行距離" + this.distance + "km");
			System.out.println("現在の燃費：" + fuelConNow + "km/L");
			System.out.println("ガソリン消費量：" + oilConI + "L");
			System.out.println("ガソリン残量：" + this.fuel + "L");
		}else if(disCar / this.fuelCon >= this.fuel) { //消費ガソリン量 >= ガソリン残量
			System.out.println(lastDistanceI + "km走ります");
			this.distance += lastDistanceI;
			this.fuel = 0;
			System.out.println("これまでの走行距離" + this.distance + "km");
			System.out.println("現在の燃費：" + fuelConNow + "km/L");
			System.out.println("ガソリン消費量：" + oilConI + "L");
			System.out.println("ガソリン残量：" + this.fuel + "L");
		}
		this.fuelCon = this.fuelCon * (Math.pow(0.9,(this.distance/40))); //40km走るごとに燃費が0.9倍になっていく
	}
	
	public void charge(int oil,Car car) {
		Practice practice = new Practice();
		
		if(oil + this.fuel >= 100) {
			System.out.println(oil + "L給油します");
			System.out.println("満タンまで給油します");
			this.fuel = 100;
		}else if(0 < oil + this.fuel && oil + this.fuel < 100) {
			System.out.println(oil + "L給油します");
			this.fuel += oil;
		}else if(oil <= 0) {
			System.out.println("給油できません");
		}
		System.out.println("ガソリン残量：" + this.fuel + "L");
		System.out.println("===============");
		practice.carRun(car);
	}
	
	public void goal() {
		System.out.println("---------------");
		System.out.println("ガソリンが無くなりました");
		System.out.println("走行お疲れ様でした");
		System.out.println("合計走行距離は" + this.distance + "kmです");
	}
	
	public int getFuel() {
		return this.fuel;
	}
}

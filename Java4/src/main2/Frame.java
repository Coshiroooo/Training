package main2;
import java.util.Scanner;

public class Frame {
	
	private String[] firstName = new String[2];
	private String middleName;
	private String[] lastName = new String[2];
	private int[] age = new int[2];
	private double[] height = new double[2];
	private double[] weight = new double[2];
	private String[] job = new String[2];
	private int whichPersonNum ;
	
	public Frame(Person person1,Person person2){ //コンストラクタ
		
		this.firstName[0] = person1.getFirstName(); this.firstName[1] = person2.getFirstName();
		this.middleName = person2.getMiddleName();
		this.lastName[0] = person1.getLastName(); this.lastName[1] = person2.getLastName();
		this.age[0] = person1.getAge(); this.age[1] = person2.getAge();
		this.height[0] = person1.getHeight(); this.height[1] = person2.getHeight();
		this.weight[0] = person1.getWeight(); this.weight[1] = person2.getWeight();
		this.job[0] = person1.getJob(); this.job[1] = person2.getJob();
	}
	
	public void confirm(Person person1,Person person2) { //情報変更の確認をするメソッド
		System.out.println("上記の情報で確定してもよろしいでしょうか？");
		System.out.println("よろしければ『yes』を、変更する場合は『no』を入力してください");
		System.out.print("よろしいですか？：");
		this.judge(person1,person2);
	}
	
	public void judge(Person person1,Person person2) { //情報変更の有無を判断するメソッド
		Scanner scanner = new Scanner(System.in);
		String judge = scanner.next();
		if(judge.equals("yes")) {
			System.out.println("入力情報を確定させました！");
		}else if(judge.equals("no")){
			System.out.println();
			System.out.println("誰の情報を変更しますか？");
			System.out.println("Person1なら『1』を、Person2なら『2』を入力してください");
			this.selectPerson(person1,person2);
		}else {
			System.out.println("『yes』か『no』を入力してください");
			System.out.print("よろしいですか？：");
			this.judge(person1,person2);
		}
	}
	
	public void selectPerson(Person person1,Person person2) { //どのPersonを選択するのか選ぶメソッド
		System.out.print("Which Person?；");
		Scanner scanner = new Scanner(System.in);
		int whichPerson = scanner.nextInt();
		this.whichPersonNum(whichPerson);
		if(whichPerson == 1 || whichPerson == 2) {
			this.changeInfo(person1, person2);
		}else {
			System.out.println("『1』か『2』を入力してください");
			this.selectPerson(person1,person2);
		}
	}
	
	public void whichPersonNum(int whichPerson) { //selectPersonで選択した番号をwhichPersonNumに格納するメソッド
		this.whichPersonNum = whichPerson-1;
	}
	
	public void changeInfo(Person person1,Person person2) { //どの情報を変更するか決め、それをどのように変更するかを決めるメソッド
		Scanner scanner = new Scanner(System.in);
		
		System.out.println();
		System.out.println("どの情報を変更しますか？情報に対応したアルファベットを入力してください");
		System.out.println("名前:fn ミドルネーム:mn 名字: ln 年齢:a 身長:h 体重:w 仕事:j");
		System.out.print("アルファベット：");
		System.out.println();
		String selectInfo = scanner.next();
		if(selectInfo.equals("fn")) {
			System.out.println("名前を変更します");
			System.out.println("変更前の名前：" + this.firstName[whichPersonNum]);
			System.out.print("変更後の名前：");
			String newFirstName = scanner.next();
			this.firstName[whichPersonNum] = newFirstName;
			System.out.println("名前を" + newFirstName + "に変更しました");
		}else if(selectInfo.equals("mn")) {
			System.out.println("ミドルネームを変更します");
			System.out.println("変更前のミドルネーム：" + this.middleName);
			System.out.print("変更後のミドルネーム：");
			String newMiddleName = scanner.next();
			this.middleName = newMiddleName;
			System.out.println("ミドルネームを" + newMiddleName + "に変更しました");
		}else if(selectInfo.equals("ln")) {
			System.out.println("名字を変更します");
			System.out.println("変更前の名字：" + this.lastName[whichPersonNum]);
			System.out.print("変更後の名字：");
			String newLastName = scanner.next();
			this.lastName[whichPersonNum] = newLastName;
			System.out.println("名字を" + newLastName + "に変更しました");
		}else if(selectInfo.equals("a")) {
			System.out.println("年齢を変更します");
			System.out.println("変更前の年齢：" + this.age[whichPersonNum]);
			System.out.print("変更後の年齢：");
			int newAge = scanner.nextInt();
			this.age[whichPersonNum] = newAge;
			System.out.println("年齢を" + newAge + "歳に変更しました");
		}else if(selectInfo.equals("h")) {
			System.out.println("身長を変更します");
			System.out.println("変更前の身長(m)：" + this.height[whichPersonNum]);
			System.out.print("変更後の身長(m)：");
			double newHeight = scanner.nextDouble();
			this.height[whichPersonNum] = newHeight;
			System.out.println("身長を" + newHeight + "mに変更しました");
		}else if(selectInfo.equals("w")) {
			System.out.println("体重を変更します");
			System.out.println("変更前の体重(kg)" + this.weight[whichPersonNum]);
			System.out.print("変更後の体重(kg)：");
			double newWeight = scanner.nextDouble();
			this.weight[whichPersonNum] = newWeight;
			System.out.print("体重を" + newWeight + "kgに変更しました");
		}else if(selectInfo.equals("j")) {
			System.out.println("仕事を変更します");
			System.out.println("変更前の仕事" + this.job[whichPersonNum]);
			System.out.print("変更後の仕事：");
			String newJob = scanner.next();
			this.job[whichPersonNum] = newJob;
			System.out.println("仕事を" + newJob + "に変更しました");
		}else {
			System.out.println("入力したアルファベットが間違っています");
			this.changeInfo(person1,person2);
		}
		
		System.out.println();
		System.out.println("他にもPerson" + (whichPersonNum+1) + "の情報を変更しますか？");
		this.changeInfoRe(person1,person2);
	}
	
	public void changeInfoRe(Person person1,Person person2) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("変更する場合は『yes』、よろしければ『no』を入力してください");
		System.out.print("入力：");
		String changeInfoJudge = scanner.next();
		if(changeInfoJudge.equals("yes")) {
			this.changeInfo(person1,person2);
		}else if(changeInfoJudge.equals("no")){
			this.confirm(person1,person2);
		}else {
			System.out.println("入力が間違っています");
			this.changeInfoRe(person1,person2);
		}
	}

	public void newStorage(Person person1,Person person2) { //新しくなった値を元のpersonインスタンスの情報に格納するメソッド
		person1.setFirstName(firstName[0]); person2.setFirstName(firstName[1]);
		person2.setMiddleName(middleName);
		person1.setLastName(lastName[0]); person2.setLastName(lastName[1]);
		person1.setAge(age[0]); person2.setAge(age[1]);
		person1.setHight(height[0]); person2.setWeight(weight[1]);
		person2.setJob(job[0]); person2.setJob(job[1]);
	}
	//ゲッター
	public int getWhichPersonNum() {
		return this.whichPersonNum;
	}
}

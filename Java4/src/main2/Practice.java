/**
 * 
 */
/**
 * @author koshiro-nora
 *
 */

//Progate Java4の再現
//インスタンス化/カプセル化/コンストラクタ(インスタンス化時に実行されるメソッド)/ゲッター/セッター/オーバーロード

package main2;

public class Practice{
		
	public static void main(String[] args) {
		
		Person person1 = new Person("Kate","Jones",27,1.6,50.0,"医者");
		person1.printData();
		
		System.out.println();
		
		Person person2 = new Person("John","Christopher","Smith",65,1.75,80.0,"教師");
		person2.printData();
		
		System.out.println("-----------------");
		
		Frame frame = new Frame(person1,person2); //インスタンス化
		frame.confirm(person1,person2);
		frame.newStorage(person1,person2);
		System.out.println();
		person1.printData();
		System.out.println();
		person2.printData();
		
	}
}
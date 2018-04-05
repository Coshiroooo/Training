/**
 * 
 */
/**
 * @author koshiro-nora
 *
 */

//Progate Java4の再現

package main1.java;

public class Practice{
	public static void main(String[] args) {
		
		Person person1 = new Person("Kate","Jones",27,1.6,50.0,"医者");
		person1.printData();
		
		System.out.println();
		
		Person person2 = new Person("John","Christopher","Smith",65,1.75,80.0,"教師");
		person2.printData();
		
		System.out.println("-----------------");
		
		//person1が獣医に転職
		person1.setJob("獣医");
		System.out.println("person1の仕事を「" + person1.getJob() + "」に変更しました。");
		
		System.out.println();
		
		person1.printData();
	}
}
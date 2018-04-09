//List,Mapの勉強
//ArrayList,Iterator,forEach,ラムダ式,StreamAPI(filter,sort,map),Map
//forEachメソッドの文法：コレクション名.forEach(引数 -> 繰り返し行う処理(引数))

package main1;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		//Iterator:配列の要素番号や要素数を気にせずループ処理をするクラス
		for(Iterator it = list.iterator(); it.hasNext();) { //.hasNext(); => リストが次の要素をもっている場合にtrueを返す
				System.out.println(it.next()); //リストの次の要素を返す
		}
		
		list.forEach(System.out::println); //forEach文 メソッド参照
		list.forEach(s -> System.out.println(s)); //forEach文　ラムダ式
		list.forEach(s -> System.out.println(list.indexOf(s) + ":" + s)); //indexOf()を用いて要素のインデックス番号を取得
		
		System.out.println("---------------");
		
		Map<String,Integer> map = new HashMap<>();
		String nengo[] = {"明治","大正","昭和","平成"};
		map.put(nengo[0], 1868);
		map.put(nengo[1], 1912);
		map.put(nengo[2], 1926);
		map.put(nengo[3], 1989);
		for(String s: nengo) {
			System.out.println(s + "元年は、西暦" + map.get(s) + "年です"); //Mapを拡張for文で
		}
		map.forEach((key,value) -> System.out.println(key + "元年は、西暦" + value + "年です")); //MapをforEach文で
		
		System.out.println("---------------");
		
		//List型からMap型への変換 => StreamAPIのcollectメソッドを用いる
			List<String> list2 = new ArrayList<>(Arrays.asList("chocolate","makaron","cheesecake","pudding","cookie","pancake"));
			Map<String,Integer> map2 = list2.stream().collect(Collectors.toMap(s -> s,s -> s.length()));
			System.out.println(map2);
			for(Map.Entry<String, Integer> entry : map2.entrySet()) { //拡張for文を使って順番に出力
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			map2.forEach((key,value) -> System.out.println(key +":"+ value)); //MapをforEach文で出力
		
		//Map型からList型への変換 => 値変換：valueメソッド　キー変換：keySetメソッド
			Map map3 = new HashMap();
			map3.put("Samrai", "Engineer");
			map3.put("Soldier", "Warrior");
			
			List<String> listValues = new ArrayList<>(map3.values());
			List<String> listKeys = new ArrayList<>(map3.keySet());
			
			System.out.println(listValues);
			System.out.println(listKeys);
		
		System.out.println("---------------");
			
			//List<Map<String,String>>を使ってみる
		
			Scanner scanner = new Scanner(System.in);
			Map map4 = new HashMap();
			Map map5 = new HashMap();
			Map map6 = new HashMap();
			List<Map<String,Object>> list4 = new ArrayList<>();
			
			map4.put("名前", "上野");
			map4.put("朝ごはん", "目玉焼き");
			map4.put("年齢", 27);
			
			map5.put("名前", "川崎");
			map5.put("朝ごはん", "コーンフレーク");
			map5.put("年齢", 34);
			
			map6.put("名前", "戸塚");
			map6.put("朝ごはん", "白米");
			map6.put("年齢", 18);
			
			list4.add(map4);
			list4.add(map5);
			list4.add(map6);
			
			System.out.println(list4);
			
//			list4.forEach();
		
		
	}
}

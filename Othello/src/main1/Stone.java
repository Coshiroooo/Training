package main1;

public class Stone {
	
	private boolean isExist = true;
	private String frontColor;
	private String backColor;
	
	//コンストラクタ
	Stone(String frontColor){
		if(frontColor.equals(Main.white)) {
			this.frontColor = Main.white;
			this.backColor =Main.black;
		}else if(frontColor.equals(Main.black)){
			this.frontColor = Main.black;
			this.backColor = Main.white;
		}
	}
	
	Stone(){
		this.isExist = false;
	}
	
	//表と裏が入れ替わるメソッド
	public void turnOver() {
		String color = this.frontColor;
		this.frontColor = this.backColor;
		this.backColor = color;
	}
	
	//ゲッター
	
	public String getFrontColor() {
		return this.frontColor;
	}
	
	public String getBackColor() {
		return this.backColor;
	}
	
	public boolean getIsExist() {
		return this.isExist;
	}
}

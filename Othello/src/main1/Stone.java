package main1;

public class Stone {
	
	private final String white = "◎";
	private final String black = "◉";
	private String frontColor;
	private String backColor;
	
	//コンストラクタ
	Stone(String frontColor){
		if(frontColor.equals("white") || frontColor.equals(white)) {
			this.frontColor = this.white;
			this.backColor =this.black;
		}else if(frontColor.equals("black") || frontColor.equals(black)){
			this.frontColor = this.black;
			this.backColor = this.white;
		}
	}
	
	//表と裏が入れ替わるメソッド
	public void turnOver() {
		String color = this.frontColor;
		
		this.frontColor = this.backColor;
		this.backColor = color;
	}
	
	public String getFrontColor() {
		return this.frontColor;
	}
	
	public String getBackColor() {
		return this.backColor;
	}
}

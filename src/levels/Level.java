package levels;

public class Level {
//storing the lvlData method
	private int[][] lvlData;
	
	public Level(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}
	
	public int[][] getLevelData() {
		return lvlData;
	}
}

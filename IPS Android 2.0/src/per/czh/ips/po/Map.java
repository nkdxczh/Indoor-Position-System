package per.czh.ips.po;

public class Map {
	private int id,color;
	private float startx,starty,endx,endy,height;
	
	
	public Map(int id, int color, float startx, float starty, float endx,
			float endy, float height) {
		super();
		this.id = id;
		this.color = color;
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
		this.height = height;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public float getStartx() {
		return startx;
	}
	public void setStartx(float startx) {
		this.startx = startx;
	}
	public float getStarty() {
		return starty;
	}
	public void setStarty(float starty) {
		this.starty = starty;
	}
	public float getEndx() {
		return endx;
	}
	public void setEndx(float endx) {
		this.endx = endx;
	}
	public float getEndy() {
		return endy;
	}
	public void setEndy(float endy) {
		this.endy = endy;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}

}

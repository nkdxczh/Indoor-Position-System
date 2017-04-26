package org.nkcs.ips.po;

public class Map {
	private int id;
	private int color;
	private float startx;
	private float starty;
	private float endx;
	private float endy;
	private float height;
	
	public Map() {
		super();
	}
	
	public Map(int id, int color, float startX, float startY, float endX,
			float endY, float height) {
		super();
		this.id = id;
		this.color = color;
		this.startx = startX;
		this.starty = startY;
		this.endx = endX;
		this.endy = endY;
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
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
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
	
}

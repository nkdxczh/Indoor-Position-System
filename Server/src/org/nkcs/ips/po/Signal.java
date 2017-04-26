package org.nkcs.ips.po;

public class Signal {
	private int id;
	private float x;
	private float y;
	private String UUID;
	public Signal(int id, float x, float y, String uUID) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		UUID = uUID;
	}
	public Signal() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	
}

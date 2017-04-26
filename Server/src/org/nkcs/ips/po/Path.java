package org.nkcs.ips.po;



public class Path {
	private int id;
	private int startP;
	private int endP;
	
	public Path() {
		super();
	}
	public Path(int id, int startP, int endP) {
		super();
		this.id = id;
		this.startP = startP;
		this.endP = endP;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStartP() {
		return startP;
	}
	public void setStartP(int startP) {
		this.startP = startP;
	}
	public int getEndP() {
		return endP;
	}
	public void setEndP(int endP) {
		this.endP = endP;
	}

	
}

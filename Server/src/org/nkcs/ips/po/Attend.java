package org.nkcs.ips.po;

public class Attend {
	private int id;
	private int uid;
	private int pid;
	public Attend(int id, int uid, int iid) {
		super();
		this.id = id;
		this.uid = uid;
		this.pid = iid;
	}
	public Attend() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int iid) {
		this.pid = iid;
	}
	
}

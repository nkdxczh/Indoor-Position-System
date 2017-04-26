package per.czh.ips.po;

public class Path {
	private int id,startP,endP;

	public Path(int id, int startid, int endid) {
		super();
		this.id = id;
		this.startP = startid;
		this.endP = endid;
	}

	public Path() {
		super();
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

	public void setStartP(int startid) {
		this.startP = startid;
	}

	public int getEndP() {
		return endP;
	}

	public void setEndP(int endid) {
		this.endP = endid;
	}
}

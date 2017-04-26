package org.nkcs.ips.biz;

import java.util.List;

import org.nkcs.ips.po.Map;
import org.nkcs.ips.po.Node;
import org.nkcs.ips.po.Path;
import org.nkcs.ips.po.Signal;

public interface IMapBiz {
	public abstract List<Node> getNode();
	public abstract List<Path> getPath();
	public abstract List<Map> getMap();
	public abstract List<Signal> getSignal();
}

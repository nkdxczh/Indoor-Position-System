package org.nkcs.ips.dao;

import java.util.List;

import org.nkcs.ips.po.Node;

public interface INodeDao {
	public abstract List<Node> selectAll();
	public abstract int add(Node node);

}

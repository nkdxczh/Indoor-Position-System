package org.nkcs.ips.dao;

import java.util.List;

import org.nkcs.ips.po.Path;

public interface IPathDao {
	public abstract List<Path> selectAll();
	public abstract int add(Path path);

}

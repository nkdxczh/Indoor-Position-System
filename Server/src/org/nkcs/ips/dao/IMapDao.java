package org.nkcs.ips.dao;

import java.util.List;

import org.nkcs.ips.po.Map;

public interface IMapDao {
	public abstract List<Map> selectAll();
	public abstract int add(Map map);

}

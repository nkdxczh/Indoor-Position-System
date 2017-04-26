package org.nkcs.ips.dao;

import java.util.List;

import org.nkcs.ips.po.Attend;

public interface IAttendDao {
	public abstract List<Attend> selectByPid(int id);
}

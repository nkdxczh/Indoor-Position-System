package org.nkcs.ips.dao;

import java.util.List;
import org.nkcs.ips.po.Signal;

public interface ISignalDao {
	public abstract List<Signal> selectAll();

}

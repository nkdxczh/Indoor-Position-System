package org.nkcs.ips.dao;

import java.util.List;

import org.nkcs.ips.po.InterestPoint;
import org.nkcs.ips.po.User;

public interface IInterestPointDao {
	abstract public List<InterestPoint> selectByCreator(int id);
	abstract public List<InterestPoint> selectAll();
}

package org.nkcs.ips.biz;

import java.util.List;

import org.nkcs.ips.po.InterestPoint;
import org.nkcs.ips.po.User;

public interface IInterestPointBiz {
	abstract List<InterestPoint> findPrivateIP(int id);
	abstract List<User> findAttend(int id);
	abstract List<InterestPoint> getInterestPoint();
}

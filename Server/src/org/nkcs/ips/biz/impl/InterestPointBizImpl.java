package org.nkcs.ips.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.nkcs.ips.biz.IInterestPointBiz;
import org.nkcs.ips.dao.IAttendDao;
import org.nkcs.ips.dao.IInterestPointDao;
import org.nkcs.ips.dao.IUserDao;
import org.nkcs.ips.dao.impl.AttendDaoImpl;
import org.nkcs.ips.dao.impl.InterestPointDaoImpl;
import org.nkcs.ips.dao.impl.UserDaoImpl;
import org.nkcs.ips.po.Attend;
import org.nkcs.ips.po.InterestPoint;
import org.nkcs.ips.po.User;

public class InterestPointBizImpl implements IInterestPointBiz {

	IInterestPointDao interestPointDao;
	
	public InterestPointBizImpl() {
		super();
		interestPointDao=new InterestPointDaoImpl();
	}


	@Override
	public List<InterestPoint> findPrivateIP(int id) {
		// TODO Auto-generated method stub
		return interestPointDao.selectByCreator(id);
	}


	@Override
	public List<User> findAttend(int id) {
		// TODO Auto-generated method stub
		IAttendDao attendDao=new AttendDaoImpl();
		List<Attend> lstAttend=attendDao.selectByPid(id);
		
		IUserDao userDao=new UserDaoImpl();
		List<User> lstUser=new ArrayList<User>();
		for(int i=0;i<lstAttend.size();i++){
			User user=userDao.selectById(lstAttend.get(i).getId());
			lstUser.add(user);
		}
		return lstUser;
	}


	@Override
	public List<InterestPoint> getInterestPoint() {
		// TODO Auto-generated method stub
		return interestPointDao.selectAll();
	}
	
	

}

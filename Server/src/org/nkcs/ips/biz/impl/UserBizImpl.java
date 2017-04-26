package org.nkcs.ips.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.nkcs.ips.biz.IUserBiz;
import org.nkcs.ips.dao.IFriendDao;
import org.nkcs.ips.dao.IUserDao;
import org.nkcs.ips.dao.impl.FriendDaoImpl;
import org.nkcs.ips.dao.impl.UserDaoImpl;
import org.nkcs.ips.po.Friend;
import org.nkcs.ips.po.User;

public class UserBizImpl implements IUserBiz {
	
	IUserDao userDao;
	IFriendDao friendDao;
	
	public UserBizImpl(){
		userDao=new UserDaoImpl();
		friendDao=new FriendDaoImpl();
	}

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		User user=userDao.selectByEmail(email);
		if(user==null)return null;
		if(user.getPassword().equals(password))return user;
		return null;
	}

	@Override
	public List<User> findFriends(int id) {
		// TODO Auto-generated method stub
		List<Friend> lstFri=friendDao.selectByUid1(id);
		if(lstFri==null)return null;
		List<User> lstUser=new ArrayList<User>();
		for(int i=0;i<lstFri.size();i++){
			lstUser.add(userDao.selectById(lstFri.get(i).getUid2()));
		}
		return lstUser;
	}

	@Override
	public User findUser(int id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		List<User> lstUser=userDao.selectAll();
		if(lstUser==null)return null;
		return lstUser;
	}
}

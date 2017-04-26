package org.nkcs.ips.biz;

import java.util.List;

import org.nkcs.ips.po.User;

public interface IUserBiz {
	abstract User login(String email,String password);
	abstract List<User> findFriends(int id);
	abstract User findUser(int id);
	abstract List<User> getUsers();
}

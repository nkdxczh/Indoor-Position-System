package org.nkcs.ips.dao;

import java.util.List;

import org.nkcs.ips.po.User;

public interface IUserDao {
	abstract public User selectById(int id);
	abstract public User selectByEmail(String email);
	abstract public List<User> selectAll();
	abstract public int add(User user);
	abstract public int adapt(User user);
}

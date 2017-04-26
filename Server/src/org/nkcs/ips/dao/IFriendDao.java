package org.nkcs.ips.dao;

import java.util.List;

import org.nkcs.ips.po.Friend;
import org.nkcs.ips.po.User;

public interface IFriendDao {
	abstract public List<Friend> selectByUid1(int id);
	abstract public int add(int id1,int id2);
	abstract public List<Friend> selectAll();
}

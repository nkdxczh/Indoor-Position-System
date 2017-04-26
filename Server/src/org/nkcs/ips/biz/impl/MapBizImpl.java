package org.nkcs.ips.biz.impl;

import java.util.List;

import org.nkcs.ips.biz.IMapBiz;
import org.nkcs.ips.dao.IMapDao;
import org.nkcs.ips.dao.INodeDao;
import org.nkcs.ips.dao.IPathDao;
import org.nkcs.ips.dao.ISignalDao;
import org.nkcs.ips.dao.impl.InterestPointDaoImpl;
import org.nkcs.ips.dao.impl.MapDaoImpl;
import org.nkcs.ips.dao.impl.NodeDaoImpl;
import org.nkcs.ips.dao.impl.PathDaoImpl;
import org.nkcs.ips.dao.impl.SignalDaoImpl;
import org.nkcs.ips.po.Map;
import org.nkcs.ips.po.Node;
import org.nkcs.ips.po.Path;
import org.nkcs.ips.po.Signal;

public class MapBizImpl implements IMapBiz {

	IMapDao mapDao;
	INodeDao nodeDao;
	IPathDao pathDao;
	ISignalDao signalDao;
	
	public MapBizImpl() {
		super();
		mapDao=new MapDaoImpl();
		nodeDao=new NodeDaoImpl();
		pathDao=new PathDaoImpl();
		signalDao=new SignalDaoImpl();
	}
	
	@Override
	public List<Node> getNode() {
		// TODO Auto-generated method stub
		return nodeDao.selectAll();
	}

	@Override
	public List<Path> getPath() {
		// TODO Auto-generated method stub
		return pathDao.selectAll();
	}

	@Override
	public List<Signal> getSignal() {
		// TODO Auto-generated method stub
		return signalDao.selectAll();
	}

	@Override
	public List<Map> getMap() {
		// TODO Auto-generated method stub
		return mapDao.selectAll();
	}

}

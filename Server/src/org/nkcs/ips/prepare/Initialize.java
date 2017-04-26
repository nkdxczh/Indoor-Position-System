package org.nkcs.ips.prepare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.nkcs.ips.dao.IMapDao;
import org.nkcs.ips.dao.INodeDao;
import org.nkcs.ips.dao.IPathDao;
import org.nkcs.ips.dao.impl.MapDaoImpl;
import org.nkcs.ips.dao.impl.NodeDaoImpl;
import org.nkcs.ips.dao.impl.PathDaoImpl;
import org.nkcs.ips.po.Map;
import org.nkcs.ips.po.Node;
import org.nkcs.ips.po.Path;


public class Initialize {
	public void writeMap(String file) throws NumberFormatException, IOException {
		InputStreamReader read = new InputStreamReader(new FileInputStream(file));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		IMapDao mapDao=new MapDaoImpl();
		while ((lineTxt = bufferedReader.readLine()) != null) {
			if(lineTxt.equals(""))break;
			String[] content=lineTxt.split("\t");
			Map map=new Map();
			map.setId(Integer.parseInt(content[0]));
			map.setStartx(Float.parseFloat(content[1]));
			map.setStarty(Float.parseFloat(content[2]));
			map.setEndx(Float.parseFloat(content[3]));
			map.setEndy(Float.parseFloat(content[4]));
			map.setHeight(Float.parseFloat(content[5]));
			map.setColor(Integer.parseInt(content[6]));
			mapDao.add(map);
		}
		read.close();
	}
	
	public void writeNode(String file) throws NumberFormatException, IOException {
		InputStreamReader read = new InputStreamReader(new FileInputStream(file));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		INodeDao nodeDao=new NodeDaoImpl();
		int i=1;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			if(lineTxt.equals(""))break;
			String[] content=lineTxt.split("\t");
			Node node=new Node();
			node.setId(Integer.parseInt(content[0]));
			node.setX(Float.parseFloat(content[1]));
			node.setY(Float.parseFloat(content[2]));
			nodeDao.add(node);
		}
		read.close();
	}
	
	public void writePath(String file) throws NumberFormatException, IOException {
		InputStreamReader read = new InputStreamReader(new FileInputStream(file));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		IPathDao pathDao=new PathDaoImpl();
		int i=1;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			if(lineTxt.equals(""))break;
			String[] content=lineTxt.split("\t");
			Path path=new Path();
			path.setId(Integer.parseInt(content[0]));
			path.setStartP(Integer.parseInt(content[1]));
			path.setEndP(Integer.parseInt(content[2]));
			pathDao.add(path);
		}
		read.close();
	}
}

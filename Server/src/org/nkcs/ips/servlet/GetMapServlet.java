package org.nkcs.ips.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nkcs.ips.biz.IMapBiz;
import org.nkcs.ips.biz.impl.MapBizImpl;
import org.nkcs.ips.dao.IAttendDao;
import org.nkcs.ips.dao.IFriendDao;
import org.nkcs.ips.dao.IInterestPointDao;
import org.nkcs.ips.dao.impl.AttendDaoImpl;
import org.nkcs.ips.dao.impl.FriendDaoImpl;
import org.nkcs.ips.dao.impl.InterestPointDaoImpl;
import org.nkcs.ips.dao.impl.UserDaoImpl;
import org.nkcs.ips.po.Attend;
import org.nkcs.ips.po.Friend;
import org.nkcs.ips.po.HeatPoint;
import org.nkcs.ips.po.InterestPoint;
import org.nkcs.ips.po.Map;
import org.nkcs.ips.po.Node;
import org.nkcs.ips.po.Path;
import org.nkcs.ips.po.User;
import org.nkcs.ips.po.drawline;
import org.nkcs.ips.po.drawnode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetMapServlet
 */
public class GetMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMapServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// -------------------处理数据-----------------------

		IMapBiz mapBiz = new MapBizImpl();
		List<Map> lstMap = mapBiz.getMap();
		List<Path> lstPath = mapBiz.getPath();
		List<Node> lstNode = mapBiz.getNode();
		
		List<Map> lstPaths=new ArrayList<Map>();
		for(int i=0;i<lstPath.size();i++){
			Map m=new Map();
			int s=lstPath.get(i).getStartP();
			int e=lstPath.get(i).getEndP();
			if(s<=0||e<=0)continue;
			for(int j=0;j<lstNode.size();j++){
				if(lstNode.get(j).getId()==s){
					m.setStartx(lstNode.get(s-1).getX());
					m.setStarty(lstNode.get(s-1).getY());
					break;
				}
			}
			for(int j=0;j<lstNode.size();j++){
				if(lstNode.get(j).getId()==e){
					m.setEndx(lstNode.get(e-1).getX());
					m.setEndy(lstNode.get(e-1).getY());
					break;
				}
			}
			lstPaths.add(m);
		}

		// -------------------响应客户端 JSon----------------
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		String gson_data = gson.toJson(lstMap);
		request.setAttribute("lstMap", gson_data);

		gson_data = gson.toJson(lstPaths);
		request.setAttribute("lstPath", gson_data);

		UserDaoImpl userDao=new UserDaoImpl();
		request.setAttribute("userrate", userDao.getNormal());

		IInterestPointDao ipDao=new InterestPointDaoImpl();
		IFriendDao friendDao=new FriendDaoImpl();
		AttendDaoImpl attendDao=new AttendDaoImpl();
		
		List<User> lstAllUser=userDao.selectAll();
		List<InterestPoint> lstAllIP=ipDao.selectAll();
		List<Friend> lstAllFriend=friendDao.selectAll();
		List<Attend> lstAllAttend=attendDao.selectAll();
		
		Random random = new Random(45);
		List<drawnode> nodes=new ArrayList<drawnode>();
		List<drawline> edges=new ArrayList<drawline>();
		int line=0;
		for(int i=0;i<lstAllUser.size();i++){
			drawnode tem=new drawnode();
			tem.id="u"+lstAllUser.get(i).getId();
			tem.label=lstAllUser.get(i).getName();
			//tem.label="xx";
			tem.size=5;
			tem.x=random.nextFloat();
			//tem.x=0;
			tem.y=random.nextFloat();
			tem.color="#ec5148";
			nodes.add(tem);
		}
		List<HeatPoint> lstHP=new ArrayList<HeatPoint>();
		for(int i=0;i<lstAllIP.size();i++){
			drawnode tem=new drawnode();
			tem.id="p"+lstAllIP.get(i).getId();
			tem.label=lstAllIP.get(i).getName();
			//tem.label="xx";
			tem.size=lstAllIP.get(i).getMaxMem();;
			tem.x=random.nextFloat();
			//tem.x=1;
			tem.y=random.nextFloat();
			tem.color="#235148";
			nodes.add(tem);
			
			drawline teml=new drawline();
			teml.id="e"+line;
			teml.source="u"+lstAllIP.get(i).getCreator();
			teml.target="p"+lstAllIP.get(i).getId();
			teml.color="#bbbbbb";
			//teml.type="curve";
			edges.add(teml);
			line++;
			
			HeatPoint hp=new HeatPoint();
			hp.x=lstAllIP.get(i).getX()*1000;
			hp.y=lstAllIP.get(i).getY()*700;
			hp.value=lstAllIP.get(i).getMaxMem()*10;
		//		hp.x=(float) 500;
		//		hp.y=(float) 500;
		//		hp.value=100;
			lstHP.add(hp);
		}
		gson_data = gson.toJson(lstHP);
		request.setAttribute("lstHeatPoint", gson_data);
		line++;
		for(int i=0;i<lstAllFriend.size();i++){
			drawline teml=new drawline();
			teml.id="e"+line;
			teml.source="u"+lstAllFriend.get(i).getUid1();
			teml.target="u"+lstAllFriend.get(i).getUid2();
			teml.color="#ee55ee";
			//teml.type="curve";
			edges.add(teml);
			line++;
		}
		line++;
		for(int i=0;i<lstAllAttend.size();i++){
			drawline teml=new drawline();
			teml.id="e"+line;
			teml.source="p"+lstAllAttend.get(i).getPid();
			teml.target="u"+lstAllAttend.get(i).getUid();
			teml.color="#eeac55";
			//teml.type="curve";
			edges.add(teml);
			line++;
		}
		
		String nodes_data = gson.toJson(nodes);
		String edges_data = gson.toJson(edges);
		String input="{\"nodes\":"+nodes_data+",\"edges\":"+edges_data+"}";
		OutputStreamWriter out=new OutputStreamWriter(new FileOutputStream(new File("G:\\工作\\GraduationDesign\\YunziServer\\WebContent\\data1.json")),"UTF-8"); 
		//FileOutputStream out=new FileOutputStream(new File("G:\\工作\\GraduationDesign\\YunziServer\\WebContent\\data1.json"));
		//out.write(input.getBytes());
		out.write(input);
		out.flush();
		out.close();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

}

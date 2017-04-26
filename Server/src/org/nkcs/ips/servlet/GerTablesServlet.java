package org.nkcs.ips.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nkcs.ips.biz.IInterestPointBiz;
import org.nkcs.ips.biz.IMapBiz;
import org.nkcs.ips.biz.IUserBiz;
import org.nkcs.ips.biz.impl.InterestPointBizImpl;
import org.nkcs.ips.biz.impl.MapBizImpl;
import org.nkcs.ips.biz.impl.UserBizImpl;
import org.nkcs.ips.po.InterestPoint;
import org.nkcs.ips.po.Map;
import org.nkcs.ips.po.Node;
import org.nkcs.ips.po.Path;
import org.nkcs.ips.po.Signal;
import org.nkcs.ips.po.User;

/**
 * Servlet implementation class GerAllOrdersByUser
 */
public class GerTablesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerTablesServlet() {
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
		
		IMapBiz mapBiz=new MapBizImpl();
		List<Map> lstMap=mapBiz.getMap();
		request.setAttribute("lstMap", lstMap);
		List<Node> lstNode=mapBiz.getNode();
		request.setAttribute("lstNode", lstNode);
		List<Path> lstPath=mapBiz.getPath();
		request.setAttribute("lstPath", lstPath);
		List<Signal> lstSignal=mapBiz.getSignal();
		request.setAttribute("lstSignal", lstSignal);

		IUserBiz userBiz=new UserBizImpl();
		List<User> lstUser=userBiz.getUsers();
		request.setAttribute("lstUser", lstUser);
		
		IInterestPointBiz ipBiz=new InterestPointBizImpl();
		List<InterestPoint> lstIP=ipBiz.getInterestPoint();
		request.setAttribute("lstIP", lstIP);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/forindex.jsp");
		dispatcher.forward(request, response);
		//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
	}

}

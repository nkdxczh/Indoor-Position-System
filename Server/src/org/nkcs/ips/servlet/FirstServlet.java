package org.nkcs.ips.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nkcs.ips.po.InterestPoint;
public class FirstServlet extends HttpServlet {
	
	 public FirstServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*	
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		InterestPoint p1=new InterestPoint();
		p1.setId("1");
		p1.setX(700);
		p1.setY(700);
		InterestPoint p2=new InterestPoint();
		p2.setId("2");
		p2.setX(200);
		p2.setY(200);
		List list =new ArrayList<InterestPoint>();
		list.add(p1);
		list.add(p2);
		System.out.println(list);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		response.getWriter().println(gson.toJson(list));
		*/
		
		
	}

	
}

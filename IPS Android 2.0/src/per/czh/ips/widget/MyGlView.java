package per.czh.ips.widget;

import java.util.ArrayList;
import java.util.List;

import per.czh.ips.R;
import per.czh.ips.activity.MainActivity;
import per.czh.ips.algorithm.Routing;
import per.czh.ips.po.InterestPoint;
import per.czh.ips.po.Node;
import per.czh.ips.po.Path;
import per.czh.ips.po.User;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyGlView extends GLSurfaceView {

	float mPreviousX;
	float mPreviousY;
	Renderer mRenderer;

	private float oldd;
	private float oldR;
	private float oldx;
	private float oldy;
	private float mode = 1;
	private long oldt;

	public MainActivity activity;

	public MyGlView(Context context) {
		super(context);
		this.setBackgroundResource(R.drawable.newbackground);
		// TODO Auto-generated constructor stub
	}

	public MyGlView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public MyGlView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);

	}

	@Override
	public void setRenderer(Renderer renderer) {
		super.setRenderer(renderer);
		mRenderer = renderer;

	/*	Node start = new Node();
		start.setX(((MyGLRenderer) mRenderer).px);
		start.setY(((MyGLRenderer) mRenderer).py);

		Node end = new Node();
		end.setX(((MyGLRenderer) mRenderer).lstIP.get(0).getX());
		end.setY(((MyGLRenderer) mRenderer).lstIP.get(0).getY());

		getRoute(start, end);*/
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onTouchEvent(MotionEvent e) {

		if (e.getPointerCount() == 2) {

			switch (e.getAction()) {

			case MotionEvent.ACTION_POINTER_2_DOWN:
				oldd = (float) Math.pow((double) (e.getX(0) - e.getX(1)), 2.0);
				oldd += (float) Math.pow((double) (e.getY(0) - e.getY(1)), 2.0);
				oldd = (float) Math.sqrt((double) oldd);
				this.oldR = ((MyGLRenderer) mRenderer).mR;
				return true;

			case MotionEvent.ACTION_MOVE:
				float newd = 0;
				newd = (float) Math.pow((double) (e.getX(0) - e.getX(1)), 2.0);
				newd += (float) Math.pow((double) (e.getY(0) - e.getY(1)), 2.0);
				newd = (float) Math.sqrt((double) newd);
				float zoom = (oldd / newd);

				float of = ((MyGLRenderer) mRenderer).mR;

				if (of * zoom < 0.2 || of * zoom > 10)
					return true;
				else
					((MyGLRenderer) mRenderer).mR = oldR * zoom;

				System.out.println(((MyGLRenderer) mRenderer).mR);
				requestRender();
				return true;

			}

			return true;
		}
		if (e.getPointerCount() == 1) {
			switch (e.getAction()) {

			case MotionEvent.ACTION_UP:
				/*if(isLongPressed(oldx, oldy, e.getX(), e.getY(), oldt, e.getEventTime(), 100)){
					e.getEventTime();
				}*/
				if(isLongPressed(oldx, oldy, e.getX(), e.getY(), oldt, e.getEventTime(), 10)){
					//((MyGLRenderer) mRenderer).mXA=0;
					//((MyGLRenderer) mRenderer).mYA=0;
					((MyGLRenderer) mRenderer).lstIP=new ArrayList<InterestPoint>();
					InterestPoint tem=new InterestPoint();
					tem.setY(e.getX()/this.getHeight());
					tem.setX(1-e.getY()/this.getWidth());
					((MyGLRenderer) mRenderer).lstIP.add(tem);
					this.activity.tx=tem.getX();
					this.activity.ty=tem.getY();
				}
				return true;

			case MotionEvent.ACTION_DOWN:
				oldx = e.getX();
				oldy = e.getY();
				oldt=e.getEventTime();
				return true;

			case MotionEvent.ACTION_MOVE:

				float temx = (float) (Math.abs(e.getX() - oldx) * 0.0001);
				float temy = (float) (Math.abs(e.getY() - oldy) * 0.0001);
				float temXA = (float) Math.asin(temx
						/ ((MyGLRenderer) mRenderer).mR);
				float temYA = (float) Math.asin(temy
						/ ((MyGLRenderer) mRenderer).mR);

				if (e.getX() - oldx < 0)
					temXA = ((MyGLRenderer) mRenderer).mXA - temXA;
				else
					temXA = ((MyGLRenderer) mRenderer).mXA + temXA;
				if (e.getY() - oldy < 0)
					temYA = ((MyGLRenderer) mRenderer).mYA - temYA;
				else
					temYA = ((MyGLRenderer) mRenderer).mYA + temYA;

				((MyGLRenderer) mRenderer).mXA = temXA;
				if (temYA > Math.PI / 2 || temYA < 0)
					return true;
				else {
					((MyGLRenderer) mRenderer).mYA = temYA;
				}

				/*
				 * 
				 * float temx=(float) (((MyGLRenderer) mRenderer).movex +
				 * (e.getX() - oldx)*0.0001); float temy=(float)
				 * (((MyGLRenderer) mRenderer).movey + (e.getY() -
				 * oldx)*0.0001); if (temx>2||temx<-2||temy>1||temy<-1) return
				 * true; else { ((MyGLRenderer) mRenderer).movex += (e.getX() -
				 * oldx)*0.0001; ((MyGLRenderer) mRenderer).movey += (e.getY() -
				 * oldy)*0.0001; }
				 */

				return true;

			}
		}
		return true;

	}
	
	public void setPosition(float x,float y){
		((MyGLRenderer) mRenderer).px=x;
		((MyGLRenderer) mRenderer).py=y;
	}
	
	public void clearIP(){
		((MyGLRenderer) mRenderer).lstIP=new ArrayList<InterestPoint>();
	}
	
	public void clearUser(){
		((MyGLRenderer) mRenderer).lstUser=new ArrayList<User>();
	}
	
	public void addIP(InterestPoint p){
		((MyGLRenderer) mRenderer).lstIP.add(p);
	}
	
	public void addUser(User u){
		((MyGLRenderer) mRenderer).lstUser.add(u);
	}

	public List<Node> getRoute() {
		System.out.println("routing");

		Node start = new Node();
		start.setX(((MyGLRenderer) mRenderer).px);
		start.setY(((MyGLRenderer) mRenderer).py);

		Node end = new Node();
		end.setX(((MyGLRenderer) mRenderer).lstIP.get(0).getX());
		end.setY(((MyGLRenderer) mRenderer).lstIP.get(0).getY());
		
		List<Path> lstPath = new Routing().getRoute(start, end,
				activity.lstNode, activity.lstPath);

		List<Node> lstNode = new ArrayList<Node>();
		lstNode.add(end);
		for (Path p : lstPath) {
			for (Node n : activity.lstNode) {
				if (n.getId() == p.getEndP()) {
					lstNode.add(n);
					break;
				}
			}
		}
		lstNode.add(start);

		((MyGLRenderer) mRenderer).lstNode = lstNode;
		System.out.println("pppppp"+((MyGLRenderer) mRenderer).lstNode.size());
		return lstNode;

	}

	boolean isLongPressed(float lastX, float lastY, float thisX, float thisY,
			long lastDownTime, long thisEventTime, long longPressTime) {
		float offsetX = Math.abs(thisX - lastX);
		float offsetY = Math.abs(thisY - lastY);
		long intervalTime = thisEventTime - lastDownTime;
		if (offsetX <= 10 && offsetY <= 10 && intervalTime >= longPressTime) {
			return true;
		}
		return false;
	}
}

package per.czh.ips.widget;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import per.czh.ips.po.InterestPoint;
import per.czh.ips.po.Map;
import per.czh.ips.po.Node;
import per.czh.ips.po.User;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyGLRenderer implements Renderer {

	public volatile float mXA = 0;
	public volatile float mYA = 0;
	public volatile float mR = 4;
	public float movex = 0;
	public float movey = -0.2f;


	public float width = 1.5f;
	public float length = 1.0f;

	private float r = 0.05f;

	private float minx = -0.75f;
	private float miny = -0.5f;

	public float px=-0.25f;
	public float py=-0.4f;
	public boolean getposition;

	private float[] data_normals = { -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,
			-1, 0, 0, };

	private List<Map> lstMapLine;
	public List<InterestPoint> lstIP;
	public List<User> lstUser=new ArrayList<User>();
	public List<Node> lstNode;

	FloatBuffer mRectDataBuffer;
	IntBuffer mRectColorBuffer;
	FloatBuffer mTrimDataBuffer;
	IntBuffer mTrimColorBuffer;
	IntBuffer mOutColorBuffer;

	float[] mRectData=null;
	int[] mRectColor=null;
	float[] mTrimData=null;
	int[] mTrimColor=null;
	int[] mOutColor=null;

	public MyGLRenderer() {
		lstIP=new ArrayList<InterestPoint>();
		InterestPoint p=new InterestPoint();
		p.setX(0.7f);
		p.setY(0.85f);
		//lstIP.add(p);
	}

	@Override
	public void onDrawFrame(GL10 gl) {

		if (lstMapLine == null)
			lstMapLine = new ArrayList<Map>();

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45, 1f, (float) (0.1), 3);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		// gl.glClearColor(0.7f, 0.7f, 0.7f, 1.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		float x = (float) (mR * Math.cos(mYA) * Math.sin(mXA));
		float y = (float) (mR * Math.sin(mYA));
		float z = (float) (mR * Math.cos(mYA) * Math.cos(mXA));

		float m = 5;

		GLU.gluLookAt(gl, z, y + movey, x + movex, 0, movey, movex, 0, 1.0f, 0);

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, new float[] { 0, 0, 0,
				0.0f }, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION, new float[] { 0,
				0, 1.0f }, 0);

		ByteBuffer normals = ByteBuffer.allocateDirect(data_normals.length * 4);
		normals.order(ByteOrder.nativeOrder());
		normals.asFloatBuffer().put(data_normals);
		normals.position(0);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		
		for(Map map:lstMapLine)drawMap(map,gl);
		for(InterestPoint p:lstIP)drawPoint(p,0,gl);
		for(User u:lstUser)drawUser(u,0,gl);
		
		drawFloor(gl);

		drawPath(gl);
		
		drawPosition(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		float ratio = (float) width / height;
		gl.glFrustumf(-ratio, ratio, -1, 1, 5, 10);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {

		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0, 0, 0, 0);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		gl.glDisable(GL10.GL_DITHER);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glClearDepthf(1f);

		gl.glFrontFace(GL10.GL_CCW);

	}

	public void getLines(List<Map> lstline) {
		lstMapLine = lstline;
	}
	
	private void drawPath(GL10 gl){
		if(lstNode==null)return;
		for(int i=0;i<lstNode.size()-1;i++){
			float sx = lstNode.get(i).getX();
			float sy = lstNode.get(i).getY();
			float ex = lstNode.get(i+1).getX();
			float ey = lstNode.get(i+1).getY();
			
			mRectData = new float[] { minx + sx * width, 0.02f,
					miny + sy * length, minx + ex * width, 0.02f,
					miny + ey * length };
			mRectDataBuffer = BufferUtil.fBuffer(mRectData);
			
			mRectColor = new int[] { 0, 50000, 0, 65535, 0, 50000,
					0, 65535 };

			mRectColorBuffer = BufferUtil.iBuffer(mRectColor);

			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mRectDataBuffer);
			gl.glColorPointer(4, GL10.GL_FIXED, 0, mRectColorBuffer);
			gl.glLineWidth(7);
			gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 2);
		}
	}

	private void drawMap(Map map, GL10 gl) {
		
		float sx = map.getStartx();
		float sy = map.getStarty();
		float ex = map.getEndx();
		float ey = map.getEndy();
		float higth = map.getHeight();

		if (higth == 0) {
			mRectData = new float[] { minx + sx * width, 0,
					miny + sy * length, minx + ex * width, 0,
					miny + ey * length };
			mRectDataBuffer = BufferUtil.fBuffer(mRectData);

			
			switch (map.getColor()) {
			case 1:
				mRectColor = new int[] { 60000, 60000, 0, 65535, 60000, 60000,
						0, 65535 };
				break;

			case 3:
				mRectColor = new int[] { 60000, 0, 60000, 65535, 60000, 0, 60000, 65535 };
				break;
			}
			mRectColorBuffer = BufferUtil.iBuffer(mRectColor);

			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mRectDataBuffer);
			gl.glColorPointer(4, GL10.GL_FIXED, 0, mRectColorBuffer);
			gl.glLineWidth(5);
			gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 2);

			return;
		}

		mRectData = new float[] { minx + sx * width, 0,
				miny + sy * length, minx + sx * width, higth,
				miny + sy * length, minx + ex * width, higth,
				miny + ey * length, minx + ex * width, 0, miny + ey * length,
				minx + sx * width, 0, miny + sy * length };
		mRectDataBuffer = BufferUtil.fBuffer(mRectData);

		switch (map.getColor()) {
		case 0:
			mRectColor = new int[] { 50000, 50000, 50000, 20000, 50000, 50000,
					50000, 20000, 50000, 50000, 50000, 20000, 50000, 50000,
					50000, 20000, 50000, 50000, 50000, 20000 };
			break;
		}

		mRectColorBuffer = BufferUtil.iBuffer(mRectColor);

		int[] mOutColor = new int[] { 0, 0, 0, 65535, 0, 0, 0, 65535, 0, 0, 0,
				65535, 0, 0, 0, 65535, 0, 0, 0, 65535 };
		mOutColorBuffer = BufferUtil.iBuffer(mOutColor);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mRectDataBuffer);
		gl.glColorPointer(4, GL10.GL_FIXED, 0, mRectColorBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 5);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mRectDataBuffer);
		gl.glColorPointer(4, GL10.GL_FIXED, 0, mOutColorBuffer);
		gl.glLineWidth(3);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 5);

		
	}
	
	private void drawFloor(GL10 gl){
		mRectData = new float[] { minx, -0.001f, miny, minx, -0.001f,
				miny + length, minx + width, -0.001f, miny + length,
				minx + width, -0.001f, miny, minx, -0.001f, miny };

		mRectDataBuffer = BufferUtil.fBuffer(mRectData);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mRectDataBuffer);

		mRectColor = new int[] { 
				30000, 30000, 30000, 50000, 
				30000, 30000, 30000, 50000, 
				30000, 30000, 30000, 50000, 
				30000, 30000, 30000, 50000, 
				30000, 30000, 30000, 50000 };

		mRectColorBuffer = BufferUtil.iBuffer(mRectColor);

		mOutColor = new int[] { 0, 0, 0, 65535, 0, 0, 0, 65535, 0, 0, 0,
				65535, 0, 0, 0, 65535, 0, 0, 0, 65535 };
		mOutColorBuffer = BufferUtil.iBuffer(mOutColor);

		gl.glColorPointer(4, GL10.GL_FIXED, 0, mRectColorBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 5);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mRectDataBuffer);
		gl.glColorPointer(4, GL10.GL_FIXED, 0, mOutColorBuffer);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 5);
	}
	
	
	private void drawPoint(InterestPoint point,int type,GL10 gl){
		float x=point.getX() * width+minx;
		float y=point.getY() * length+miny;
		
		float h=0.03f;
		float r=0.01f;
		
		
		mTrimColor = new int[] { 
				65535, 0, 0, 65535,
				65535, 0, 0, 65535,
				65535, 0, 0, 65535  };

		mTrimColorBuffer = BufferUtil.iBuffer(mTrimColor);
		
		gl.glColorPointer(4, GL10.GL_FIXED, 0, mTrimColorBuffer);
		
		mTrimData = new float[] { 
				x, 0, y, 
				x+r, h, y+r,
				x+r, h, y-r };
		mTrimDataBuffer = BufferUtil.fBuffer(mTrimData);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTrimDataBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		
		mTrimData = new float[] { 
				x, 0, y, 
				x+r, h, y-r,
				x-r, h, y-r };
		mTrimDataBuffer = BufferUtil.fBuffer(mTrimData);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTrimDataBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		
		mTrimData = new float[] { 
				x, 0, y, 
				x-r, h, y-r,
				x-r, h, y+r };
		mTrimDataBuffer = BufferUtil.fBuffer(mTrimData);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTrimDataBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		
		mTrimData = new float[] { 
				x, 0, y, 
				x-r, h, y+r,
				x+r, h, y+r };
		mTrimDataBuffer = BufferUtil.fBuffer(mTrimData);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTrimDataBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		
		
	}
	
	private void drawUser(User user,int type,GL10 gl){
		if(user.getX()<0||user.getX()>1||user.getY()<0||user.getY()>1)return;
		float x=user.getX() * width+minx;
		float y=user.getY() * length+miny;
		
		float h=0.03f;
		float r=0.01f;
		
		mTrimColor = new int[] { 
				0, 0, 65535, 65535,
				0, 0, 65535, 65535,
				0, 0, 65535, 65535  };

		mTrimColorBuffer = BufferUtil.iBuffer(mTrimColor);
		
		gl.glColorPointer(4, GL10.GL_FIXED, 0, mTrimColorBuffer);
		
		mTrimData = new float[] { 
				x, 0, y, 
				x+r, h, y+r,
				x+r, h, y-r };
		mTrimDataBuffer = BufferUtil.fBuffer(mTrimData);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTrimDataBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		
		mTrimData = new float[] { 
				x, 0, y, 
				x+r, h, y-r,
				x-r, h, y-r };
		mTrimDataBuffer = BufferUtil.fBuffer(mTrimData);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTrimDataBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		
		mTrimData = new float[] { 
				x, 0, y, 
				x-r, h, y-r,
				x-r, h, y+r };
		mTrimDataBuffer = BufferUtil.fBuffer(mTrimData);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTrimDataBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		
		mTrimData = new float[] { 
				x, 0, y, 
				x-r, h, y+r,
				x+r, h, y+r };
		mTrimDataBuffer = BufferUtil.fBuffer(mTrimData);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTrimDataBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		
		
	}
	
	private void drawPosition(GL10 gl){
		if(px<0||px>1||py<0||py>1)return;
		
		float[][] position = getPosition();

		float[] mRectColorf = new float[] { 0.0f, 0.7f, 0.7f, 0.0f, 0.0f, 0.7f,
				0.7f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
				0.0f, 0.7f, 0.7f, 0.0f, 0.0f, 0.7f, 0.7f, 0.0f };

		mRectColor = new int[] { 0, 65535, 0, 65535, 0, 65535, 0, 0, 0, 65535,
				0, 0, 0, 65535, 0, 0, 0, 65535, 0, 65535 };

		FloatBuffer mRectDataBufferf = BufferUtil.fBuffer(mRectColorf);
		mRectColorBuffer = BufferUtil.iBuffer(mRectColor);

		for (int i = 0; i < 6; i++) {
			mRectData = position[i];
			mRectDataBuffer = BufferUtil.fBuffer(mRectData);

			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mRectDataBuffer);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, mRectDataBufferf);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 5);
		}

		float[] mTriColorf = new float[] { 0.0f, 0.7f, 0.7f, 0.0f, 1.0f, 1.0f,
				1.0f, 0.0f, 0.0f, 0.7f, 0.7f, 0.0f };
		FloatBuffer mTriColorBuffer = BufferUtil.fBuffer(mTriColorf);

	/*	for (int i = 6; i < 12; i++) {
			float[] mTriData = new float[] { position[i][0], position[i][1],
					position[i][2], position[i][3], position[i][4],
					position[i][5], position[i][6], position[i][7],
					position[i][8], };
			FloatBuffer mTriDataBuffer = BufferUtil.fBuffer(mTriData);

			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriDataBuffer);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, mTriColorBuffer);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
		}*/

		gl.glFinish();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	}
	

	private float[][] getPosition() {
		float[][] mOvalData = new float[12][15];
		
		float tpx=px*width+minx;
		float tpy=py*length+miny;
		float h=0.02f;

		for (int i = 0; i < 6; i++) {
			mOvalData[i][0] = (float) (tpx + r * Math.cos((Math.PI / 3) * i));
			mOvalData[i][1] = 0;
			mOvalData[i][2] = (float) (tpy + r * Math.sin((Math.PI / 3) * i));

			mOvalData[i][3] = (float) (tpx + r
					* Math.cos((Math.PI / 3) * (i + 1)));
			mOvalData[i][4] = 0;
			mOvalData[i][5] = (float) (tpy + r
					* Math.sin((Math.PI / 3) * (i + 1)));

			mOvalData[i][6] = (float) (tpx + r
					* Math.cos((Math.PI / 3) * (i + 1)));
			mOvalData[i][7] = h;
			mOvalData[i][8] = (float) (tpy + r
					* Math.sin((Math.PI / 3) * (i + 1)));

			mOvalData[i][9] = (float) (tpx + r * Math.cos((Math.PI / 3) * i));
			mOvalData[i][10] = h;
			mOvalData[i][11] = (float) (tpy + r * Math.sin((Math.PI / 3) * i));

			mOvalData[i][12] = (float) (tpx + r * Math.cos((Math.PI / 3) * i));
			mOvalData[i][13] = 0;
			mOvalData[i][14] = (float) (tpy + r * Math.sin((Math.PI / 3) * i));
		}

		for (int i = 6; i < 12; i++) {
			mOvalData[i][0] = (float) (tpx + r * Math.cos((Math.PI / 3) * i));
			mOvalData[i][1] = 0;
			mOvalData[i][2] = (float) (tpy + r * Math.sin((Math.PI / 3) * i));

			mOvalData[i][3] = px;
			mOvalData[i][4] = 0;
			mOvalData[i][5] = py;

			mOvalData[i][6] = (float) (tpx + r
					* Math.cos((Math.PI / 3) * (i + 1)));
			mOvalData[i][7] = 0;
			mOvalData[i][8] = (float) (tpy + r
					* Math.sin((Math.PI / 3) * (i + 1)));

		}

		return mOvalData;
	}

	public static class BufferUtil {
		public static IntBuffer intBuffer;
		public static FloatBuffer floatBuffer;

		public static IntBuffer iBuffer(int[] a) {
			// 先初始化buffer,数组的长度*4,因为一个float占4个字节
			ByteBuffer mbb = ByteBuffer.allocateDirect(a.length * 4);
			// 数组排列用nativeOrder
			mbb.order(ByteOrder.nativeOrder());
			intBuffer = mbb.asIntBuffer();
			intBuffer.put(a);
			intBuffer.position(0);
			return intBuffer;
		}

		public static FloatBuffer fBuffer(float[] a) {
			// 先初始化buffer,数组的长度*4,因为一个float占4个字节
			ByteBuffer mbb = ByteBuffer.allocateDirect(a.length * 4);
			// 数组排列用nativeOrder
			mbb.order(ByteOrder.nativeOrder());
			floatBuffer = mbb.asFloatBuffer();
			floatBuffer.put(a);
			floatBuffer.position(0);
			return floatBuffer;
		}
	}
}

package per.czh.ips.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import per.czh.ips.R;
import per.czh.ips.activity.MainActivity;
import per.czh.ips.po.InterestPoint;
import per.czh.ips.po.Node;
import per.czh.ips.po.User;
import per.czh.ips.widget.MyDialog;
import per.czh.ips.widget.MyGLRenderer;
import per.czh.ips.widget.MyGlView;

import com.sensoro.beacon.kit.Beacon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MapFragment extends Fragment implements OnClickListener {

	MainActivity activity;
	private MyGlView myGlView;
	
	private Button btnSearch;
	private Button btnSave;
	private Button btnGuide;
	private Button btnIP;
	private Button btnUser;

	private Dialog dialog;
	
	public InterestPoint p;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_map, container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		activity = (MainActivity) this.getActivity();
		
		myGlView=(MyGlView)activity.findViewById(R.id.view_map_map);

		MyGLRenderer renderer = new MyGLRenderer();
		renderer.getLines(activity.lstMap);

		myGlView.activity=activity;
		myGlView.setZOrderOnTop(true);
		myGlView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		myGlView.setEGLConfigChooser(8,8,8,8,16,0);
		myGlView.setRenderer(renderer);
		
		btnSearch=(Button) this.getActivity().findViewById(R.id.btn_map_search);
		btnSearch.setOnClickListener(this);

		btnSave=(Button) this.getActivity().findViewById(R.id.btn_map_save);
		btnSave.setOnClickListener(this);

		btnGuide=(Button) this.getActivity().findViewById(R.id.btn_map_guide);
		btnGuide.setOnClickListener(this);

		btnIP=(Button) this.getActivity().findViewById(R.id.btn_map_interest);
		btnIP.setOnClickListener(this);

		btnUser=(Button) this.getActivity().findViewById(R.id.btn_map_user);
		btnUser.setOnClickListener(this);
		
		float x=0.25f;
		float y=0.4f;
		activity.x=x;
		activity.y=y;
		myGlView.setPosition(activity.x,activity.y);
		
		myGlView.clearIP();
		myGlView.addIP(p);
		this.activity.tx=p.getX();
		this.activity.ty=p.getY();
	}
	
	public void showIP(InterestPoint p){
		myGlView.clearIP();
		myGlView.addIP(p);
	}
	
	public void showUser(User u){
		myGlView.clearUser();
		myGlView.addUser(u);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_map_search) {
			Bundle bundle = new Bundle();
			if(activity.searchFragment==null)activity.searchFragment=new SearchFragment();
			activity.searchFragment.setArguments(bundle);

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.activity_main_container,
					activity.searchFragment, MainActivity.TAG_FRAG_SEA);
			transaction.addToBackStack(null);
			transaction.commit();
		}


		if (v.getId() == R.id.btn_map_save) {
			dialog = new MyDialog(activity,R.style.MyDialog);
			dialog.show();
		}
		

		if (v.getId() == R.id.btn_map_guide) {
			myGlView.getRoute();
		}
		

		if (v.getId() == R.id.btn_map_interest) {
			float temx=activity.x;
			float temy=activity.y;
			myGlView.clearIP();
			for(int i=0;i<activity.lstIP.size();i++){
				if(activity.lstIP.get(i).getType()==0)continue;
				float x=activity.lstIP.get(i).getX();
				float y=activity.lstIP.get(i).getY();
				double d=(temx-x)*(temx-x)+(temy-y)*(temy-y);
				d=Math.pow(d,0.5);
				if(d<=0.5)myGlView.addIP(activity.lstIP.get(i));
			}
			
		}

		if (v.getId() == R.id.btn_map_user) {
			float temx=activity.x;
			float temy=activity.y;
			myGlView.clearUser();
			for(int i=0;i<activity.lstFriends.size();i++){
				if(activity.lstFriends.get(i).getType()==0)continue;
				float x=activity.lstFriends.get(i).getX();
				float y=activity.lstFriends.get(i).getY();
				double d=(temx-x)*(temx-x)+(temy-y)*(temy-y);
				d=Math.pow(d,0.5);
				if(d<=0.5)myGlView.addUser(activity.lstFriends.get(i));
			}
		}
	}
	
	

}

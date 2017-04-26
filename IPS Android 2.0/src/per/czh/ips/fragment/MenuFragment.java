package per.czh.ips.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import per.czh.ips.R;
import per.czh.ips.activity.MainActivity;
import per.czh.ips.po.InterestPoint;
import per.czh.ips.po.Map;
import per.czh.ips.po.Node;
import per.czh.ips.po.Path;
import per.czh.ips.po.Signal;
import per.czh.ips.po.User;
import per.jason.ips.internet.WebAccessUtils;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuFragment extends Fragment implements OnClickListener {

	private LinearLayout loginLay;
	private LinearLayout meunLay;

	private Button loginBtn;
	private Button mapBtn;
	private Button infBtn;
	private Button softBtn;

	private EditText emailTxt;
	private EditText passwordTxt;

	MainActivity activity;

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	private String response;
	private List<NameValuePair> lstNameValuePairs;

	private User user;
	
	static boolean login=false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_menu, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		init();

		MapFragment mapFragment = new MapFragment();
		Bundle bundle = new Bundle();
		mapFragment.setArguments(bundle);
		// MainActivity.map=mapFragment;
	}

	private void init() {
		activity = (MainActivity) getActivity();

		loginLay = (LinearLayout) activity.findViewById(R.id.login_lay_login);
		meunLay = (LinearLayout) activity.findViewById(R.id.login_lay_meun);

		loginBtn = (Button) activity.findViewById(R.id.login_btn_login);
		mapBtn = (Button) activity.findViewById(R.id.login_btn_map);
		infBtn = (Button) activity.findViewById(R.id.login_btn_inf);
		softBtn = (Button) activity.findViewById(R.id.login_btn_soft);

		emailTxt = (EditText) activity.findViewById(R.id.login_txt_email);
		passwordTxt = (EditText) activity.findViewById(R.id.login_txt_pw);

		loginLay.setVisibility(View.VISIBLE);
		meunLay.setVisibility(View.GONE);

		loginBtn.setOnClickListener(this);
		mapBtn.setOnClickListener(this);
		infBtn.setOnClickListener(this);
		softBtn.setOnClickListener(this);
		
		if(login){
			loginLay.setVisibility(View.GONE);
			meunLay.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.login_btn_login) {
			login(emailTxt.getText().toString(), passwordTxt.getText()
					.toString());

			loginLay.setVisibility(View.GONE);
			
			meunLay.setVisibility(View.VISIBLE);
			
			login=true;
		}

		if (v.getId() == R.id.login_btn_map) {
			// EnvironmentFragment envFragment = new EnvironmentFragment();
			Bundle bundle = new Bundle();
			
			if(activity.mapFragment==null)activity.mapFragment=new MapFragment();
			activity.mapFragment.setArguments(bundle);

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.activity_main_container,
					activity.mapFragment, MainActivity.TAG_FRAG_MAP);
			transaction.addToBackStack(null);
			transaction.commit();
		}
		
		if (v.getId() == R.id.login_btn_inf) {
			// EnvironmentFragment envFragment = new EnvironmentFragment();
			Bundle bundle = new Bundle();
			if(activity.infoFragment==null)activity.infoFragment=new InfoFragment();
			activity.infoFragment.setArguments(bundle);

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.activity_main_container,
					activity.infoFragment, MainActivity.TAG_FRAG_INF);
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}

	@SuppressLint("ShowToast")
	private void login(String email, String password) {
		user = new User();
		user.setEmail("dgzxczh@163.com");
		user.setPassword("111");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddhh:mm:ss")
				.create();
		String u = gson.toJson(user);
		lstNameValuePairs = new ArrayList<NameValuePair>();
		lstNameValuePairs.add(new BasicNameValuePair("user_data", u));

		new Thread() {// 创建一个新的线程
			public void run() {
				try {

					handler.post(new Runnable() {// 此处用一个匿名内部类，runnable自动把消息发送给主线程创建处理的handler，主线程会自动更新。
						public void run() {

							dialog = new ProgressDialog(activity);
							dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条样式
							dialog.setMessage("正在验证身份信息...");
							dialog.setCancelable(false);// 判断是否取消进度条
							dialog.show();
						}
					});

					Type userType= new TypeToken<User>() {
					}.getType();
					Gson gson = new GsonBuilder().setDateFormat(
							"yyyy-MM-dd hh:mm:ss").create();
					// 由于网络操作比较耗时，所以在新线程中操作
					response = WebAccessUtils.httpRequest("ALoginServlet",
							lstNameValuePairs);


					user = gson.fromJson(response, userType);
					System.out.println(user);
					if (user == null) {
						dialog.dismiss();
						Toast.makeText(activity, "用户信息错误，请重试！", 0).show();
						return;
					}
					
					activity.userImage=WebAccessUtils.getBitmapFromServer("userImage/"+user.getImage());
					
					activity.user=user;
					
					
					handler.post(new Runnable() {// 此处用一个匿名内部类，runnable自动把消息发送给主线程创建处理的handler，主线程会自动更新。
						public void run() {
							dialog.dismiss();
							dialog = new ProgressDialog(activity);
							dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条样式
							dialog.setMessage("正在获取用户信息...");
							dialog.setCancelable(false);// 判断是否取消进度条
							dialog.show();
						}
					});
					
					
					String u = gson.toJson(user);
					lstNameValuePairs = new ArrayList<NameValuePair>();
					lstNameValuePairs.add(new BasicNameValuePair("user_data", u));

					// 由于网络操作比较耗时，所以在新线程中操作
					response = WebAccessUtils.httpRequest("AGetPrivateIPServlet",
							lstNameValuePairs);

					userType = new TypeToken<List<InterestPoint>>() {
					}.getType();

					activity.lstIP = gson.fromJson(response, userType);
					if(activity.lstIP==null)activity.lstIP=new ArrayList<InterestPoint>();
					
					response = WebAccessUtils.httpRequest("AGetFriendServlet",
							lstNameValuePairs);

					userType = new TypeToken<List<User>>() {
					}.getType();

					activity.lstFriends = gson.fromJson(response, userType);
					if(activity.lstFriends==null)activity.lstFriends=new ArrayList<User>();

					dialog.dismiss();
					
					handler.post(new Runnable() {// 此处用一个匿名内部类，runnable自动把消息发送给主线程创建处理的handler，主线程会自动更新。
						public void run() {

							dialog = new ProgressDialog(activity);
							dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条样式
							dialog.setMessage("正在获取地图信息...");
							dialog.setCancelable(false);// 判断是否取消进度条
							dialog.show();
						}
					});
					
					
					lstNameValuePairs = new ArrayList<NameValuePair>();

					// 由于网络操作比较耗时，所以在新线程中操作
					response = WebAccessUtils.httpRequest("AGetNodeServlet",
							lstNameValuePairs);

					userType = new TypeToken<List<Node>>() {
					}.getType();

					activity.lstNode = gson.fromJson(response, userType);
					if(activity.lstNode==null)activity.lstNode=new ArrayList<Node>();
					
					
					response = WebAccessUtils.httpRequest("AGetPathServlet",
							lstNameValuePairs);

					userType = new TypeToken<List<Path>>() {
					}.getType();

					activity.lstPath = gson.fromJson(response, userType);
					if(activity.lstPath==null)activity.lstPath=new ArrayList<Path>();
					
					response = WebAccessUtils.httpRequest("AGetMapServlet",
							lstNameValuePairs);

					userType = new TypeToken<List<Map>>() {
					}.getType();

					activity.lstMap = gson.fromJson(response, userType);
					System.out.println("kkkkkk  "+activity.lstMap.get(0).getStartx());
					if(activity.lstMap==null)activity.lstMap=new ArrayList<Map>();
					
					response = WebAccessUtils.httpRequest("AGetSignalServlet",
							lstNameValuePairs);

					userType = new TypeToken<List<Signal>>() {
					}.getType();

					activity.lstSignal = gson.fromJson(response, userType);
					if(activity.lstSignal==null)activity.lstSignal=new ArrayList<Signal>();

					dialog.dismiss();
					
				} catch (Exception e) {
					e.printStackTrace();
					//Toast.makeText(activity, "服务器忙，请稍后再试！", 0).show();
				}
			}
		}.start();
	}
}
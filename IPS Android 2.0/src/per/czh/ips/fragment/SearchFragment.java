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
import per.czh.ips.item.InterestItemAdapter;
import per.czh.ips.item.UserItemAdapter;
import per.czh.ips.po.InterestPoint;
import per.czh.ips.po.User;
import per.jason.ips.internet.WebAccessUtils;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.AdapterView.OnItemClickListener;

public class SearchFragment extends Fragment implements OnItemClickListener {

	
	private ListView lstUserView;
	private ListView lstInterstPView;

	private List<User> lstUser;
	private List<InterestPoint> lstInterstP;

	private TabHost tabHost;

	MainActivity activity;

	private List<Bitmap> userImage;
	private List<Bitmap> IPImage;

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	private String response;
	private List<NameValuePair> lstNameValuePairs;

	private User user;
	private InterestPoint ip;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_search, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		activity = (MainActivity) this.getActivity();
		
		lstUserView = (ListView) this.getActivity()
				.findViewById(R.id.search_lst_user);
		lstUserView.setAdapter(new UserItemAdapter(activity, activity.lstFriends,
				activity.lstFriImg));
		lstUserView.setOnItemClickListener(this);

		lstInterstPView = (ListView) this.getActivity().findViewById(
				R.id.search_lst_ip);
		lstInterstPView.setAdapter(new InterestItemAdapter(activity,
				activity.lstIP, activity.lstIPImg));
		lstInterstPView.setOnItemClickListener(this);

		// 获取TabHost对象
		tabHost = (TabHost) this.getActivity().findViewById(R.id.user_tabhost);
		// 如果没有继承TabActivity时，通过该种方法加载启动tabHost
		tabHost.setup();

		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("相关用户")
				.setContent(R.id.search_widget_user));

		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("相关兴趣点")
				.setContent(R.id.search_widget_ip));

		TabWidget tabWidget = tabHost.getTabWidget();
	}

	private void init() {

		Thread t = new Thread() {// 创建一个新的线程
			public void run() {
				try {

					handler.post(new Runnable() {// 此处用一个匿名内部类，runnable自动把消息发送给主线程创建处理的handler，主线程会自动更新。
						public void run() {

							dialog = new ProgressDialog(activity);
							dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条样式
							dialog.setMessage("正在获取信息...");
							dialog.setCancelable(false);// 判断是否取消进度条
							dialog.show();
						}
					});

					Gson gson = new GsonBuilder().setDateFormat(
							"yyyy-MM-dd hh:mm:ss").create();
					String u = gson.toJson(user);
					lstNameValuePairs = new ArrayList<NameValuePair>();
					lstNameValuePairs
							.add(new BasicNameValuePair("user_data", u));

					response = WebAccessUtils.httpRequest("ASearchUserServlet",
							lstNameValuePairs);

					Type attendType = new TypeToken<List<User>>() {
					}.getType();

					lstUser = gson.fromJson(response, attendType);
					if (lstUser == null)
						lstUser = new ArrayList<User>();

					attendType = new TypeToken<User>() {
					}.getType();

					userImage = new ArrayList<Bitmap>();
					for (int i = 0; i < lstUser.size(); i++) {
						Bitmap bitmap = WebAccessUtils
								.getBitmapFromServer("userImage/"
										+ lstUser.get(i).getImage());
						userImage.add(bitmap);
					}
					
					u = gson.toJson(ip);
					lstNameValuePairs = new ArrayList<NameValuePair>();
					lstNameValuePairs
							.add(new BasicNameValuePair("ip_data", u));

					response = WebAccessUtils.httpRequest("ASearchInterestPointServlet",
							lstNameValuePairs);

					attendType = new TypeToken<List<InterestPoint>>() {
					}.getType();

					lstUserView.setAdapter(new UserItemAdapter(activity,
							lstUser, userImage));
					lstUserView.invalidate();

					lstInterstP = gson.fromJson(response, attendType);
					if (lstInterstP == null)
						lstInterstP = new ArrayList<InterestPoint>();

					attendType = new TypeToken<User>() {
					}.getType();

					IPImage = new ArrayList<Bitmap>();
					for (int i = 0; i < lstInterstP.size(); i++) {
						Bitmap bitmap = WebAccessUtils
								.getBitmapFromServer("ipImage/"
										+ lstInterstP.get(i).getImage());
						IPImage.add(bitmap);
					}
					
					lstInterstPView.setAdapter(new InterestItemAdapter(activity,
							lstInterstP, IPImage));
					lstInterstPView.invalidate();

					dialog.dismiss();


				} catch (Exception e) {
					e.printStackTrace();
					// Toast.makeText(activity, "服务器忙，请稍后再试！", 0).show();
				}
			}
		};
		try {
			t.start();
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		if (arg0.getId() == lstInterstPView.getId()) {
			activity.temIP = (InterestPoint) arg0.getItemAtPosition(arg2);
			activity.temImage = IPImage.get(arg2);

			Bundle bundle = new Bundle();
			activity.interetFragment = new InterestFragment();
			activity.interetFragment.setArguments(bundle);

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.activity_main_container,
					activity.interetFragment, MainActivity.TAG_FRAG_INF);
			transaction.addToBackStack(null);
			transaction.commit();
		}

		if (arg0.getId() == lstUserView.getId()) {
			activity.temUser = (User) arg0.getItemAtPosition(arg2);
			activity.temImage = userImage.get(arg2);

			Bundle bundle = new Bundle();
			activity.userFragment = new UserFragment();
			activity.userFragment.setArguments(bundle);

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.activity_main_container,
					activity.userFragment, MainActivity.TAG_FRAG_INF);
			transaction.addToBackStack(null);
			transaction.commit();
		}

	}

}

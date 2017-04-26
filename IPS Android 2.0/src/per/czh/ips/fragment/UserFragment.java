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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class UserFragment extends Fragment implements OnClickListener,
		OnItemClickListener {

	private TextView txtName;
	private TextView txtEmail;
	private TextView txtType;
	private TextView txtShare;
	private TextView txtFriend;
	private TextView txtIp;

	private Button btnShow;

	private ImageView imgUser;

	private ListView lstFriend;
	private ListView lstInterstP;

	private TabHost tabHost;

	MainActivity activity;

	private List<User> lstFri;
	private List<InterestPoint> lstIP;
	private List<Bitmap> friendImage;
	private List<Bitmap> IPImage;

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	private String response;
	private List<NameValuePair> lstNameValuePairs;

	private User user;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_user, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		activity = (MainActivity) this.getActivity();

		user = activity.temUser;

		activity.setTitle("�û���Ϣ");

		imgUser = (ImageView) this.getActivity().findViewById(
				R.id.user_img_user);
		imgUser.setImageBitmap(activity.temImage);

		txtName = (TextView) this.getActivity()
				.findViewById(R.id.user_txt_name);
		txtName.setText(user.getName());

		txtEmail = (TextView) this.getActivity().findViewById(
				R.id.user_txt_email);
		txtEmail.setText(user.getEmail());

		txtType = (TextView) this.getActivity()
				.findViewById(R.id.user_txt_type);
		switch (user.getType()) {
		case 0:
			txtType.setText("����Ա");
			break;
		case 1:
			txtType.setText("��ͨ�û�");
			break;
		}
		
		txtShare = (TextView) this.getActivity()
				.findViewById(R.id.user_txt_share);
		switch (user.getShare()) {
		case 0:
			txtShare.setText("������λ��");
			break;
		case 1:
			txtShare.setText("����λ��");
			break;
		}
		
		txtFriend = (TextView) this.getActivity().findViewById(
				R.id.user_txt_friend);
		
		txtIp = (TextView) this.getActivity().findViewById(
				R.id.user_txt_ip);

		btnShow = (Button) this.getActivity().findViewById(R.id.user_btn_show);
		btnShow.setOnClickListener(this);


		lstFriend = (ListView) this.getActivity()
				.findViewById(R.id.user_lst_fri);
		/*lstFriend.setAdapter(new UserItemAdapter(activity, activity.lstFriends,
				friendImage));*/
		//lstFriend.setOnItemClickListener(this);

		lstInterstP = (ListView) this.getActivity().findViewById(
				R.id.user_lst_int);
		/*lstInterstP.setAdapter(new InterestItemAdapter(activity,
				activity.lstIP, IPImage));
		lstInterstP.setOnItemClickListener(this);*/

		// ��ȡTabHost����
		tabHost = (TabHost) this.getActivity().findViewById(R.id.user_tabhost);
		// ���û�м̳�TabActivityʱ��ͨ�����ַ�����������tabHost
		tabHost.setup();

		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("�����б�")
				.setContent(R.id.user_widget_friend));

		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("��Ȥ���б�")
				.setContent(R.id.user_widget_privateip));

		TabWidget tabWidget = tabHost.getTabWidget();
		System.out.println(tabWidget.getChildCount());
		init();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.login_btn_login) {

		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		if (arg0.getId() == lstInterstP.getId()) {
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

		if (arg0.getId() == lstFriend.getId()) {
			activity.temUser = (User) arg0.getItemAtPosition(arg2);
			activity.temImage = friendImage.get(arg2);

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

	private void init() {
		User user = activity.temUser;

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		String u = gson.toJson(user);
		lstNameValuePairs = new ArrayList<NameValuePair>();
		lstNameValuePairs.add(new BasicNameValuePair("user_data", u));

		Thread t = new Thread() {// ����һ���µ��߳�
			public void run() {
				try {

				/*	handler.post(new Runnable() {// �˴���һ�������ڲ��࣬runnable�Զ�����Ϣ���͸����̴߳��������handler�����̻߳��Զ����¡�
						public void run() {

							dialog = new ProgressDialog(activity);
							dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// ���ý�������ʽ
							dialog.setMessage("���ڻ�ȡ��Ϣ...");
							dialog.setCancelable(false);// �ж��Ƿ�ȡ��������
							dialog.show();
						}
					});*/

					response = WebAccessUtils.httpRequest(
							"AGetPrivateIPServlet", lstNameValuePairs);

					Type userType = new TypeToken<List<InterestPoint>>() {
					}.getType();

					Gson gson = new GsonBuilder().setDateFormat(
							"yyyy-MM-dd hh:mm:ss").create();

					lstIP = gson.fromJson(response, userType);
					System.out.println(lstIP.size());
					if (lstIP == null)
						lstIP = new ArrayList<InterestPoint>();

					IPImage=new ArrayList<Bitmap>();
					for (int i = 0; i < lstIP.size(); i++) {
						Bitmap bitmap = WebAccessUtils
								.getBitmapFromServer("ipImage/"
										+ lstIP.get(i).getImage());
						IPImage.add(bitmap);
					}

					response = WebAccessUtils.httpRequest("AGetFriendServlet",
							lstNameValuePairs);

					userType = new TypeToken<List<User>>() {
					}.getType();

					lstFri = gson.fromJson(response, userType);
					
					if (lstFri == null)
						lstFri = new ArrayList<User>();

					friendImage=new ArrayList<Bitmap>();
					for (int i = 0; i < lstFri.size(); i++) {
						Bitmap bitmap = WebAccessUtils
								.getBitmapFromServer("userImage/"
										+ lstFri.get(i).getImage());
						friendImage.add(bitmap);
					}

					lstFriend.setAdapter(new UserItemAdapter(activity,
							lstFri, friendImage));
					lstFriend.invalidate();

					lstInterstP.setAdapter(new InterestItemAdapter(activity,
							lstIP, IPImage));
					lstInterstP.invalidate();
					
					txtFriend.setText(lstFri.size()+"������");
					txtIp.setText(lstIP.size()+"����Ȥ��");
					
					activity.lstFriends=lstFri;
					activity.lstFriImg=friendImage;
					activity.lstIP=lstIP;
					activity.lstIPImg=IPImage;

				} catch (Exception e) {
					e.printStackTrace();
					// Toast.makeText(activity, "������æ�����Ժ����ԣ�", 0).show();
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
}

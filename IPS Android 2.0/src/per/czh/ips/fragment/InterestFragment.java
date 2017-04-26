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
import per.czh.ips.po.Attend;
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

public class InterestFragment extends Fragment implements OnClickListener,
		OnItemClickListener {

	private TextView txtName;
	private TextView txtNum;
	private TextView txtType;
	private TextView txtPos;
	private TextView txtInf;
	private TextView txtCre;
	private TextView txtAttend;

	private Button btnShow;
	private Button btnAttend;

	private ImageView imgIP;

	private ListView lstAttendView;

	private TabHost tabHost;

	MainActivity activity;

	private List<User> lstUser;
	private List<Bitmap> userImage;

	private Handler handler = new Handler();
	private ProgressDialog dialog;
	private String response;
	private List<NameValuePair> lstNameValuePairs;

	private InterestPoint interestPoint;
	private User creator;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_interest, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		activity = (MainActivity) this.getActivity();

		interestPoint = activity.temIP;

		activity.setTitle("��Ȥ����Ϣ");

		imgIP = (ImageView) this.getActivity().findViewById(R.id.ip_img_ip);
		imgIP.setImageBitmap(activity.temImage);

		txtName = (TextView) this.getActivity().findViewById(R.id.ip_txt_name);
		txtName.setText(interestPoint.getName());

		txtNum = (TextView) this.getActivity().findViewById(R.id.ip_txt_num);
		txtNum.setText("���������" + interestPoint.getMaxMem());

		txtType = (TextView) this.getActivity().findViewById(R.id.ip_txt_type);
		switch (interestPoint.getType()) {
		case 0:
			txtType.setText("˽����Ȥ��");
			break;
		case 1:
			txtType.setText("������Ȥ��");
			break;
		}

		btnShow = (Button) this.getActivity().findViewById(R.id.ip_btn_show);
		btnShow.setOnClickListener(this);

		btnAttend = (Button) this.getActivity()
				.findViewById(R.id.ip_btn_attend);
		btnAttend.setOnClickListener(this);

		txtPos = (TextView) this.getActivity().findViewById(R.id.ip_txt_pos);
		txtPos.setText("��Ȥ�����꣺��" + interestPoint.getX() + "��"
				+ interestPoint.getY() + "��");

		txtPos = (TextView) this.getActivity().findViewById(R.id.ip_txt_int);
		txtPos.setText("��Ȥ����ܣ�" + interestPoint.getDescription());

		txtAttend = (TextView) this.getActivity().findViewById(R.id.ip_txt_attend);
		
		lstAttendView = (ListView) this.getActivity().findViewById(
				R.id.ip_lst_attend);
		lstAttendView.setOnItemClickListener(this);

		txtCre = (TextView) this.getActivity()
				.findViewById(R.id.ip_txt_creator);
		txtCre.setOnClickListener(this);

		// ��ȡTabHost����
		tabHost = (TabHost) this.getActivity().findViewById(R.id.ip_tabhost);
		// ���û�м̳�TabActivityʱ��ͨ�����ַ�����������tabHost
		tabHost.setup();

		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("��Ȥ����Ϣ")
				.setContent(R.id.ip_widget_inf));

		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("�������б�")
				.setContent(R.id.ip_widget_attend));

		init();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.ip_btn_show) {
			Bundle bundle = new Bundle();
			
			if(activity.mapFragment==null)activity.mapFragment=new MapFragment();
			activity.mapFragment.setArguments(bundle);

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.activity_main_container,
					activity.mapFragment, MainActivity.TAG_FRAG_MAP);
			transaction.addToBackStack(null);
			transaction.commit();
			activity.mapFragment.p=interestPoint;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		if (arg0.getId() == lstAttendView.getId()) {
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

	private void init() {

		Thread t = new Thread() {// ����һ���µ��߳�
			public void run() {
				try {

					handler.post(new Runnable() {// �˴���һ�������ڲ��࣬runnable�Զ�����Ϣ���͸����̴߳��������handler�����̻߳��Զ����¡�
						public void run() {

							dialog = new ProgressDialog(activity);
							dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// ���ý�������ʽ
							dialog.setMessage("���ڻ�ȡ��Ϣ...");
							dialog.setCancelable(false);// �ж��Ƿ�ȡ��������
							dialog.show();
						}
					});

					Gson gson = new GsonBuilder().setDateFormat(
							"yyyy-MM-dd hh:mm:ss").create();
					String u = gson.toJson(interestPoint);
					lstNameValuePairs = new ArrayList<NameValuePair>();
					lstNameValuePairs.add(new BasicNameValuePair("ip_data", u));

					response = WebAccessUtils.httpRequest(
							"AGetIpAttendServlet", lstNameValuePairs);

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

					creator = new User();
					creator.setId(interestPoint.getCreator());
					u = gson.toJson(creator);
					lstNameValuePairs = new ArrayList<NameValuePair>();
					lstNameValuePairs
							.add(new BasicNameValuePair("user_data", u));
					response = WebAccessUtils.httpRequest("AGetUserServlet",
							lstNameValuePairs);
					creator = gson.fromJson(response, attendType);

					handler.post(new Runnable() {// �˴���һ�������ڲ��࣬runnable�Զ�����Ϣ���͸����̴߳��������handler�����̻߳��Զ����¡�
						public void run() {

							dialog.dismiss();
						}
					});

					lstAttendView.setAdapter(new UserItemAdapter(activity,
							lstUser, userImage));
					lstAttendView.invalidate();

					txtCre.setText("�����ߣ�" + creator.getName());
					txtCre.invalidate();

					txtAttend.setText("��ǰ������" + lstUser.size());
					txtAttend.invalidate();

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

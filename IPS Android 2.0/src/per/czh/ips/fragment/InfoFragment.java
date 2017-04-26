package per.czh.ips.fragment;

import java.util.ArrayList;
import java.util.List;

import per.czh.ips.R;
import per.czh.ips.activity.MainActivity;
import per.czh.ips.item.InterestItemAdapter;
import per.czh.ips.item.UserItemAdapter;
import per.czh.ips.po.InterestPoint;
import per.czh.ips.po.User;
import per.jason.ips.internet.Visitor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class InfoFragment extends Fragment implements OnClickListener,
		OnItemClickListener {

	private TextView txtName;
	private TextView txtEmail;
	private TextView txtType;

	private EditText txtNewName;
	private EditText txtFile;
	private EditText txtNewPW;

	private Button btnChangeInf;
	private Button btnNewPW;
	private Button btnMap;
	
	private ImageView imgSelf;

	private ListView lstFriend;
	private ListView lstInterstP;

	private TabHost tabHost;

	MainActivity activity;

	private List<Bitmap> userImage;
	private List<Bitmap> IPImage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_inf, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		activity = (MainActivity) this.getActivity();

		activity.setTitle("用户信息");
	
		imgSelf = (ImageView) this.getActivity().findViewById(R.id.inf_img_self);
		imgSelf.setImageBitmap(activity.userImage);

		txtName = (TextView) this.getActivity().findViewById(R.id.inf_txt_name);
		txtName.setText(activity.user.getName());

		txtEmail = (TextView) this.getActivity().findViewById(
				R.id.inf_txt_email);
		txtEmail.setText(activity.user.getEmail());

		txtType = (TextView) this.getActivity().findViewById(R.id.inf_txt_type);
		switch (activity.user.getType()) {
		case 0:
			txtType.setText("管理员");
			break;
		case 1:
			txtType.setText("普通用户");
			break;
		}

		txtNewName = (EditText) this.getActivity().findViewById(
				R.id.inf_txt_newname);
		txtFile = (EditText) this.getActivity().findViewById(R.id.inf_txt_file);
		txtNewPW = (EditText) this.getActivity().findViewById(
				R.id.inf_txt_newpw);

		btnChangeInf = (Button) this.getActivity().findViewById(
				R.id.inf_btn_change);
		// btnChangeInf.setOnClickListener(this);

		btnNewPW = (Button) this.getActivity().findViewById(
				R.id.inf_btn_changepw);
		// btnNewPW.setOnClickListener(this);

		userImage = new ArrayList<Bitmap>();
		for (int i = 0; i < activity.lstFriends.size(); i++) {
			Visitor visitor = new Visitor(
					"userImage/"+activity.lstFriends.get(i).getImage(), null);
			visitor.flag = 1;
			visitor.start();
			try {
				visitor.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userImage.add(visitor.bitmap);
		}
		activity.lstFriImg=userImage;
		lstFriend = (ListView) this.getActivity()
				.findViewById(R.id.inf_lst_fri);
		System.out.println(activity.lstFriends.size());
		lstFriend.setAdapter(new UserItemAdapter(activity, activity.lstFriends,
				userImage));
		lstFriend.setOnItemClickListener(this);

		IPImage = new ArrayList<Bitmap>();
		for (int i = 0; i < activity.lstIP.size(); i++) {
			Visitor visitor = new Visitor("ipImage/"+activity.lstIP.get(i).getImage(),
					null);
			visitor.flag = 1;
			visitor.start();
			try {
				visitor.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			IPImage.add(visitor.bitmap);
		}
		activity.lstIPImg=IPImage;
		lstInterstP = (ListView) this.getActivity().findViewById(
				R.id.inf_lst_int);
		lstInterstP.setAdapter(new InterestItemAdapter(activity,
				activity.lstIP, IPImage));
		 lstInterstP.setOnItemClickListener(this);

		// 获取TabHost对象
		tabHost = (TabHost) this.getActivity().findViewById(R.id.inf_tabhost);
		// 如果没有继承TabActivity时，通过该种方法加载启动tabHost
		tabHost.setup();
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("更改信息")
				.setContent(R.id.widget_update));

		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("好友列表")
				.setContent(R.id.widget_friend));

		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("兴趣点列表")
				.setContent(R.id.widget_privateip));

		TabWidget tabWidget = tabHost.getTabWidget();


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
		if(arg0.getId()==lstInterstP.getId()){
			activity.temIP=(InterestPoint) arg0.getItemAtPosition(arg2);
			activity.temImage=IPImage.get(arg2);
			
			Bundle bundle = new Bundle();
			activity.interetFragment=new InterestFragment();
			activity.interetFragment.setArguments(bundle);

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.activity_main_container,
					activity.interetFragment, MainActivity.TAG_FRAG_INF);
			transaction.addToBackStack(null);
			transaction.commit();
		}
		
		if(arg0.getId()==lstFriend.getId()){
			activity.temUser=(User) arg0.getItemAtPosition(arg2);
			activity.temImage=userImage.get(arg2);
			
			Bundle bundle = new Bundle();
			activity.userFragment=new UserFragment();
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

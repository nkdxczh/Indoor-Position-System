package per.czh.ips.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import per.czh.ips.activity.MainActivity;
import per.czh.ips.po.User;
import per.jason.ips.internet.WebAccessUtils;
import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RefreshUser extends Thread {

	private int id;
	private Activity activity;

	public RefreshUser(int id, Activity activity) {
		super();
		this.id = id;
		this.activity = activity;
	}

	@Override
	public void run() {
		while (true) {
			/*
			 * double x=0,y=0; if(MainActivity.lstDetUser.size()!=0){
			 * x=MainActivity.lstDetUser.get(0).getXcoor();
			 * y=MainActivity.lstDetUser.get(0).getYcoor(); }
			 * MainActivity.lstDetUser.clear(); MainActivity.lstDetUser.add(new
			 * User(id, x+0.5, y+0.5, "", ""));
			 */

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
					.create();
			List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
			lstNameValuePairs.add(new BasicNameValuePair("id", id + ""));
			String response = WebAccessUtils
					.httpRequest("servlet/GetUserServlet?id=" + id);
			/*try {
				User user = gson.fromJson(response, User.class);

				boolean has = false;
				for (User u : MainActivity.lstDetUser) {
					if (u.getId() == user.getId()) {
						has = true;
						u.setXcoor(user.getXcoor());
						u.setYcoor(user.getYcoor());
						break;
					}
				}
				if (has == false)
					MainActivity.lstDetUser.add(user);

			} catch (Exception e) {

			}*/

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

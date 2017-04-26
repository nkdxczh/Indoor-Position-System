package per.czh.ips.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import per.czh.ips.activity.MainActivity;
import per.jason.ips.internet.WebAccessUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RefreshMe extends Thread {

	private int id;

	public RefreshMe(int id) {
		super();
		this.id = id;
	}

	@Override
	public void run() {
		while (true) {

			try {
				Gson gson = new GsonBuilder().setDateFormat(
						"yyyy-MM-dd hh:mm:ss").create();
				List<NameValuePair> lstNameValuePairs = new ArrayList<NameValuePair>();
				lstNameValuePairs.add(new BasicNameValuePair("id", id + ""));
			/*	String response = WebAccessUtils
						.httpRequest("servlet/SetUserServlet?id=" + id + "&x="
								+ MainActivity.x + "&y=" + MainActivity.y);*/

			} catch (Exception e) {

			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

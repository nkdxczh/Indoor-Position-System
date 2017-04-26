package per.czh.ips.util;

import com.sensoro.cloud.SensoroManager;

import android.app.Application;
import android.util.Log;

public class MyApp extends Application {

	private static final String TAG = MyApp.class.getSimpleName();
	private SensoroManager sensoroManager;

	@Override
	public void onCreate() {
		initSensoro();
		super.onCreate();
	}

	private void initSensoro() {
		setSensoroManager(SensoroManager.getInstance(getApplicationContext()));
		getSensoroManager().setCloudServiceEnable(false);
		try {
			getSensoroManager().startService();
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
	}

	@Override
	public void onTerminate() {
		if (getSensoroManager() != null) {
			getSensoroManager().stopService();
		}
		super.onTerminate();
	}

	public SensoroManager getSensoroManager() {
		return sensoroManager;
	}

	public void setSensoroManager(SensoroManager sensoroManager) {
		this.sensoroManager = sensoroManager;
	}

}

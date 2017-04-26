package per.czh.ips.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.sensoro.beacon.kit.Beacon;
import com.sensoro.beacon.kit.BeaconManagerListener;
import com.sensoro.cloud.SensoroManager;

import per.czh.ips.R;
import per.czh.ips.fragment.InfoFragment;
import per.czh.ips.fragment.InterestFragment;
import per.czh.ips.fragment.MapFragment;
import per.czh.ips.fragment.MenuFragment;
import per.czh.ips.fragment.SearchFragment;
import per.czh.ips.fragment.UserFragment;
import per.czh.ips.po.InterestPoint;
import per.czh.ips.po.Map;
import per.czh.ips.po.Node;
import per.czh.ips.po.Path;
import per.czh.ips.po.Signal;
import per.czh.ips.po.User;
import per.czh.ips.util.MyApp;
import per.czh.ips.util.RefreshUser;
import per.czh.ips.util.TTFIcon;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	RelativeLayout containLayout;

	public MenuFragment menuFragment;
	public MapFragment mapFragment;
	public InfoFragment infoFragment;
	public UserFragment userFragment;
	public InterestFragment interetFragment;
	public SearchFragment searchFragment;

	ActionBar actionBar;
	
	LayoutInflater inflater;
	private RelativeLayout actionBarMainLayout;
	RelativeLayout actionBarLayout;
	TTFIcon freshIcon;
	TTFIcon infoIcon;
	TextView actionBarTitle;

	NotificationManager notificationManager;
	public static final int NOTIFICATION_ID = 0;
	SharedPreferences sharedPreferences;

	FragmentManager fragmentManager;
	
	BeaconManagerListener beaconManagerListener;
	MyApp app;
	
	SensoroManager sensoroManager;
	
	private CopyOnWriteArrayList<Beacon> beacons;
	// private ArrayList<Beacon> beaconsl;
	private String beaconFilter;
	private String matchFormat;
	Handler handler = new Handler();
	Runnable runnable;

	public static final String TAG_FRAG_BEACONS = "TAG_FRAG_BEACONS";
	public static final String TAG_FRAG_MENU = "TAG_FRAG_MENU";
	public static final String TAG_FRAG_MAP = "TAG_FRAG_MAP";
	public static final String TAG_FRAG_ENV = "TAG_FRAG_ENV";
	public static final String TAG_FRAG_RES = "TAG_FRAG_RES";
	public static final String TAG_FRAG_INF = "TAG_FRAG_INF";
	public static final String TAG_FRAG_SEA = "TAG_FRAG_SEA";

	public static final String BEACON = "beacon";
	public static final String MENU = "menu";
	public static final String MAP = "map";
	public static final String ENV = "enviroment";
	public static final String RES = "research";
	public static final String INF = "information";

	BluetoothManager bluetoothManager;
	BluetoothAdapter bluetoothAdapter;
	ArrayList<OnBeaconChangeListener> beaconListeners;

	public static Context context;
	public static NotificationManager mNotificationManager;

	public static Bitmap background;
	public static List<InterestPoint> lstiPots = new ArrayList<InterestPoint>();
	public List<User> lstUser = new ArrayList<User>();
	public static List<User> lstDetUser = new ArrayList<User>();
	public static List<Node> nodes = new ArrayList<Node>();
	public static float w, h;

	public static List<InterestPoint> cusPots = new ArrayList<InterestPoint>();

	public static boolean haschange = false;
	public static boolean haslocated = false;
	public static boolean haspress = false;
	public static boolean hasshare = false;

	public static float x, y;
	public static int Pid;
	public static int count=0;
	public static float tx, ty;

	public static MapFragment map;
	
	public static RefreshUser refreshUser;
	public static RefreshUser refreshMe;
	
	public User user;
	public User temUser;
	public InterestPoint temIP;
	public Bitmap userImage;
	public Bitmap temImage;
	public List<Map> lstMap;
	public List<Node> lstNode;
	public List<Path> lstPath;
	public List<User> lstFriends;
	public List<Bitmap> lstFriImg;
	public List<Signal> lstSignal;
	public List<InterestPoint> lstIP;
	public List<Bitmap> lstIPImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = getApplicationContext();
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		initCtrl();
		showFragment(0);
		
		initSensoroListener();
		initRunnable();
		initBroadcast();

		refresh();
	}

	private void initCtrl() {
		containLayout = (RelativeLayout) findViewById(R.id.activity_main_container);
		fragmentManager = getSupportFragmentManager();
		inflater = getLayoutInflater();
		app = (MyApp) getApplication();
		setMatchFormat("%s-%04x-%04x");
		sensoroManager = app.getSensoroManager();
		setBeacons(new CopyOnWriteArrayList<Beacon>());
		beaconListeners = new ArrayList<OnBeaconChangeListener>();
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		sharedPreferences = getPreferences(Activity.MODE_PRIVATE);

	}
	
	private void showFragment(int fragmentID) {
		menuFragment = new MenuFragment();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(R.id.activity_main_container, menuFragment,
				TAG_FRAG_MENU);
		transaction.commit();
		setTitle(R.string.title);
	}
	
	private void initSensoroListener() {
		beaconManagerListener = new BeaconManagerListener() {

			@Override
			public void onUpdateBeacon(final ArrayList<Beacon> arg0) {
				/*
				 * Add the update beacons into the grid.
				 */
				for (Beacon beacon : arg0) {
					if (getBeacons().contains(beacon)) {
						continue;
					}
					/*
					 * filter
					 */

					if (TextUtils.isEmpty(getBeaconFilter())) {
						getBeacons().add(beacon);
					} 
					else {
						String matchString = String.format(getMatchFormat(),
								beacon.getSerialNumber(), beacon.getMajor(),
								beacon.getMinor());
						if (matchString.contains(getBeaconFilter())) {
							getBeacons().add(beacon);
						}
					}
				}
				
				runOnUiThread(new Runnable() {
					public void run() {
						for (OnBeaconChangeListener listener : beaconListeners) {
							if (listener == null) {
								continue;
							}
							listener.onBeaconChange(arg0);
						}
					}
				});

			}

			@Override
			public void onNewBeacon(Beacon arg0) {
				/*
				 * A new beacon appears.
				 */
				String key = getKey(arg0);
				boolean state = sharedPreferences.getBoolean(key, false);
				if (state) {
					/*
					 * show notification
					 */

					showNotification(arg0, true);
				}

			}

			@Override
			public void onGoneBeacon(Beacon arg0) {
				/*
				 * A beacon disappears.
				 */
				String key = getKey(arg0);
				boolean state = sharedPreferences.getBoolean(key, false);
				if (state) {
					/*
					 * show notification
					 */

					showNotification(arg0, false);
				}
			}
		};
	}
	
	private void initRunnable() {
		runnable = new Runnable() {

			@Override
			public void run() {
				updateGridView();
				handler.postDelayed(this, 2000);
			}
		};
	}
	
	private void initBroadcast() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);

		registerReceiver(new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
					int state = intent.getIntExtra(
							BluetoothAdapter.EXTRA_STATE,
							BluetoothAdapter.STATE_OFF);
					if (state == BluetoothAdapter.STATE_ON) {
						startSensoroService();
					}
				}
			}
		}, filter);
	}

	@SuppressLint("NewApi")
	private boolean isBlueEnable() {
		bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
		bluetoothAdapter=bluetoothManager.getAdapter();
		boolean status = bluetoothAdapter.isEnabled();
		if (!status) {
			Builder builder = new Builder(this);
			builder.setNegativeButton(R.string.yes, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(
							BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivity(intent);
				}
			}).setPositiveButton(R.string.no, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).setTitle(R.string.ask_bt_open);
			builder.show();
		}

		return status;
	}


	/*
	 * update the grid
	 */
	private void updateGridView() {
	}

	public String getKey(Beacon beacon) {
		if (beacon == null) {
			return null;
		}
		String key = beacon.getProximityUUID() + beacon.getMajor()
				+ beacon.getMinor() + beacon.getSerialNumber();

		return key;

	}

	private void showNotification(final Beacon beacon, final boolean isIn) {
		runOnUiThread(new Runnable() {

			@SuppressLint("NewApi")
			@Override
			public void run() {
				Notification.Builder builder = new Notification.Builder(
						getApplicationContext());
				String context = null;
				if (isIn) {
					context = String.format("IN:%s", beacon.getSerialNumber());
				} else {
					context = String.format("OUT:%s", beacon.getSerialNumber());
				}
				builder.setTicker(context);
				builder.setContentText(context);
				builder.setWhen(System.currentTimeMillis());
				builder.setAutoCancel(true);
				builder.setContentTitle(getString(R.string.app_name));
				builder.setSmallIcon(R.drawable.ic_launcher);
				builder.setDefaults(Notification.DEFAULT_SOUND);
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(
						MainActivity.this, 0, intent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				builder.setContentIntent(pendingIntent);

				Notification notification = builder.build();

				notificationManager.notify(NOTIFICATION_ID, notification);

			}
		});

	}

	/*
	 * Start sensoro service.
	 */
	private void startSensoroService() {
		// set a tBeaconManagerListener.
		sensoroManager.setBeaconManagerListener(beaconManagerListener);
		try {
			sensoroManager.startService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onResume() {
		boolean isBTEnable = isBlueEnable();
		if (isBTEnable) {
			startSensoroService();
		}
		handler.post(runnable);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		handler.removeCallbacks(runnable);
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	/*
	 * Beacon Change Listener.Use it to notificate updating of beacons.
	 */
	public interface OnBeaconChangeListener {
		public void onBeaconChange(ArrayList<Beacon> beacons);
	}

	/*
	 * Register beacon change listener.
	 */
	public void registerBeaconChangerListener(
			OnBeaconChangeListener onBeaconChangeListener) {
		if (beaconListeners == null) {
			return;
		}
		beaconListeners.add(onBeaconChangeListener);
	}

	/*
	 * Unregister beacon change listener.
	 */
	public void unregisterBeaconChangerListener(
			OnBeaconChangeListener onBeaconChangeListener) {
		if (beaconListeners == null) {
			return;
		}
		beaconListeners.remove(onBeaconChangeListener);
	}

	public String getBeaconFilter() {
		return beaconFilter;
	}

	public void setBeaconFilter(String beaconFilter) {
		this.beaconFilter = beaconFilter;
	}

	public String getMatchFormat() {
		return matchFormat;
	}

	public void setMatchFormat(String matchFormat) {
		this.matchFormat = matchFormat;
	}

	public RelativeLayout getActionBarMainLayout() {
		return actionBarMainLayout;
	}

	public void setActionBarMainLayout(RelativeLayout actionBarMainLayout) {
		this.actionBarMainLayout = actionBarMainLayout;
	}

	public CopyOnWriteArrayList<Beacon> getBeacons() {
		return beacons;
	}

	public void setBeacons(CopyOnWriteArrayList<Beacon> beacons) {
		this.beacons = beacons;
	}

	public Bitmap getBackground() {
		return background;
	}


	public void refresh() {
//		new Thread() {

//			public void run() {
				while (haschange == false) {

					haschange = true;
				}
	//		}

	//	}.start();

	}
}

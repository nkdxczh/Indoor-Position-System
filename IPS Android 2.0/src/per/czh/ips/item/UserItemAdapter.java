package per.czh.ips.item;

import java.util.List;

import per.czh.ips.R;
import per.czh.ips.po.User;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserItemAdapter extends BaseAdapter {

	private List<User> lstUser;
	private List<Bitmap> lstBitmap;
	private Context context;

	public UserItemAdapter(Context context, List<User> lstUser,
			List<Bitmap> lstBitmap) {
		super();
		this.lstUser = lstUser;
		this.lstBitmap = lstBitmap;
		this.context = context;
	}
	
	static class UserViewHolder {
		TextView name;
		TextView type;
		ImageView image;
	} 

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lstUser.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lstUser.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return lstUser.get(arg0).getId();
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		UserViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.item_user,
					null);
			holder = new UserViewHolder();
			view.setTag(holder);
			holder.name = (TextView) view.findViewById(R.id.iuser_txt_name);
			holder.type = (TextView) view.findViewById(R.id.iuser_txt_type);
			holder.image = (ImageView) view.findViewById(R.id.iuser_img_img);
		} else {
			holder = (UserViewHolder) view.getTag();
		}
		holder.name.setText(lstUser.get(arg0).getName());
		holder.image.setImageBitmap(lstBitmap.get(arg0));
		switch(lstUser.get(arg0).getType()){
		case 0:
			holder.type.setText("管理员");
			break;
		case 1:
			holder.type.setText("普通用户");
			break;
		}
		;
		return view;
	}

}
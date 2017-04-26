package per.czh.ips.item;

import java.util.List;

import per.czh.ips.R;
import per.czh.ips.po.InterestPoint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InterestItemAdapter extends BaseAdapter {

	private List<InterestPoint> lstInteretPoint;
	private List<Bitmap> ImgInteretPoint;
	private Context context;

	public InterestItemAdapter(Context context, List<InterestPoint> lstInteretPoint,List<Bitmap> ImgInteretPoint) {
		super();
		this.lstInteretPoint = lstInteretPoint;
		this.ImgInteretPoint=ImgInteretPoint;
		this.context = context;
	}
	
	static class IPViewHolder {
		TextView name;
		TextView type;
		ImageView image;
	} 

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lstInteretPoint.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lstInteretPoint.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return lstInteretPoint.get(arg0).getId();
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		IPViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.item_interestpoint,
					null);
			holder = new IPViewHolder();
			view.setTag(holder);
			holder.name = (TextView) view.findViewById(R.id.iip_txt_name);
			holder.type = (TextView) view.findViewById(R.id.iip_txt_type);
			holder.image = (ImageView) view.findViewById(R.id.iip_img_img);
		} else {
			holder = (IPViewHolder) view.getTag();
		}
		holder.name.setText(lstInteretPoint.get(arg0).getName());
		holder.image.setImageBitmap(ImgInteretPoint.get(arg0));
		switch(lstInteretPoint.get(arg0).getType()){
		case 0:
			holder.type.setText("私有兴趣点");
			break;
		case 1:
			holder.type.setText("公共兴趣点");
			break;
		}
		return view;
	}

}
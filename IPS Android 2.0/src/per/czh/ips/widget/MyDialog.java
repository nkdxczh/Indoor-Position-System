package per.czh.ips.widget;

import java.util.ArrayList;
import java.util.List;

import per.czh.ips.R;
import per.czh.ips.activity.MainActivity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MyDialog extends Dialog implements android.view.View.OnClickListener{

	Context context;
	private TextView txtPos;
	private Button btnOk;
	
    private List<String> list = new ArrayList<String>();    
    private Spinner mySpinner;    
    private ArrayAdapter<String> adapter;    
	
	public MyDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	

	public MyDialog(Context context,int theme) {
		super(context,theme);
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		
		this.setContentView(R.layout.dialog_save);

		MainActivity activity=(MainActivity)this.getOwnerActivity();
		txtPos = (TextView) findViewById(R.id.txt_add_pos);
		txtPos.setText("���꣺("+activity.tx+","+activity.ty+")");
		
		list.add("˽����Ȥ��");    
        list.add("������Ȥ��");        
        mySpinner = (Spinner)findViewById(R.id.spinner_type);    
        //�ڶ�����Ϊ�����б���һ����������������õ���ǰ�涨���list��    
        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, list);    
        //��������Ϊ���������������б�����ʱ�Ĳ˵���ʽ��    
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
        //���Ĳ�������������ӵ������б���    
        mySpinner.setAdapter(adapter);    
        
        btnOk=(Button)findViewById(R.id.btn_add_add);
        btnOk.setOnClickListener(this);
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		this.dismiss();
	}


}

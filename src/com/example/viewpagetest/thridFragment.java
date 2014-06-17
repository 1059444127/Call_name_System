package com.example.viewpagetest;

import java.util.HashMap;
import java.util.Map;

import com.example.call_name_system.Call_login;
import com.example.call_name_system.R;
import com.example.call_name_system.logic.logic;
import com.example.call_name_system.util.Https;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class thridFragment extends Activity {
	private TextView person_name, person_pa, person_pass;
	private EditText person_name_text, person_pas_text, person_pass_text;
	private Spinner spinner;
	private ArrayAdapter<String> arrayAdapter = null;
	private int CALL_LOGIN_NUM = 1;
	private int item_num;// ��¼ѡ�˵ڼ���Spinner item
	private static final String str[] = {"","�к�", "ԤԼ����" };

	// ����Spinner item ����Ϣ
	/*
	 * private Handler mhandler = new Handler() {
	 * 
	 * @Override public void handleMessage(Message msg) {
	 * super.handleMessage(msg); if (msg.what == CALL_LOGIN_NUM) { item_num =
	 * msg.getData().getInt("num"); } else { // Log.v("mes", "�������ݲ��ɹ�"); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * };
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thrid_fragment);
		init();

		spinner = (Spinner) findViewById(R.id.spinner2);

		// ����ѡ������ArrayAdapter����
		arrayAdapter = new ArrayAdapter<String>(thridFragment.this,
				android.R.layout.simple_spinner_item, str);
		spinner.setAdapter(arrayAdapter);
		// Spinner����¼�
		
System.out.println(!person_name_text.getText().toString().equals(""));
System.out.println(!person_pas_text.getText().toString().equals(""));

       
		if ((!person_name_text.getText().toString().equals("")
				&& !person_pas_text.getText().toString().equals("")
				&& !person_pass_text.getText().toString().equals(""))){
			
		} else{
			spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
					 	int arg2, long arg3) {

					arg0.setVisibility(View.VISIBLE);
					
					/*
					 * Message message = new Message(); message.what =
					 * CALL_LOGIN_NUM; Bundle bundle = new Bundle();
					 * bundle.putInt("num", arg2); // ��Bundle�д������
					 * message.setData(bundle);// mes����Bundle��������
					 * thridFragment.this.mhandler.sendMessage(message);//
					 * ��activity�е�handler������
					 */
					// �߼��жϣ�����ת��Viewpage����
					String user = person_name_text.getText().toString();
					String password = person_pas_text.getText().toString();
					String passwords = person_pass_text.getText().toString();
					
                    if(!password.equals("")){
					if (password.equals(passwords)) {
						Map<String, String> maps = new HashMap<String, String>();
						maps.put("username", user);
						maps.put("password", password);
						logic los = new logic(Https.IP_ADDRESS + "/logins",
								maps);
				//	System.out.println("���   " + los.getDate());
						if (los.getDate().equals("success")) {
							Map<String, String> map = new HashMap<String, String>();
							map.put("username", user);
							map.put("password", password);
							map.put("permisson", str[arg2].toString());
							logic loss = new logic(Https.IP_ADDRESS
									+ "/personner_table", map);
							//System.out.println("mes" + loss.getDate());
							if (loss.getDate().equals("success")) {
								Intent inent = new Intent();
								inent.setClass(thridFragment.this,
										Viewpage.class);
								startActivity(inent);
								finish();
							} else {
								Toast.makeText(thridFragment.this,
										"������,���Ȩ��ʧ��", Toast.LENGTH_SHORT)
										.show();
							}
						} else {
							Toast.makeText(thridFragment.this, "�û������������",
									Toast.LENGTH_SHORT).show();
						}

					} else {
						Toast.makeText(thridFragment.this, "�������벻һ��",
								Toast.LENGTH_SHORT).show();
					}

				}
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}

			});
			
			}
	}

	private void init() {
		person_name = (TextView) findViewById(R.id.person_name);
		person_pa = (TextView) findViewById(R.id.person_pas);
		person_pass = (TextView) findViewById(R.id.again_pas);

		person_name_text = (EditText) findViewById(R.id.person_name_edi);
		person_pas_text = (EditText) findViewById(R.id.person_pas_edi);
		person_pass_text = (EditText) findViewById(R.id.again_pas_edi);

	}

}

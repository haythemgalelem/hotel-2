package com.philippspiess.hotel;

import com.philippspiess.hotel.api.APIHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	public static String email = null;
	private ProgressDialog loading = null;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if(loading != null) {
				loading.dismiss();
				loading = null;
			}
			
			if(msg.what == 0) {
				errorDialog("Wrong data (or internal error)");
			} else {
    	    	EditText e_email = (EditText) findViewById(R.id.editTextEmail);
    			APIHelper.auth_email = e_email.getText().toString();
				
				Intent i = new Intent(RegisterActivity.this, BookingsActivity.class);
				startActivity(i);
			}
		}
	};
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.register);
		
		if(email != null) {
	    	EditText e_email = (EditText) this.findViewById(R.id.editTextEmail);
	    	e_email.setText(email);
		}		
	}
	
	 public void register(View view) {

		 	final String email = ((EditText) this.findViewById(R.id.editTextEmail)).getText().toString();
	    	final String pwd = ((EditText) this.findViewById(R.id.editTextPwd)).getText().toString();
	    	final String pwd2 = ((EditText) this.findViewById(R.id.editTextPwd2)).getText().toString();
	    	final String name = ((EditText) this.findViewById(R.id.editTextName)).getText().toString();
	    	final String adr = ((EditText) this.findViewById(R.id.editTextAdr)).getText().toString();
	    	final String tel = ((EditText) this.findViewById(R.id.editTextTel)).getText().toString();
	    	

			Log.w("log", "email = " + email);
			Log.w("log", "pwd = " + pwd);
			Log.w("log", "pwd2 = " + pwd2);
			Log.w("log", "name = " + name);
			Log.w("log", "adr = " + adr);
			Log.w("log", "tel = " + tel);
	    	
	    	
	    	if(email.length() > 0 && pwd.length() > 0 && pwd.equals(pwd2) && name.length() > 0 && adr.length() > 0 && tel.length() > 0) {
	    		
	    		loading = new ProgressDialog(RegisterActivity.this);
	    		loading.setTitle("Loading...");
	    		loading.setMessage("Grab a cup of coffee and wait a moment.");
	    		loading.show();
	    		
	    		new Thread(new Runnable() {
	    			public void run() {
	    				boolean code = APIHelper.Register(email, pwd, name, adr, tel);
	    				Log.w("log", "code = " + code);
	    				
	    				int m_code = 0;
	    				if(code)
	    					m_code = 1;
	    				
	    				// Create a new Message and post it to the queue!
	    				Message m = new Message();
	    				m.what = m_code;
	    				handler.sendMessage(m);
	    			}
	    		}).start();
	    	} else {
	    		errorDialog("Please check your form.");
	    	}
	    }
	
	public void errorDialog(String message) {
	  	AlertDialog alert = new AlertDialog.Builder(RegisterActivity.this).create();
	   	alert.setTitle("Whoops...");
	   	alert.setMessage(message);
	   	alert.setIcon(R.drawable.ic_launcher);
	   	alert.setButton("Ok", new DialogInterface.OnClickListener() {
	   		public void onClick(DialogInterface dialog, int which) {
	   			dialog.cancel();
	   		}
	   	});
	   	alert.show();
	}
}

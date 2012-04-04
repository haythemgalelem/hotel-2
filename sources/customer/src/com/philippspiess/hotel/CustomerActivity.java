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

public class CustomerActivity extends Activity {
	
	private ProgressDialog loading = null;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if(loading != null) {
				loading.dismiss();
				loading = null;
			}
			
			if(msg.what == 0 || msg.what == 3) {
				errorDialog("Wrong password (or internal error)");
			} else if(msg.what == 2) {
				AlertDialog alert = new AlertDialog.Builder(CustomerActivity.this).create();
	    		 alert.setTitle("Email not knwon");
		    	alert.setIcon(R.drawable.ic_launcher);
		    	alert.setButton("Create Account", new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog, int which) {
		    	    	EditText e_email = (EditText) findViewById(R.id.editText1);
		    			RegisterActivity.email = e_email.getText().toString();
		    			
		    			Intent i = new Intent(CustomerActivity.this, RegisterActivity.class);
		    			startActivity(i);
		    		}
		    	}); 
		    	alert.setMessage("Do you want to register an account?");
		    	alert.show();
			} else {
    	    	EditText e_email = (EditText) findViewById(R.id.editText1);
    			APIHelper.auth_email = e_email.getText().toString();
				
				Intent i = new Intent(CustomerActivity.this, BookingsActivity.class);
				startActivity(i);
			}
		}
	};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // I'm just lazy
    	EditText e_email = (EditText) this.findViewById(R.id.editText1);
    	e_email.setText("hello@philippspiess.com");
    	
    	EditText e_pwd = (EditText) this.findViewById(R.id.editText2);
    	e_pwd.setText("test");
    }
    
    public void register(View view) {
		Intent i = new Intent(CustomerActivity.this, RegisterActivity.class);
		startActivity(i);
    }
    
    public void login(View view) {

    	EditText e_email = (EditText) this.findViewById(R.id.editText1);
    	EditText e_password = (EditText) this.findViewById(R.id.editText2);
    	
    	final String email = e_email.getText().toString();
    	final String password = e_password.getText().toString();
    	
    	if(email.length() > 0 && password.length() > 0) {
    		
    		loading = new ProgressDialog(CustomerActivity.this);
    		loading.setTitle("Loading...");
    		loading.setMessage("Please wait while we're fetching your data.");
    		loading.show();
    		
    		new Thread(new Runnable() {
    			public void run() {
    				int code = APIHelper.Authenticate(email, password);
    				// Create a new Message and post it to the queue!
    				Message m = new Message();
    				m.what = code;
    				handler.sendMessage(m);
    			}
    		}).start();
    	} else {
    		errorDialog("You haven't set a passwor!");
    	}
    }
    
    public void errorDialog(String message) {
    	AlertDialog alert = new AlertDialog.Builder(CustomerActivity.this).create();
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
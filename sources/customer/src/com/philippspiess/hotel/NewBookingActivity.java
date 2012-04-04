package com.philippspiess.hotel;

import com.philippspiess.hotel.api.APIHelper;
import com.philippspiess.hotel.api.model.Hotel;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class NewBookingActivity extends Activity {

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
				
				AlertDialog alert = new AlertDialog.Builder(NewBookingActivity.this).create();
			   	alert.setTitle("Whoops...");
			   	alert.setMessage("Your booking was saved.");
			   	alert.setIcon(R.drawable.ic_launcher);
			   	alert.setButton("Ok", new DialogInterface.OnClickListener() {
			   		public void onClick(DialogInterface dialog, int which) {
			   			Intent i = new Intent(NewBookingActivity.this, BookingsActivity.class);
		    			startActivity(i);
			   		}
			   	});
			   	alert.show();
			}
		}
	};
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.new_booking);
		
		Hotel h = APIHelper.current_hotel;
		
		Log.w("log", h.toString());
		
		
		 // Hotel
        ((TextView) this.findViewById(R.id.newBooking_hotel_name)).setText(h.Name);
        ((TextView) this.findViewById(R.id.newBooking_hotel_adr)).setText(h.Adr);
	}
	
	public void newBooking(View view) {
		
		DatePicker d = (DatePicker) this.findViewById(R.id.newBooking_at);
		
		final String at = String.format("%04d.%02d.%02d", d.getYear(), d.getMonth(), d.getDayOfMonth());
		final int duration = Integer.parseInt(((EditText) this.findViewById(R.id.newBooking_duration)).getText().toString());
	    final int roomNr = Integer.parseInt(((EditText) this.findViewById(R.id.newBooking_roomNr)).getText().toString());
	    final int numAdults = Integer.parseInt(((EditText) this.findViewById(R.id.newBooking_numAdults)).getText().toString());
	    final int numChilds = Integer.parseInt(((EditText) this.findViewById(R.id.newBooking_numChilds)).getText().toString());
	 
		Log.w("log", "at = " + at);
		Log.w("log", "duration = " + duration);
		Log.w("log", "roomNr = " + roomNr);
		Log.w("log", "numAdults = " + numAdults);
		Log.w("log", "numChilds = " + numChilds);
		
		String error = "";
		
		if(roomNr <= 0 )
			error = "RoomNr can't be zero.";
		if(numAdults <= 0)
			error = "There must be at least one adult.";
		if(duration <= 0)
			error = "You must be at least one day in the hotel.";
		
		if(error.isEmpty()) {
			
			loading = new ProgressDialog(NewBookingActivity.this);
    		loading.setTitle("Loading...");
    		loading.setMessage("Grab a cup of coffee and wait a moment.");
    		loading.show();
    		
    		new Thread(new Runnable() {
    			public void run() {
    				boolean code = APIHelper.NewBooking(at, duration, roomNr, numAdults, numChilds);
    				
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
			errorDialog(error);
		}
	}
	
	public void errorDialog(String message) {
	  	AlertDialog alert = new AlertDialog.Builder(NewBookingActivity.this).create();
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

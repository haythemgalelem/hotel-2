package com.philippspiess.hotel;

import java.util.ArrayList;

import com.philippspiess.hotel.api.APIHelper;
import com.philippspiess.hotel.api.model.Booking;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class BookingsActivity extends Activity {
	
	private ProgressDialog loading = null;
	
	private ArrayList<Booking> list = new ArrayList<Booking>();
	private ListView listView;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(loading != null) {
				loading.dismiss();
				loading = null;
			}
			
			if(msg.obj != null) {
				list = (ArrayList<Booking>) msg.obj;
				Log.w("log", "list = " + list.toString());
				
				// Don't know why I have to create a new Adapter every time. But i have to.
				updateList();
			}
		}
	};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bookings);

		listView = (ListView) findViewById(R.id.list);
		updateList();
		
		listView.setClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				if(arg2 < list.size()) {
					Booking b = list.get(arg2);
				
					Log.w("log", "Click = " + b.toString());
				}
			}
		});

		//this.setListAdapter(adapter);
		
		loading = new ProgressDialog(BookingsActivity.this);
		loading.setTitle("Loading...");
		loading.setMessage("Please wait while we're fetching your data.");
		loading.show();
		
		new Thread(new Runnable() {
			public void run() {
				ArrayList<Booking> list = APIHelper.ListBookings();
				
				
				// Create a new Message and post it to the queue!
				Message m = new Message();
				m.obj = list;
				handler.sendMessage(m);
			}
		}).start();
	}
	
	public void updateList() {
		ArrayAdapter<Booking> adapter = new ArrayAdapter<Booking>(BookingsActivity.this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
	}
	
	
}

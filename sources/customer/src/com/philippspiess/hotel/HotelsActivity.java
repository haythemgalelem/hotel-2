package com.philippspiess.hotel;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.philippspiess.hotel.api.APIHelper;
import com.philippspiess.hotel.api.model.Booking;
import com.philippspiess.hotel.api.model.Hotel;

public class HotelsActivity extends Activity {
	
	private ProgressDialog loading = null;
	
	private ArrayList<Hotel> list = new ArrayList<Hotel>();
	private ListView listView;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(loading != null) {
				loading.dismiss();
				loading = null;
			}
			
			if(msg.obj != null) {
				list = (ArrayList<Hotel>) msg.obj;
				updateList();
			}
		}
	};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.hotels);

		listView = (ListView) findViewById(R.id.hotelsList);
		updateList();
		
		listView.setClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				if(arg2 < list.size()) {
					APIHelper.current_hotel = list.get(arg2);
					Log.w("log", "Click = " + APIHelper.current_hotel.toString());
					
					//Intent i = new Intent(BookingsActivity.this, BookingActivity.class);
	    			//startActivity(i);
				}
			}
		});

		//this.setListAdapter(adapter);
		
		loading = new ProgressDialog(HotelsActivity.this);
		loading.setTitle("Loading...");
		loading.setMessage("Please wait while we're fetching your data.");
		loading.show();
		
		new Thread(new Runnable() {
			public void run() {
				ArrayList<Hotel> list = APIHelper.ListHotels();
				
				// Create a new Message and post it to the queue!
				Message m = new Message();
				m.obj = list;
				handler.sendMessage(m);
			}
		}).start();
	}
	
	public void updateList() {
		ArrayAdapter<Hotel> adapter = new ArrayAdapter<Hotel>(HotelsActivity.this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
	}	
}

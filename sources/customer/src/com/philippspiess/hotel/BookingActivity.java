package com.philippspiess.hotel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.philippspiess.hotel.api.*;
import com.philippspiess.hotel.api.model.*;

public class BookingActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);
        
        Booking b = APIHelper.current_booking;
        
        Log.w("log", b.toString());
        
        
        // Hotel
        ((TextView) this.findViewById(R.id.booking_hotel_name)).setText(b.Hotel.Name);
        ((TextView) this.findViewById(R.id.booking_hotel_adr)).setText(b.Hotel.Adr);
        

        Log.w("log", b.toString());
        
        // Booking
        ((TextView) this.findViewById(R.id.booking_at)).setText(b.At);
        ((TextView) this.findViewById(R.id.booking_duration)).setText("" + b.Duration);
        ((TextView) this.findViewById(R.id.booking_roomnr)).setText("" + b.RoomNr);
        ((TextView) this.findViewById(R.id.booking_numadults)).setText("" + b.NumAdults);
        ((TextView) this.findViewById(R.id.booking_numchilds)).setText("" + b.NumChilds);
        

        Log.w("log", b.toString());
        
        // Customer
        ((TextView) this.findViewById(R.id.booking_customer_email)).setText(b.Customer.Email);
        ((TextView) this.findViewById(R.id.booking_customer_name)).setText(b.Customer.Name);
        ((TextView) this.findViewById(R.id.booking_customer_adr)).setText(b.Customer.Adr);
        ((TextView) this.findViewById(R.id.booking_customer_tel)).setText("" + b.Customer.Tel);
        
        
	}
}

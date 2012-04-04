package com.philippspiess.hotel.api;

import java.util.ArrayList;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.HttpTransportSE;
import com.philippspiess.hotel.api.model.*;
import android.util.Log;

public class APIHelper {
	
	public static String NAMESPACE = "http://myxcode.at/";
	public static String URL = "http://192.168.2.110/hotel/Server.asmx";
	
	public static String auth_email = null; // will be set when logged in
	
	public static int Authenticate(String email, String password) {
		SoapObject req = new SoapObject(NAMESPACE, "Authenticate");
		req.addProperty("email", email);
		req.addProperty("pwd", password);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;	
        envelope.setOutputSoapObject(req);
        
        try
        {
        	HttpTransportSE t = new HttpTransportSE(URL);
            
            t.call(NAMESPACE + "Authenticate", envelope);
            SoapPrimitive response = (SoapPrimitive)envelope.getResponse();            
            int result = Integer.parseInt(response.toString());

        	//Log.w("Log", "Result = " + result);
            
            return result;
        }
        catch(Exception e)
        {
        	
        	Log.w("Log", e.toString());
        	
            e.printStackTrace();
            return 0;
        }
	}
	
	public static ArrayList<Booking> ListBookings() {
		ArrayList<Booking> list = new ArrayList<Booking>();
		
		try {
			SoapObject req = new SoapObject(NAMESPACE, "ListBookings");
			req.addProperty("email", auth_email);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet = true;	
	        envelope.setOutputSoapObject(req);
	        envelope.addMapping(NAMESPACE, "Booking", Booking.class);
	        envelope.addMapping(NAMESPACE, "Hotel", Hotel.class);
	        envelope.addMapping(NAMESPACE, "Customer", Customer.class);
	        
	        HttpTransportSE t = new HttpTransportSE(URL);
	            
	        t.call(NAMESPACE + "ListBookings", envelope);
	        SoapObject response = (SoapObject)envelope.getResponse();  
	        
	        for (int i = 0; i < response.getPropertyCount(); i++) {
	            SoapObject pii = (SoapObject)response.getProperty(i);
	            
	            //Log.w("log", "pii = " + pii.toString());
	            
	            //Log.w("log", "LOL="+pii.getPropertyAsString(0));
	            SoapObject c = (SoapObject)pii.getProperty(0);
	            
	            SoapObject h = (SoapObject)pii.getProperty(1);
	            
	            
	            Booking booking = new Booking();
	            
	            Customer customer = new Customer();
	            customer.Email = c.getPropertyAsString(0);
	            customer.Name = c.getPropertyAsString(1);
	            customer.Adr = c.getPropertyAsString(2);
	            customer.Tel = Long.parseLong(c.getPropertyAsString(3));
	            
	            Hotel hotel = new Hotel();
	            hotel.Hid = Integer.parseInt(h.getPropertyAsString(0));
	            hotel.Name = h.getPropertyAsString(1);
	            hotel.Adr = h.getPropertyAsString(2);
	            
	            booking.Customer = customer;
	            booking.Hotel = hotel;
	            booking.At = pii.getPropertyAsString(2);
	            
	            //Log.w("log", booking.toString());
	            
	            list.add(booking);
	        }
	            
		} catch(Exception ignore) { 
			// Ignoring, we will serve an empty list if something went wrong.
			Log.w("log", ignore);
		} 
		
		
		return list;
	}
	

	public static boolean Register(String email, String pwd, String name, String adr, String tel) {
		SoapObject req = new SoapObject(NAMESPACE, "Register");
		req.addProperty("email", email);
		req.addProperty("pwd", pwd);
		req.addProperty("name", name);
		req.addProperty("adr", adr);
		req.addProperty("tel", tel);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;	
        envelope.setOutputSoapObject(req);
        
        try
        {
        	HttpTransportSE t = new HttpTransportSE(URL);
            
            t.call(NAMESPACE + "Register", envelope);
            SoapPrimitive response = (SoapPrimitive)envelope.getResponse();            
            boolean result = Boolean.parseBoolean(response.toString());

        	//Log.w("Log", "Result = " + result);
            
            return result;
        }
        catch(Exception e)
        {
        	Log.w("Log", e.toString());
        	
            e.printStackTrace();
            return false;
        }
	}
	
	
	
}

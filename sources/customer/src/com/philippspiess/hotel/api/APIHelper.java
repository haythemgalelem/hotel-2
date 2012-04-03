package com.philippspiess.hotel.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class APIHelper {
	
	public static String NAMESPACE = "http://myxcode.at/";
	public static String URL = "http://192.168.2.110/hotel/Server.asmx";
	
	public int Authenticate(String email, String password) {
		
		SoapObject req = new SoapObject(NAMESPACE, "Authenticate");
		req.addProperty("email", email);
		req.addProperty("pwd", password);
		
		//Log.w("log", "email = " + email);
		//Log.w("log", "pwd = " + password);
		
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
        }/*
		
		
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpPost post = new HttpPost(URL + "/Authenticate");
		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("email", email));
			nameValuePairs.add(new BasicNameValuePair("pwd", password));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse response = httpClient.execute(post);
			
			String res = (inputStreamToString(response.getEntity().getContent())).toString();
			
			Log.w("log", res);
			
			return res;
			
			//return 1;
		} catch (Exception e) {
			Log.w("log", e.toString());
			return e.toString();
		}*/
	}
	/*
	private StringBuilder inputStreamToString(InputStream is) {
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		try {
			while ((line = rd.readLine()) != null) {
				total.append(line);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return total;		
	}
	*/
}

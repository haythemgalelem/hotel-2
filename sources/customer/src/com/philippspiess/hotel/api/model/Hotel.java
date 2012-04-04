package com.philippspiess.hotel.api.model;

public class Hotel {
	public int Hid;
	public String Name;
	public String Adr;
	
	
	public int getHid() {
		return Hid;
	}
	public void setHid(int hid) {
		Hid = hid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAdr() {
		return Adr;
	}
	public void setAdr(String adr) {
		Adr = adr;
	}	
	
	public String toString() {
		return Name + " - " + Adr;
	}
}

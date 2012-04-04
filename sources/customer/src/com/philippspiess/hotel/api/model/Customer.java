package com.philippspiess.hotel.api.model;

public class Customer {

    public String Email;
    public String Name;
    public String Adr;
    public Long Tel;
    
    
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
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
	public Long getTel() {
		return Tel;
	}
	public void setTel(Long tel) {
		Tel = tel;
	}
}

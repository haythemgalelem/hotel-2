package com.philippspiess.hotel.api.model;

public class Booking {
    public Customer Customer;
    public Hotel Hotel;
    public String At;
    public int Duration;
    public int RoomNr;
    public int NumAdults;
    public int NumChilds;
	
    
    public Customer getCustomer() {
		return Customer;
	}
	public void setCustomer(Customer customer) {
		Customer = customer;
	}
	public Hotel getHotel() {
		return Hotel;
	}
	public void setHotel(Hotel hotel) {
		Hotel = hotel;
	}
	public String getAt() {
		return At;
	}
	public void setAt(String at) {
		At = at;
	}
	public int getDuration() {
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
	}
	public int getRoomNr() {
		return RoomNr;
	}
	public void setRoomNr(int roomNr) {
		RoomNr = roomNr;
	}
	public int getNumAdults() {
		return NumAdults;
	}
	public void setNumAdults(int numAdults) {
		NumAdults = numAdults;
	}
	public int getNumChilds() {
		return NumChilds;
	}
	public void setNumChilds(int numChilds) {
		NumChilds = numChilds;
	}
	
	public String toString() {
		return Hotel.Name + " at " + At;
	}
}

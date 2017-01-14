package com.phasebeyond.restaurantfinder;

public class Restaurant {

    private String Name;
    private String Address;
    private String Locality;
    private String Cuisines;
    private String Cost;
    private String Hours;
    private String Phone_No;
    private String Latitude;
    private String Longitude;
    //private String photoUrl;

    public Restaurant(){
        //Empty Constructor
    }

    public Restaurant(String Name, String Address, String Locality, String Cuisines, String Cost, String Hours, String Phone_No, String Latitude, String Longitude/*, String photoUrl*/){
        this.Name = Name;
        this.Address = Address;
        this.Locality = Locality;
        this.Cuisines = Cuisines;
        this.Cost = Cost;
        this.Hours = Hours;
        this.Phone_No = Phone_No;
        this.Latitude = Latitude;
        this.Longitude = Longitude;

        //this.photoUrl = photoUrl;
    }

    //GETTERS

    public String getName() {
        return Name;
    }

    public String getAddress() {return Address;}

    public String getLocality() {return Locality;}

    public String getCuisines() {return Cuisines;}

    public String getCost() {
        return Cost;
    }

    public String getHours() {
        return Hours;
    }

    public String getPhone_No() {return Phone_No;}

    public String getLatitude() {return Latitude;}

    public String getLongitude() {return Longitude;}

    //public String getPhotoUrl() { return photoUrl; }

    //SETTERS

    public void setName(String name) { this.Name = name; }

    public void setAddress(String address) {Address = address;}

    public void setLocality(String locality) {Locality = locality;}

    public void setCuisines(String cuisines) { Cuisines = cuisines; }

    public void setCost(String Cost) {this.Cost = Cost;}

    public void setHours(String Hours) {
        this.Hours = Hours;
    }

    public void setPhone_No(String phone_No) {Phone_No = phone_No;}

    public void setLatitude(String latitude) {Latitude = latitude;}

    public void setLongitude(String longitude) {Longitude = longitude;}

    //public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}

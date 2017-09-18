package com.celloscope.contact.phonebook.bean;

/**
 * Created by aney on 9/6/17.
 */

public class Contact {
    int _id;
    public String name;
    public String mobile_number;
    String email;
    String address;
    String dept;
    public String path;



    public Contact(){

    }

    public Contact(int id, String name, String mobile_number, String path, String email, String address, String dept){
        this._id = id;
        this.name = name;
        this.mobile_number=mobile_number;
        this.path = path;
        this.email = email;
        this.address = address;
        this.dept = dept;
    }

    public void setID(int id){
        this._id = id;
    }

    public int getID(){
        return this._id;
    }

    public void setName(String name){
        this.name =name;
    }
    public String getName() {
        return name;
    }


    public void setMobNo(String mobile_number){
        this.mobile_number=mobile_number;
    }
    public String getMobNo() {
        return  mobile_number;
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail() {
        return  email;
    }

    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress() {
        return  address;
    }

    public void setDept(String dept){
        this.dept=dept;
    }
    public String getDept() {
        return  dept;
    }
}

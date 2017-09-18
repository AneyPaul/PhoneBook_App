package com.celloscope.contact.phonebook.bean;

/**
 * Created by aney on 8/20/17.
 */

public class Registration {

    int _id;
    String first_name;
    String email_id;
    String mobile_number;
    String password;


    public Registration(){

    }

    public Registration(int id, String first_name, String email_id, String phone_number, String mobile_number){
        this._id = id;
        this.first_name = first_name;
        this.email_id=email_id;
        this.mobile_number=mobile_number;
    }

    public void setID(int id){
        this._id = id;
    }

    public int getID(){
        return this._id;
    }


    public void setfirstName(String first_name){
        this.first_name =first_name;
    }
    public String getfirstName() {
        return first_name;
    }

    public void setEmailId(String email_id){
        this.email_id =email_id;
    }
    public String getEmailId() {
        return email_id;
    }

    public void setMobNo(String mobile_number){
        this.mobile_number=mobile_number;
    }
    public String getMobNo() {
        return  mobile_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }




}

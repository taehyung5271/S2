package khs.mjc.project;

import java.util.Date;

public class UserInfo {
    public String name, date, gender;

    public UserInfo(){}

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public  UserInfo(String name, String date, String gender) {
        this.name = name;
        this.date = date;
        this.gender = gender;
    }
}

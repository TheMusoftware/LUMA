/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luma;

import java.util.Date;

/**
 *
 * @author musoftware
 */
public class Profile {
    private int profile_id;
    public String profile_name;
    private Date date;
    private String username;
    private String password;
    
    public Profile(int profile_id, String profile_name, String username){
        this.profile_id = profile_id;
        this.profile_name = profile_name;
        this.username = username;
        this.password = password;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public String getUsername() {
        return username;
    }
    
    
   
    
}

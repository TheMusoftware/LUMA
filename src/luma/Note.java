/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luma;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author musoftware
 */
public class Note {
    private int note_id;
    private int profile_id;
    private String title;
    private String note;
    private String updateDate;

    public Note(int note_id, int profile_id, String title, String note, String updateDate) {
        this.note_id = note_id;
        this.profile_id = profile_id;
        this.title = title;
        this.note = note;
        this.updateDate = updateDate;
       
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUpdateDate() {
        return updateDate;
    }


    
    
    
    
    
    
}

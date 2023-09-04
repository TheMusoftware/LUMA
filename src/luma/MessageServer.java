/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luma;

import java.sql.SQLException;

/**
 *
 * @author musoftware
 */
public class MessageServer {
    private String message;
    public MessageServer(String message){
        this.message = message;
    }
    public void sendMessage(String sender, String receiver) throws SQLException{
        DatabaseOperations.newMessage(message, sender, receiver);
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package luma;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.*;

/**
 *
 * @author musoftware
 */
public class LUMA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        // TODO code application logic here
     // Connection connection = DriverManager.getConnection("jdbc:sqlite:Databases/luma.db");
      //Connection cnotes = DriverManager.getConnection("jdbc:sqlite:Databases/luma_usernotes.db");
     // Statement st = connection.createStatement();
      //Statement stnotes = cnotes.createStatement();
     // ResultSet usernames = st.executeQuery("select * from profiles where 1");
     // while(usernames.next()){
     /// }
     //   System.out.println("Operations done!!!");
      InetAddress IA = InetAddress.getByName("localhost");
        System.out.println(IA.getHostAddress());
         String sharedFolderPath = System.getProperty("user.dir") + File.separator + "Shared";
         System.out.println(sharedFolderPath);
        
      
     /*final byte[] ip;
    try {
        ip = InetAddress.getLocalHost().getAddress();
    } catch (Exception e) {
        return;     // exit method, otherwise "ip might not have been initialized"
    }

    for(int i=1;i<=254;i++) {
        final int j = i;  // i as non-final variable cannot be referenced from inner class
        new Thread(new Runnable() {   // new thread for parallel execution
            public void run() {
                try {
                    ip[3] = (byte)j;
                    InetAddress address = InetAddress.getByAddress(ip);
                    String output = address.toString().substring(1);
                    if (address.isReachable(5000)) {
                        System.out.println(output + " is on the network");
                    } else {
                        //System.out.println("Not Reachable: "+output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();     // dont forget to start the thread
    }
     try (Socket socket = new Socket()) {
    socket.connect(new InetSocketAddress("google.com", 80));
         System.out.println( "Ip adress: "+socket.getLocalAddress().getHostAddress());
}  */
    }
    public static InetAddress[] getLocalIps() {
        InetAddress[] ia = null;
        try {
            ia = InetAddress.getAllByName(InetAddress.getLocalHost()
                    .getHostName());
        } catch (Exception e) {
        }
        return ia;
    }
}
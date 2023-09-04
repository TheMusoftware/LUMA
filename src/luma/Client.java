/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luma;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author musoftware
 */
public class Client {
    private Socket socket = null;
    private Profile profile;
    private InputStreamReader isr;
    private OutputStreamWriter osw;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Server server;
    
    public Client(Profile profile){
        this.profile = profile;
    }
    public void connectServer() {
        
        System.out.println("start client");
        final String hostAddress = "192.168.1.";
        final int startIP = 1;
        final int endIP = 255;
        final int port = 2051;
        ExecutorService executor = Executors.newFixedThreadPool(endIP - startIP + 1);

        try {
            for (int i = startIP; i <= endIP; i++) {
                final String ip = hostAddress + i;
                Future<?> future = executor.submit(() -> {
                    try {
                        Socket socket = new Socket();
                        socket.connect(new InetSocketAddress(ip, port), 5000); // Timeout 5 saniye
                        if (socket.isConnected()) {
                            System.out.println("Connected to " + ip + " on port " + port);
                            this.socket = socket;
                        }
                    } catch (Exception e) {
                       // System.out.println("Error for " + ip + ": " + e.getMessage());
                    }
                });
         
         }
            executor.shutdown();
        if(socket==null){
          // Server server = new Server();
           //LoginScreen ls = new LoginScreen();
           //sls.setVisible(true);
           
        }
        else{
        isr = new InputStreamReader(socket.getInputStream(),"UTF-8");
        osw = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
        reader = new BufferedReader(isr);
        writer = new BufferedWriter(osw);
         System.out.println("Socket's settings success");
         if(profile != null){
             writer.write(profile.getUsername());
             writer.newLine();
             writer.flush();
         }
         else{
             writer.write("an user ");
             writer.newLine();
             writer.flush();
         }
        }
     }   
     catch(Exception e){
         System.out.println(e.getMessage());
         System.out.println("error");
     }
    }
    public String getHostAddress(){
        InetAddress ia = socket.getInetAddress();
        return ia.getHostAddress();
    }
     
    public String getMessage() throws IOException{
        if(reader!=null){
            return reader.readLine()+"\n";
        }
        return "";
    }

    public Socket getSocket() {
        return socket;
    }

    public Profile getProfile() {
        return profile;
    }

    public InputStreamReader getIsr() {
        return isr;
    }

    public OutputStreamWriter getOsw() {
        return osw;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }
    
    

    
}

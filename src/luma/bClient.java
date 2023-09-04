/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luma;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author musoftware
 */
public class bClient {
    private Socket socket;
    private static final int SERVER_PORT = 4102;
    private String SERVER_IP = "";
    private InputStream is = null;
    protected void saveFile() throws IOException{
        InputStreamReader isr = new InputStreamReader(socket.getInputStream(),"UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String fileName = reader.readLine();
        String savePath = System.getProperty("user.dir") + File.separator + "Shared/"+fileName;
        Path outputPath = Paths.get(savePath);
        System.out.println("Save Path: "+savePath);
        if (!Files.exists(outputPath.getParent())) {
                Files.createDirectories(outputPath.getParent());
            }
        
                    byte[] contents = new byte[1000000];

            // Initialize the FileOutputStream to the output file's full path.
            FileOutputStream fos = new FileOutputStream(savePath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            InputStream is = socket.getInputStream();

            // No of bytes read in one read() call
            int bytesRead = 0;
            while ((bytesRead = is.read(contents)) != -1) {
                bos.write(contents, 0, bytesRead);
            }
            System.out.println(socket.getInetAddress());
            bos.flush();
            socket.close();
            System.out.println("File saved successfully!");
            JOptionPane.showMessageDialog(null, "File saved succesfully on Shared folder", "Operation done", 1);
    }
    
    public bClient(String SERVER_IP){
        this.SERVER_IP = SERVER_IP;
    }
    protected boolean connectServer() {
    try {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        

        if (socket.isConnected()) {
            System.out.println("Connection success");
            return true;
        }
    } catch (Exception e) {
        e.printStackTrace(); // Print detailed error information
        return false;
    }
    return false;
}
    public Socket getSocket(){
        return this.socket;
    }

}

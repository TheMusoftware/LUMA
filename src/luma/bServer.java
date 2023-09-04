/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luma;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author musoftware
 */
public class bServer {
    private Socket socket;
    private static final int SERVER_PORT = 4102;
    private OutputStream os = null;
    private static String name;
    public static void setName(String str){
        name = str;
    }
    protected boolean connectServer() {
    try (ServerSocket ssocket = new ServerSocket(SERVER_PORT)) {
        socket = ssocket.accept();
        os = socket.getOutputStream();
        return true; 
       
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
    }
    
}
    protected void sendFile(File file) throws FileNotFoundException, IOException{
        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
        BufferedWriter writer = new BufferedWriter(osw);
        writer.write(file.getName());
        writer.newLine();
        writer.flush();
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        
                    byte[] contents;
            long fileLength = file.length();
            long current = 0;
            long start = System.nanoTime();

            while (current != fileLength) {
                int size = 1000000;
                if (fileLength - current >= size)
                    current += size;
                else {
                    size = (int) (fileLength - current);
                    current = fileLength;
                }
                contents = new byte[size];
                bis.read(contents, 0, size);
                os.write(contents);
                System.out.println("Sending file ... " + (current * 100) / fileLength + "% complete!");
            }

            os.flush();
                        // File transfer done. Close the socket connection!
            socket.close();
            System.out.println("File sent successfully!");
            JOptionPane.showMessageDialog(null, "File send succesfully on Shared folder", "Operation done", 1);

    }

    
}

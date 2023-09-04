package luma;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadServer extends Thread {
    private Socket socket;
    private ArrayList<Socket> sockets;
    private Server server;

    public ThreadServer(Socket socket, ArrayList<Socket> sockets, Server server) throws IOException {
        this.socket = socket;
        this.sockets = sockets;
        this.server = server;
    }

    public ArrayList<Socket> getOnlineUsers() {
        return this.sockets;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        while (true) {
        String message = input.readLine();
        if (message != null) {
            message += "\n";
            System.out.println(message);
            if (message.startsWith("@")) {
                int spaceIndex = message.indexOf(' ');
                if (spaceIndex != -1) {
                    String targetUser = message.substring(1, spaceIndex); 
                    String privateMessage = message.substring(spaceIndex + 1); 
                    
                    //send everyone
                    if(targetUser.equals("everyone") && !privateMessage.isEmpty()){
                     for (Socket clientSocket : sockets) {
                    if (clientSocket != socket) {
                        server.writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
                        server.writer.write(privateMessage);
                        server.writer.newLine();
                        server.writer.flush();
                    }
                }   
                    }
                    if (!privateMessage.isEmpty()) {
                        server.sendPrivateMessage(targetUser, privateMessage);
                    }
                }
            } else {
                
            }
        }
    }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

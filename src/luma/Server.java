package luma;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author musoftware
 */
public class Server extends javax.swing.JFrame {

    /**
     * Creates new form Server
     */
    BufferedReader reader;
    InputStreamReader isr;
    OutputStreamWriter osw;
    BufferedWriter writer;
    public Server() throws IOException {
        initComponents();
        serverIp.setText(getServerIp());
        showAllIpAddress();
        startServer();
        
    }
    public static String getServerIp() throws IOException{
        String ip = "";
        try (Socket socket = new Socket()) {
    socket.connect(new InetSocketAddress("google.com", 80));
    return socket.getLocalAddress().getHostAddress();
}
    }
    private void showAllIpAddress(){
        final byte[] ip;
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
                        logs.append(output + " is on the network.\n");
                    } else {
                        //System.out.println("Not Reachable: "+output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();   
    }
    }
    ArrayList<Socket> clients = new ArrayList<>();
    static ArrayList <String> onlineUsers = new ArrayList<>();
    public static  ArrayList<String> getOnlineUsers(){
        return onlineUsers;
    }
    private void startServer(){
       /* ServerSocket server;
        Socket socket;
        Socket socket1;
        try{
            server = new ServerSocket(2051);
            socket = server.accept();
            logs.append(socket.toString() + "is connected");
            socket1 = server.accept();
            logs.append(socket1.toString());
        }
        catch(Exception e){
            
        }*/
        try (ServerSocket serversocket = new ServerSocket(2051)) {
            System.out.println("Server is started...");
            while (true) {
                System.out.println("waiting");
                Socket socket = serversocket.accept();
                
                isr = new InputStreamReader(socket.getInputStream(),"UTF-8");
                osw = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
                reader = new BufferedReader(isr);
                writer = new BufferedWriter(osw);
                //System.out.println("getting name");
                String name = reader.readLine();
               // System.out.println("scss");
                logs.append(socket.toString()+"  "+name + " connected \n");
                System.out.println(socket.toString()+"  "+name + " connected \n");
                clients.add(socket);
                onlineUsers.add(name);
                System.out.println("added");
                ThreadServer threadServer = new ThreadServer(socket, clients, this);
                threadServer.start();
                sendAll();
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        
    }
    public void sendAll() throws IOException {
       /* for (Socket socket : clients) {
            if (!socket.isConnected()) {
                System.out.println("is not connected");
            }

            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            writer.write("bu bir deneme" + socket.getLocalAddress() + "\n");
            writer.flush(); 
        }
        */
    }
    
    public void sendPrivateMessage(String targetUser, String message) throws IOException {
    for (Socket socket : clients) {
        if (!socket.isConnected()) {
            System.out.println("is not connected");
            continue;
        }
        
        String userName = onlineUsers.get(clients.indexOf(socket));
        if (userName.equals(targetUser)) {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            writer.write(message + "\n");
            writer.flush();
            return; 
        }
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        text = new javax.swing.JLabel();
        serverIp = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logs = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        text.setText("Server IP Address");

        logs.setEditable(false);
        logs.setColumns(20);
        logs.setRows(5);
        jScrollPane1.setViewportView(logs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(text)
                        .addGap(18, 18, 18)
                        .addComponent(serverIp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text)
                    .addComponent(serverIp))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Server().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logs;
    private javax.swing.JLabel serverIp;
    private javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package luma;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author musoftware
 */
public class MainScreen extends javax.swing.JFrame {
    //Userinf
    Profile profile;
    Socket socket;
    InputStreamReader isr;
    OutputStreamWriter osw;
    BufferedReader reader;
    BufferedWriter writer;
    Client client;
    private ArrayList <String> receivers;
    private String receiver ="everyone";
    public void setProfile(Profile profile,boolean isConnected){
        this.profile = profile;
        client = new Client(profile);
        if(isConnected){
        client.connectServer();
        reader = client.getReader();
        writer = client.getWriter();
        isr = client.getIsr();
        osw = client.getOsw();
        }
        
    }
    private void connectServer(){
     /*try{
         socket = new Socket("127.0.0.1",2051);
         isr = new InputStreamReader(socket.getInputStream());
        osw = new OutputStreamWriter(socket.getOutputStream());
        reader = new BufferedReader(isr);
        writer = new BufferedWriter(osw);
         System.out.println("1");
         if(profile != null){
             writer.write(profile.getProfile_name());
             writer.newLine();
             writer.flush();
         }
         else{
             writer.write("an user ");
             writer.newLine();
             writer.flush();
         }
        
         System.out.println("connect success");
     }   
     catch(Exception e){
         System.out.println("error");
     }
        */
    }

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
        page2.setVisible(false);
        page3.setVisible(false);
        page4.setVisible(false);
        page5.setVisible(false);
        //connectServer();
        changeRowColors();
    }
    private boolean inSettings = false;
    private boolean inNotes = false;
    private boolean inWhiteBoard = false;
    private boolean inChats = true;
    
    //Colors
    int selectedArea = 0;
    Color selectedColor = new Color(153,153,255);
    Color nonSelectedColor = new Color(204,204,255);
    Color defaultColor = new Color(208,208,208);
    
 
    private void changeSettingsIcon(){
        if(inSettings){
            iconSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/settings.gif")));
        }
        else{
            iconSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/settings.png")));
        }
    }
    private void changeRowColors() {
    switch (selectedArea) {
        case 1:
            area1.setBackground(selectedColor);
            area2.setBackground(nonSelectedColor);
            area3.setBackground(nonSelectedColor);
            area4.setBackground(nonSelectedColor);
            area5.setBackground(nonSelectedColor);
            
            
            break;
        case 2:
            area1.setBackground(nonSelectedColor);
            area2.setBackground(selectedColor);
            area3.setBackground(nonSelectedColor);
            area4.setBackground(nonSelectedColor);
            area5.setBackground(nonSelectedColor);
            
            
            break;
        case 3:
            area1.setBackground(nonSelectedColor);
            area2.setBackground(nonSelectedColor);
            area3.setBackground(selectedColor);
            area4.setBackground(nonSelectedColor);
            area5.setBackground(nonSelectedColor);
            
           
            break;
        case 4:
            area1.setBackground(nonSelectedColor);
            area2.setBackground(nonSelectedColor);
            area3.setBackground(nonSelectedColor);
            area4.setBackground(selectedColor);
            area5.setBackground(nonSelectedColor);
            
           
            break;
        case 5:
            area1.setBackground(nonSelectedColor);
            area2.setBackground(nonSelectedColor);
            area3.setBackground(nonSelectedColor);
            area4.setBackground(nonSelectedColor);
            area5.setBackground(selectedColor);
            
            
            break;
        default:
            area1.setBackground(nonSelectedColor);
            area2.setBackground(nonSelectedColor);
            area3.setBackground(nonSelectedColor);
            area4.setBackground(nonSelectedColor);
            area5.setBackground(nonSelectedColor);
            break;
    }
}
    private void getNotes() throws SQLException{
        ArrayList <Note> notes = DatabaseOperations.getNotes(profile.getUsername());
        int size = notes.size();
        //System.out.println(Math.floorDiv(size, 5)+1);
        switch(Math.floorDiv(size, 5)+1){
            case 1:
                page1.setVisible(true);
                page2.setVisible(false);
                page3.setVisible(false);
                page4.setVisible(false);
                page5.setVisible(false);
                break;
            case 2:
                page2.setVisible(true);
                page3.setVisible(false);
                page4.setVisible(false);
                page5.setVisible(false);
                break;
            case 3:
                page2.setVisible(true);
                page3.setVisible(true);
                page4.setVisible(false);
                page5.setVisible(false);
                break;
            case 4:
                page2.setVisible(true);
                page3.setVisible(true);
                page4.setVisible(true);
                page5.setVisible(false);
                break;
            case 5:
                page2.setVisible(true);
                page3.setVisible(true);
                page4.setVisible(true);
                page5.setVisible(true);
                break;
                
        }
        for(int i = 0 ;i< size ; i++){
            if(i+((currentPage-1)*5) < size){
                
            
            switch(i){
                case 0:
                     titleA1.setText(notes.get(i+((currentPage-1)*5)).getTitle());
                     messageA1.setText(notes.get(i+((currentPage-1)*5)).getNote());
                     break;
                case 1:
                    titleA2.setText(notes.get(i+((currentPage-1)*5)).getTitle());
                    messageA2.setText(notes.get(i+((currentPage-1)*5)).getNote());
                    break;
                case 2:
                    titleA3.setText(notes.get(i+((currentPage-1)*5)).getTitle());
                    messageA3.setText(notes.get(i+((currentPage-1)*5)).getNote());
                    break;
                case 3:
                    titleA4.setText(notes.get(i+((currentPage-1)*5)).getTitle());
                    messageA4.setText(notes.get(i+((currentPage-1)*5)).getNote());
                    break;  
                case 4:
                    
                    titleA5.setText(notes.get(i+((currentPage-1)*5)).getTitle());
                    messageA5.setText(notes.get(i+((currentPage-1)*5)).getNote());
                    break;
                default :
                    break;
            }
            }
            else{
                switch (i) {
            case 0:
                titleA1.setText("");
                messageA1.setText("");
                title.setText("Write your title here");
                titleArea.setText("Write your title here");
                textArea.setText("");
                saveInfo.setText("");
                break;
            case 1:
                titleA2.setText("");
                messageA2.setText("");
                title.setText("Write your title here");
                titleArea.setText("Write your title here");
                textArea.setText("");
                saveInfo.setText("");
                break;
            case 2:
                titleA3.setText("");
                messageA3.setText("");
                 title.setText("Write your title here");
                titleArea.setText("Write your title here");
                 textArea.setText("");
                 saveInfo.setText("");
                break;
            case 3:
                titleA4.setText("");
                messageA4.setText("");
                 title.setText("Write your title here");
                titleArea.setText("Write your title here");
                 textArea.setText("");
                 saveInfo.setText("");
                break;
            case 4:
                titleA5.setText("");
                messageA5.setText("");
                 title.setText("Write your title here");
                titleArea.setText("Write your title here");
                 textArea.setText("");
                 saveInfo.setText("");
                break;
            default:
                break;
        }
            }
        }
         switch (selectedArea) {
        case 1:
            title.setText(notes.get(0+((currentPage-1)*5)).getTitle());
            titleArea.setText(notes.get(0+((currentPage-1)*5)).getTitle());
            textArea.setText(notes.get(0+((currentPage-1)*5)).getNote());
            saveInfo.setText("Last updated on: " + notes.get(0+((currentPage-1)*5)).getUpdateDate());
            break;
        case 2:
            title.setText(notes.get(1+((currentPage-1)*5)).getTitle());
            titleArea.setText(notes.get(1+((currentPage-1)*5)).getTitle());
            textArea.setText(notes.get(1+((currentPage-1)*5)).getNote());
            saveInfo.setText("Last updated on: " + notes.get(1+((currentPage-1)*5)).getUpdateDate());
            break;
        case 3:
            title.setText(notes.get(2+((currentPage-1)*5)).getTitle());
            titleArea.setText(notes.get(2+((currentPage-1)*5)).getTitle());
            textArea.setText(notes.get(2+((currentPage-1)*5)).getNote());
            saveInfo.setText("Last updated on: " + notes.get(2+((currentPage-1)*5)).getUpdateDate());
            break;
        case 4:
            title.setText(notes.get(3+((currentPage-1)*5)).getTitle());
            titleArea.setText(notes.get(3+((currentPage-1)*5)).getTitle());
            textArea.setText(notes.get(3+((currentPage-1)*5)).getNote());
            saveInfo.setText("Last updated on: " + notes.get(3).getUpdateDate());
            break;
        case 5:
            title.setText(notes.get(4+((currentPage-1)*5)).getTitle());
            titleArea.setText(notes.get(4+((currentPage-1)*5)).getTitle());
            textArea.setText(notes.get(4+((currentPage-1)*5)).getNote());
            saveInfo.setText("Last updated on: " + notes.get(4+((currentPage-1)*5)).getUpdateDate());
            break;
        default:
           
            break;
    }
    }
    
    private void getMessages() throws SQLException{
        clearAreas();
        receivers = DatabaseOperations.getReceivers(profile.getUsername());
        
        int size = receivers.size();
       // System.out.println(Math.floorDiv(size, 5)+1);
        switch(Math.floorDiv(size, 5)+1){
            case 1:
                page1.setVisible(true);
                page2.setVisible(false);
                page3.setVisible(false);
                page4.setVisible(false);
                page5.setVisible(false);
                break;
            case 2:
                page2.setVisible(true);
                page3.setVisible(false);
                page4.setVisible(false);
                page5.setVisible(false);
                break;
            case 3:
                page2.setVisible(true);
                page3.setVisible(true);
                page4.setVisible(false);
                page5 .setVisible(false);
                break;
            case 4:
                page2.setVisible(true);
                page3.setVisible(true);
                page4.setVisible(true);
                page5.setVisible(false);
                break;
            case 5:
                page2.setVisible(true);
                page3.setVisible(true);
                page4.setVisible(true);
                page5.setVisible(true);
                break;
                
        }
        for(int i = 0 ;i< size ; i++){
            if(i+((currentPage-1)*5) < size){
            switch(i){
                case 0:
                     titleA1.setText(receivers.get(i+((currentPage-1)*5)));
                     if(!titleA1.getText().equals("everyone")){
                     messageA1.setText(DatabaseOperations.getProfileByUsername(receivers.get(i+((currentPage-1)*5))).getProfile_name());
                     }
                     break;
                case 1:
                    titleA2.setText(receivers.get(i+((currentPage-1)*5)));
                    messageA2.setText(DatabaseOperations.getProfileByUsername(receivers.get(i+((currentPage-1)*5))).getProfile_name());
                    break;
                case 2:
                    titleA3.setText(receivers.get(i+((currentPage-1)*5)));
                    messageA3.setText(DatabaseOperations.getProfileByUsername(receivers.get(i+((currentPage-1)*5))).getProfile_name());
                    break;
                case 3:
                    titleA4.setText(receivers.get(i+((currentPage-1)*5)));
                    messageA4.setText(DatabaseOperations.getProfileByUsername(receivers.get(i+((currentPage-1)*5))).getProfile_name());
                    break;  
                case 4:
                    
                    titleA5.setText(receivers.get(i+((currentPage-1)*5)));
                    messageA5.setText(DatabaseOperations.getProfileByUsername(receivers.get(i+((currentPage-1)*5))).getProfile_name());
                   // messageA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                    break;
                default :
                    break;
            }
            }
        }
        fillEmptyAreas();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        leftContainers = new javax.swing.JPanel();
        area1 = new javax.swing.JPanel();
        titleA1 = new javax.swing.JLabel();
        messageA1 = new javax.swing.JLabel();
        area2 = new javax.swing.JPanel();
        titleA2 = new javax.swing.JLabel();
        messageA2 = new javax.swing.JLabel();
        area3 = new javax.swing.JPanel();
        messageA3 = new javax.swing.JLabel();
        titleA3 = new javax.swing.JLabel();
        area4 = new javax.swing.JPanel();
        titleA4 = new javax.swing.JLabel();
        messageA4 = new javax.swing.JLabel();
        area5 = new javax.swing.JPanel();
        messageA5 = new javax.swing.JLabel();
        titleA5 = new javax.swing.JLabel();
        page1 = new javax.swing.JLabel();
        page2 = new javax.swing.JLabel();
        page3 = new javax.swing.JLabel();
        page4 = new javax.swing.JLabel();
        page5 = new javax.swing.JLabel();
        add = new javax.swing.JLabel();
        bottom = new javax.swing.JPanel();
        chatsContainers = new javax.swing.JPanel();
        iconChats = new javax.swing.JLabel();
        whiteBoardsContainers = new javax.swing.JPanel();
        iconwhiteBoards = new javax.swing.JLabel();
        notesContainers = new javax.swing.JPanel();
        iconNotes = new javax.swing.JLabel();
        settingsContainer = new javax.swing.JPanel();
        iconSettings = new javax.swing.JLabel();
        dataArea = new javax.swing.JPanel();
        messageArea = new javax.swing.JTextField();
        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        titleArea = new javax.swing.JTextField();
        button = new javax.swing.JLabel();
        saveInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 650));
        setSize(new java.awt.Dimension(600, 650));

        mainContainer.setBackground(new java.awt.Color(255, 255, 255));
        mainContainer.setPreferredSize(new java.awt.Dimension(600, 650));

        leftContainers.setBackground(new java.awt.Color(255, 255, 255));
        leftContainers.setPreferredSize(new java.awt.Dimension(175, 550));

        area1.setBackground(new java.awt.Color(153, 153, 255));
        area1.setPreferredSize(new java.awt.Dimension(175, 100));
        area1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                area1MouseClicked(evt);
            }
        });

        titleA1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N

        javax.swing.GroupLayout area1Layout = new javax.swing.GroupLayout(area1);
        area1.setLayout(area1Layout);
        area1Layout.setHorizontalGroup(
            area1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(area1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageA1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleA1))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        area1Layout.setVerticalGroup(
            area1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleA1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageA1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        area2.setBackground(new java.awt.Color(204, 204, 255));
        area2.setPreferredSize(new java.awt.Dimension(175, 100));
        area2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                area2MouseClicked(evt);
            }
        });

        titleA2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N

        javax.swing.GroupLayout area2Layout = new javax.swing.GroupLayout(area2);
        area2.setLayout(area2Layout);
        area2Layout.setHorizontalGroup(
            area2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(area2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageA2)
                    .addComponent(titleA2))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        area2Layout.setVerticalGroup(
            area2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleA2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageA2)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        area3.setBackground(new java.awt.Color(204, 204, 255));
        area3.setPreferredSize(new java.awt.Dimension(175, 100));
        area3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                area3MouseClicked(evt);
            }
        });

        titleA3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N

        javax.swing.GroupLayout area3Layout = new javax.swing.GroupLayout(area3);
        area3.setLayout(area3Layout);
        area3Layout.setHorizontalGroup(
            area3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(area3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageA3)
                    .addComponent(titleA3))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        area3Layout.setVerticalGroup(
            area3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleA3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageA3)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        area4.setBackground(new java.awt.Color(204, 204, 255));
        area4.setPreferredSize(new java.awt.Dimension(175, 100));
        area4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                area4MouseClicked(evt);
            }
        });

        titleA4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N

        javax.swing.GroupLayout area4Layout = new javax.swing.GroupLayout(area4);
        area4.setLayout(area4Layout);
        area4Layout.setHorizontalGroup(
            area4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(area4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageA4)
                    .addComponent(titleA4))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        area4Layout.setVerticalGroup(
            area4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleA4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageA4)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        area5.setBackground(new java.awt.Color(204, 204, 255));
        area5.setPreferredSize(new java.awt.Dimension(175, 90));
        area5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                area5MouseClicked(evt);
            }
        });

        messageA5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageA5MouseClicked(evt);
            }
        });

        titleA5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N

        javax.swing.GroupLayout area5Layout = new javax.swing.GroupLayout(area5);
        area5.setLayout(area5Layout);
        area5Layout.setHorizontalGroup(
            area5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(area5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageA5)
                    .addComponent(titleA5))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        area5Layout.setVerticalGroup(
            area5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleA5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageA5)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        page1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/filled1.png"))); // NOI18N
        page1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                page1MouseClicked(evt);
            }
        });

        page2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty2.png"))); // NOI18N
        page2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                page2MouseClicked(evt);
            }
        });

        page3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty3.png"))); // NOI18N
        page3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                page3MouseClicked(evt);
            }
        });

        page4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty4.png"))); // NOI18N
        page4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                page4MouseClicked(evt);
            }
        });

        page5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty5.png"))); // NOI18N
        page5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                page5MouseClicked(evt);
            }
        });

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/add.png"))); // NOI18N
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout leftContainersLayout = new javax.swing.GroupLayout(leftContainers);
        leftContainers.setLayout(leftContainersLayout);
        leftContainersLayout.setHorizontalGroup(
            leftContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftContainersLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(leftContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(leftContainersLayout.createSequentialGroup()
                        .addComponent(page1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(page2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(page3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(page4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(page5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add)
                        .addGap(8, 8, 8))
                    .addComponent(area1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(area2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(area3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(area4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(area5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        leftContainersLayout.setVerticalGroup(
            leftContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftContainersLayout.createSequentialGroup()
                .addComponent(area1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(area2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(area3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(area4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(area5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(leftContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(leftContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(page3)
                            .addComponent(page2))
                        .addComponent(page4, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(page5)
                    .addComponent(page1)
                    .addComponent(add))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bottom.setPreferredSize(new java.awt.Dimension(600, 80));

        chatsContainers.setBackground(new java.awt.Color(255, 255, 255));
        chatsContainers.setPreferredSize(new java.awt.Dimension(140, 80));
        chatsContainers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatsContainersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chatsContainersMouseEntered(evt);
            }
        });

        iconChats.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/logo.png"))); // NOI18N
        iconChats.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconChatsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout chatsContainersLayout = new javax.swing.GroupLayout(chatsContainers);
        chatsContainers.setLayout(chatsContainersLayout);
        chatsContainersLayout.setHorizontalGroup(
            chatsContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatsContainersLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(iconChats)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        chatsContainersLayout.setVerticalGroup(
            chatsContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatsContainersLayout.createSequentialGroup()
                .addComponent(iconChats)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        whiteBoardsContainers.setBackground(new java.awt.Color(255, 255, 255));
        whiteBoardsContainers.setPreferredSize(new java.awt.Dimension(140, 80));
        whiteBoardsContainers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                whiteBoardsContainersMouseClicked(evt);
            }
        });

        iconwhiteBoards.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/ftp.png"))); // NOI18N
        iconwhiteBoards.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconwhiteBoardsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout whiteBoardsContainersLayout = new javax.swing.GroupLayout(whiteBoardsContainers);
        whiteBoardsContainers.setLayout(whiteBoardsContainersLayout);
        whiteBoardsContainersLayout.setHorizontalGroup(
            whiteBoardsContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, whiteBoardsContainersLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(iconwhiteBoards)
                .addGap(44, 44, 44))
        );
        whiteBoardsContainersLayout.setVerticalGroup(
            whiteBoardsContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(whiteBoardsContainersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconwhiteBoards)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        notesContainers.setBackground(new java.awt.Color(255, 255, 255));
        notesContainers.setPreferredSize(new java.awt.Dimension(140, 80));
        notesContainers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notesContainersMouseClicked(evt);
            }
        });

        iconNotes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/notes.png"))); // NOI18N
        iconNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconNotesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout notesContainersLayout = new javax.swing.GroupLayout(notesContainers);
        notesContainers.setLayout(notesContainersLayout);
        notesContainersLayout.setHorizontalGroup(
            notesContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, notesContainersLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(iconNotes)
                .addGap(39, 39, 39))
        );
        notesContainersLayout.setVerticalGroup(
            notesContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesContainersLayout.createSequentialGroup()
                .addComponent(iconNotes)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        settingsContainer.setBackground(new java.awt.Color(255, 255, 255));
        settingsContainer.setPreferredSize(new java.awt.Dimension(140, 80));
        settingsContainer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsContainerMouseClicked(evt);
            }
        });

        iconSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/settings.png"))); // NOI18N
        iconSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconSettingsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout settingsContainerLayout = new javax.swing.GroupLayout(settingsContainer);
        settingsContainer.setLayout(settingsContainerLayout);
        settingsContainerLayout.setHorizontalGroup(
            settingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsContainerLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(iconSettings)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        settingsContainerLayout.setVerticalGroup(
            settingsContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconSettings)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addComponent(chatsContainers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(whiteBoardsContainers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notesContainers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addGroup(bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(whiteBoardsContainers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(notesContainers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settingsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chatsContainers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        dataArea.setBackground(new java.awt.Color(255, 255, 255));
        dataArea.setPreferredSize(new java.awt.Dimension(425, 550));

        messageArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageAreaKeyPressed(evt);
            }
        });

        title.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        title.setText("TITLE");

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textAreaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(textArea);

        titleArea.setText("Write your title here");
        titleArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                titleAreaKeyPressed(evt);
            }
        });

        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/save.png"))); // NOI18N
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMouseClicked(evt);
            }
        });

        saveInfo.setFont(new java.awt.Font("Copperplate", 0, 9)); // NOI18N

        javax.swing.GroupLayout dataAreaLayout = new javax.swing.GroupLayout(dataArea);
        dataArea.setLayout(dataAreaLayout);
        dataAreaLayout.setHorizontalGroup(
            dataAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataAreaLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titleArea, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dataAreaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataAreaLayout.createSequentialGroup()
                        .addComponent(messageArea, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataAreaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        dataAreaLayout.setVerticalGroup(
            dataAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(titleArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(dataAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(messageArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainContainerLayout = new javax.swing.GroupLayout(mainContainer);
        mainContainer.setLayout(mainContainerLayout);
        mainContainerLayout.setHorizontalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainContainerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainContainerLayout.createSequentialGroup()
                        .addComponent(leftContainers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainContainerLayout.setVerticalGroup(
            mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainContainerLayout.createSequentialGroup()
                .addGroup(mainContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftContainers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iconSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconSettingsMouseClicked
        // TODO add your handling code here:
        inNotes = false;
        inWhiteBoard = false;
        inSettings = true;
        inChats = false;
        changeSettingsIcon();
        startsSettings();
    }//GEN-LAST:event_iconSettingsMouseClicked
     void startsChats(){
        clearAreas();
        selectedArea = -1;
        button.setVisible(true);
        changeRowColors();
        currentPage = 1;
        page1.setVisible(true);
        setPageIcons();
        inChats = true;
        inNotes = false;
        inWhiteBoard = false;
        inSettings = false;
       messageArea.setVisible(true);
       add.setVisible(false);
       textArea.setEditable(false);
       saveInfo.setVisible(false);
       clearAreas();
        changeSettingsIcon();
        setButtonIcon();
        titleArea.setVisible(false);
        try {
            getMessages();
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
             Runnable receiveMessage = new Runnable(){
              @Override
              public void run(){
                  while(true){
                      try {
                          textArea.append(client.getMessage());
                      } catch (IOException ex) {
                          Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
              }
          };
          Thread thread = new Thread(receiveMessage);
          thread.start();
    }
    private void chatsContainersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatsContainersMouseClicked
        startsChats();
    }//GEN-LAST:event_chatsContainersMouseClicked

    private void whiteBoardsContainersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_whiteBoardsContainersMouseClicked
        // TODO add your handling code here:
        clearAreas();
         inWhiteBoard = true;
         inChats = false;
         inNotes = false;
         inSettings = false;
       
        changeSettingsIcon();
        setButtonIcon();
        try{
        /*    
        oFileSharingScreen fss = new oFileSharingScreen();
        fss.setHostAddress(client.getHostAddress());
        fss.setPublisher(profile.getProfile_name());
        fss.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        fss.setVisible(true);
            */
        basicFileShareScreen ftp = new basicFileShareScreen();
        ftp.isDark = isDark;
        ftp.setApperance();
        ftp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ftp.setVisible(true);
        
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "You do not have any internet connection", "Connection Error", HEIGHT);
        }
        
    }//GEN-LAST:event_whiteBoardsContainersMouseClicked
    private void startsNotes(){
        selectedArea = -1;
        changeRowColors();
        clearAreas();
        button.setVisible(true);
        currentPage = 1;
        page1.setVisible(true);
        setPageIcons();
        textArea.setEditable(true);
        inNotes = true;
        inChats = false;
        inWhiteBoard = false;
        inSettings = false;
        title.setVisible(true);
        titleArea.setVisible(true);
        titleArea.setText("Write your title here");
        saveInfo.setVisible(true);
        try {
            getNotes();
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        changeSettingsIcon();
        setButtonIcon();
        
    }
    private void notesContainersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notesContainersMouseClicked
        // TODO add your handling code here:
        startsNotes();
    }//GEN-LAST:event_notesContainersMouseClicked
    
    private String addReceiver(){
        String[] options = {"Users not found"};
ArrayList<String> allUsers;
ArrayList<String> receivers;
ArrayList<String> optionsList = new ArrayList<>();

try {
    allUsers = DatabaseOperations.getAllUsernames();
    receivers = DatabaseOperations.getReceivers(profile.getUsername());
   Set<String> receiversSet = new HashSet<>(receivers);
    for (String user : allUsers) {
        if (!receiversSet.contains(user)) {
            optionsList.add(user);
        }
    }
    
    options = optionsList.toArray(String[]::new);
} catch (SQLException ex) {
    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
}

String input = (String) JOptionPane.showInputDialog(
    null,
    "Select a User",
    "Send new user a message",
    JOptionPane.QUESTION_MESSAGE,
    null,
    options,
    options.length > 0 ? options[0] : null
);

return input;

    }
    private void area1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_area1MouseClicked
        // TODO add your handling code here:
        selectedArea = 1;
        changeRowColors();
        if(inNotes){
            try {
                getNotes();
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
       else if(inChats){
            if(titleA1.getText().equals("")){
                String str = addReceiver();
                titleA1.setText(str);
                try {
                    messageA1.setText(DatabaseOperations.getProfileByUsername(str).getProfile_name());
                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            receiver = titleA1.getText();
            messageA1.setIcon(null);
            try {
                textArea.setText(DatabaseOperations.getUserMessages(profile.getUsername(), receiver));
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       else if(inSettings){
           String [] options = {"Yes","Cancel"};
          int selection = JOptionPane.showOptionDialog(rootPane, "Are you sure delete all your messages", "Delete All Messages", 0, 1, null, options, null);
            // 0 yes 1 cancel
            if(selection==0){
                JPasswordField pwd = new JPasswordField(16);
               int action = JOptionPane.showConfirmDialog(null, pwd,"Enter your Password",JOptionPane.OK_CANCEL_OPTION);
               if(action == 0){
                  String userPass = "";
                    try {
                        userPass = DatabaseOperations.getUserPass(this.profile.getUsername());
                    } catch (SQLException ex) {
                        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  String enteredPass = new String(pwd.getPassword());
                  if(enteredPass.equals(userPass)){
                      try {
                          DatabaseOperations.deleteAllMessages(this.profile.getUsername());
                          JOptionPane.showMessageDialog(rootPane, "All messages have been successfully deleted", "Operation Successfull", 1);
                      } catch (SQLException ex) {
                          Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                          JOptionPane.showMessageDialog(rootPane, "All messages could not be deleted", "Operation failed", 2);
                      }
                  }
               }
            }
       }
    }//GEN-LAST:event_area1MouseClicked

    private void area2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_area2MouseClicked
        // TODO add your handling code here:
        selectedArea = 2;
        changeRowColors();
        if(inNotes){
            try {
                getNotes();
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        if(inChats){
            if(titleA2.getText().equals("")){
                String str = addReceiver();
                titleA2.setText(str);
                try {
                    messageA2.setText(DatabaseOperations.getProfileByUsername(str).getProfile_name());
                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            receiver = titleA2.getText();
            messageA2.setIcon(null);
            try {
                textArea.setText(DatabaseOperations.getUserMessages(profile.getUsername(), receiver));
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(inSettings){
           String [] options = {"Yes","Cancel"};
          int selection = JOptionPane.showOptionDialog(rootPane, "Are you sure delete all your notes", "Delete All Notes", 0, 1, null, options, null);
            // 0 yes 1 cancel
            if(selection==0){
                JPasswordField pwd = new JPasswordField(16);
               int action = JOptionPane.showConfirmDialog(null, pwd,"Enter your Password",JOptionPane.OK_CANCEL_OPTION);
               if(action == 0){
                  String userPass = "";
                    try {
                        userPass = DatabaseOperations.getUserPass(this.profile.getUsername());
                    } catch (SQLException ex) {
                        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  String enteredPass = new String(pwd.getPassword());
                  if(enteredPass.equals(userPass)){
                      try {
                          DatabaseOperations.deleteAllNotes(this.profile.getUsername());
                          JOptionPane.showMessageDialog(rootPane, "All notes have been successfully deleted", "Operation Successfull", 1);
                      } catch (SQLException ex) {
                          Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                          JOptionPane.showMessageDialog(rootPane, "All notes could not be deleted", "Operation failed", 2);
                      }
                  }
               }
            }
       }
    }//GEN-LAST:event_area2MouseClicked

    private void area3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_area3MouseClicked
        // TODO add your handling code here:
        selectedArea = 3;
        changeRowColors();
        if(inNotes){
            try {
                getNotes();
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        if(inChats){
           if(titleA3.getText().equals("")){
                String str = addReceiver();
                titleA3.setText(str);
                try {
                    messageA3.setText(DatabaseOperations.getProfileByUsername(str).getProfile_name());
                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            receiver = titleA3.getText();
            messageA3.setIcon(null);
            try {
                textArea.setText(DatabaseOperations.getUserMessages(profile.getUsername(), receiver));
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_area3MouseClicked

    private void area4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_area4MouseClicked
        // TODO add your handling code here:
        selectedArea = 4;
        changeRowColors();
        if(inNotes){
            try {
                getNotes();
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        else if(inChats){
            if(titleA4.getText().equals("")){
                String str = addReceiver();
                titleA4.setText(str);
                try {
                    messageA4.setText(DatabaseOperations.getProfileByUsername(str).getProfile_name());
                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            receiver = titleA4.getText();
            messageA4.setIcon(null);
            try {
                textArea.setText(DatabaseOperations.getUserMessages(profile.getUsername(), receiver));
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(inSettings){
           ChangePassScreen passScreen = new ChangePassScreen();
           passScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            try {
                passScreen.setProfile(this.profile);
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
           passScreen.isDark = this.isDark;
           passScreen.setApperance();
           passScreen.setVisible(true);
        }
    }//GEN-LAST:event_area4MouseClicked

    private void area5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_area5MouseClicked
        // TODO add your handling code here:
        selectedArea = 5;
        changeRowColors();
        if(inNotes){
            try {
                getNotes();
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        else if(inChats){
            if(titleA5.getText().equals("")){
                String str = addReceiver();
                titleA5.setText(str);
                try {
                    messageA5.setText(DatabaseOperations.getProfileByUsername(str).getProfile_name());
                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            receiver = titleA5.getText();
            messageA5.setIcon(null);
            try {
                textArea.setText(DatabaseOperations.getUserMessages(profile.getUsername(), receiver));
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(inSettings){
            changeApperance();
        }
    }//GEN-LAST:event_area5MouseClicked

    private void iconNotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconNotesMouseClicked
        // TODO add your handling code here:
      startsNotes();
    }//GEN-LAST:event_iconNotesMouseClicked

    private void titleAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleAreaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() > -1){
            String str = titleArea.getText()+evt.getKeyChar();
            title.setText(str);
            switch(selectedArea){
                case 1:
                    titleA1.setText(str);
                    break;
                case 2:
                    titleA2.setText(str);
                    break;
                case 3:
                    titleA3.setText(str);
                    break;
                case 4:
                    titleA4.setText(str);
                    break;
                case 5:
                    titleA5.setText(str);
                    break;
                default :
                    break;
            }
        }
    }//GEN-LAST:event_titleAreaKeyPressed
    private void clearAreas(){
        titleA1.setText("");
        messageA1.setText("");
        messageA1.setIcon(null);
        titleA2.setText("");
        messageA2.setText("");
        messageA2.setIcon(null);
        titleA3.setText("");
        messageA3.setText("");
        messageA3.setIcon(null);
        titleA4.setText("");
        messageA4.setText("");
        messageA4.setIcon(null);
        titleA5.setText("");
        messageA5.setText("");
        messageA5.setIcon(null);
        messageA5.repaint();
        title.setText("");
        textArea.setText("");
        titleArea.setText("");
    }
    private void iconChatsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconChatsMouseClicked
      startsChats();
    }//GEN-LAST:event_iconChatsMouseClicked
    public void setButtonIcon(){
        if(inChats){
            button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/send.png")));
            messageArea.setVisible(true);
        }
        else if(inNotes){
            button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/save.png")));
            messageArea.setVisible(false);
        }
    }
    private void buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMouseClicked
        // TODO add your handling code here:
        if(inChats){
            String message = messageArea.getText();
            try {
                textArea.append(message+"\n\n");
                writer.write("@"+receiver+" "+profile.getProfile_name()+": "+message);
                writer.newLine();
                writer.flush();
                
                
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            finally{
               
        
        try {
            DatabaseOperations.newMessage(message, this.profile.getUsername(), receiver);
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             messageArea.setText("");
        }
            }    
            
        }
        else if(inWhiteBoard){
            System.out.println("in white");   
        }
        else if(inNotes){
            System.out.println("in notes");
            String dbtitle = title.getText();
            String dbNote = textArea.getText();
           String username = profile.getUsername();
           int profile_id = profile.getProfile_id();
            try {
                int noteid = selectedArea+(currentPage-1)*5;
                System.out.println(noteid);
                DatabaseOperations.updateNotes(noteid,profile_id, username, dbtitle, dbNote);
                //DatabaseOperations.createNoteTable(profile.getUsername());
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
           
          
           
        }
        else if(inSettings){
            System.out.println("in settings");
        }
        
       
        
    }//GEN-LAST:event_buttonMouseClicked

    private void textAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textAreaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() > -1){
            String str = textArea.getText()+evt.getKeyChar();
            switch(selectedArea){
                case 1:
                    messageA1.setText(str);
                    break;
                case 2:
                    messageA2.setText(str);
                    break;
                case 3:
                    messageA3.setText(str);
                    break;
                case 4:
                    messageA4.setText(str);
                    break;
                case 5:
                    messageA5.setText(str);
                    break;
                default :
                    break;
            }
        }
    }//GEN-LAST:event_textAreaKeyPressed
    private void startsSettings(){
        messageArea.setVisible(false);
        title.setVisible(false);
        titleArea.setVisible(false);
        textArea.setEditable(false);
        button.setVisible(false);
        page1.setVisible(false);
        page2.setVisible(false);
        page3.setVisible(false);
        page4.setVisible(false);
        page5.setVisible(false);
        
        clearAreas();
        textArea.setText("Your IP Address \t\t");
        try {
            textArea.append(Server.getServerIp());
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            textArea.append("You do not have the internet connection");
        }
        textArea.append("\nThis application works via port 2051");
        textArea.append("\n\n\nThe application was developed by themusoftware");
        textArea.append("\n\nApplication version : v0.1");
        
        // Options
        titleA1.setText("Delete All Chats");
        titleA2.setText("Delete All Notes");
        titleA3.setText("Share Notes");
        titleA4.setText("Change Password");
        titleA5.setText("Appearance");
        messageA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/light.png")));
        
    }
    boolean isDark = false;
    private void changeApperance(){
        if(isDark){
            selectedColor = new Color(153,153,255);
            nonSelectedColor = new Color(204,204,255);
            defaultColor = new Color(208,208,208);
            mainContainer.setBackground(Color.WHITE);
            settingsContainer.setBackground(Color.WHITE);
            notesContainers.setBackground(Color.WHITE);
            whiteBoardsContainers.setBackground(Color.WHITE);
            chatsContainers.setBackground(Color.WHITE);
            dataArea.setBackground(Color.WHITE);
            leftContainers.setBackground(Color.WHITE);
            bottom.setBackground(Color.GRAY);
            textArea.setBackground(Color.WHITE);
            messageArea.setBackground(Color.WHITE);
            titleArea.setBackground(Color.WHITE);
            isDark = false;
             messageA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/light.png")));
             changeRowColors();

        }
        else{
            mainContainer.setBackground(Color.GRAY);
            settingsContainer.setBackground(Color.GRAY);
            notesContainers.setBackground(Color.GRAY);
            whiteBoardsContainers.setBackground(Color.GRAY);
            chatsContainers.setBackground(Color.GRAY);
            dataArea.setBackground(Color.GRAY);
            leftContainers.setBackground(Color.GRAY);
            bottom.setBackground(Color.DARK_GRAY);
            textArea.setBackground(Color.LIGHT_GRAY);
            messageArea.setBackground(Color.LIGHT_GRAY);
            titleArea.setBackground(Color.LIGHT_GRAY);
            selectedColor = new Color(102,102,187);
            nonSelectedColor = new Color(170,170,221);
            isDark = true;
            messageA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/dark.png")));
            changeRowColors();
        }
    }
    private void settingsContainerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsContainerMouseClicked
        // TODO add your handling code here:
        inNotes = false;
        inWhiteBoard = false;
        inSettings = true;
        inChats = false;
        changeSettingsIcon();
        startsSettings();
    }//GEN-LAST:event_settingsContainerMouseClicked
    
    private void fillEmptyAreas(){
        if(titleA1.getText().equals("")){
                 messageA1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
               }
               else if(titleA2.getText().equals("")){
                 messageA2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
               }
               else if(titleA3.getText().equals("")){
                 messageA3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
               }
               else if(titleA4.getText().equals("")){
                 messageA4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
                 messageA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
               }
               else if(titleA5.getText().equals("")){
                    messageA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/adduser.png")));
               }
    }
    private void setPageIcons(){
        switch(currentPage){
            case 1:
                page1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/filled1.png")));
                page2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty2.png")));
                page3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty3.png")));
                page4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty4.png")));
                page5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty5.png")));
                break;
            case 2:
                page1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty1.png")));
                page2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/filled2.png")));
                page3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty3.png")));
                page4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty4.png")));
                page5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty5.png")));
                break;
            case 3:
                page1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty1.png")));
                page2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty2.png")));
                page3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/filled3.png")));
                page4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty4.png")));
                page5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty5.png")));
                break;
            case 4:
                page1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty1.png")));
                page2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty2.png")));
                page3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty3.png")));
                page4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/filled4.png")));
                page5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty5.png")));
                break;
            case 5:
                page1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty1.png")));
                page2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty2.png")));
                page3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty3.png")));
                page4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty4.png")));
                page5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/filled5.png")));
                break;
            default:
                page1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty1.png")));
                page2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty2.png")));
                page3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty3.png")));
                page4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty4.png")));
                page5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/empty5.png")));
                break;
                
        }
    }
    
    private void page1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_page1MouseClicked
        // TODO add your handling code here:
        
        currentPage = 1;
        setPageIcons();
        clearAreas();
        if(inNotes){
        try {
            getNotes();
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        else if(inChats){
            try {
                getMessages();
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
       
    }//GEN-LAST:event_page1MouseClicked

    private void page2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_page2MouseClicked
        // TODO add your handling code here:
        
        currentPage = 2;
        setPageIcons();
        selectedArea = -1;
        changeRowColors();
        clearAreas();
       if(inNotes){
        try {
            getNotes();
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        else if(inChats){
            try {
               getMessages();
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_page2MouseClicked

    private void page3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_page3MouseClicked
        // TODO add your handling code here:
        currentPage = 3;
        setPageIcons();
        selectedArea = -1;
        changeRowColors();
        clearAreas();
        if(inNotes){
        try {
            getNotes();
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        else if(inChats){
            try {
               getMessages();
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_page3MouseClicked

    private void page4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_page4MouseClicked
        // TODO add your handling code here:
        currentPage = 4;
        setPageIcons();
        selectedArea = -1;
        changeRowColors();
        clearAreas();
        if(inNotes){
        try {
            getNotes();
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        else if(inChats){
            try {
                getMessages();                
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }//GEN-LAST:event_page4MouseClicked

    private void page5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_page5MouseClicked
        // TODO add your handling code here:
        
        currentPage = 5;
        setPageIcons();
        selectedArea = -1;
        changeRowColors();
        clearAreas();
        if(inNotes){
        try {
            getNotes();
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        else if(inChats){
            try {
           getMessages();
            } catch (SQLException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_page5MouseClicked
    static int currentPage = 1;
    static int totalPage = 1;
    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        // TODO add your handling code here:
        if(totalPage == 1){
            page2.setVisible(true);
            totalPage++;
        }
        else if(totalPage == 2){
            page3.setVisible(true);
            totalPage++;
        }
        else if(totalPage == 3){
            page4.setVisible(true);
            totalPage++;
        }
        else if(totalPage == 4){
            page5.setVisible(true);
            totalPage++;
        }
        else{
            
        }
    }//GEN-LAST:event_addMouseClicked

    private void messageAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageAreaKeyPressed
        // TODO add your handling code here:
        String message = messageArea.getText();
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){    
            try {
                textArea.append(message+"\n\n");
                writer.write("@"+receiver+" "+profile.getProfile_name()+": "+message);
                writer.newLine();
                writer.flush();
                
                
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            finally{
               
        message+=evt.getKeyChar();
        try {
            DatabaseOperations.newMessage(message, this.profile.getUsername(), receiver);
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             messageArea.setText("");
        }
            }    
            }
            
            
    }//GEN-LAST:event_messageAreaKeyPressed

    private void chatsContainersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatsContainersMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_chatsContainersMouseEntered

    private void messageA5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageA5MouseClicked
        // TODO add your handling code here:
        if(inSettings){
            changeApperance();
            
            
        }
    }//GEN-LAST:event_messageA5MouseClicked

    private void iconwhiteBoardsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconwhiteBoardsMouseClicked
        // TODO add your handling code here:
        clearAreas();
         inWhiteBoard = true;
         inChats = false;
         inNotes = false;
         inSettings = false;
       
        changeSettingsIcon();
        setButtonIcon();
        try{
        /*    
        oFileSharingScreen fss = new oFileSharingScreen();
        fss.setHostAddress(client.getHostAddress());
        fss.setPublisher(profile.getProfile_name());
        fss.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        fss.setVisible(true);
            */
        basicFileShareScreen ftp = new basicFileShareScreen();
        ftp.isDark = isDark;
        ftp.setApperance();
        ftp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ftp.setApperance();
        ftp.setVisible(true);
        
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "You do not have any internet connection", "Connection Error", HEIGHT);
        }
    }//GEN-LAST:event_iconwhiteBoardsMouseClicked

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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add;
    private javax.swing.JPanel area1;
    private javax.swing.JPanel area2;
    private javax.swing.JPanel area3;
    private javax.swing.JPanel area4;
    private javax.swing.JPanel area5;
    private javax.swing.JPanel bottom;
    private javax.swing.JLabel button;
    private javax.swing.JPanel chatsContainers;
    private javax.swing.JPanel dataArea;
    private javax.swing.JLabel iconChats;
    private javax.swing.JLabel iconNotes;
    private javax.swing.JLabel iconSettings;
    private javax.swing.JLabel iconwhiteBoards;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel leftContainers;
    private javax.swing.JPanel mainContainer;
    private javax.swing.JLabel messageA1;
    private javax.swing.JLabel messageA2;
    private javax.swing.JLabel messageA3;
    private javax.swing.JLabel messageA4;
    private javax.swing.JLabel messageA5;
    private javax.swing.JTextField messageArea;
    private javax.swing.JPanel notesContainers;
    private javax.swing.JLabel page1;
    private javax.swing.JLabel page2;
    private javax.swing.JLabel page3;
    private javax.swing.JLabel page4;
    private javax.swing.JLabel page5;
    private javax.swing.JLabel saveInfo;
    private javax.swing.JPanel settingsContainer;
    private javax.swing.JTextArea textArea;
    private javax.swing.JLabel title;
    private javax.swing.JLabel titleA1;
    private javax.swing.JLabel titleA2;
    private javax.swing.JLabel titleA3;
    private javax.swing.JLabel titleA4;
    private javax.swing.JLabel titleA5;
    private javax.swing.JTextField titleArea;
    private javax.swing.JPanel whiteBoardsContainers;
    // End of variables declaration//GEN-END:variables
}

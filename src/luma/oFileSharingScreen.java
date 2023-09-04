/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package luma;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author musoftware
 */
public class oFileSharingScreen extends javax.swing.JFrame {

    /**
     * Creates new form FileSharingScreen
     */
    private oFileServerRunnable fsr = null;
    private String hostAddress;
    private String publisher;
    void setHostAddress(String hostAddress){
        this.hostAddress = hostAddress;
    }
    void setPublisher(String publisher){
        this.publisher = publisher;
    }
    public oFileSharingScreen() {
        initComponents();
       pane.getViewport().setBackground(Color.WHITE);
       
       String [] columns = {"File Name","File size","Publisher","Upload Date"};
       String[][] data = {};
       model = new DefaultTableModel(data,columns);
       table.setModel(model);
       table.getColumnModel().getColumn(0).setMinWidth(100);
            table.getColumnModel().getColumn(1).setMinWidth(100);
            table.getColumnModel().getColumn(2).setMinWidth(100);
            table.getColumnModel().getColumn(3).setMinWidth(100);
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  dd MMM YY");
    DefaultTableModel model;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        pane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        upload = new javax.swing.JLabel();
        download = new javax.swing.JLabel();
        brand = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setMinimumSize(new java.awt.Dimension(600, 650));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/logo.png"))); // NOI18N

        pane.setBackground(new java.awt.Color(255, 255, 255));

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Adventur Game.jar", "3 MB", "mkazi", "20:23:45 29 Aug 2023"},
                {"Lottery App", "279 KB", "mkazi", "20:24:14 29 Aug 2023"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "File Name", "File Size", "Publisher", "Upload Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        pane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(100);
            table.getColumnModel().getColumn(1).setMinWidth(100);
            table.getColumnModel().getColumn(2).setMinWidth(100);
            table.getColumnModel().getColumn(3).setMinWidth(100);
        }

        upload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/upload.png"))); // NOI18N
        upload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uploadMouseClicked(evt);
            }
        });

        download.setIcon(new javax.swing.ImageIcon(getClass().getResource("/luma/images/download.png"))); // NOI18N
        download.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                downloadMouseClicked(evt);
            }
        });

        brand.setFont(new java.awt.Font("Copperplate", 1, 11)); // NOI18N
        brand.setText("THEMUSOFTWARE ©");

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(logo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(mainLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(upload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(download)
                .addGap(100, 100, 100))
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brand)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(download)
                    .addComponent(upload))
                .addGap(31, 31, 31)
                .addComponent(brand)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int  selected = table.getSelectedRow();
        System.out.println(selected);
    }//GEN-LAST:event_tableMouseClicked

    private void uploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadMouseClicked
        // TODO add your handling code here:
        String path = "";
        File file = null;
        JFileChooser fileC = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileC.setDialogTitle("Choose the file to upload");
        int returnValue = fileC.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			path = fileC.getSelectedFile().getPath();
                        System.out.println(path);
                file = new File(path);
                Date date = new Date();
                String [] rows = {file.getName(),calculateSize(file.length()),publisher,sdf.format(date)};
                model.addRow(rows);
		}
                
        try {
            if(file!=null){
                uploadFile(file);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(oFileSharingScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }//GEN-LAST:event_uploadMouseClicked

    private void downloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadMouseClicked
        // TODO add your handling code here:
        int row = table.getSelectedRow();
        if(row <0){
            JOptionPane.showMessageDialog(rootPane, "Please select a file", "Unselected File", 1);
            return;
        }
        String fileName = model.getValueAt(row, 0).toString();
        try {
            downloadFile(fileName);
        } catch (IOException ex) {
            Logger.getLogger(oFileSharingScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_downloadMouseClicked
    private String calculateSize(long l){
       
    if (l < 1000){
        return l + " Bytes";
    }
    else if (l < 1000000){
        double kbValue = (double) l / 1000;
        return String.format("%.2f KB", kbValue);
    }
    else if (l < 100000000){
        double mbValue = (double) l / 1000000;
        return String.format("%.2f MB", mbValue);
    }
    else {
        double gbValue = (double) l / 1000000000;
        return String.format("%.2f GB", gbValue);
    }
}
    
    private void uploadFile(File file) throws FileNotFoundException, IOException{
      if(fsr==null){
          fsr = new oFileServerRunnable();
          fsr.setPathName(file.getPath());
          fsr.setIp(hostAddress);
          //Thread thread = new Thread(fsr);
          //thread.start();
      }
      //FileClientRunnable fcr = new FileClientRunnable(file.getName(),hostAddress);
     // fcr.setFileName(file.getName());
      //Thread th = new Thread(fcr);
      //th.start();
      fsr = null;
    }
    
    private void downloadFile(String fileName) throws IOException{
        oFileClientRunnable fcr = new oFileClientRunnable(fileName,hostAddress);
     // fcr.setFileName(file.getName());
        Thread th = new Thread(fcr);
        th.start();
    }


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
            java.util.logging.Logger.getLogger(oFileSharingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(oFileSharingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(oFileSharingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(oFileSharingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new oFileSharingScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brand;
    private javax.swing.JLabel download;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel main;
    private javax.swing.JScrollPane pane;
    private javax.swing.JTable table;
    private javax.swing.JLabel upload;
    // End of variables declaration//GEN-END:variables
}
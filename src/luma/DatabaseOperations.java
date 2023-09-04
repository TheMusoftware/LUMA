/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luma;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author musoftware
 */
public class DatabaseOperations {
    private static String DATABASE_URL = "jdbc:sqlite:Databases/luma.db";
    private static String NOTES_URL = "jdbc:sqlite:Databases/luma_usernotes.db";
    private static String MESSAGES_URL = "jdbc:sqlite:Databases/luma_messages.db";
    private static boolean isConnected = false;
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preSt;
    
    private static void connect() throws SQLException {
       
        connection = DriverManager.getConnection(DATABASE_URL);
        isConnected = true;
    }
    
    private static void connectNotes() throws SQLException{
        connection = DriverManager.getConnection(NOTES_URL);
        isConnected = true;
    }
    private static void connectMessage() throws SQLException{
        connection = DriverManager.getConnection(MESSAGES_URL);
        isConnected = true;
    }
    private static void closeConnection() throws SQLException{
        connection.close();
        isConnected = false;
    }
    
    public static void addNewProfile(String profile_name, String username, String password) throws SQLException{
        String sql = "INSERT INTO profiles (profile_name,username,password) VALUES (?,?,?)";
        if(!isConnected){
            connect();
        }
        preSt = connection.prepareStatement(sql);
        preSt.setString(1, profile_name);
        preSt.setString(2,username);
        preSt.setString(3,password);
        preSt.execute();
        preSt.close();
        closeConnection();
        
    }
    
    public static void updateProfile(int profile_id, String profile_name, String username, String password) throws SQLException{
        //String sql = "UPDATE profiles SET profile_name = ?, username = ?, password = ? where profile_id = ?";
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE profiles SET profile_name = '");
        sb.append(profile_name);
        sb.append("',username = '");
        sb.append(username);
        sb.append("',password = '");
        sb.append(password);
        sb.append("' where profile_id = ");
        sb.append(profile_id);
        sb.append(";");
        String sql = sb.toString();
        
        if(!isConnected){
            connect();
        }
 
       statement = connection.createStatement();
       statement.execute(sql);
       statement.close();
       closeConnection();
    }
    
    public static ArrayList<String> usernames() throws SQLException{
        ArrayList <String> usernames = new ArrayList();
        String sql = "SELECT * FROM profiles where 1";
        if(!isConnected){
            connect();
        }
        statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        while(res.next()){
            usernames.add(res.getString("username"));
        }
        statement.close();
        closeConnection();
        
        return usernames;
    }
    
    public static String getUserPass(String username) throws SQLException{
        String sql = "Select * from profiles where username = '"+username+"'";
        
        if(!isConnected){
            connect();
        }
        
        statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        String pass ="";
        
        while(res.next()){
            pass = res.getString(5);
        }
        closeConnection();
        return pass;
    }
    public static Profile getProfileByUsername(String username) throws SQLException{
        String sql = "Select * from profiles where username = '"+username+"'";
        if(!isConnected){
            connect();
        }
        statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        int profile_id = -1;
        String profile_name = "";    
        Profile profile;
        while(res.next()){
           profile_id = res.getInt(1);
           profile_name = res.getString(2);
        }
        statement.close();
        closeConnection();
        profile = new Profile(profile_id,profile_name,username);
        return profile;  
    }
    
    public static Profile getProfileByID(int id) throws SQLException{
        String sql = "Select * from profiles where profile_id = '"+id+"'";
        connect();
        if(!isConnected){
            connect();
        }
        statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        String username = "";
        String profile_name = "";    
        Profile profile;
        while(res.next()){
           profile_name = res.getString(2);
           username = res.getString(4);
        }
        statement.close();
        closeConnection();
        profile = new Profile(id,profile_name,username);
        return profile;  
    }
    
    public static void createNoteTable(String username) throws SQLException{
        String sql = "CREATE TABLE \""+username+"\" ( \"profile_id\"	INTEGER NOT NULL, \"note_id\"	INTEGER, \"title\"	VARCHAR(16) NOT NULL, \"note\"	TEXT NOT NULL, \"update_date\"	DATETIME NOT NULL DEFAULT (strftime('%Y-%m-%d %H:%M', 'now', 'localtime')), \"shared_profiles\"	TEXT, PRIMARY KEY(\"note_id\" AUTOINCREMENT) );";
        System.out.println("not started");
        if(!isConnected){
            connectNotes();
            System.out.println("connection started");
        }
        statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
        System.out.println("added");
        closeConnection();
        
    }
    
    public static void  updateNotes(int note_id, int profile_id ,String username,String title,String note) throws SQLException{
        boolean isExist = false;
        String checkSQL = "select * from "+username+" where note_id = "+note_id;
        if(!isConnected){
            connectNotes();
        }
        statement = connection.createStatement();
        ResultSet res = statement.executeQuery(checkSQL);
        while(res.next()){
            isExist = true;
        }
        statement.close();
        
        if(isExist){
           String upSql = "Update "+username+" SET title= '"+title+"', note='"+note +"',update_date = strftime('%Y-%m-%d %H:%M', 'now', 'localtime') where note_id = "+note_id;
           statement = connection.createStatement();
           statement.execute(upSql);
            System.out.println("updated");
            
        }
        else{
            String addSql = "INSERT INTO "+username+" ( profile_id, title, note) VALUES ( ?, ?, ?)";
       if(!isConnected){
          connectNotes(); 
       }
        System.out.println("connected");
       preSt = connection.prepareStatement(addSql);
      // preSt.setInt(1,note_id);
       preSt.setInt(1, profile_id);
       preSt.setString(2, title);
       preSt.setString(3, note);
       preSt.executeUpdate();
       closeConnection();
        System.out.println("added");
       
        }
}
    public static ArrayList <Note> getNotes(String username) throws SQLException{
        ArrayList <Note> notes = new ArrayList();
        if(!isConnected){
            connectNotes();
        }
        statement = connection.createStatement();
        String sql = "Select * from "+username;
        ResultSet res = statement.executeQuery(sql);
        while(res.next()){
            int note_id = res.getInt(2);
            int profile_id = res.getInt(1);
            String title = res.getString(3);
            String note = res.getString(4);
            String date = res.getString(5);
            Note addThis = new Note(note_id,profile_id,title,note,date);
            notes.add(addThis);
        }
        closeConnection();
        return notes;
        //return fixedNotes(notes,username);
}
    private static ArrayList <Note> fixedNotes(ArrayList<Note> notes,String username) throws SQLException{
        String sql = "Select note_id from "+username +"ORDER by note_id ASC";
        if(!isConnected){
            connectNotes();
        }
        statement = connection.createStatement();
        ResultSet set = statement.executeQuery(sql);
       for(int i = 0; i < 25; i++){
           int a = set.getInt(1);
           while(a != i){
               notes.add(i,new Note(-1,-1,"","",""));
               i++;
           }
       }
       closeConnection();
       return notes;
    }
    
    public static void createMessageTable(String username) throws SQLException{
        String sql = "CREATE TABLE \""+username+"\" ( \"sender_id\"	INTEGER NOT NULL, \"message_id\"	INTEGER, \"receiver_id\"	INTEGER NOT NULL, \"message\"	TEXT NOT NULL, \"update_date\"	DATETIME NOT NULL DEFAULT (strftime('%Y-%m-%d %H:%M', 'now', 'localtime')), PRIMARY KEY(\"message_id\" AUTOINCREMENT) );";
       // System.out.println("not started");
        if(!isConnected){
            connectMessage();
            System.out.println("connection started");
        }
        statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
        System.out.println("added");
        closeConnection();
    }
    
    public static void newMessage(String message,String sender, String receiver) throws SQLException{
        int sender_id = DatabaseOperations.getProfileByUsername(sender).getProfile_id();
        int receiver_id = DatabaseOperations.getProfileByUsername(receiver).getProfile_id();
        if(!isConnected){
            connectMessage();
        }
        
        String sql = "INSERT INTO "+sender +"(sender_id, receiver_id, message) VALUES (?,?,?)";
        preSt = connection.prepareStatement(sql);
        preSt.setInt(1, sender_id);
        preSt.setInt(2,receiver_id);
        preSt.setString(3, message);
        preSt.execute();
        preSt.close();
        closeConnection();
    }
    
    public static ArrayList<String> getReceivers(String username) throws SQLException {
    ArrayList<String> receivers = new ArrayList<String>();
    receivers.add("everyone");
    String sql = "SELECT * FROM " + username + " WHERE receiver_id IS NOT NULL order by update_date DESC";
    if (!isConnected) {
        connectMessage();
    }
    statement = connection.createStatement();
    ResultSet res = statement.executeQuery(sql);
    while (res.next()) {
        Profile profile = DatabaseOperations.getProfileByID(res.getInt(3));
        boolean isExist = false;
        for (String str : receivers) {
            if (str.equals(profile.getUsername())) {
                isExist = true;
                break; 
            }
        }
        if (!isExist && profile.getProfile_id()>0) {
            receivers.add(profile.getUsername());
        }
    }
    closeConnection();
    return receivers;
}
public  static ArrayList <String> getAllUsernames() throws SQLException{
    ArrayList <String> usernames = new ArrayList<>();
    if(!isConnected){
        connect();
    }
    statement = connection.createStatement();
    String sql = "Select * from profiles";
    ResultSet res = statement.executeQuery(sql);
    
    while(res.next()){
        usernames.add(res.getString(4));
    }
  closeConnection();
    return usernames;
}
public static String getUserMessages(String sender, String receiver) throws SQLException{
    //ArrayList<String> messages = new ArrayList<>();
    /*
    I think string builder more efficient than arraylist ---???---
    */
    StringBuilder messages = new StringBuilder();
    messages.append("");
    Profile sen = DatabaseOperations.getProfileByUsername(sender);
    Profile rec = DatabaseOperations.getProfileByUsername(receiver);
    
   if (!isConnected) {
    connectMessage();
            }

    String sql = "SELECT * FROM " + rec.getUsername() + " WHERE receiver_id = ? " +
             "UNION " +
             "SELECT * FROM " + sen.getUsername() + " WHERE receiver_id = ? " +
             "ORDER BY update_date ASC;";
    
    if(receiver.equals("everyone")){
        closeConnection();
        return unionAllTables(DatabaseOperations.getAllUsernames());
    }
    
    preSt = connection.prepareStatement(sql);

// Set infos
preSt.setInt(1, sen.getProfile_id());
preSt.setInt(2, rec.getProfile_id());


ResultSet res = preSt.executeQuery();
while (res.next()) {
    int sender_id = res.getInt(1);
    if (sender_id == rec.getProfile_id()) {
        messages.append(rec.getProfile_name() + ": " + res.getString(4) + "\n");
    } else {
        messages.append(res.getString(4) + "\n");
    }
}
closeConnection();
return messages.toString();


}
public static String unionAllTables(ArrayList<String> tableNames) throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder();
        
        for (String tableName : tableNames) {
            if (sqlBuilder.length() > 0) {
                sqlBuilder.append(" UNION ALL ");
            }
            sqlBuilder.append("SELECT * FROM ");
            sqlBuilder.append(tableName);
            sqlBuilder.append(" where receiver_id = -1");
        }
        
        sqlBuilder.append(" ORDER BY update_date ASC;");
        
        String sql = sqlBuilder.toString();

        if (!isConnected) {
            connectMessage();
        }
        statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        
        StringBuilder result = new StringBuilder();
        while (res.next()) {
            int sender_id = res.getInt(1);
            Profile senderProfile = DatabaseOperations.getProfileByID(sender_id);
           result.append(senderProfile.getProfile_name()+": "+res.getString(4)+"\n");
        }
        closeConnection();
        return result.toString();
    }


/**
 * 
 * SQLite does not have an explicit TRUNCATE TABLE command like other databases.
 * Instead, it has added a TRUNCATE optimizer to the DELETE statement.
 * To truncate a table in SQLite, you just need to execute a DELETE statement without a WHERE clause.
 * 
 */
public static void deleteAllMessages(String username) throws SQLException{
    if(!isConnected){
        connectMessage();
    }
    String sql = "DELETE FROM "+username;
    statement = connection.createStatement();
    statement.execute(sql);
    closeConnection();
}
public static void deleteAllNotes(String username) throws SQLException{
    if(!isConnected){
        connectNotes();
    }
    String sql = "DROP TABLE "+username;
    statement = connection.createStatement();
    statement.execute(sql);
    closeConnection();
    DatabaseOperations.createNoteTable(username);
}

}
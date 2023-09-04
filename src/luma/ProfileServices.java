package luma;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author musoftware
 */
public class ProfileServices {
    
    public static boolean checkUsername(String username) throws SQLException{
       // if returned value true this username avaliable
       ArrayList <String> usernames = DatabaseOperations.usernames();
        for(String u : usernames){
          
            if(u.equals(username)){
                return false;
            }
        }
        
       return true; 
    };
    
    public static boolean checkPassword(String password){
        //The password  include at least one capital , one symbol and digit
        if(password.length()>=8){
    
       Pattern letter = Pattern.compile("[A-Z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
       

        Matcher hasLetter = letter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);

        return hasLetter.find() && hasDigit.find() && hasSpecial.find();
    }
    else
        return false;
    }
    
    
    
}

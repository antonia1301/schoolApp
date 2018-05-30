/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import schoolApplicationUI.MainDialog;

/**
 *
 * @author Flora
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
   public static void main(String[] args) throws SQLException {
        Connection conn ;
        
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldb?autoReconnect=true&useSSL=false", "root", "");
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            System.out.println("dewd");
        }
        
        MainDialog frame = new MainDialog();
                
       
        
    }
    
}
    


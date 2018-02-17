/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import java.awt.event.ActionEvent;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Sara
 */
public class FirstInserts {

    public FirstInserts() {
        insertUsers();
    }
     
    
    
    public static void insertUsers (){
    
                try{
                 Connection connection = DBConnect.getConnection();
                Statement statement = connection.createStatement();
                statement.execute("INSERT INTO `ticket`.`systemusers` (`username`, `password`, `type`) VALUES ('admin', 'adminadmin', 'Admin');");
                statement.execute("INSERT INTO `ticket`.`systemusers`  (`username`, `password`, `type`) VALUES ('john', 'johnsupport', 'Support');"); 
                statement.execute("INSERT INTO `ticket`.`systemusers` (`username`, `password`, `type`) VALUES ('james', 'jamessupport', 'Support');");
                statement.execute("INSERT INTO `ticket`.`systemusers` (`username`, `password`, `type`) VALUES ('johan', 'johansupport', 'Support');");
                statement.execute("INSERT INTO `ticket`.`systemusers` (`username`, `password`, `type`) VALUES ('marcus', 'marcusmanager', 'Manager');");                    
                      
           }
           catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
   
         

        }

    }
            public static void main(String[] args) {
		// TODO Auto-generated method stub
          new FirstInserts();
} 


           }


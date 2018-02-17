/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DBConnect;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sara
 */
public class UpagradeUserPanels extends JFrame implements ActionListener{
      JTextField username;    
      JTextField password = null;
      int id_user;
     ResultSet result = null;
    String user_name;
    String pass_word;
    String type_sql;
    JTextField type;
    
    public UpagradeUserPanels(int id) {  
        setSize(325, 500);
        setVisible(true);
         id_user = id;
         this.setLayout(new BorderLayout());
        this.setLayout(new GridLayout(2, 2));
        
        try {

            Connection connection = DBConnect.getConnection();
            Statement st = connection.createStatement();

            // Execute insert User
          if(st.execute("SELECT * FROM systemusers WHERE id ='"+id_user+ "';")){
            result = st.getResultSet();
          
          }
            // Loop over results and open the appropriate user control panel based on user's account type
            if (result.next()) {
                      
               user_name = result.getString("username");
               pass_word = result.getString("password");
               type_sql = result.getString("type");
              
                
            } else { // If ID not found.

                JOptionPane.showMessageDialog(this, "User not found");
                 dispose();

            }
          
        } catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
          

        }
        JPanel top = new JPanel();
       JLabel showuser = new JLabel("Username: ");
        top.add(showuser);
        username = new JTextField(20);
        username.setText(user_name);
        top.add(username);
        JLabel pw = new JLabel("Password: ");
        top.add(pw);
        password = new JTextField(20);
        password.setText(pass_word);
        top.add(password);
        JLabel tp = new JLabel("Type: ");
        top.add(tp);
        type = new JTextField(20);
        type.setText(type_sql);
        top.add(type);
        this.add(top, BorderLayout.PAGE_START);
         JPanel ends = new JPanel();
        JButton upgrade = new JButton("Upgrade"); 
        upgrade.addActionListener(this); 
        upgrade.setActionCommand("upgrade"); 
        ends.add(upgrade);
       JButton cancel = new JButton("Cancel"); 
        cancel.addActionListener(this); 
        cancel.setActionCommand("cancel"); 
        ends.add(cancel);
        this.add(ends, BorderLayout.CENTER);
        validate();
        repaint();
    }
      public static void main(String args[]){
           new UpagradeUserPanels(2);
      }        
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("upgrade")) {
            if (password.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter password");
            }     
            if(username.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter username");
            }           
     
        try {

            Connection connection = DBConnect.getConnection();
            Statement st = connection.createStatement();

            // Execute insert User
            st.execute("UPDATE systemusers SET password ='" +password.getText()+"' , username ='"+ username.getText() +"' WHERE id =" +id_user+";");
             JOptionPane.showMessageDialog(this, "User upgrade");
              setVisible(false);
        } catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
     
    }
        }
       else if(e.getActionCommand().equals("cancel")){
        this.setVisible(false);
    }
    }

    }


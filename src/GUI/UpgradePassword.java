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
public class UpgradePassword extends JFrame implements ActionListener{
     String username;
     JTextField searchTxt = null;
     JTextField password = null;
     int id;
     ResultSet result = null;
    
    
    public UpgradePassword(String user_name, int id_user) {  
        username = user_name; 
        id = id_user;
        setSize(350, 200);
        setVisible(true);
        this.setLayout(new GridLayout(2, 2));
        this.setLayout(new BorderLayout());
        JPanel top = new JPanel();
        JLabel sh = new JLabel("Username: ");
        top.add(sh);
        JLabel showuser = new JLabel(username);
        top.add(showuser);
        this.add(top, BorderLayout.PAGE_START);
        JPanel cents = new JPanel(); 
        JLabel pw = new JLabel("Password: ");
        cents.add(pw);
        password = new JTextField(20);
        cents.add(password);
        this.add(cents, BorderLayout.CENTER);
        JPanel ends = new JPanel();
        JButton upgrade = new JButton("upgrade"); 
        upgrade.addActionListener(this); 
        upgrade.setActionCommand("upgrade"); 
        ends.add(upgrade);
        JButton cancel = new JButton("Cancel"); 
        cancel.addActionListener(this); 
        cancel.setActionCommand("cancel"); 
        ends.add(cancel);
        this.add(ends, BorderLayout.PAGE_END);
        
        validate();
        repaint();
    }
      public static void main(String args[]){
           new UpgradePassword("juana", 1);
      }        
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("upgrade")) {
            if (password.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter password");
            }     
         
     
        try {

            Connection connection = DBConnect.getConnection();
            Statement st = connection.createStatement();
            
            // Execute insert User
            st.execute("UPDATE systemusers SET password ='" +password.getText()+"' WHERE id ='" +id+"';");
             JOptionPane.showMessageDialog(this, "User upgrade");
              setVisible(false);
        } catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
            Main main = new Main();   
    }
    }
     else if(e.getActionCommand().equals("cancel")){
        this.setVisible(false);
    }
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DBConnect;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.time.Instant;
import javax.swing.JPanel;
/**
 *
 * @author Sara
 */
public class CreateTicketPanel extends JFrame implements ActionListener{

      JTextField clientID = null;
      JTextField description = null;
      String sev = null;
      String openTime;
      JTextField assigned;
      
      String closeTime = null;
      String status;
        String[] cho = {"Urgent", "Normal", "Long Term"};
        JComboBox<String> cb = new JComboBox<String>(cho);
     CreateTicketPanel() {
         

        this.setSize(600, 170);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        
        JPanel top = new JPanel();
        JLabel cl = new JLabel("Client:");
        top.add(cl);
        clientID = new JTextField(20);
        top.add(clientID);
        JLabel des = new JLabel("Description:");
        top.add(des);
        description = new JTextField(20);
        top.add(description);
        this.add(top, BorderLayout.PAGE_START);
        JPanel cent = new JPanel();
        JLabel sv = new JLabel("Sev:");
        cent.add(sv);
        cent.add(cb);
       
      
       
        JLabel lblassigned = new JLabel("Assigned");
        cent.add(lblassigned);
        assigned = new JTextField(20);
        cent.add(assigned);
        this.add(cent, BorderLayout.CENTER);
        //button create
        JPanel ends = new JPanel();
        JButton create = new JButton("Create Ticket"); 
        create.addActionListener(this); 
        create.setActionCommand("create"); 
        ends.add(create);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setActionCommand("cancel");
        ends.add(cancel);
        this.add(ends, BorderLayout.PAGE_END);
        validate();
        repaint();
    }
public static void main(String arg[]){
  new  CreateTicketPanel();
}
            
    @Override
    public void actionPerformed(ActionEvent e) {
   
     if (e.getActionCommand().equals("create")) {
            if (clientID.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter password");
            }
         
            if(description.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter username");
            }
             
            
             if (assigned.getText().equals("")){
                 JOptionPane.showMessageDialog(this, "Enter assigned"); 
             }
             sev = (String) cb.getSelectedItem();
             status = "Open";
             openTime =  Long.toString(Instant.now().getEpochSecond());
             closeTime = null;
            
        // Attempt to connect to database
        try {

            Connection connection = DBConnect.getConnection();
            Statement statement = connection.createStatement();

            // Execute insert User
            statement.execute("INSERT INTO `ticket`.`tickets` (`clientid`, `description`, `sev`, `opentime`, `closetime`, `assigned`,`status`) VALUES ('"+ clientID.getText() + "', '" + description.getText() + "', '" + sev+ "', '" + openTime + "', '" + closeTime + "', '"+ assigned.getText() +"', '"+status+ "');");
             JOptionPane.showMessageDialog(this, "Ticket created");
        } catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
            

        }
      }else  if (e.getActionCommand().equals("cancel")){
                setVisible(false);
            }
        }
    }
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
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
import java.time.Instant;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sara
 */
public class UpgradeTicketPanel extends JFrame implements ActionListener{
    int id;
    String client_sql;
    String description_sql;
    String sev_sql;
    String openTime_sql;
    String closeTime_sql;
    String assigned_sql;
    String status_sql;
    String status;
    String closeTime;
    JTextField client = null;
    JTextField description  = null;    
    JTextField openTime = null;
    JTextField  assigned = null;
    String[] cho = { "Open","Close"};
    JComboBox<String> cbstatus = new JComboBox<String>(cho);
    String[] choices = { "Urgent","Normal", "Long Term"};
    JComboBox<String> cb = new JComboBox<String>(choices);
    ResultSet result = null;
    
    public UpgradeTicketPanel(int id_ticket) {
        setSize(350, 500);
        setVisible(true);
        this.setLayout(new GridLayout(4, 2));
        id = id_ticket;
        try {

            Connection connection = DBConnect.getConnection();
            Statement st = connection.createStatement();

            // Execute search ticket
          if(st.execute("SELECT * FROM tickets WHERE id ='"+id+ "';")){
            result = st.getResultSet();
          
          }
            // Loop over results and open the appropriate tickets control panel based on ticket's
            if (result.next()) {
                      
               client_sql = result.getString("clientid");
               description_sql = result.getString("description");
               sev_sql = result.getString("sev");
               openTime_sql = result.getString("opentime");
               closeTime_sql = result.getString("closetime");
               assigned_sql = result.getString("assigned");
               status_sql = result.getString("status");
              
                
            } else { // If ID not found.

                JOptionPane.showMessageDialog(this, "Ticket not found");
                 dispose();

            }
          
        } catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
          

        }
        JPanel top = new JPanel();
        JLabel showCli = new JLabel("Client ID: ");
        top.add(showCli);
        client = new JTextField(20);
        client.setText(client_sql);
        top.add(client);
        JLabel showDes = new JLabel("Description: ");
        top.add(showDes);
        description = new JTextField(20);
        description.setText(description_sql);
        top.add(description);
        JLabel showSev = new JLabel("Sev: ");
        top.add(showSev);
        if(sev_sql.equals("Urgent"))
           cb.setSelectedIndex(0);
       
        if(sev_sql.equals("Normal"))
           cb.setSelectedIndex(1);
        if (sev_sql.equals("Long Term"))
              cb.setSelectedIndex(2);
           
        top.add(cb);
        this.add(top, BorderLayout.PAGE_START);
        JPanel cent = new JPanel();
        
        JLabel showOpe = new JLabel("Open Time: ");
        cent.add(showOpe);
        openTime = new JTextField(20);
        openTime.setText(openTime_sql);
        cent.add(openTime);        
        
        JLabel showAss = new JLabel("Assigned: ");
        cent.add(showAss);
        assigned = new JTextField(20);
        assigned.setText(assigned_sql);
        cent.add(assigned);
        JLabel showSta = new JLabel("Status: ");
        cent.add(showSta);
        if(status_sql.equals("Open"))
            cbstatus.setSelectedIndex(0);
        if(status_sql.equals("Close"))
            cbstatus.setSelectedIndex(1);
        cent.add(cbstatus);
        this.add(cent, BorderLayout.CENTER);
       JPanel ends= new JPanel();
        JButton upgrade = new JButton("Upgrade"); 
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
           new UpgradeTicketPanel(2);
      }        
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("upgrade")) {
            status = (String) cbstatus.getSelectedItem();
                     if(status.equals("Close")){                        
                      closeTime =  Long.toString(Instant.now().getEpochSecond());
                     }
            sev_sql = (String) cb.getSelectedItem();
        try {
           
            Connection connection = DBConnect.getConnection();
            Statement st = connection.createStatement();

            // Execute insert User
            st.execute("UPDATE tickets SET  sev='" +sev_sql+"' , status ='"+ status +"', closeTime= '"+ closeTime +"'  WHERE id =" +id+";");
             JOptionPane.showMessageDialog(this, "Ticket upgrade");
              setVisible(false);
        } catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");     
    }
   }else if (e.getActionCommand().equals("cancel")) {
    this.setVisible(false);
   }
  }
}      
        
        

    
   
    
    
    
    
    
    
    
    
    
    
    
   
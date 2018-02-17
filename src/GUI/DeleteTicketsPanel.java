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
import org.omg.CORBA.portable.Delegate;

/**
 *
 * @author Sara
 */
public class DeleteTicketsPanel extends JFrame implements ActionListener{
    int id;
    String client_sql;
    String description_sql;
    String sev_sql;
    String openTime_sql;
    String closeTime_sql;
    String assigned_sql;
    String status_sql;
    JTextField client = null;
    JTextField description  = null;    
    JTextField sev = null;
    JTextField openTime = null;
    JTextField closeTime = null;
    JTextField  assigned = null;
    JTextField status = null;
    
    ResultSet result = null;
    
    public DeleteTicketsPanel(int id_ticket) {
        setSize(400, 500);
        setVisible(true);
        this.setLayout(new GridLayout(4, 4));
        id = id_ticket;
        try {

            Connection connection = DBConnect.getConnection();
            Statement st = connection.createStatement();

            // Execute search ticket
          if(st.execute("SELECT * FROM tickets WHERE id ='"+id_ticket+ "';")){
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
        client = new JTextField(10);
        client.setText(client_sql);
        top.add(client);
        JLabel showDes = new JLabel("Description: ");
        top.add(showDes);
        description = new JTextField(10);
        description.setText(description_sql);
        top.add(description);
        
        JPanel cents = new JPanel();
        JLabel showSev = new JLabel("Sev: ");
        top.add(showSev);
        sev = new JTextField(10);
        sev.setText(sev_sql);
        top.add(sev);
        JLabel showOpe = new JLabel("Open Time: ");
        top.add(showOpe);
        openTime = new JTextField(10);
        openTime.setText(openTime_sql);
        top.add(openTime);        
        JLabel showClo = new JLabel("Close Time: ");
        top.add(showClo);
        closeTime = new JTextField(10);
        closeTime.setText(closeTime_sql);
        top.add(closeTime);
        JLabel showAss = new JLabel("Assigned: ");
        top.add(showAss);
        assigned = new JTextField(10);
        assigned.setText(assigned_sql);
        top.add(assigned);
        JLabel showSta = new JLabel("Status: ");
        top.add(showSta);
        status = new JTextField(10);
        status.setText(status_sql);
        top.add(status);
        this.add(top, BorderLayout.PAGE_START);
        this.add(cents, BorderLayout.CENTER);
        
        JPanel ends = new JPanel();
        JButton upgrade = new JButton("Delete"); 
        upgrade.addActionListener(this); 
        upgrade.setActionCommand("delete"); 
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
           new DeleteTicketsPanel(2);
      }        
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("delete")) {
               new DeleteTicket(id);
    }else if (e.getActionCommand().equals("cancel")){
        this.setVisible(false);
    }

    }
}
        
         

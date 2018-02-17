/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DBConnect;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Sara
 */
public class DeleteTicket extends JFrame implements ActionListener {
        int id_ticket;
        DeleteTicket(int id) {
         id_ticket = id;
        this.setSize(250, 100);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
       

        JLabel u = new JLabel("Are​ ​you​ ​sure​ ​you​ ​want​ ​delete ticket?");
        this.add(u);
        JButton yes = new JButton("Yes"); 
        yes.addActionListener(this); 
        yes.setActionCommand("yes"); 
        this.add(yes); 

        JButton no = new JButton("No"); 
        no.addActionListener(this); 
        no.setActionCommand("no"); 
        this.add(no);

        validate();
        repaint();
}

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getActionCommand().equals("no")) {
            setVisible(false);
        } else if (e.getActionCommand().equals("yes")) {
             try {

            Connection connection = DBConnect.getConnection();
            Statement st = connection.createStatement();

            // Execute insert User
            st.execute("DELETE FROM tickets WHERE id =" +id_ticket+";");
             JOptionPane.showMessageDialog(this, "Ticket Delete");
              setVisible(false);
        } catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
          

      
     
    }
        }
    }
    
    }
    


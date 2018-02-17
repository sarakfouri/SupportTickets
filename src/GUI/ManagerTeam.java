/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import DB.DBConnect;

import Entities.MyModelTime;

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
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;

import javax.swing.JTable;

/**
 *
 * @author Sara
 */
public class ManagerTeam extends JFrame implements ActionListener{
         ResultSet result = null;
         String totalJ = null ;
         int totalIntJ = 0;
         JTextField johanTotal = new JFormattedTextField(5);
         JTextField johanOpen = new JFormattedTextField(5);
         JTextField johanClose = new JFormattedTextField(5);
         JTextField jaTotal = new JFormattedTextField(5);
         JTextField jaOpen = new JFormattedTextField(5);
         JTextField jaClose = new JFormattedTextField(5);
         JTextField johnTotal = new JFormattedTextField(5);
         JTextField johnOpen = new JFormattedTextField(5);
         JTextField johnClose = new JFormattedTextField(5);
         
         
public ManagerTeam(){    
      this.setSize(530, 200);
      this.setLayout(new GridLayout(4,4));
       this.setLayout(new BorderLayout());
       this.setVisible(true);
       
       try {
            Connection connection = DBConnect.getConnection();
            Statement st = connection.createStatement();
          // Johan support
             if(st.execute("Select COUNT(*) from tickets Where assigned = 'johan' or assigned = 'Johan';" )){
             
             result = st.getResultSet();
             if(result.next()){
                int  totaljohan = result.getInt("count(*)");
                 johanTotal.setText(Integer.toString(totaljohan));
             }
              
             }
             if(st.execute("Select COUNT(*) from tickets Where assigned = 'johan' or assigned = 'Johan' and closetime = 'null';")){
                 result = st.getResultSet();
                 if(result.next()){
                   int  totalOpen = result.getInt("count(*)");
                   johanOpen.setText(Integer.toString(totalOpen));
                     }
                 
             }
             if(st.execute("Select COUNT(*) from tickets Where assigned = 'johan' or assigned = 'Johan' and closetime != 'null';")){
                 result = st.getResultSet();
                 if(result.next()){
                   int  totalClose = result.getInt("count(*)");
                   johanClose.setText(Integer.toString(totalClose));
                     }
             }
             // end Johan support
             // John support
              if(st.execute("Select COUNT(*) from tickets Where assigned = 'john' or assigned = 'John';" )){
             
             result = st.getResultSet();
             if(result.next()){
                int  totaljohn = result.getInt("count(*)");
                 johnTotal.setText(Integer.toString(totaljohn));
             }
              
             }
             if(st.execute("Select COUNT(*) from tickets Where assigned = 'john' or assigned = 'John' and closetime = 'null';")){
                 result = st.getResultSet();
                 if(result.next()){
                   int  totalOpen = result.getInt("count(*)");
                   johnOpen.setText(Integer.toString(totalOpen));
                     }
                 
             }
             if(st.execute("Select COUNT(*) from tickets Where assigned = 'john' or assigned = 'John' and closetime != 'null';")){
                 result = st.getResultSet();
                 if(result.next()){
                   int  totalClose = result.getInt("count(*)");
                   johnClose.setText(Integer.toString(totalClose));
                     }
             }
              if(st.execute("Select COUNT(*) from tickets Where assigned = 'james' or assigned = 'James';" )){
             //end Johan
             //james support
             result = st.getResultSet();
             if(result.next()){
                int  totaljames = result.getInt("count(*)");
                 jaTotal.setText(Integer.toString(totaljames));
             }
              
             }
             if(st.execute("Select COUNT(*) from tickets Where assigned = 'james' or assigned = 'James' and closetime = 'null';")){
                 result = st.getResultSet();
                 if(result.next()){
                   int  totalOpen = result.getInt("count(*)");
                   jaOpen.setText(Integer.toString(totalOpen));
                     }
                 
             }
             if(st.execute("Select COUNT(*) from tickets Where assigned = 'james' or assigned = 'James' and closetime != 'null';")){
                 result = st.getResultSet();
                 if(result.next()){
                   int  totalClose = result.getInt("count(*)");
                   jaClose.setText(Integer.toString(totalClose));
                     }
             }
             }catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
          

        }
          JPanel top = new JPanel();
          JLabel showfirst = new JLabel("Tickets by Team");
          top.add(showfirst);
           this.add(top, BorderLayout.PAGE_START);
          //John
          JPanel cent = new JPanel();
          JLabel john = new JLabel("Team John's Tickets: ");
          cent.add(john);
          JLabel john1 = new JLabel(" Tickets total: ");
          cent.add(john1);
          cent.add(johnTotal);
          JLabel john2 = new JLabel("Tickets Open: ");
          cent.add(john2);
          cent.add(johnOpen);
          JLabel john3 = new JLabel("Tickets Close: ");
          cent.add(john3);
          cent.add(johnClose);
        //Johan
          JLabel johan = new JLabel("Team Johan's Tickets: ");
          cent.add(johan);
          JLabel johan1 = new JLabel(" Tickets total: ");
          cent.add(johan1);
          cent.add(johanTotal);
          JLabel johan2 = new JLabel("Tickets Open: ");
          cent.add(johan2);
          cent.add(johanOpen);
          JLabel johan3 = new JLabel("Tickets Close: ");
          cent.add(johan3);
          cent.add(johanClose);
         
          //james
          JLabel james = new JLabel("Team James's Tickets: ");
          cent.add(james);
          JLabel james1 = new JLabel(" Tickets total: ");
          cent.add(james1);
          cent.add(jaTotal);
          JLabel james2 = new JLabel("Tickets Open: ");
          cent.add(james2);
          cent.add(jaOpen);
          JLabel james3 = new JLabel("Tickets Close: ");
          cent.add(james3);
          cent.add(jaClose);
          this.add(cent, BorderLayout.CENTER);
           
          JPanel ends = new JPanel();
          JButton close = new JButton("Close");
          close.addActionListener(this);
          close.setActionCommand("close");
          ends.add(close);
          JButton showTime = new JButton("Show Ticket Time");
          showTime.addActionListener(this);
          showTime.setActionCommand("showTime");
          ends.add(showTime);
          this.add(ends, BorderLayout.PAGE_END);
          
          
          validate();
           repaint();
}
  
 
                 
public static void main (String args[]){
    new ManagerTeam();
}


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("close")){
            this.setVisible(false);
        
    }else if(e.getActionCommand().equals("showTime")){
            new ShowTicketsTime();
        }
    }
       
}


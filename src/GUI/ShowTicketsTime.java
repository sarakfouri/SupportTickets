/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DBConnect;
import Entities.MyModelTicket;
import Entities.MyModelTime;
import Entities.Tickets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Sara
 */
public class ShowTicketsTime extends JFrame implements ActionListener{
      private JTable table = new JTable();
        private MyModelTime tableMode3;


public ShowTicketsTime (){
    this.setSize(730, 600);
      this.setLayout(new BorderLayout());
      this.setLayout(new GridLayout(2, 2));
    
       this.setVisible(true);
       this.setLayout(new FlowLayout());
        this.setSize(new Dimension(650, 400));
        this.setLocationRelativeTo(null);
        tableMode3 = new MyModelTime(getListTickets());
        table.setModel(tableMode3);
        table.setPreferredScrollableViewportSize(new Dimension(400,300));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
    
         JButton close = new JButton("Close");
          close.addActionListener(this);
          close.setActionCommand("close");
          this.add(close);
        validate();
        repaint();

}

public List<Tickets> getListTickets(){
        //add botões ao JFrame
         List<Tickets> ticket=new ArrayList<Tickets>();
         Tickets one = null;
    	ResultSet rs = null;
    	try {
    	    Connection connection = DBConnect.getConnection();
            Statement statement = connection.createStatement();
    	    // Do something with the Connection
    	    
    	    statement.execute("select * from tickets;"); 
            rs = statement.getResultSet();
    	    
    	    while(rs.next()){
    	    	 one = new Tickets();
                 one.setId(rs.getInt("id"));
                 one.setOpentime(rs.getString("opentime"));
                 one.setClosetime(rs.getString("closetime"));
                 one.setAssigned(rs.getString("assigned"));
                 
                 ticket.add(one);
                 
                 one =null;
                  }                
            statement.close();
       
    	   
    	     

            
    	    
    	} catch (SQLException ex) {
    	    // handle any errors
    	    System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	}
    	return ticket;
  }


public static void main (String args[]){
    new ShowTicketsTime();
}


  @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("close")){
            this.setVisible(false);
        
    }


}
}
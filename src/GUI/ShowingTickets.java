/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DBConnect;
import Entities.MyModelTicket;
import Entities.Tickets;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 *
 * @author Sara
 */
public class ShowingTickets extends JFrame implements ActionListener{
      private JTable table = new JTable();
        private MyModelTicket tableMode2;
        int id_ticket=0;
   
    public ShowingTickets(){
    	this.setVisible(true);
        this.setTitle("Tickets");
      
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(800, 400));
        this.setLocationRelativeTo(null);
        JButton upgrade = new JButton("Upgrade");
        upgrade.addActionListener(this); 
        upgrade.setActionCommand("upgrade");
        this.add(upgrade);
        JButton upTicket = new JButton("Upgrade Ticket");
        upTicket.addActionListener(this); 
        upTicket.setActionCommand("upTicket");
        this.add(upTicket);
        JButton delete = new JButton("Delete Ticket");
       delete.addActionListener(this); 
        delete.setActionCommand("delete");
        this.add(delete);
        JButton cancel = new JButton("Close");
        cancel.addActionListener(this); 
        cancel.setActionCommand("cancel");
        this.add(cancel);
        tableMode2 = new MyModelTicket(getListTickets());
        table.setModel(tableMode2);
        table.setPreferredScrollableViewportSize(new Dimension(700,300));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        validate();
        repaint();
    }
 
        //add eventos aos botões
       
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
                 one.setClientID(rs.getString("clientid"));
                 one.setDescription(rs.getString("description"));
                 one.setSev(rs.getString("sev"));
                 one.setOpentime(rs.getString("opentime"));
                 one.setClosetime(rs.getString("closetime"));
                 one.setAssigned(rs.getString("assigned"));
                 one.setStatus(rs.getString("status"));
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
                 
 
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new ShowingTickets();
	}

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("upgrade")) {
        this.dispose();
        new ShowingTickets();
    } else if (e.getActionCommand().equals("upTicket")) {
        new UpgradeTicketPanel(id_ticket);
    }else if (e.getActionCommand().equals("delete")){
        new DeleteTicketsPanel(id_ticket);
    }
     else if(e.getActionCommand().equals("cancel")){
        this.setVisible(false);
    }
  
    }

 public void table1MouseClicked (java.awt.event.MouseEvent evt){
       if(table.getSelectedRow() != -1){
          
            int selectRow = table.getSelectedRow();
        String id = tableMode2.getValueAt(selectRow, 0).toString();
             
             id_ticket = Integer.parseInt(id);
            
       
 }

}
 }




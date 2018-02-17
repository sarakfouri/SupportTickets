/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DBConnect;
import Entities.MyModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Entities.Users;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;




/**
 *
 * @author Sara
 */
public class ShowingUsers extends JFrame implements ActionListener{
        private JTable table = new JTable();
        private MyModel tableModel;
        int id_user;
      
    public ShowingUsers(){
    	this.setVisible(true);
        this.setTitle("User System");
      
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(700, 500));
        this.setLocationRelativeTo(null);
        JPanel top = new JPanel();
        JButton upgrade = new JButton("Upgrade");
        upgrade.addActionListener(this); 
        upgrade.setActionCommand("upgrade");
        top.add(upgrade);
        JButton upuser = new JButton("Upgrade User");
        upuser.addActionListener(this); 
        upuser.setActionCommand("upuser");
        top.add(upuser);
        JButton cancel = new JButton("Close");
        cancel.addActionListener(this); 
        cancel.setActionCommand("cancel");
        top.add(cancel);
        this.add(top, BorderLayout.PAGE_START); 
        tableModel = new MyModel(getListUsers());
        table.setModel(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(400,300));
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
 
  
       
 public List<Users> getListUsers(){
       
         List<Users> user=new ArrayList<Users>();
         Users one = null;
    	ResultSet rs = null;
    	try {
    	    Connection connection = DBConnect.getConnection();
            Statement statement = connection.createStatement();
    	    
    	    
    	    statement.execute("select * from systemusers;"); 
            rs = statement.getResultSet();
    	    
    	    while(rs.next()){
    	    	 one = new Users();
                 one.setId(rs.getInt("id"));
                 one.setUsername(rs.getString("username"));
                 one.setPassword(rs.getString("password"));
                 user.add(one);
                 one =null;
                  }                
            statement.close();
       
    	   
    	     

            
    	    
    	} catch (SQLException ex) {
    	 
    	    System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());
    	}
    	return user;
  }
                 
 
 
	public static void main(String[] args) {
			new ShowingUsers();
	}

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("upgrade")) {
        this.dispose();
        new ShowingUsers();
       
    }else if(e.getActionCommand().equals("upuser")){
        new UpagradeUserPanels(id_user);
    }
    else if(e.getActionCommand().equals("cancel")){
        this.setVisible(false);
    }
  
    }

 public void table1MouseClicked (java.awt.event.MouseEvent evt){
    if(table.getSelectedRow() != -1){
          
            int selectRow = table.getSelectedRow();
        String id = tableModel.getValueAt(selectRow, 0).toString();
                
              
             id_user = Integer.parseInt(id);
             
       
 }

}
}
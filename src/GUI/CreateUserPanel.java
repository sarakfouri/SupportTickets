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
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Sara
 */
public class CreateUserPanel extends JFrame implements ActionListener  {
    JTextField username = null;
    JTextField password = null;
    JRadioButton admin;
    JRadioButton support;
    JRadioButton manager;
    String type;
    String[] choices = { "Support","Manager", "Admin"};
    JComboBox<String> cb = new JComboBox<String>(choices);
    
    public CreateUserPanel() {
       setSize(500, 150);
        setVisible(true);

        this.setLayout(new BorderLayout());
        JPanel top = new JPanel();
        JLabel un = new JLabel("Username");
        top.add(un);
        username = new JTextField(10);
        top.add(username);
        JPanel cents= new JPanel();
        JLabel pw = new JLabel("Password");
        cents.add(pw);
        password = new JTextField(10);
        cents.add(password);
        this.add(top, BorderLayout.PAGE_START);
        // add JRadio
        
        JLabel pt = new JLabel("Type");
        cents.add(pt);
        cents.add(cb);
        this.add(cents, BorderLayout.CENTER);
        //button create
        JPanel ends = new JPanel();
        JButton create = new JButton("Create User"); 
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
    public static void main (String[] args){
        new CreateUserPanel();
    }
              
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("create")) {
            if (password.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter password");
            }
         
            if(username.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter username");
            }
           type = (String) cb.getSelectedItem();
     
        // Attempt to connect to database
        try {

            Connection connection = DBConnect.getConnection();
            Statement statement = connection.createStatement();

            // Execute insert User
            statement.execute("INSERT INTO `ticket`.`systemusers` (`username`, `password`, `type`) VALUES ('"+ username.getText() + "','" + password.getText() + "','"+ type + "');");
             JOptionPane.showMessageDialog(this, "User created");
        } catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
            Main main = new Main();
        }
        }else  if (e.getActionCommand().equals("cancel")){
                setVisible(false);
            }

        
    
        }
    }
         
 
   

    
    
    
    


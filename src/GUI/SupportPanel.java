/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sara
 */
public class SupportPanel extends JFrame implements ActionListener {
   
    String username;
    int idUser; 
    public SupportPanel(String user_name, int id_user){
       
		setSize(300,300);
		setVisible(true);
		
		this.setLayout(new GridLayout(3,2));
                
		username = user_name;
                idUser = id_user;
                JPanel top = new JPanel();
		JLabel n = new JLabel("Username: ");
		JLabel un = new JLabel(username);
		top.add(n);
                top.add(un);
                this.add(top, BorderLayout.PAGE_START);
                JPanel cents = new JPanel();
              JButton createTicket = new JButton("Create Ticket"); 
              createTicket.addActionListener(this); 
              createTicket.setActionCommand("createTicket"); 
              cents.add(createTicket); 

            JButton showTickets = new JButton("Show Tickets"); 
            showTickets.addActionListener(this); 
            showTickets.setActionCommand("showTickets"); 
            cents.add(showTickets);

            JButton password = new JButton("Change Password"); 
            password.addActionListener(this); 
            password.setActionCommand("password"); 
            cents.add(password);
            
             JButton exit = new JButton("Exit!"); 
             exit.addActionListener(this); 
             exit.setActionCommand("exit"); 
             cents.add(exit);
            this.add(cents, BorderLayout.CENTER);
		
		validate();
		repaint();
	}

    @Override
    public void actionPerformed(ActionEvent e) {
          if (e.getActionCommand().equals("createTicket")) {
          new CreateTicketPanel();
        }else if (e.getActionCommand().equals("showTickets")) {
           new ShowingTickets(); 
        } else if (e.getActionCommand().equals("password")){
            new UpgradePassword(username, idUser);
        } else if (e.getActionCommand().equals("exit")) {
            new ExitPanel();
        }
       
    }
    }
    

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

/**
 *
 * @author Sara
 */
public class ManagerPanel extends JFrame implements ActionListener {
   String username;
   int idUser;
    public ManagerPanel(String user_name, int id_user){
        	username = user_name;
                idUser = id_user;
		setSize(300,300);
		setVisible(true);        
        this.setLayout(new GridLayout(3, 2));
        JPanel top = new JPanel();
        JLabel u = new JLabel("Username: ");
        top.add(u);
        JLabel un = new JLabel(user_name.toString());
        top.add(un);
        this.add(top, BorderLayout.PAGE_START);
            JPanel cent = new JPanel();
              JButton money = new JButton("Manager Expenses"); 
              money.addActionListener(this); 
              money.setActionCommand("money"); 
              cent.add(money); 

            JButton ticketS = new JButton("Manager Ticket"); 
            ticketS.addActionListener(this); 
            ticketS.setActionCommand("ticketS"); 
            cent.add(ticketS);
            
            JButton team = new JButton("Manager Team"); 
            team.addActionListener(this); 
            team.setActionCommand("team"); 
            cent.add(team);
            
             JButton exit = new JButton("Exit!"); 
             exit.addActionListener(this); 
             exit.setActionCommand("exit"); 
              cent.add(exit);
              this.add(cent, BorderLayout.CENTER);
            
            
            validate();
            repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     if (e.getActionCommand().equals("money")){
         new MoneyTickets();
     }else if (e.getActionCommand().equals("ticketS")){
         new ManageTickets();
     }else if(e.getActionCommand().equals("team")){
      new ManagerTeam();
     }else if(e.getActionCommand().equals("exit")){
     new ExitPanel();
    }
     
    }
}
 

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
public class AdminPanel extends JFrame implements ActionListener {
            String username;
            int idUser;
    AdminPanel(String user_name, int id_user) {
        username = user_name;
        idUser = id_user;
        
        setSize(300, 300);
        setVisible(true);
        this.setLayout(new BorderLayout());
        JPanel top = new JPanel();
        JLabel u = new JLabel("Username: ");
        top.add(u);
        JLabel un = new JLabel(user_name.toString());
        top.add(un);
        this.add(top, BorderLayout.PAGE_START);
                JPanel cent = new JPanel();
              JButton createUser = new JButton("Create User"); 
              createUser.addActionListener(this); 
              createUser.setActionCommand("createUser"); 
              cent.add(createUser); 

            JButton upgrade = new JButton("Upgrade user"); 
            upgrade.addActionListener(this); 
            upgrade.setActionCommand("upgrade"); 
            cent.add(upgrade);
            
            JButton password = new JButton("Change Password"); 
            password.addActionListener(this); 
            password.setActionCommand("password"); 
            cent.add(password);
            this.add(cent, BorderLayout.CENTER);
            JPanel ends = new JPanel();
            JButton exit = new JButton("Exit!"); 
             exit.addActionListener(this); 
             exit.setActionCommand("exit"); 
              ends.add(exit);
            this.add(ends, BorderLayout.PAGE_END);
            
            validate();
            repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("createUser")) {
            new CreateUserPanel();
        }else if (e.getActionCommand().equals("upgrade")) {
            new ShowingUsers();
        }else if (e.getActionCommand().equals("password")) {
            new UpgradePassword(username, idUser);
        } else if (e.getActionCommand().equals("exit")) {
            new ExitPanel();
        }
       
    }
    



    



}

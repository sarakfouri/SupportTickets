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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Sara
 */
public class Main extends JFrame implements ActionListener {

    public Main() {

        this.setSize(400, 400);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit with x

        // Login button	
        JButton login = new JButton("Login!"); // Making the button
        login.addActionListener(this); // turn the listening on
        login.setActionCommand("login"); // give it an ID
        this.add(login); // Adding the button to the JFrame

        JButton exit = new JButton("Exit!"); // Making the button
        exit.addActionListener(this); // turn the listening on
        exit.setActionCommand("exit"); // give it an ID
        this.add(exit);

        validate();
        repaint();

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("login")) {
            new BasicLogin();
        } else if (e.getActionCommand().equals("exit")) {
            new ExitPanel();
        }
    }

}

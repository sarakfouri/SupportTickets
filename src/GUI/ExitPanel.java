/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Sara
 */
public class ExitPanel extends JFrame implements ActionListener {
  
       public ExitPanel(){
        this.setSize(250, 100);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
       

        JLabel u = new JLabel("Are​ ​you​ ​sure​ ​you​ ​want​ ​to​ ​logout?");
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
            System.exit(0);
        }
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DBConnect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Sara
 */
public class MoneyTickets extends JFrame implements ActionListener{
    ResultSet result = null;
 int ticketOpen;
 int ticketClose;
 
    public MoneyTickets(){
       this.setSize(700, 700);
       this.setLayout(new BorderLayout());
       this.setVisible(true);
       
       try {
            Connection connection = DBConnect.getConnection();
            Statement st = connection.createStatement();
          
             if(st.execute("select count(*) from tickets where closetime = 'null';")){
             
             result = st.getResultSet();
             if(result.next()){
                 ticketOpen = result.getInt("count(*)");
             }
             }
             if(st.execute("select count(*) from tickets where closetime != 'null';")){
                 result = st.getResultSet();
                 if(result.next()){
                     ticketClose = result.getInt("count(*)");
                 }
             }
             }catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
          

        }
          double tickets = ticketClose * 50;
          double ticketswill = ticketOpen * 50;
           JPanel top = new JPanel();
           JButton upgrade = new JButton("Upgrade");
           upgrade.addActionListener(this);
           upgrade.setActionCommand("upgrade");
           top.add(upgrade);
           JButton close = new JButton("Close");
           close.addActionListener(this);
           close.setActionCommand("close");
           top.add(close);
           this.add(top, BorderLayout.NORTH);
           JPanel ends = new JPanel();
           JLabel d1 = new JLabel("Money already spent: ");
           ends.add(d1);
           JTextField spend = new JTextField(20);
           spend.setText(Double.toString(tickets));
           ends.add(spend);
           JLabel d2 = new JLabel("Money pending ");
            ends.add(d2);
            JTextField pending = new JTextField(20);
            pending.setText(Double.toString(ticketswill));
            ends.add(pending);
            this.add(ends, BorderLayout.PAGE_END);
           
            DefaultCategoryDataset barChatData = new DefaultCategoryDataset();
            
            barChatData.setValue(tickets,"Money","Expenses");
            barChatData.setValue(ticketswill,"Money","Expenses Peding");
            JFreeChart barChat = ChartFactory.createBarChart3D("Costs","Money" ,"", barChatData, PlotOrientation.VERTICAL,false, true, true);
            CategoryPlot barCht = barChat.getCategoryPlot();
            barCht.setRangeGridlinePaint(Color.RED);
            ChartPanel barPanel = new ChartPanel(barChat);
            Panel panelChart = new Panel();
            panelChart.removeAll();
            panelChart.add(barPanel,BorderLayout.CENTER);
            panelChart.validate();
            this.add(panelChart);
    
    validate();
    repaint();
    }
       
       
    
    
    
    public static void main (String[]args){
        new MoneyTickets();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("upgrade")){
            dispose();
            new MoneyTickets();
    }else  if (e.getActionCommand().equals("close")){
            this.setVisible(false);
    }
    
 
}

}
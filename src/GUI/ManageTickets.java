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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;


/**
 *
 * @author Sara
 */
public class ManageTickets  extends JFrame implements ActionListener {
 ResultSet result = null;
 int ticketOpen;
 int ticketClose;
 
    public ManageTickets(){
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
           JPanel top = new JPanel();
           JButton upgrade = new JButton("Upgrade");
           upgrade.addActionListener(this);
           upgrade.setActionCommand("upgrade");
           top.add(upgrade);
           JButton close = new JButton("Close");
           close.addActionListener(this);
           close.setActionCommand("close");
           top.add(close);
           this.add(top, BorderLayout.PAGE_START);
           JPanel ends = new JPanel();
           JLabel d1 = new JLabel("Tickets Open:");
           ends.add(d1);
           JTextField open = new JTextField(10);
           open.setText(Integer.toString(ticketOpen));
           ends.add(open);
           JLabel d2 = new JLabel("Tickets Close");
           ends.add(d2);
           JTextField closet = new JTextField(20);
           closet.setText(Integer.toString(ticketClose));
           ends.add(closet);
           this.add(ends, BorderLayout.PAGE_END);
            DefaultCategoryDataset barChatData = new DefaultCategoryDataset();
            barChatData.setValue(ticketOpen,"Open","Tickets Open");
            barChatData.setValue(ticketClose,"Close","Tickets Close");
            JFreeChart barChart = ChartFactory.createBarChart3D("Tickets", "Amount", "", barChatData, PlotOrientation.VERTICAL, false, true, true);
            CategoryPlot barCht = barChart.getCategoryPlot();
            barCht.setRangeGridlinePaint(Color.RED);
            ChartPanel barPanel = new ChartPanel(barChart);
            Panel panelChart = new Panel();
            panelChart.removeAll();
            panelChart.add(barPanel,BorderLayout.CENTER);
            panelChart.validate();
            this.add(panelChart);
            validate();
           repaint();
    }
       
       
    
    
    
    public static void main (String[]args){
        new ManageTickets();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("upgrade")){
            dispose();
            new ManageTickets();
        }else if (e.getActionCommand().equals("close")){
            this.setVisible(false);
            
        }
    }
    
}

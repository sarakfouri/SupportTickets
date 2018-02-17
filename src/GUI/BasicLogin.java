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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BasicLogin extends JFrame implements ActionListener {

    JTextField username = null;
    JTextField password = null;

    public BasicLogin() {

        setSize(300, 200);
        this.setLayout(new BorderLayout());
        setVisible(true);
        JPanel top = new JPanel();
        JLabel un = new JLabel("Username:");
        top.add(un);
        username = new JTextField(15);
        top.add(username);
        this.add(top, BorderLayout.PAGE_START);
        JPanel cent = new JPanel();
        JLabel pw = new JLabel("Password:");
        cent.add(pw);
        password = new JTextField(15);
        cent.add(password);
        this.add(cent, BorderLayout.CENTER);
        JPanel ends = new JPanel();
        JButton button = new JButton("Login!");
        button.addActionListener(this);
        ends.add(button);
        this.add(ends, BorderLayout.PAGE_END);
        validate();
        repaint();
    }

    public static void main(String args[]) {
        BasicLogin dd = new BasicLogin();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ResultSet result = null;

        // Attempt to connect to database
        try {

            Connection connection = DBConnect.getConnection();
            Statement statement = connection.createStatement();

            // Execute SQL Query to check if username/password are correct.
            if (statement.execute("select * from systemusers where username = '" + username.getText() + "' and password = '" + password.getText() + "';")) {
                result = statement.getResultSet();
            }

            // Loop over results and open the appropriate user control panel based on user's account type
            if (result.next()) {

                String user_type = result.getString("type");
                String user_name = result.getString("username");
                int id_user = result.getInt("id");

                if ("Manager".equals(user_type)) {

                    new ManagerPanel(user_name, id_user);

                } else if ("Support".equals(user_type)) {

                    new SupportPanel(user_name, id_user);

                } else if ("Admin".equals(user_type)) {

                    new AdminPanel(user_name, id_user);
                } else { // If account type is not recognised

                    JOptionPane.showMessageDialog(this, "Account type is unknown.");
                    setVisible(false);

                }

            } else { // If username or password is wrong.

                JOptionPane.showMessageDialog(this, "Username and/or password wrong.");
                setVisible(false);
                
            }

        } catch (SQLException ex) { // Catching any SQL errors and warning the user.

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            JOptionPane.showMessageDialog(this, "SQL Error! Check database tables.");
            Main main = new Main();

        }
    }
}

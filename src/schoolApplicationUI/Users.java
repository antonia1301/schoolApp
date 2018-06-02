/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author Flora
 */
public class Users extends JFrame {

    private final JPanel panel, panel2;
    private final JToolBar toolBar;
    private final JLabel label1, label2, label3, label4, label5, label6, labelNum;
    private final JTextField field1, field2, field3, field4, field5;
    private final JPasswordField fieldPass;
    private final JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13;
    private Statement stmt;
    private String query;
    private Integer count, index;
    private ResultSet rs;

    public Users() throws SQLException {

        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        panel = new JPanel();
        panel2 = new JPanel();
        b1 = new JButton(new ImageIcon("resources/first16.png"));
        b2 = new JButton(new ImageIcon("resources/previous16.png"));
        b3 = new JButton(new ImageIcon("resources/next16.png"));
        b4 = new JButton(new ImageIcon("resources/last16.png"));
        b5 = new JButton(new ImageIcon("resources/add16.png"));
        b6 = new JButton(new ImageIcon("resources/cancel16.png"));
        b7 = new JButton(new ImageIcon("resources/ok16.png"));
        b8 = new JButton(new ImageIcon("resources/modify16.png"));
        b9 = new JButton(new ImageIcon("resources/delete16.png"));
        b10 = new JButton(new ImageIcon("resources/refresh16.png"));
        b11 = new JButton(new ImageIcon("resources/search16.png"));
        b12 = new JButton(new ImageIcon("resources/replicate16.png"));
        b13 = new JButton(new ImageIcon("resources/table16.png"));
        label1 = new JLabel("Serial:");
        label2 = new JLabel("Last Name:");
        label3 = new JLabel("First Name:");
        label4 = new JLabel("Email");
        label5 = new JLabel("Login");
        label6 = new JLabel("Password");
        labelNum = new JLabel();
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        field4 = new JTextField();
        field5 = new JTextField();
        fieldPass = new JPasswordField();

        field1.setEditable(false);
        retrieveQuery();
        b1.setEnabled(false);
        b2.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);

        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b1.isEnabled()) {
                    try {
                        first();
                    } catch (SQLException ex) {
                        Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });
        b2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b2.isEnabled()) {
                    try {
                        previous();
                    } catch (SQLException ex) {
                        Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });
        b3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b3.isEnabled()) {
                    try {
                        next();
                    } catch (SQLException ex) {
                        Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });
        b4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b4.isEnabled()) {
                    try {
                        last();
                    } catch (SQLException ex) {
                        Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });

        toolBar.add(b1);
        toolBar.addSeparator();
        toolBar.add(b2);
        toolBar.addSeparator();
        toolBar.add(b3);
        toolBar.addSeparator();
        toolBar.add(b4);
        toolBar.addSeparator();
        toolBar.add(b5);
        toolBar.addSeparator();
        toolBar.add(b6);
        toolBar.addSeparator();
        toolBar.add(b7);
        toolBar.addSeparator();
        toolBar.add(b8);
        toolBar.addSeparator();
        toolBar.add(b9);
        toolBar.addSeparator();
        toolBar.add(b10);
        toolBar.addSeparator();
        toolBar.add(b11);
        toolBar.addSeparator();
        toolBar.add(b12);
        toolBar.addSeparator();
        toolBar.add(b13);

        panel2.add(labelNum);

        panel.setBorder(BorderFactory.createTitledBorder("Users"));
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1)
                        .addComponent(label2)
                        .addComponent(label3)
                        .addComponent(label4)
                        .addComponent(label5)
                        .addComponent(label6))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(field1)
                        .addComponent(field2)
                        .addComponent(field3)
                        .addComponent(field4)
                        .addComponent(field5)
                        .addComponent(fieldPass))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(field1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(field2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(field3))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(field4))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5)
                        .addComponent(field5))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label6)
                        .addComponent(fieldPass))
        );

        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        setSize(525, 300);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void retrieveQuery() throws SQLException {

        stmt = null;
        query = "SELECT * FROM users";

        try {
            stmt = MainDialog.conn.createStatement();
            rs = stmt.executeQuery(query);

            rs.last();

            count = rs.getInt("idusers");

            first();

        } catch (SQLException e) {

        }

    }

    private void first() throws SQLException {

        rs.first();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("login"));
        field3.setText(rs.getString("password"));
        field4.setText(rs.getString("email"));
        field5.setText(rs.getString("lastname"));
        fieldPass.setText(rs.getString("firstname"));
        index = rs.getInt("idusers");

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    private void next() throws SQLException {

        rs.next();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("login"));
        field3.setText(rs.getString("password"));
        field4.setText(rs.getString("email"));
        field5.setText(rs.getString("lastname"));
        fieldPass.setText(rs.getString("firstname"));
        index = rs.getInt("idusers");

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    private void previous() throws SQLException {

        rs.previous();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("login"));
        field3.setText(rs.getString("password"));
        field4.setText(rs.getString("email"));
        field5.setText(rs.getString("lastname"));
        fieldPass.setText(rs.getString("firstname"));
        index = rs.getInt("idusers");

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    private void last() throws SQLException {

        rs.last();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("login"));
        field3.setText(rs.getString("password"));
        field4.setText(rs.getString("email"));
        field5.setText(rs.getString("lastname"));
        fieldPass.setText(rs.getString("firstname"));
        index = rs.getInt("idusers");

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    private void checkEnabled() {
        b6.setEnabled(false);
        b7.setEnabled(false);
        if (index == 1) {
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(true);
            b4.setEnabled(true);
        } else if (index > 1 && index < count) {
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            b4.setEnabled(true);

        } else if (index == count) {
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(false);
            b4.setEnabled(false);

        }
    }

}

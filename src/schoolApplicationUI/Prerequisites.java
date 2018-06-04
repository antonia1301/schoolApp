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
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author Flora
 */
public class Prerequisites extends JFrame {

    private final JPanel panel;
    private final JPanel panel2;
    private final JToolBar toolBar;
    private final JLabel label1, label2, labelNum;
    private final JTextField field1, field2;
    private final JButton b1, b2, b3, b4, b5, b6, b7, b9, b11;
    private final Statement stmt;
    private String query, idText, idText1;
    private Integer count, index, idlessons, idNum, idNum1, idlesson, idlesson1;
    private ResultSet rs;
    private final GroupLayout layout;
    private java.util.List<Integer> list;
    private final JComboBox<String> combo1, combo2;

    public Prerequisites() throws SQLException {

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
        b9 = new JButton(new ImageIcon("resources/delete16.png"));
        b11 = new JButton(new ImageIcon("resources/search16.png"));
        label1 = new JLabel("Required Lesson ID:");
        label2 = new JLabel("Requiring Lesson ID:");
        labelNum = new JLabel();
        field1 = new JTextField();
        field2 = new JTextField();
        combo1 = new JComboBox<>();
        combo2 = new JComboBox<>();
        stmt = MainDialog.conn.createStatement();
        query = "SELECT * FROM prerequisite";

        retrieveQuery();
       
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
        b5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b5.isEnabled()) {
                    add();
                }

            }

        });
        b6.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b6.isEnabled()) {
                    try {
                        disagree();
                    } catch (SQLException ex) {
                        Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });
        b7.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b7.isEnabled()) {
                    try {
                        agree();
                    } catch (SQLException ex) {
                        Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });

        b9.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b9.isEnabled()) {
                    remove();
                }

            }

        });
        b11.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b11.isEnabled()) {
                    search();
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
        toolBar.add(b9);
        toolBar.addSeparator();
        toolBar.add(b11);

        panel2.add(labelNum);

        panel.setBorder(BorderFactory.createTitledBorder("Users"));
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1)
                        .addComponent(label2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(field1)
                        .addComponent(field2))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(field1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(field2))
        );

        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        setSize(410, 300);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    protected void retrieveQuery() throws SQLException {

        try {

            rs = stmt.executeQuery(query);

            first();

        } catch (SQLException e) {

        }

    }

    protected void first() throws SQLException {

        rs.first();

        field1.setText(rs.getString("lesson_idlesson"));
        field2.setText(rs.getString("lesson_idlesson1"));
        index = 1;

        count = 1;
        rs.first();
        while (rs.next()) {
            count = count + 1;
        }

        rs.first();

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    protected void next() throws SQLException {

        rs.next();

        field1.setText(rs.getString("lesson_idlesson"));
        field2.setText(rs.getString("lesson_idlesson1"));
        index = index + 1;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    protected void previous() throws SQLException {

        rs.previous();

        field1.setText(rs.getString("lesson_idlesson"));
        field2.setText(rs.getString("lesson_idlesson1"));
        index = index - 1;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    protected void last() throws SQLException {

        rs.last();

        field1.setText(rs.getString("lesson_idlesson"));
        field2.setText(rs.getString("lesson_idlesson1"));
        index = count;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    protected void add() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b9.setEnabled(false);
        b11.setEnabled(false);
        panel.setBorder(BorderFactory.createTitledBorder("Add a Prerequisite"));

       
        changeLayout(0);

        query = "select idlesson from lesson where idlesson not in(select lesson_idlesson from prerequisite\n"
                + "union \n"
                + "select lesson_idlesson1 from prerequisite);";

        try {
            rs = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                combo1.removeAllItems();
                combo1.addItem("No lesson availiable");
                b7.setEnabled(false);

            } else {

                while (rs.next()) {
                    combo1.addItem(String.valueOf(rs.getString("idlesson")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
        }

        query = "select idlesson from lesson where idlesson not in(select lesson_idlesson1 from prerequisite);";

        try {
            rs = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                combo2.removeAllItems();
                combo2.addItem("No lesson availiable");
                b7.setEnabled(false);

            } else {

                while (rs.next()) {
                    combo2.addItem(String.valueOf(rs.getString("idlesson")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
        }

        idlessons = count + 1;

    }

    protected void remove() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b9.setEnabled(false);
        b11.setEnabled(false);
        panel.setBorder(BorderFactory.createTitledBorder("Remove this Prerequisite"));

        idlessons = 0;
    }

    protected void search() {

        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b9.setEnabled(false);
        b11.setEnabled(false);
        field2.setText("1");
        field1.setEditable(false);
        field2.setEditable(true);

        panel.setBorder(BorderFactory.createTitledBorder("Search a Prerequisite"));

        idlessons = - 1;

    }

    protected void agree() throws SQLException {

        if (combo1.isShowing() && combo2.isShowing()) {
            idText = String.valueOf(combo1.getSelectedItem());
            idText1 = String.valueOf(combo2.getSelectedItem());
            idNum = Integer.valueOf(idText);
            idNum1 = Integer.valueOf(idText1);
        }
        idlesson = Integer.valueOf(field1.getText());
        idlesson1 = Integer.valueOf(field2.getText());

        if (idlessons > count) {
            if (idNum != idNum1) {

                query = "INSERT INTO prerequisite (lesson_idlesson,lesson_idlesson1 ) VALUES (" + idNum1 + ", " + idNum + ");";
                stmt.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Successful Insertion!");
            } else {
                JOptionPane.showMessageDialog(null, "Not a valid Option!");

            }

        } else if (idlessons == 0) {
           
            query = "DELETE FROM prerequisite WHERE lesson_idlesson1 = " + idlesson1 + " ;";

            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Successful Removal!");

        } else if (idlessons == -1) {
            list = new ArrayList<>();

            query = "select lesson_idlesson1 from prerequisite;";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getInt("lesson_idlesson1"));
            }
            if (list.contains(Integer.valueOf(idlesson1))) {
                query = "SELECT * FROM prerequisite WHERE lesson_idlesson1 = " + idlesson1 + ";";

                rs = stmt.executeQuery(query);
                rs.first();

                String result = "Required Lesson ID: " + rs.getString("lesson_idlesson") + System.lineSeparator()
                        + "Requiring Lesson ID: " + idlesson1 + "";

                JOptionPane.showMessageDialog(null, result);
            } else {
                JOptionPane.showMessageDialog(null, "There is not a Prerequisite with this Serial!");
            }

        }

        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b9.setEnabled(true);
        b11.setEnabled(true);
        changeLayout(1);
        combo1.removeAllItems();
        combo2.removeAllItems();
        field1.setEditable(true);
        field2.setEditable(true);

        query = "SELECT * FROM prerequisite";
        retrieveQuery();

    }

    protected void disagree() throws SQLException {

        query = "SELECT * FROM prerequisite";

        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b9.setEnabled(true);
        b11.setEnabled(true);
        changeLayout(1);
        combo1.removeAllItems();
        combo2.removeAllItems();
        field1.setEditable(true);
        field2.setEditable(true);
        retrieveQuery();

    }

    protected void checkEnabled() {
        b6.setEnabled(false);
        b7.setEnabled(false);
        if (index == 0 || index == 1 && count == 1) {
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);

        } else if (index == 1) {
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(true);
            b4.setEnabled(true);
        } else if (index > 1 && index < count) {
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            b4.setEnabled(true);

        } else if (Objects.equals(index, count) && index != 0) {
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(false);
            b4.setEnabled(false);

        }

    }

    private void changeLayout(Integer a) {
        if (a == 0) {
            panel.remove(field2);
            panel.remove(field1);
            panel.add(combo1);
            panel.add(combo2);

            layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(label1)
                            .addComponent(label2))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(combo1)
                            .addComponent(combo2))
            );
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(combo1))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(combo2))
            );
        } else if (a == 1) {
            panel.remove(combo1);
            panel.remove(combo2);
            panel.add(field2);
            panel.add(field1);

            layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(label1)
                            .addComponent(label2)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(field1)
                            .addComponent(field2)
                    )
            );
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(field1))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(field2))
            );
        }
    }

    private void getSerial() throws SQLException {

    }
}

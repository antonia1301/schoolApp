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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author Antonia
 */
public final class Lessons extends JFrame {

    private final JPanel panel;
    private final JPanel panel2;
    private final JToolBar toolBar;
    private final JLabel label1, label2, label3, labelNum;
    private final JTextField field1, field2, field3;
    private final JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b11;
    private final Statement stmt;
    private String query, name, description, idText;
    private Integer count, index, idlessons, max;
    private ResultSet rs;
    private final GroupLayout layout;
    private java.util.List<Integer> list;

    /**
     *
     * @throws SQLException
     */
    public Lessons() throws SQLException {
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
        b11 = new JButton(new ImageIcon("resources/search16.png"));
        label1 = new JLabel("Lesson ID:");
        label2 = new JLabel("Lesson Name:");
        label3 = new JLabel("Lesson Description:");
        labelNum = new JLabel();
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        stmt = MainDialog.conn.createStatement();
        query = "SELECT * FROM lesson";

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
        b8.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (b8.isEnabled()) {
                    modify();
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
        toolBar.add(b8);
        toolBar.addSeparator();
        toolBar.add(b9);
        toolBar.addSeparator();
        toolBar.add(b11);

        panel2.add(labelNum);

        panel.setBorder(BorderFactory.createTitledBorder("Lessons"));
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1)
                        .addComponent(label2)
                        .addComponent(label3))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(field1)
                        .addComponent(field2)
                        .addComponent(field3))
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
        );

        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        setSize(410, 300);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    /**
     *
     * @throws SQLException
     */
    private void retrieveQuery() throws SQLException {

        try {

            rs = stmt.executeQuery(query);

            first();

        } catch (SQLException e) {

        }

    }

    /**
     *
     * @throws SQLException
     */
    private void first() throws SQLException {

        rs.first();

        field1.setText(rs.getString("idlesson"));
        field2.setText(rs.getString("name"));
        field3.setText(rs.getString("description"));
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

    /**
     *
     * @throws SQLException
     */
    private void next() throws SQLException {

        rs.next();

        field1.setText(rs.getString("idlesson"));
        field2.setText(rs.getString("name"));
        field3.setText(rs.getString("description"));
        index = index + 1;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    /**
     *
     * @throws SQLException
     */
    private void previous() throws SQLException {

        rs.previous();

        field1.setText(rs.getString("idlesson"));
        field2.setText(rs.getString("name"));
        field3.setText(rs.getString("description"));
        index = index - 1;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    /**
     *
     * @throws SQLException
     */
    private void last() throws SQLException {

        rs.last();

        field1.setText(rs.getString("idlesson"));
        field2.setText(rs.getString("name"));
        field3.setText(rs.getString("description"));
        index = count;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    /**
     *
     */
    private void add() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(false);
        b9.setEnabled(false);
        b11.setEnabled(false);
        field1.setText("");
        field2.setText("");
        field3.setText("");
        panel.setBorder(BorderFactory.createTitledBorder("Add a Lesson"));

        idlessons = count + 1;

    }

    /**
     *
     */
    private void remove() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(false);
        b9.setEnabled(false);
        b11.setEnabled(false);
        int idpre = Integer.valueOf(field1.getText());

        panel.setBorder(BorderFactory.createTitledBorder("Remove this Lesson"));

        idlessons = 0;
        // pernoume to text apo to proto field diladi to id, an auto einai iso me 1,2,3,4 apogaoreuoume na ginei agree,diladi diagrafi gt
        //ta mathimata autoi einai autoi p itan eksarxis stin vasi sto teachers, kai i idia i vasi exei graftei etsi oste na apagoreuete i diagrafi tous
        int a = Integer.valueOf(field1.getText());
        if (a == 1 || a == 2 || a == 3 || a == 4) {
            b7.setEnabled(false);
        }
        //episis an kapoio lesson einai sta prerequsuite den ginete na diagraftei
        list = new ArrayList<>();

        query = "SELECT idlesson FROM schooldb.lesson left join schooldb.prerequisite on idlesson = lesson_idlesson where idlesson = lesson_idlesson\n"
                + "union\n"
                + "SELECT idlesson FROM schooldb.lesson left join schooldb.prerequisite on idlesson = lesson_idlesson1 where idlesson = lesson_idlesson1;";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getInt("idlesson"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (list.contains(idpre)) {
            b7.setEnabled(false);

        }
    }

    /**
     *
     */
    private void modify() {

        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(false);
        b9.setEnabled(false);
        b11.setEnabled(false);
        panel.setBorder(BorderFactory.createTitledBorder("Update this Lesson"));

        idlessons = -5;

    }

    /**
     *
     */
    private void search() {

        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(false);
        b9.setEnabled(false);
        b11.setEnabled(false);
        field1.setText("1");
        field2.setText("");
        field3.setText("");
        field1.setEditable(true);
        field2.setEditable(false);
        field3.setEditable(false);

        panel.setBorder(BorderFactory.createTitledBorder("Search a Lesson"));

        idlessons = - 1;

    }

    /**
     *
     * @throws SQLException
     */
    private void agree() throws SQLException {

        idText = field1.getText();
        name = field2.getText();
        description = field3.getText();

        if (idlessons > count) {
            maxID();
            query = "INSERT INTO lesson (idlesson, name, description) VALUES (" + max + ", '" + name
                    + "', '" + description + "');";

            stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Successful Insertion!");

        } else if (idlessons == 0) {
            query = "DELETE FROM lesson WHERE idlesson = " + idText + ";";

            stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Successful Removal!");

        } else if (idlessons == -1) {
            list = new ArrayList<>();

            query = "select idlesson from lesson;";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getInt("idlesson"));
            }
            if (list.contains(Integer.valueOf(idText))) {
                query = "SELECT * FROM lesson WHERE idlesson = " + idText + ";";

                rs = stmt.executeQuery(query);
                rs.first();

                String result = "Lesson ID: " + idText + System.lineSeparator()
                        + "Lesson Name: " + rs.getString("name") + System.lineSeparator()
                        + "Lesson Description: " + rs.getString("description") + "";

                JOptionPane.showMessageDialog(null, result);
            } else {
                JOptionPane.showMessageDialog(null, "There is not a Lesson with this Serial!");
            }

        } else {
            idlessons = Integer.valueOf(idText);
            query = "UPDATE lesson SET  name = '" + name
                    + "', description = '" + description
                    + "' WHERE idlesson = " + idlessons + ";";

            stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Successful Update!");

        }

        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b11.setEnabled(true);
        field1.setEditable(false);
        field2.setEditable(true);
        field3.setEditable(true);

        query = "SELECT * FROM lesson";
        retrieveQuery();

    }

    /**
     *
     * @throws SQLException
     */
    private void disagree() throws SQLException {

        query = "SELECT * FROM lesson";

        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b11.setEnabled(true);
        field1.setEditable(false);
        field2.setEditable(true);
        field3.setEditable(true);
        retrieveQuery();

    }

    /**
     *
     */
    private void checkEnabled() {
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

    /**
     *
     * @throws SQLException
     */
    private void maxID() throws SQLException {
        query = "select MAX(idlesson) from lesson";
        rs = stmt.executeQuery(query);
        rs.first();
        max = rs.getInt("MAX(idlesson)") + 1;

    }
}

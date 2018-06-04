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
public class Teachings extends JFrame {

    private final JPanel panel;
    private final JPanel panel2;
    private final JToolBar toolBar;
    private final JLabel label1, label2, label3, labelNum;
    private final JTextField field1, field2, field3;
    private final JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b11;
    private final Statement stmt;
    private String query, idText, idText1;
    private Integer count, index, idlessons, idNum, idNum1, idteacher, idlesson, idteaches, max;
    private ResultSet rs;
    private final GroupLayout layout;
    private java.util.List<Integer> list;
    private final JComboBox<String> combo1, combo2;

    public Teachings() throws SQLException {

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
        label1 = new JLabel("Teaching ID:");
        label2 = new JLabel("Teacher ID:");
        label3 = new JLabel("Lesson ID:");
        labelNum = new JLabel();
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        combo1 = new JComboBox<>();
        combo2 = new JComboBox<>();
        stmt = MainDialog.conn.createStatement();
        query = "SELECT * FROM teaches";

        retrieveQuery();

        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(true);

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

        panel.setBorder(BorderFactory.createTitledBorder("Teaching"));
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

    protected void retrieveQuery() throws SQLException {

        try {

            rs = stmt.executeQuery(query);

            first();

        } catch (SQLException e) {

        }

    }

    protected void first() throws SQLException {

        rs.first();

        field1.setText(rs.getString("idteaches"));
        field3.setText(rs.getString("lesson_idlesson"));
        field2.setText(rs.getString("teacher_idteacher"));

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

        field1.setText(rs.getString("idteaches"));
        field3.setText(rs.getString("lesson_idlesson"));
        field2.setText(rs.getString("teacher_idteacher"));
        index = index + 1;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    protected void previous() throws SQLException {

        rs.previous();

        field1.setText(rs.getString("idteaches"));
        field3.setText(rs.getString("lesson_idlesson"));
        field2.setText(rs.getString("teacher_idteacher"));
        index = index - 1;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    protected void last() throws SQLException {

        rs.last();

        field1.setText(rs.getString("idteaches"));
        field3.setText(rs.getString("lesson_idlesson"));
        field2.setText(rs.getString("teacher_idteacher"));
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
        b8.setEnabled(false);
        b9.setEnabled(false);
        b11.setEnabled(false);
        panel.setBorder(BorderFactory.createTitledBorder("Add a Teaching"));
        field1.setEditable(false);

        changeLayout(0);

        query = "select idteacher from teacher;";

        try {
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                combo1.addItem(String.valueOf(rs.getString("idteacher")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
        }

        query = "select idlesson from lesson where idlesson not in(select lesson_idlesson from teaches);";

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

    protected void modify() {

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
        panel.setBorder(BorderFactory.createTitledBorder("Update this Teachng"));
        field1.setEditable(false);
        field3.setEditable(false);
        changeLayout(2);

        query = "select idteacher from teacher;";

        try {
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                combo2.addItem(String.valueOf(rs.getString("idteacher")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
        }

        idlessons = -5;

    }

    protected void remove() {
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
        panel.setBorder(BorderFactory.createTitledBorder("Remove this Teaching"));
        field1.setEditable(false);

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
        b8.setEnabled(false);
        b9.setEnabled(false);
        b11.setEnabled(false);
        field1.setText("1");
        field3.setText("");
        field2.setText("");
        field3.setEditable(false);
        field2.setEditable(false);

        panel.setBorder(BorderFactory.createTitledBorder("Search a Teaching"));

        idlessons = - 1;

    }

    protected void agree() throws SQLException {

        if (combo1.isShowing()) {
            idText = String.valueOf(combo1.getSelectedItem());
            idNum = Integer.valueOf(idText);

        }
        if (combo2.isShowing()) {
            idText1 = String.valueOf(combo2.getSelectedItem());
            idNum1 = Integer.valueOf(idText1);
        }
        idteaches = Integer.valueOf(field1.getText());
        if (field2.isEditable() && field3.isEditable()) {
            idteacher = Integer.valueOf(field2.getText());
            idlesson = Integer.valueOf(field3.getText());
        }

        if (idlessons > count) {
            maxID();
            query = "INSERT INTO teaches (idteaches, lesson_idlesson,teacher_idteacher ) VALUES (" + max + ", " + idNum1 + ", " + idNum + ");";
            stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Successful Insertion!");

        } else if (idlessons == 0) {

            query = "DELETE FROM teaches WHERE idteaches = " + idteaches + " ;";

            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Successful Removal!");

        } else if (idlessons == -1) {
            list = new ArrayList<>();

            query = "select idteaches from teaches;";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getInt("idteaches"));
            }
            if (list.contains(Integer.valueOf(idteaches))) {
                query = "SELECT * FROM teaches WHERE idteaches = " + idteaches + ";";

                rs = stmt.executeQuery(query);
                rs.first();

                String result = "Teaching ID: " + idteaches + System.lineSeparator()
                        + "Lesson ID: " + rs.getInt("lesson_idlesson") + System.lineSeparator()
                        + "Teacher ID:" + rs.getInt("teacher_idteacher") + "";

                JOptionPane.showMessageDialog(null, result);
            } else {
                JOptionPane.showMessageDialog(null, "There is not a teaching with this Serial!");
            }

        } else {
            idlessons = index;
            query = "UPDATE teaches SET  teacher_idteacher = " + idNum1
                    + " WHERE idteaches = " + idteaches + ";";

            stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Successful Update!");

        }

        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b11.setEnabled(true);
        changeLayout(1);
        combo1.removeAllItems();
        combo2.removeAllItems();
        field1.setEditable(true);
        field2.setEditable(true);
        field3.setEditable(true);
        query = "SELECT * FROM teaches";
        retrieveQuery();

    }

    protected void disagree() throws SQLException {

        query = "SELECT * FROM teaches";

        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b11.setEnabled(true);
        changeLayout(1);
        combo1.removeAllItems();
        combo2.removeAllItems();
        field1.setEditable(true);
        field2.setEditable(true);
        field3.setEditable(true);
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
        if (null != a) {
            switch (a) {
                case 0:
                    panel.remove(field2);
                    panel.remove(field3);
                    panel.add(combo1);
                    panel.add(combo2);
                    layout.setHorizontalGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label1)
                                    .addComponent(label2)
                                    .addComponent(label3)
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(field1)
                                    .addComponent(combo1)
                                    .addComponent(combo2))
                    );
                    layout.setVerticalGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1)
                                    .addComponent(field1))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2)
                                    .addComponent(combo1))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(combo2))
                    );
                    break;
                case 1:
                    panel.remove(combo1);
                    panel.remove(combo2);
                    panel.add(field2);
                    panel.add(field3);
                    layout.setHorizontalGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label1)
                                    .addComponent(label2)
                                    .addComponent(label3)
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(field1)
                                    .addComponent(field2)
                                    .addComponent(field3)
                            )
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
                    break;
                case 2:
                    panel.remove(field2);
                    panel.add(combo2);
                    layout.setHorizontalGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label1)
                                    .addComponent(label2)
                                    .addComponent(label3)
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(field1)
                                    .addComponent(combo2)
                                    .addComponent(field3)
                            )
                    );
                    layout.setVerticalGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label1)
                                    .addComponent(field1))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2)
                                    .addComponent(combo2))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(field3))
                    );
                    break;
                default:
                    break;
            }
        }
    }

    protected void maxID() throws SQLException {
        query = "select MAX(idteaches) from teaches";
        rs = stmt.executeQuery(query);
        rs.first();
        max = rs.getInt("MAX(idteaches)") + 1;

    }
}

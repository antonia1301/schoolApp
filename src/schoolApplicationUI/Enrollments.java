/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;
//imports
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
 * @author Antonia
 */
public class Enrollments extends JFrame {

    private final JPanel panel;
    private final JPanel panel2;
    private final JToolBar toolBar;
    private final JLabel label1, label2, label3, label4, label5, labelNum;
    private final JTextField field1, field2, field3, field4, field5;
    private final JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b11;
    private final Statement stmt;
    private String query, idenrollment, lessonid, studentid, grade, status;
    private Integer count, index, idenroll, idNum, idNum1;
    private ResultSet rs;
    private final GroupLayout layout;
    private java.util.List<Integer> list;
    private final JComboBox<String> combo1, combo2;

    /**
     *
     * @throws SQLException
     */
    public Enrollments() throws SQLException {

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
        label1 = new JLabel("Enrollment ID:");
        label2 = new JLabel("Lesson ID:");
        label3 = new JLabel("Student ID:");
        label4 = new JLabel("Grade:");
        label5 = new JLabel("Status:");
        labelNum = new JLabel();
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        field4 = new JTextField();
        field5 = new JTextField();
        combo1 = new JComboBox<>();
        combo2 = new JComboBox<>();
        stmt = MainDialog.conn.createStatement();
        count=0;
        query = "SELECT * FROM enrollment";

        field1.setEditable(false);
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
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
        //ftiaxnoume to toolbar
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

        panel.setBorder(BorderFactory.createTitledBorder("Enrollment"));
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                //ftiaxnoume ta orizontia group
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1)
                        .addComponent(label2)
                        .addComponent(label3)
                        .addComponent(label4)
                        .addComponent(label5))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(field1)
                        .addComponent(field2)
                        .addComponent(field3)
                        .addComponent(field4)
                        .addComponent(field5))
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

        field1.setText(rs.getString("idenrollment"));
        field2.setText(rs.getString("lesson_idlesson"));
        field3.setText(rs.getString("student_idstudent"));
        field4.setText(rs.getString("grade"));
        field5.setText(rs.getString("status"));
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

        field1.setText(rs.getString("idenrollment"));
        field2.setText(rs.getString("lesson_idlesson"));
        field3.setText(rs.getString("student_idstudent"));
        field4.setText(rs.getString("grade"));
        field5.setText(rs.getString("status"));
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

        field1.setText(rs.getString("idenrollment"));
        field2.setText(rs.getString("lesson_idlesson"));
        field3.setText(rs.getString("student_idstudent"));
        field4.setText(rs.getString("grade"));
        field5.setText(rs.getString("status"));
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

        field1.setText(rs.getString("idenrollment"));
        field2.setText(rs.getString("lesson_idlesson"));
        field3.setText(rs.getString("student_idstudent"));
        field4.setText(rs.getString("grade"));
        field5.setText(rs.getString("status"));
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
        field4.setText("0");
        field5.setText("fail");
        panel.setBorder(BorderFactory.createTitledBorder("Add an Enrollment"));
        changeLayout(0);
        //dialegoume mathimata
        query = "select idlesson from lesson;";

        try {
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                combo1.addItem(String.valueOf(rs.getString("idlesson")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
        }
        //dialegoume tous mathites
        query = "select idstudent from student;";

        try {
            rs = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                combo2.removeAllItems();
                combo2.addItem("No Student availiable");
                b7.setEnabled(false);

            } else {

                while (rs.next()) {
                    combo2.addItem(String.valueOf(rs.getString("idstudent")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
        }

        idenroll = count + 1;

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
        panel.setBorder(BorderFactory.createTitledBorder("Remove this Enrollment"));

        idenroll = 0;
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
        panel.setBorder(BorderFactory.createTitledBorder("Update this Enrollment"));
        field2.setEditable(false);
        field3.setEditable(false);
        idenroll = -5;

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
        field4.setText("");
        field5.setText("");
        field1.setEditable(true);
        field2.setEditable(false);
        field3.setEditable(false);
        field4.setEditable(false);
        field5.setEditable(false);

        panel.setBorder(BorderFactory.createTitledBorder("Search an Enrollment"));

        idenroll = - 1;

    }

    /**
     *
     * @throws SQLException
     */
    private void agree() throws SQLException {

        idenrollment = field1.getText();
        lessonid = field2.getText();
        studentid = field3.getText();
        grade = field4.getText();
        status = field5.getText();
        if (combo1.isShowing()) {
            lessonid = String.valueOf(combo1.getSelectedItem());
            idNum = Integer.valueOf(lessonid);
        }
        if (combo2.isShowing()) {
            studentid = String.valueOf(combo2.getSelectedItem());
            idNum1 = Integer.valueOf(studentid);
        }

        if (idenroll > count) {

            //an den to exei parei o foititis idi
            //vriskoume to id to id ton foititon p xei parei to mathima (an iparxei)
            query = "select student_idstudent from enrollment where lesson_idlesson = " + lessonid + ";";
            rs = stmt.executeQuery(query);
            list = new ArrayList<>();
            //ta vazoume se mia lista
            while (rs.next()) {
                list.add(rs.getInt("student_idstudent"));
            }
            // an auti i lista den exei mesa to id tou foititi p thelei na parei t mathima
            if (!list.contains(Integer.valueOf(studentid))) {

                //an to lesson den exei prerequisite
                query = "select lesson_idlesson from prerequisite where lesson_idlesson1=" + idNum + ";";
                rs = stmt.executeQuery(query);

                if (!rs.isBeforeFirst()) {

                    query = "INSERT INTO enrollment (idenrollment, lesson_idlesson, student_idstudent, grade, status) VALUES (" + idenroll + ", " + lessonid
                            + ", " + studentid + ", " + grade + ", '" + status + "' );";

                    stmt.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Successful Insertion!");
                } //an exei omos prerequisite
                else {
                    //vriskoume poio mathima einai prereq
                    rs.first();
                    int prereq = rs.getInt("lesson_idlesson");

                    //vriskoume ton vathmo foititi sto mathima prereq an iparxei
                    query = "select grade from enrollment where lesson_idlesson=" + prereq + " and student_idstudent= " + studentid + ";";
                    //exei vathmo?
                    rs = stmt.executeQuery(query);
                    //an den exei
                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "This Student has not enrolled and passed the required lesson " + prereq + " "
                                + "for lesson " + lessonid + "!");
                    } else {
                        //an exei vathmo
                        rs.first();
                        int grade1 = rs.getInt("grade");

                        //an einai megaliteros tou 5 diladi to exei perasei
                        if (grade1 >= 5) {
                            // ara dilonei to mathima kanonika
                            query = "INSERT INTO enrollment (idenrollment, lesson_idlesson, student_idstudent, grade, status) VALUES (" + idenroll + ", " + lessonid
                                    + ", " + studentid + ", " + grade + ", '" + status + "' );";

                            stmt.executeUpdate(query);

                            JOptionPane.showMessageDialog(null, "Successful Insertion!");
                        } //an exei parei kato apo 5
                        else {
                            // enimeronoume oti den exei perasei to aparaitito mathima
                            JOptionPane.showMessageDialog(null, "This Student has not passed( grade: " + grade + " ) the required lesson " + prereq + " "
                                    + "for lesson " + lessonid + "!");
                        }
                    }

                }

            } //an i lista exei to id tou foititi, simainei to exei parei ara eidopoioume oti den ginete na to ksanaparei
            else {
                JOptionPane.showMessageDialog(null, "Student has already enrolled to this lesson!");
            }

        } else if (idenroll == 0) {
            query = "DELETE FROM enrollment WHERE idenrollment = " + idenrollment + ";";

            stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Successful Removal!");

        } else if (idenroll == -1) {

            query = "select idenrollment from enrollment;";
            rs = stmt.executeQuery(query);
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("idenrollment"));
            }
            if (list.contains(Integer.valueOf(idenrollment))) {
                query = "SELECT * FROM enrollment WHERE idenrollment = " + idenrollment + ";";

                rs = stmt.executeQuery(query);
                rs.first();

                String result = "Enrollment ID: " + idenrollment + System.lineSeparator()
                        + "Lesson ID: " + rs.getInt("lesson_idlesson") + System.lineSeparator()
                        + "Student ID: " + rs.getInt("student_idstudent") + System.lineSeparator()
                        + "Grade: " + rs.getString("grade") + System.lineSeparator()
                        + "Status: " + rs.getString("status") + "";
                JOptionPane.showMessageDialog(null, result);
            } else {
                JOptionPane.showMessageDialog(null, "There is not an Enrollment with this ID!");
            }

        } else {
            idenroll = index;
            query = "UPDATE enrollment SET  grade = " + grade
                    + ", status = '" + status + "' WHERE idenrollment = " + idenrollment + ";";

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
        field4.setEditable(true);
        field5.setEditable(true);
        changeLayout(1);
        combo1.removeAllItems();
        combo2.removeAllItems();

        query = "SELECT * FROM enrollment";
        retrieveQuery();

    }

    /**
     *
     * @throws SQLException
     */
    private void disagree() throws SQLException {

        query = "SELECT * FROM enrollment";

        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b11.setEnabled(true);
        field1.setEditable(false);
        field2.setEditable(true);
        field3.setEditable(true);
        field4.setEditable(true);
        field5.setEditable(true);
        changeLayout(1);
        combo1.removeAllItems();
        combo2.removeAllItems();
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
                                    .addComponent(label4)
                                    .addComponent(label5)
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(field1)
                                    .addComponent(combo1)
                                    .addComponent(combo2)
                                    .addComponent(field4)
                                    .addComponent(field5))
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
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(field4))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label5)
                                    .addComponent(field5))
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
                                    .addComponent(label4)
                                    .addComponent(label5)
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(field1)
                                    .addComponent(field2)
                                    .addComponent(field3)
                                    .addComponent(field4)
                                    .addComponent(field5)
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
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(field4))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label5)
                                    .addComponent(field5))
                    );
                    break;

                default:
                    break;
            }
        }
    }

}

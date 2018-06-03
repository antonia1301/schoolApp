/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;

import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Flora
 */
public class Students extends Users {

    private String am, eksamino;
    private final JComboBox<String> comboBox;
    private java.util.List<Integer> list;

    public Students() throws SQLException {

        panel.setBorder(BorderFactory.createTitledBorder("Students"));
        label2.setText("User:");
        label3.setText("AM:");
        label4.setText("Semester:");
        comboBox = new JComboBox<>();
        panel.remove(label5);
        panel.remove(field5);
        panel.remove(label6);
        panel.remove(fieldPass);

        query = "SELECT * FROM users left JOIN student ON users.idusers = student.idstudent WHERE student.idstudent IS NOT NULL;";
        retrieveQuery();

    }

    @Override
    protected void retrieveQuery() throws SQLException {

        try {

            rs = stmt.executeQuery(query);
            if (rs.isBeforeFirst()) {
                first();
            } else {
                index = 0;
                count = 0;
                labelNum.setText(index + "/" + count);
                checkEnabled();
                field1.setText("");
                field2.setText("");
                field3.setText("");
                field4.setText("");

            }

        } catch (SQLException e) {

        }

    }

    @Override
    protected void first() throws SQLException {

        rs.first();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname") + ", " + rs.getString("firstname"));
        field3.setText(rs.getString("AM"));
        field4.setText(rs.getString("examino"));
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

    @Override
    protected void next() throws SQLException {

        rs.next();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname") + ", " + rs.getString("firstname"));
        field3.setText(rs.getString("AM"));
        field4.setText(rs.getString("examino"));
        index = index + 1;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    @Override
    protected void previous() throws SQLException {

        rs.previous();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname") + ", " + rs.getString("firstname"));
        field3.setText(rs.getString("AM"));
        field4.setText(rs.getString("examino"));

        index = index - 1;
        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    @Override
    protected void last() throws SQLException {

        rs.last();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname") + ", " + rs.getString("firstname"));
        field3.setText(rs.getString("AM"));
        field4.setText(rs.getString("examino"));
        index = count;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    @Override
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
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);
        b13.setEnabled(false);
        field3.setText("0");
        field4.setText("0");
        changeLayout(0);
        panel.setBorder(BorderFactory.createTitledBorder("Add a Student"));

        query = "SELECT * FROM users left join student on idusers=idstudent WHERE idstudent is NULL and idusers NOT IN (SELECT idteacher from teacher);";
        try {
            rs = stmt.executeQuery(query);
            if (!rs.isBeforeFirst()) {
                comboBox.removeAllItems();
                field1.setText("");
                comboBox.addItem("No user availiable");
                b7.setEnabled(false);

            } else {

                rs.first();
                field1.setText(rs.getString("idusers"));
                comboBox.addItem(rs.getString("lastname") + ", " + rs.getString("firstname"));

                while (rs.next()) {
                    comboBox.addItem(rs.getString("lastname") + ", " + rs.getString("firstname"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
        }

        comboBox.addItemListener((ItemEvent arg0) -> {
            try {
                getSerial();
            } catch (SQLException ex) {
                Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        idusers = count + 1;

    }

    @Override
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
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);
        b13.setEnabled(false);
        panel.setBorder(BorderFactory.createTitledBorder("Remove this Student"));

        idusers = 0;

    }

    @Override
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
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);
        b13.setEnabled(false);
        field1.setEditable(false);
        field2.setEditable(false);
        panel.setBorder(BorderFactory.createTitledBorder("Update this Student"));

        idusers = -5;

    }

    @Override
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
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);
        b13.setEnabled(false);
        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setText("");
        field1.setEditable(true);
        field2.setEditable(false);
        field3.setEditable(false);
        field4.setEditable(false);

        panel.setBorder(BorderFactory.createTitledBorder("Search a Student"));

        idusers = - 1;

    }

    @Override
    protected void agree() throws SQLException {

        idText = field1.getText();
        am = field3.getText();
        eksamino = field4.getText();

        if (idusers > count) {

            query = "INSERT INTO student (idstudent, AM, examino) VALUES (" + idText + ", '" + am
                    + "', " + eksamino + ");";

            stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Successful Insertion!");
            changeLayout(1);

        } else if (idusers == 0) {

            query = "DELETE FROM student WHERE  idstudent = " + idText + ";";

            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Successful Removal!");

        } else if (idusers == -1) {

            list = new ArrayList<>();
              
            query = "select idstudent from student;";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getInt("idstudent"));
            }

            if (list.contains(Integer.valueOf(idText)) ) {
                query = "SELECT * FROM student left JOIN users ON users.idusers = student.idstudent WHERE idstudent = " + idText + ";";

                rs = stmt.executeQuery(query);
                rs.first();

                String result = "Serial: " + idText + System.lineSeparator()
                        + "Last Name: " + rs.getString("lastname") + System.lineSeparator()
                        + "First Name: " + rs.getString("firstname") + System.lineSeparator()
                        + "AM: " + rs.getString("AM") + System.lineSeparator()
                        + "Semester: " + rs.getInt("examino") + "";

                JOptionPane.showMessageDialog(null, result);
            } else {
                JOptionPane.showMessageDialog(null, "There is not a User with this Serial!");
            }

        } else {

            idusers = index;
            query = "UPDATE student left JOIN users  ON users.idusers = student.idstudent SET AM = '" + am + "', examino = " + eksamino + " WHERE idstudent = " + idText + ";";
            stmt.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Successful Update!");

        }

        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b10.setEnabled(true);
        b11.setEnabled(true);
        b12.setEnabled(true);
        b13.setEnabled(true);
        field1.setEditable(false);
        field2.setEditable(true);
        field3.setEditable(true);
        field4.setEditable(true);
        query = "SELECT * FROM users left JOIN student ON users.idusers = student.idstudent WHERE student.idstudent IS NOT NULL;";
        retrieveQuery();

    }

    @Override
    protected void disagree() throws SQLException {

        query = "SELECT * FROM users left JOIN student ON users.idusers = student.idstudent WHERE student.idstudent IS NOT NULL;";

        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b10.setEnabled(true);
        b11.setEnabled(true);
        b12.setEnabled(true);
        b13.setEnabled(true);
        field1.setEditable(false);
        field2.setEditable(true);
        field3.setEditable(true);
        field4.setEditable(true);
        field5.setEditable(true);
        fieldPass.setEditable(true);
        changeLayout(1);
        comboBox.removeAllItems();
        retrieveQuery();

    }

    private void changeLayout(Integer a) {
        if (a == 0) {
            panel.remove(field2);
            panel.add(comboBox);

            layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(label1)
                            .addComponent(label2)
                            .addComponent(label3)
                            .addComponent(label4)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(field1)
                            .addComponent(comboBox)
                            .addComponent(field3)
                            .addComponent(field4)
                    )
            );
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(field1))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(comboBox))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(field3))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(field4))
            );
        } else if (a == 1) {
            panel.remove(comboBox);
            panel.add(field2);

            layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(label1)
                            .addComponent(label2)
                            .addComponent(label3)
                            .addComponent(label4)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(field1)
                            .addComponent(field2)
                            .addComponent(field3)
                            .addComponent(field4)
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
            );
        }
    }

    private void getSerial() throws SQLException {

        if (rs.isBeforeFirst()) {
            String s = (String) comboBox.getSelectedItem();

            String[] l;
            l = s.split(", ");

            s = l[0];
            query = "SELECT * FROM users WHERE lastname = '" + s + "';";
            rs = stmt.executeQuery(query);
            rs.first();
            field1.setText(rs.getString("idusers"));

        }
    }

}

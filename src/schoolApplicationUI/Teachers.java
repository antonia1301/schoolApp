/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;

import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Flora
 */
public class Teachers extends Users {

    private String tilefono, eidikotita, endiaferonta;

    public Teachers() throws SQLException {

        panel.setBorder(BorderFactory.createTitledBorder("Teachers"));
        label2.setText("User:");
        label3.setText("Specialization:");
        label4.setText("Interests:");
        label5.setText("Phone:");
        panel.remove(label6);
        panel.remove(fieldPass);
        query = "SELECT * FROM users left JOIN teacher ON users.idusers = teacher.idteacher WHERE teacher.idteacher IS NOT NULL;";
        retrieveQuery();

    }

    @Override
    protected void retrieveQuery() throws SQLException {

        try {

            rs = stmt.executeQuery(query);

            first();

        } catch (SQLException e) {

        }

    }

    @Override
    protected void first() throws SQLException {

        rs.first();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname") + ", " + rs.getString("firstname"));
        field3.setText(rs.getString("eidikotita"));
        field4.setText(rs.getString("endiaferonta"));
        field5.setText(rs.getString("tilefono"));
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
        field3.setText(rs.getString("eidikotita"));
        field4.setText(rs.getString("endiaferonta"));
        field5.setText(rs.getString("tilefono"));
        index = index + 1;

        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    @Override
    protected void previous() throws SQLException {

        rs.previous();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname") + ", " + rs.getString("firstname"));
        field3.setText(rs.getString("eidikotita"));
        field4.setText(rs.getString("endiaferonta"));
        field5.setText(rs.getString("tilefono"));

        index = index - 1;
        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    @Override
    protected void last() throws SQLException {

        rs.last();

        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname") + ", " + rs.getString("firstname"));
        field3.setText(rs.getString("eidikotita"));
        field4.setText(rs.getString("endiaferonta"));
        field5.setText(rs.getString("tilefono"));
        index = count;

        labelNum.setText(index + "/" + count);
        checkEnabled();
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
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);
        b13.setEnabled(false);
        field1.setEditable(false);
        field2.setEditable(false);
        panel.setBorder(BorderFactory.createTitledBorder("Update this Teacher"));

        idusers = -5;

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
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);
        b13.setEnabled(false);
        field1.setEditable(true);
        field2.setEditable(false);
        field3.setEditable(false);
        field4.setEditable(false);
        field5.setEditable(false);

        panel.setBorder(BorderFactory.createTitledBorder("Search a Teacher"));

        idusers = - 1;

    }

    protected void agree() throws SQLException {

        idText = field1.getText();
        //lastname = field2.getText();
        eidikotita = field3.getText();
        endiaferonta = field4.getText();
        tilefono = field5.getText();

        if (idusers > count) {
            // query = "INSERT INTO users (idusers, login, password, email, lastname, firstname) VALUES (" + idusers + ", '" + login
            //         + "', '" + password + "', '" + email + "', '" + lastname + "', '" + firstname + "');";

            // stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Successful Insertion!");

        } else if (idusers == 0) {
            // query = "DELETE FROM users WHERE idusers = " + index + ";";

            // stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Successful Removal!");

        } else if (idusers == -1) {

            if (Integer.valueOf(idText) > 0 && Integer.valueOf(idText) <= count) {
                query = "SELECT * FROM teacher left JOIN users ON users.idusers = teacher.idteacher WHERE idteacher = " + idText + ";";

                rs = stmt.executeQuery(query);
                rs.first();

                String result = "Serial: " + idText + System.lineSeparator()
                        + "Last Name: " + rs.getString("lastname") + System.lineSeparator()
                        + "First Name: " + rs.getString("firstname") + System.lineSeparator()
                        + "Specialization: " + rs.getString("eidikotita") + System.lineSeparator()
                        + "Interests: " + rs.getString("endiaferonta") + System.lineSeparator()
                        + "Phone: " + rs.getString("tilefono") + "";

                JOptionPane.showMessageDialog(null, result);
            } else {
                JOptionPane.showMessageDialog(null, "There is not a User with this Serial!");
            }

        } else {
            
            idusers = index;
            query = "UPDATE teacher left JOIN users  ON users.idusers = teacher.idteacher SET eidikotita = '" + eidikotita + "' WHERE idteacher = " + idText + ";";
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
        field5.setEditable(true);
        fieldPass.setEditable(true);

        query = "SELECT * FROM users left JOIN teacher ON users.idusers = teacher.idteacher WHERE teacher.idteacher IS NOT NULL;";
        retrieveQuery();

    }

    protected void disagree() throws SQLException {

        query = "SELECT * FROM users left JOIN teacher ON users.idusers = teacher.idteacher WHERE teacher.idteacher IS NOT NULL;";

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
        retrieveQuery();

    }
}

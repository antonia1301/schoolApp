/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;

import java.sql.SQLException;
import javax.swing.BorderFactory;

/**
 *
 * @author Flora
 */
public class Teachers extends Users {

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
        index=index+1;

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
        
        index = index-1;
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
}

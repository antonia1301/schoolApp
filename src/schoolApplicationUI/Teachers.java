/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;
//ta import

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
 * the class Teachers is a subclass of the class Users it inherits the variables
 * and the methods
 *
 * @author Antonia
 */
public class Teachers extends Users {

    //oi metavlites, private den xreiazete na tis dei alli klasi
    private String tilefono, eidikotita, endiaferonta;
    private final JComboBox<String> comboBox;
    private String[] splitString;

    /**
     * the constructor of the class Teachers
     *
     * @throws SQLException
     */
    public Teachers() throws SQLException {
        //allazoume ton titlo se teachers
        panel.setBorder(BorderFactory.createTitledBorder("Teachers"));
        //ftiaxnoume ta labels analoga
        label2.setText("User:");
        label3.setText("Specialization:");
        label4.setText("Interests:");
        label5.setText("Phone:");
        //kanoume initialize to combobox
        comboBox = new JComboBox<>();
        //vgazo oti den thelo pou eixe i mitriki klasi Users
        panel.remove(label6);
        panel.remove(fieldPass);
        //ftiaxno to arxiko query p xrisimopoioume stin methodo retrieve g n mas vgalei tous teachers
        query = "SELECT * FROM users left JOIN teacher ON users.idusers = teacher.idteacher WHERE teacher.idteacher IS NOT NULL;";
        //kalo tin methodo retrieve
        retrieveQuery();

    }

    @Override
    protected void first() throws SQLException {
        //mas paei sto proto row tou result set apo tin vasi
        rs.first();
        //gemizoume ta fields analoga
        field1.setText(rs.getString("idusers"));
        //sindiazo to lastname me to firstame se ena field
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

    @Override
    protected void add() {
        //adiazoume oti perioxomena mporei na exi to combobox apo proigoumena add
        comboBox.removeAllItems();
        //ftiaxnoume ta koumpia
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
        //diamorfonoume ta field oste na grapsoume auta p theloume mesa
        field3.setText("");
        field4.setText("");
        field5.setText("");
        //kalo tin methodo changelayout k allazo to layout oste n exei to combobox
        changeLayout(0);
        //allazo ton titlo t panel
        panel.setBorder(BorderFactory.createTitledBorder("Add a Teacher"));

        //ftiaxno t query g n vro poioi xristes den einai kathigites oute foitites etsi oste na mporo na tous prostheso
        query = "SELECT * FROM users left join student on idusers=idstudent WHERE idstudent is NULL and idusers NOT IN (SELECT idteacher from teacher);";
        try {
            //ektelo t query
            rs = stmt.executeQuery(query);
            //an einai adeio to result set ara den iparxei kaneis xristis p mporei n ginei kathigitis
            if (!rs.isBeforeFirst()) {
                //adiase to combobox k valtou oti den iparxei kapoios, empodise to koumpi agree
                comboBox.removeAllItems();
                field1.setText("");
                comboBox.addItem("No user availiable");
                b7.setEnabled(false);

            } //an iparxoun xristes pou mporoun na ginoun kathigites
            else {
                //pigene ston proto
                rs.first();
                //vale stin thesi tou id to id tou protou
                field1.setText(rs.getString("idusers"));
                // kai san epilogi to combobox to name kai to last name t
                comboBox.addItem(rs.getString("lastname") + ", " + rs.getString("firstname"));
                //kai oso exei stoixeia to result set vale kai tous ipoloipous
                while (rs.next()) {
                    comboBox.addItem(rs.getString("lastname") + ", " + rs.getString("firstname"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
        }
        // vazoume sto combobox listener etsi oste opote dialegoume kpoion, na allazei to id na kanei match me auton
        comboBox.addItemListener((ItemEvent arg0) -> {
            try {
                // i sinartisi pou kaleite apo ton listener
                getSerial();
            } catch (SQLException ex) {
                Logger.getLogger(Teachers.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        idusers = count + 1;

    }

    @Override
    protected void remove() {
        //ftiaxnoume ta koumpia analoga
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
        int idTeaching = Integer.valueOf(field1.getText());
        //kai ton titlo tou panel
        panel.setBorder(BorderFactory.createTitledBorder("Remove this Teacher"));
        // thetoume to idusers=0 g n mpoume stin katallilo prakladi tis if sto agree
        idusers = 0;
        // pernoume to text apo to proto field diladi to id, an auto einai iso me 1,2,3 apogaoreuoume na ginei agree,diladi diagrafi gt
        //oi xristes autoi einai autoi p itan eksarxis stin vasi sto teachers, kai i idia i vasi exei graftei etsi oste na apagoreuete i diagrafi tous
        int a = Integer.valueOf(field1.getText());
        if (a == 1 || a == 2 || a == 3) {
            b7.setEnabled(false);
        }
        
        //enas daskalos pou didaskei den ginete na diagraftei
         
       
        list = new ArrayList<>();

        query = "SELECT idteacher FROM schooldb.teacher left join schooldb.teaches on idteacher = teacher_idteacher where idteacher = teacher_idteacher;";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getInt("idteacher"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (list.contains(Integer.valueOf(idTeaching))) {
            b7.setEnabled(false);
            
        }
    }

    @Override
    protected void modify() {
        //ftiaxnoume ta koumpia analoga
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
        field1.setEditable(false);
        field2.setEditable(false);
        //k ton titlo t panel
        panel.setBorder(BorderFactory.createTitledBorder("Update this Teacher"));
        // thetoume to idusers=0 g n mpoume stin katallilo prakladi tis if sto agree
        idusers = -5;

    }

    @Override
    protected void search() {
        //ftiaxnoume ola t koumpia kai ta fields
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
        //allazoume to titlo t panel
        panel.setBorder(BorderFactory.createTitledBorder("Search a Teacher"));
        //g n mpoume sto parakladi tis if sto agree
        idusers = - 1;

    }

    @Override
    protected void agree() throws SQLException {
        //pernoume tis times apo ola ta fields mas
        idText = field1.getText();
        eidikotita = field3.getText();
        endiaferonta = field4.getText();
        tilefono = field5.getText();
        //otan theloume na prosthesoume kapoion kathigiti
        if (idusers > count) {
            //ftiaxnoume to qquery analoga
            query = "INSERT INTO teacher (idteacher, eidikotita, endiaferonta, tilefono) VALUES (" + idText + ", '" + eidikotita
                    + "', '" + endiaferonta + "', '" + tilefono + "') ;";
            //to ekteloume
            stmt.executeUpdate(query);
            //eidopoioume me alert oti egine epitixis eisagogi
            JOptionPane.showMessageDialog(null, "Successful Insertion!");

        } //otan theloume na diagrapsoume kapoion apo daskalo
        else if (idusers == 0) {
            //ftiaxnoume to query k to ekteloume
            query = "DELETE FROM teacher WHERE  idteacher = " + idText + ";";

            stmt.executeUpdate(query);
            //eidopoioume me alert g tin epitixis diagrafi
            JOptionPane.showMessageDialog(null, "Successful Removal!");

        } //otan theloume na psaksoume kapoion kathigiti
        else if (idusers == -1) {
            // ftiaxnoume mia lista me ola ta id ton kathigiton
            list = new ArrayList<>();
            query = "select idteacher from teacher;";
            rs = stmt.executeQuery(query);
            //ta vazoume stin lista
            while (rs.next()) {
                list.add(rs.getInt("idteacher"));
            }
            //an to id p psaxnoume iparxei stous kathigites
            if (list.contains(Integer.valueOf(idText))) {
                //vres ola ta stoixeia autou tou kathigiti
                query = "SELECT * FROM teacher left JOIN users ON users.idusers = teacher.idteacher WHERE idteacher = " + idText + ";";

                rs = stmt.executeQuery(query);
                rs.first();
                //valta se ena string me katallili diatiposi
                String result = "Serial: " + idText + System.lineSeparator()
                        + "Last Name: " + rs.getString("lastname") + System.lineSeparator()
                        + "First Name: " + rs.getString("firstname") + System.lineSeparator()
                        + "Specialization: " + rs.getString("eidikotita") + System.lineSeparator()
                        + "Interests: " + rs.getString("endiaferonta") + System.lineSeparator()
                        + "Phone: " + rs.getString("tilefono") + "";
                //me alert tipose ta stoixeia t
                JOptionPane.showMessageDialog(null, result);
            } else {
                //an den iparxei auto to id stous kathigites eidopoihse
                JOptionPane.showMessageDialog(null, "There is not a Teacher with this Serial!");
            }

        } //an theloume na epeksergastoume enan daskalo
        else {

            //ftiaxnoume to query kai to ekteloume
            query = "UPDATE teacher left JOIN users  ON users.idusers = teacher.idteacher SET eidikotita = '" + eidikotita + "',endiaferonta = '" + endiaferonta
                    + "', tilefono = '" + tilefono + "' WHERE idteacher = " + idText + ";";
            stmt.executeUpdate(query);
            //eidopoioume
            JOptionPane.showMessageDialog(null, "Successful Update!");

        }
        //allazoume to layout pali stin arxiki tou morfi kai to query episis kai kaloume tin proti sinartisi p vriskei tous kathigites
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
        fieldPass.setEditable(true);
        changeLayout(1);

        query = "SELECT * FROM users left JOIN teacher ON users.idusers = teacher.idteacher WHERE teacher.idteacher IS NOT NULL;";
        retrieveQuery();

    }

    @Override
    protected void disagree() throws SQLException {

        //allazoume to layout pali stin arxiki tou morfi kai to query episis kai kaloume tin proti sinartisi p vriskei tous kathigites
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
        fieldPass.setEditable(true);
        changeLayout(1);

        query = "SELECT * FROM users left JOIN teacher ON users.idusers = teacher.idteacher WHERE teacher.idteacher IS NOT NULL;";
        retrieveQuery();

    }

    /**
     *
     */
    private void changeLayout(Integer a) {
        //an a=0
        if (a == 0) {
            //vgale to deutero field kai vale to combobox
            panel.remove(field2);
            panel.add(comboBox);
            //k stoixise ta
            layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(label1)
                            .addComponent(label2)
                            .addComponent(label3)
                            .addComponent(label4)
                            .addComponent(label5))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(field1)
                            .addComponent(comboBox)
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
                            .addComponent(comboBox))
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
        } //an a=1 epanaferoume to arxiko layout
        else if (a == 1) {
            //diladi vgazoume to combobox k ksanavazoume to field
            panel.remove(comboBox);
            panel.add(field2);
            //k stoixizoume pali
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
        }
    }

    /**
     *
     */
    private void getSerial() throws SQLException {
        //ektelei pali to query na vrei tous ipopsifious kathigites
        query = "SELECT * FROM users left join student on idusers=idstudent WHERE idstudent is NULL and idusers NOT IN (SELECT idteacher from teacher);";
        rs = stmt.executeQuery(query);
        // an to resultset den einai adeio opote iparxoun atoma pou mporoun na ginoun kathigites
        if (rs.isBeforeFirst()) {
            //thetoume ena string iso me ti einai auti tin stigmi epilegmeno sto combobox
            String s = (String) comboBox.getSelectedItem();
            //an auto to string den einai adeio
            if (s != null) {
                //ftiaxnoume enan pinaka strings,pou vazoume stin proti thesi to latname kai stin deuteri to name pou einai xorismena me ", "
                splitString = s.split(", ");
                //dialegoume tin proti thesi p einai to epitheto
                s = splitString[0];
                //kai vrikoume to id autnou pou exei auto to epitheto
                query = "SELECT idusers FROM users WHERE lastname = '" + s + "';";
                rs = stmt.executeQuery(query);
                //pame stin proti thesi (moni thesi)
                rs.first();
                //kai thetoume to field tou id analoga me ton user p exei epilexthei sto combobox
                field1.setText(rs.getString("idusers"));
            }

        }
    }

}

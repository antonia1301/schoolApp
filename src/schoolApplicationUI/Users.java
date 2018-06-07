/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;
//ta import
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author Antonia The class Users shows the users, and gives the ability to
 * add,modify,delete or search a user
 */
public class Users extends JFrame {

    //oi metavlites einai protected,gt Users einai iperklassi opote theloume oi ipoklaseei na vlepoun tis metavlites tis
    protected final JPanel panel;
    protected final JPanel panel2;
    protected final JToolBar toolBar;
    protected final JLabel label1, label2, label3, label4, label5, label6, labelNum;
    protected final JTextField field1, field2, field3, field4, field5;
    protected final JPasswordField fieldPass;
    protected final JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b11;
    protected Statement stmt;
    protected String query, login, password, firstname, lastname, email, idText;
    protected Integer count, index, idusers, id, max;
    protected ResultSet rs;
    protected GroupLayout layout;
    protected java.util.List<Integer> list;

    /**
     * the constructor of the Users class
     *
     * @throws SQLException
     */
    public Users() throws SQLException {
        //kanoume initialize tis metavlites
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        panel = new JPanel();
        panel2 = new JPanel();
        //takoumpia mesa sto toolbar
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
        //ta labels
        label1 = new JLabel("Serial:");
        label2 = new JLabel("Last Name:");
        label3 = new JLabel("First Name:");
        label4 = new JLabel("Email");
        label5 = new JLabel("Login");
        label6 = new JLabel("Password");
        // to label pou deixnei ton metriti
        labelNum = new JLabel();
        
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        field4 = new JTextField();
        field5 = new JTextField();
        fieldPass = new JPasswordField();
        //pernoume to connection apo to mainDialog
        stmt = MainDialog.conn.createStatement();
        query = "SELECT * FROM users";
        field1.setEditable(false);
        //kaloume tin methodo retrieveQuery
        retrieveQuery();
        //kanoume disable ta koumpia pou den xrisimopoioume
        b1.setEnabled(false);
        b2.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);

        //mouse listeners
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
               
                if (b1.isEnabled()) {
                    try {
                        //call the method first
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
        //add the buttons
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
        //sto panel1 xrisimopoioume to GroupLayout
        //ftiaxnoume to border tou panel
        panel.setBorder(BorderFactory.createTitledBorder("Users"));
        //dimiourgoume tokainourgio grouplayout g t panel
        layout = new GroupLayout(panel);
        //vazoume to layout sto panel
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        //ftiaxnoume ta orizontia group
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
        //ftiaxnoume ta vertical group
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
        //to layout g t frame
        setLayout(new BorderLayout());
        //vazoume to toolbar pano
        add(toolBar, BorderLayout.NORTH);
        //to grouppanel sto kento
        add(panel, BorderLayout.CENTER);
        //to panel metrit kato
        add(panel2, BorderLayout.SOUTH);

        setSize(410, 300);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    /**
     * execute the first query to show the results
     *
     * @throws SQLException
     */
    protected void retrieveQuery() throws SQLException {

        try {
            //sto result set vale ta apotelesmata tou query
            rs = stmt.executeQuery(query);
            //kalo tin methodo first
            first();

        } catch (SQLException e) {

        }

    }

    /**
     * find the first result of the query and show on panel
     *
     * @throws SQLException
     */
    protected void first() throws SQLException {
        //mas paei sto proto stoixeio tou result set
        rs.first();
        //vazei sta fields is antistoixes times apo in vasi
        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname"));
        field3.setText(rs.getString("firstname"));
        field4.setText(rs.getString("email"));
        field5.setText(rs.getString("login"));
        fieldPass.setText(rs.getString("password"));
        //vazo ton arithmo p antiprosopeuei ton deikti stin proti thesi
        index = 1;
        //kai ton metriti iso me 1 osa ta apotelesmata mas
        count = 1;
        //to ksanafenro stin arxi
        rs.first();
        //pigene stin epomeni grammi tou table kai auksise ton metriti eos telous
        while (rs.next()) {
            count = count + 1;
        }
        //to ksanapame stin arxi
        rs.first();
        //to label p deixnei tin metrisi to orizo os deiktis/metritis
        labelNum.setText(index + "/" + count);
        //kaloume tin methodo checkEnabled
        checkEnabled();
    }

    /**
     * goes to the next row of results and shows results on panel
     *
     * @throws SQLException
     */
    protected void next() throws SQLException {
        //proxorise stin epomeni grammi
        rs.next();
        //gemise ta field analoga
        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname"));
        field3.setText(rs.getString("firstname"));
        field4.setText(rs.getString("email"));
        field5.setText(rs.getString("login"));
        fieldPass.setText(rs.getString("password"));
        //auksise to deikti na deixnei stin thesi pou eimaste
        index = index + 1;
        //allakse to label g n deixnei pou vriskomaste
        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    /**
     * goes to previous row of results and show result on panel
     *
     * @throws SQLException
     */
    protected void previous() throws SQLException {
        //pigene stin proigoumeni grammi
        rs.previous();
        //gemise ta fields analoga
        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname"));
        field3.setText(rs.getString("firstname"));
        field4.setText(rs.getString("email"));
        field5.setText(rs.getString("login"));
        fieldPass.setText(rs.getString("password"));
        // meiose ton deikti na deixnei sosta
        index = index - 1;
        //allakse to label na deixnei pou vriskomaste
        labelNum.setText(index + "/" + count);
        checkEnabled();
    }

    /**
     * goes to last row of results and show results on panel
     *
     * @throws SQLException
     */
    protected void last() throws SQLException {
        //pigene stin teleutaia grammi
        rs.last();
        //gemise ta fields analoga
        field1.setText(rs.getString("idusers"));
        field2.setText(rs.getString("lastname"));
        field3.setText(rs.getString("firstname"));
        field4.setText(rs.getString("email"));
        field5.setText(rs.getString("login"));
        fieldPass.setText(rs.getString("password"));
        //o deiktis ginete isos me tn metriti gt eimaste sto telos
        index = count;
        // allazoume to label n vlepoume p vriskomastw
        labelNum.setText(index + "/" + count);
        checkEnabled();
    }
    //prosthiki xristi

    /**
     * add a new user to the database, in the table users
     */
    protected void add() {
        //kanoume ola ta koumpia anenerga ektos tou disagree kai agree
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
        //kano ta fields adeia
        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setText("");
        field5.setText("");
        fieldPass.setText("");
        //allazo ton titlo tou panel
        panel.setBorder(BorderFactory.createTitledBorder("Add a User"));
        //afou tha valo k allon user , leo oti to idusers(mia metavliti) einai isi me osous eixame +1
        idusers = count + 1;

    }

    /**
     * delete a user from the database
     */
    protected void remove() {
        //kanoume ta koumpia disable ektos tou disagree k agree
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
        //allazume ton tilo t panel
        panel.setBorder(BorderFactory.createTitledBorder("Remove this User"));
        //theto tin metavliti idusers =0 gia na mpo stin katallili proipothesi tis if sto agree
        idusers = 0;
        
        // pernoume to text apo to proto field diladi to id, an auto einai iso me 1,2,3,4,5,6 apogaoreuoume na ginei agree,diladi diagrafi gt
        //oi xristes autoi einai autoi p itan eksarxis stin vasi sto teachers, kai i idia i vasi exei graftei etsi oste na apagoreuete i diagrafi tous
        int a = Integer.valueOf(field1.getText());
        if (a == 1 || a == 2 || a == 3 || a==4 || a==5 ||a==6) {
            b7.setEnabled(false);
        }
    }

    /**
     * edit a user from the database
     */
    protected void modify() {
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
        //ftiaxnoume k ton tilo t panel
        panel.setBorder(BorderFactory.createTitledBorder("Update this User"));
        // theto to idusers =-5 g n mpo stin katallili proipothesi tis if (diladi stin else)sto agree
        idusers = -5;

    }

    /**
     * searches for a user based on his serial
     */
    protected void search() {
        //ftiaxno ta koumpia
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
        //ftiaxno ta fields etsi oste na einai ola apenergopoimena ektos tou serial p tha simpliroso g n vro ton analogo user
        field1.setText("1");
        field2.setText("");
        field3.setText("");
        field4.setText("");
        field5.setText("");
        fieldPass.setText("");
        field1.setEditable(true);
        field2.setEditable(false);
        field3.setEditable(false);
        field4.setEditable(false);
        field5.setEditable(false);
        fieldPass.setEditable(false);
        //allazo ton titlo tou paanel
        panel.setBorder(BorderFactory.createTitledBorder("Search a User"));
        //gia na mpei stin sosti if sto agree
        idusers = - 1;

    }

    /**
     * the agree method is called by the agree butoon and confirms our action
     *
     * @throws SQLException
     */
    protected void agree() throws SQLException {
        //theto kapoies metavlites ises me to periexomeno ton koution mas
        idText = field1.getText();
        lastname = field2.getText();
        firstname = field3.getText();
        email = field4.getText();
        login = field5.getText();
        password = String.valueOf(fieldPass.getPassword());
        //gia to insertion
        //an oi xristes einai megaliteri tou metriti
        if (idusers > count) {
            //kaloume tin maxID
            maxID();
            //ftiaxno to query pou xreiazete g n valo kainourgio xristi
            query = "INSERT INTO users (idusers, login, password, email, lastname, firstname) VALUES (" + max + ", '" + login
                    + "', '" + password + "', '" + email + "', '" + lastname + "', '" + firstname + "');";
            //pragmatopoioume tin prosthiki
            stmt.executeUpdate(query);
            //alert sosto add
            JOptionPane.showMessageDialog(null, "Successful Insertion!");

        } //gia to removal
        else if (idusers == 0) {
            //ftiaxno to query g n diagrafei ton user apo to database
            query = "DELETE FROM users WHERE idusers = " + idText + ";";
            //pragmatopoioume tin diagrafi
            stmt.executeUpdate(query);
            //kanei alert oi egine diagrafi
            JOptionPane.showMessageDialog(null, "Successful Removal!");

        }//gia to search
        else if (idusers == -1) {
            //ftiaxno mia lista
            list = new ArrayList<>();
            // ftiaxno to query g n vrei olous tous xristes
            query = "select idusers from users;";
            rs = stmt.executeQuery(query);
            //oso iparxoun query proxora
            while (rs.next()) {
                //vale stin lista to id xristi(olon diladi)
                list.add(rs.getInt("idusers"));
            }
            //an mesa stin lista iparxei to id autounou pou psaxno, ara iparxei autos o xristis
            if (list.contains(Integer.valueOf(idText))) {
                // vres ta stoixeia tou xristi
                query = "SELECT * FROM users WHERE idusers = " + idText + ";";

                rs = stmt.executeQuery(query);
                //valta sto resultset
                rs.first();
                // ftiaxno ena steing pou exei mesa ola ta apotelsmata, me line sperator g n vgenei to ena kato apo t allo
                String result = "Serial: " + idText + System.lineSeparator()
                        + "Last Name: " + rs.getString("lastname") + System.lineSeparator()
                        + "First Name: " + rs.getString("firstname") + System.lineSeparator()
                        + "Email: " + rs.getString("email") + System.lineSeparator()
                        + "Login: " + rs.getString("login") + "";
                //me alert vgale ta apotelesmata
                JOptionPane.showMessageDialog(null, result);
            } //an o xristis p psaxno den einai mesa stin lista, den iparxei stous xristes
            else {
                //opote enimerono oti den iparxei me alert
                JOptionPane.showMessageDialog(null, "There is not a User with this Serial!");
            }

        } // kanoume edit ton user
        else {

            //ftiaxnoume o query g t edit tou user
            query = "UPDATE users SET  login = '" + login
                    + "', password = '" + password + "', email = '" + email + "', lastname = '" + lastname + "', firstname = '" + firstname
                    + "' WHERE idusers = " + idText + ";";
            //pragmatopoise tin allagi
            stmt.executeUpdate(query);
            //alert oti i allagi egine
            JOptionPane.showMessageDialog(null, "Successful Update!");

        }
        //allazoume to layout pali stin arxiki tou morfi kai to query episis kai kaloume tin proti sinartisi p vriskei tous xristes

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

        query = "SELECT * FROM users";
        retrieveQuery();

    }

    /**
     * this method is called when we press the button disagree and cancels our
     * action
     *
     * @throws SQLException
     */
    protected void disagree() throws SQLException {
        //epanafero to query stin arxiki t morfi
        query = "SELECT * FROM users";
        //ksanaftiaxno ta koumpia
        b5.setEnabled(true);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b11.setEnabled(true);
        //ksanaferno tin katastasi ton fields stin arxiki tous morfi
        field1.setEditable(false);
        field2.setEditable(true);
        field3.setEditable(true);
        field4.setEditable(true);
        field5.setEditable(true);
        fieldPass.setEditable(true);
        // kalo tin mthodo retrievequery
        retrieveQuery();

    }

    /**
     *
     */
    protected void checkEnabled() {
        //einai panta apenergopoihmena ektos otan ta energopoiei mia methods
        b6.setEnabled(false);
        b7.setEnabled(false);
        //stin periptosi p den exoume apotelesmata i exoume 1 apotelesma ta koumpia perihghsis apenergopoiountai
        if (index == 0 || index == 1 && count == 1) {
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);

        } //an eimaste stin proti thesi apo polles, energopoioume mono ta koumpia gia mprosta
        else if (index == 1) {
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(true);
            b4.setEnabled(true);
        } //an eimaste sto endiameso energopoioume ta panta
        else if (index > 1 && index < count) {
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            b4.setEnabled(true);

        } //an eimaste sto telos apenergopoioume ta koumpia g mprosta
        else if (Objects.equals(index, count) && index != 0) {
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(false);
            b4.setEnabled(false);

        }

    }

    /**
     * finds the maximum id
     *
     * @throws SQLException
     */
    protected void maxID() throws SQLException {
        //vrisko to megalitero id ston pinaka
        query = "select MAX(idusers) from users";
        rs = stmt.executeQuery(query);
        rs.first();
        //theto to max iso me to megalitero id +1, oste na moenoun sosta id stous pinakes
        max = rs.getInt("MAX(idusers)") + 1;

    }
}

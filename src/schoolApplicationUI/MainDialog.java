/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Flora
 */
public class MainDialog extends JFrame {

    private final JPanel panel;
    private final JMenuBar menuBar;
    private final ImageIcon image;
    private final JLabel label;
    private JMenu peopleMenu, lessonsMenu, assignmentsMenu,printMenu,helpMenu;
    private JMenuItem usersAction, teachersAction, studentsAction, exitAction, lessonsAction, prerequisitesAction,
            teachingAction, enrollmentAction;
    /**
     *
     */
    protected static Connection conn;

    /**
     *
     * the constructor of the BrickBreakerDialog, creates a new board(see
     * Board() class) and adds it to the frame , sets the size of the window,
     * makes the operation close by closing the window and makes it visible
     *
     */
    //private constructor to avoid client applications to use constructor
    public MainDialog() {

        panel = new JPanel();
        menuBar = new JMenuBar();
        image = new ImageIcon("resources/teicrete.jpg");
        label = new JLabel();
        label.setIcon(image);

        peopleMenu = new JMenu("People");
        lessonsMenu = new JMenu("Lessons");
        assignmentsMenu = new JMenu("Assignments");
        printMenu = new JMenu("Print");
        helpMenu = new JMenu("Help");

        usersAction = new JMenuItem("Users");
        teachersAction = new JMenuItem("Teachers");
        studentsAction = new JMenuItem("Students");
        exitAction = new JMenuItem("Exit");
        lessonsAction = new JMenuItem("Lessons");
        prerequisitesAction = new JMenuItem("Prerequisites");
        teachingAction = new JMenuItem("Teaching");
        enrollmentAction = new JMenuItem("Enrollment");
       

        exitAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                System.exit(0);

            }

        });
        usersAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                try {
                    Users users = new Users();
                } catch (SQLException ex) {
                    Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        teachersAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                try {
                    Teachers teachers = new Teachers();
                } catch (SQLException ex) {
                    Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        printMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                JOptionPane.showMessageDialog(null, "Print Completed!");

            }

        });
        helpMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                JOptionPane.showMessageDialog(null, "For help visit our site");
            }

        });

        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldb?autoReconnect=true&useSSL=false", "root", "");
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            System.out.println("Not Connected");
        }

        peopleMenu.add(usersAction);
        peopleMenu.add(teachersAction);
        peopleMenu.add(studentsAction);
        peopleMenu.addSeparator();
        peopleMenu.add(exitAction);
        lessonsMenu.add(lessonsAction);
        lessonsMenu.add(prerequisitesAction);
        assignmentsMenu.add(teachingAction);
        assignmentsMenu.add(enrollmentAction);
        menuBar.add(peopleMenu);
        menuBar.add(lessonsMenu);
        menuBar.add(assignmentsMenu);
        menuBar.add(printMenu);
        menuBar.add(helpMenu);
        panel.add(label);

        setJMenuBar(menuBar);
        add(panel);

        setSize(660, 360);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

    }

}

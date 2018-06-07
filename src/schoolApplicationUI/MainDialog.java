/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;
//ta import 
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
 * @author Antonia
 * this class in the main window, with the Menu bar that leads to the other dialogues
 */
//i maindialog klironomei to Jframe
public class MainDialog extends JFrame {
    //oi metavlites, oi protes einai private giati den xreiazete na einai 'orates; stis alles klasseis
    private final JPanel panel;
    private final JMenuBar menuBar;
    private final ImageIcon image;
    private final JLabel label;
    private JMenu peopleMenu, lessonsMenu, assignmentsMenu,printMenu,helpMenu;
    private JMenuItem usersAction, teachersAction, studentsAction, exitAction, lessonsAction, prerequisitesAction,
            teachingAction, enrollmentAction;
    
    //protected gia na to vlepoun oi upoloipes klaseis tou paketou 
    //to connection me tin vasi
    protected static Connection conn;

    /**
     *
     *the constructor of the class
     *
     */
    
    public MainDialog() {
        //kanoume instantiate tis metavlites
        panel = new JPanel();
        menuBar = new JMenuBar();
        // to link gia tin eikona
        image = new ImageIcon("resources/teicrete.jpg");
        label = new JLabel();
        //vazoume tin eikona sto label
        label.setIcon(image);
        //oi epiloges tou menou
        peopleMenu = new JMenu("People");
        lessonsMenu = new JMenu("Lessons");
        assignmentsMenu = new JMenu("Assignments");
        printMenu = new JMenu("Print");
        helpMenu = new JMenu("Help");
        //oi epiloges ton epilogon
        usersAction = new JMenuItem("Users");
        teachersAction = new JMenuItem("Teachers");
        studentsAction = new JMenuItem("Students");
        exitAction = new JMenuItem("Exit");
        lessonsAction = new JMenuItem("Lessons");
        prerequisitesAction = new JMenuItem("Prerequisites");
        teachingAction = new JMenuItem("Teaching");
        enrollmentAction = new JMenuItem("Enrollment");
       
        //oi mouselisteners
        exitAction.addMouseListener(new MouseAdapter() {
            @Override
            //otan kanoume press to pontiki stin epilogi exitAction
            public void mousePressed(MouseEvent me) {
                //kleinei to programma
                System.exit(0);

            }

        });
        usersAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                try {
                    //kanoume instantiate tin klassi users i opoia xrisimopoiei SQL statements opote xreiazete try catch
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
        studentsAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                try {
                    Students students = new Students();
                } catch (SQLException ex) {
                    Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        lessonsAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                try {
                    Lessons lessons = new Lessons();
                } catch (SQLException ex) {
                    Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        prerequisitesAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                try {
                    Prerequisites prerequisites = new Prerequisites();
                } catch (SQLException ex) {
                    Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        teachingAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                try {
                    Teachings teachings = new Teachings();
                } catch (SQLException ex) {
                    Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        enrollmentAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                try {
                    Enrollments enrollments = new Enrollments();
                } catch (SQLException ex) {
                    Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        printMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                //alert me minima g print
                JOptionPane.showMessageDialog(null, "Print Completed!");

            }

        });
        helpMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                //alert me minima g help
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
        //vazo sto peoplemenu ta items tou
        peopleMenu.add(usersAction);
        peopleMenu.add(teachersAction);
        peopleMenu.add(studentsAction);
        //diaxoristiki grammi
        peopleMenu.addSeparator();
        peopleMenu.add(exitAction);
        //vazo sto lessonmenu ta items
        lessonsMenu.add(lessonsAction);
        lessonsMenu.add(prerequisitesAction);
        //vazo sto assignmentsMenu ta items
        assignmentsMenu.add(teachingAction);
        assignmentsMenu.add(enrollmentAction);
        //vazo sto kurio menu ta mikra menu
        menuBar.add(peopleMenu);
        menuBar.add(lessonsMenu);
        menuBar.add(assignmentsMenu);
        menuBar.add(printMenu);
        menuBar.add(helpMenu);
        //sto panel vazoume to label me to image
        panel.add(label);
        //vazoume to menuBar sto frame mas
        setJMenuBar(menuBar);
        //sto frame vazoume to panel
        add(panel);
        //orizoume to megethos tou frame(platos,ipsos)
        setSize(660, 360);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

    }

}

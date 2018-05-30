/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolApplicationUI;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Flora
 */
public class MainDialog extends JFrame {

    private final JPanel panel;
    private final JMenuBar menuBar;
    private final ImageIcon image;
    private JLabel label;
    

    /**
     *
     * the constructor of the BrickBreakerDialog, creates a new board(see
     * Board() class) and adds it to the frame , sets the size of the window,
     * makes the operation close by closing the window and makes it visible
     *
     */
    //private constructor to avoid client applications to use constructor
    public MainDialog() {

        

        panel= new JPanel();
        menuBar = new JMenuBar();
        image = new ImageIcon("resources/teicrete.jpg");
        label = new JLabel(image);
    

        JMenu peopleMenu = new JMenu("People");
             
        JMenuItem usersAction = new JMenuItem("Users");
        JMenuItem teachersAction = new JMenuItem("Teachers");
        JMenuItem studentsAction = new JMenuItem("Students");
        JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem lessonsAction = new JMenuItem("Lessons");
        JMenuItem assignmentsAction = new JMenuItem("Assignments");
        JMenuItem printAction = new JMenuItem("Print");
        JMenuItem helpAction = new JMenuItem("Help");
        
        
        peopleMenu.add(usersAction);
        peopleMenu.add(teachersAction);
        peopleMenu.add(studentsAction);
        peopleMenu.add(exitAction);
        menuBar.add(peopleMenu);
        menuBar.add(lessonsAction);
        menuBar.add(assignmentsAction);
        menuBar.add(printAction);
        menuBar.add(helpAction);
        panel.add(label);
         
        setJMenuBar(menuBar);
        add(panel);

        setSize(660, 360);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

    }

}

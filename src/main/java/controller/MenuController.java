package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    JButton button, button1, button2;
    AdminController adminController = new AdminController();
    UserController userController = new UserController();

    public void MenuOptions() {
        JFrame frame = new JFrame("Choose your Option");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        button = new JButton();
        button.setText("Client");
        button.setPreferredSize(new Dimension(300, 40));
        panel.add(button);
        button.setBackground(Color.ORANGE);
        button.addActionListener(this);

        button1 = new JButton();
        button1.setText("Administrator");
        button1.setPreferredSize(new Dimension(300, 40));
        panel.add(button1);
        button1.setBackground(Color.ORANGE);
        button1.addActionListener(this);

        button2 = new JButton();
        button2.setText("EXIT");
        button2.setPreferredSize(new Dimension(300, 40));
        panel.add(button2);
        button2.setBackground(Color.ORANGE);
        button2.addActionListener(this);

        frame.add(panel);
        panel.setBackground(Color.GRAY);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            userController.start();
        }
        if (e.getSource() == button1){
            adminController.start();
        }
        if (e.getSource() == button2){
           System.exit(0);
        }
    }
}
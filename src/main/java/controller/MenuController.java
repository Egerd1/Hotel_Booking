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

        button = createButton("Client", panel);
        button1 = createButton("Administrator", panel);
        button2 = createButton("EXIT", panel);

        frame.add(panel);
        panel.setBackground(Color.GRAY);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    private JButton createButton(String buttonName, JPanel panel){
        JButton myButton = new JButton(buttonName);
        myButton.setPreferredSize(new Dimension(210, 40));
        panel.add(myButton);
        myButton.setBackground(Color.ORANGE);
        myButton.addActionListener(this);
        return myButton;
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
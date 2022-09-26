package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController implements ActionListener {

    JButton button, button1, button2, button3, button4, button5;
    ClientController clientController = new ClientController();
    BookingController bookingController = new BookingController();
    private final HotelController hotelController = new HotelController();
    public void start(){
        this.showUserOptions();
    }


    public void showUserOptions (){

        JFrame frame = new JFrame("Choose your Option");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        button = new JButton();
        button.setText("Create client");
        button.setPreferredSize(new Dimension(250, 40));
        panel.add(button);
        button.addActionListener(this);

        button1 = new JButton();
        button1.setText("Update client");
        button1.setPreferredSize(new Dimension(250, 40));
        panel.add(button1);
        button1.addActionListener(this);

        button2 = new JButton();
        button2.setText("View all Hotels");
        button2.setPreferredSize(new Dimension(250, 40));
        panel.add(button2);
        button2.addActionListener(this);

        button3 = new JButton();
        button3.setText("Create Booking");
        button3.setPreferredSize(new Dimension(250, 40));
        panel.add(button3);
        button3.addActionListener(this);

        button4 = new JButton();
        button4.setText("Update Booking");
        button4.setPreferredSize(new Dimension(250, 40));
        panel.add(button4);
        button4.addActionListener(this);

        button5 = new JButton();
        button5.setText("EXIT");
        button5.setPreferredSize(new Dimension(100, 40));
        panel.add(button5);
        button5.addActionListener(this);

        frame.add(panel);
        frame.setSize(300, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            clientController.createClient();
        }
        if (e.getSource() == button1){
            clientController.updateClient();
        }
        if (e.getSource() == button2){
            hotelController.displayAllHotels();
        }
        if (e.getSource() == button3){
            bookingController.createNewBooking();
        }
        if (e.getSource() == button4){
            bookingController.updateBooking();
        }
        if (e.getSource() == button5){
            System.exit(0);
        }
    }
}
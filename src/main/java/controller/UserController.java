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

        button = createButton("Create Client", panel);
        button1 = createButton("Update Client", panel);
        button2 = createButton("View all Hotels", panel);
        button3 = createButton("Create booking", panel);
        button4 = createButton("Update Booking", panel);
        button5 = createButton("EXIT", panel);

        frame.add(panel);
        panel.setBackground(Color.GRAY);
        frame.setSize(400, 350);
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
            clientController.createClient();
        }
        if (e.getSource() == button1){
            clientController.updateClient();
        }
        if (e.getSource() == button2){
            hotelController.getAllHotels();
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
package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController implements ActionListener {
    JButton button, button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15;
    private final ClientController clientController = new ClientController();
    private final HotelController hotelController = new HotelController();
    private final BookingController bookingController = new BookingController();

    public void start() {
        this.showMenuOptions();
    }

    private void showMenuOptions() {

        JFrame frame = new JFrame("Choose your Option");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        button = new JButton();
        button.setText("Create client");
        panel.add(button);
        button.addActionListener(this);

        button1 = new JButton();
        button1.setText("Update client");
        panel.add(button1);
        button1.addActionListener(this);

        button2 = new JButton();
        button2.setText("View all clients");
        panel.add(button2);
        button2.addActionListener(this);

        button3 = new JButton();
        button3.setText("Delete client");
        panel.add(button3);
        button3.addActionListener(this);

        button4 = new JButton();
        button4.setText("Find client by personal ID");
        panel.add(button4);
        button4.addActionListener(this);

        button5 = new JButton();
        button5.setText("Create Hotel");
        panel.add(button5);
        button5.addActionListener(this);

        button6 = new JButton();
        button6.setText("Update Hotel");
        panel.add(button6);
        button6.addActionListener(this);

        button7 = new JButton();
        button7.setText("View all Hotels");
        panel.add(button7);
        button7.addActionListener(this);

        button8 = new JButton();
        button8.setText("Delete Hotel");
        panel.add(button8);
        button8.addActionListener(this);

        button9 = new JButton();
        button9.setText("Find Hotel by ID");
        panel.add(button9);
        button9.addActionListener(this);

        button10 = new JButton();
        button10.setText("Create Booking");
        panel.add(button10);
        button10.addActionListener(this);

        button11 = new JButton();
        button11.setText("Update Booking");
        panel.add(button11);
        button11.addActionListener(this);

        button12 = new JButton();
        button12.setText("View all Bookings");
        panel.add(button12);
        button12.addActionListener(this);

        button13 = new JButton();
        button13.setText("Delete Booking");
        panel.add(button13);
        button13.addActionListener(this);

        button14 = new JButton();
        button14.setText("Find Booking by personal ID");
        panel.add(button14);
        button14.addActionListener(this);

        button15 = new JButton();
        button15.setText("EXIT");
        panel.add(button15);
        button15.addActionListener(this);

        frame.add(panel);
        frame.setSize(225, 550);
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
            clientController.viewAllMyClients();
        }
        if (e.getSource() == button3){
            clientController.deleteClient();
        }
        if (e.getSource() == button4){
            clientController.findClientByPersonalId();
        }
        if (e.getSource() == button5){
            hotelController.makeNewHotel();
        }
        if (e.getSource() == button6){
            hotelController.updateHotel();
        }
        if (e.getSource() == button7){
            hotelController.displayAllHotels();
        }
        if (e.getSource() == button8){
            hotelController.deleteHotel();
        }
        if (e.getSource() == button9){
            hotelController.findHotelById();
        }
        if (e.getSource() == button10){
            bookingController.createNewBooking();
        }
        if (e.getSource() == button11){
            bookingController.updateBooking();
        }
        if (e.getSource() == button12){
            bookingController.viewAllMyBookings();
        }
        if (e.getSource() == button13){
            bookingController.deleteBooking();
        }
        if (e.getSource() == button14){
           bookingController.findBookingById();
        }
        if (e.getSource() == button15){
            System.exit(0);
        }
    }
}
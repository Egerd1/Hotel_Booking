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

        button = createButton("Create Client", panel);
        button1 = createButton("Update Client", panel);
        button2 = createButton("View all Clients", panel);
        button3 = createButton("Delete Client", panel);
        button4 = createButton("Find client by personal ID", panel);
        button5 = createButton("Create Hotel", panel);
        button6 = createButton("Update Hotel", panel);
        button7 = createButton("View all Hotels", panel);
        button8 = createButton("Delete Hotel", panel);
        button9 = createButton("Find Hotel by ID", panel);
        button10 = createButton("Create booking", panel);
        button11 = createButton("Update Booking", panel);
        button12 = createButton("View all Bookings", panel);
        button13 = createButton("Delete Booking", panel);
        button14 = createButton("Find Booking by ID", panel);
        button15 = createButton("EXIT", panel);


        frame.add(panel);
        panel.setBackground(Color.GRAY);
        frame.setSize(400, 800);
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
            hotelController.getAllHotels();
        }
        if (e.getSource() == button8){
            hotelController.deleteHotel();
        }
        if (e.getSource() == button9){
            hotelController.findHotelById();
        }
        if (e.getSource() == button10){
            hotelController.getAllHotels();
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
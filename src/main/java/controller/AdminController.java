package controller;

import javax.swing.*;

public class AdminController {

    private final ClientController clientController = new ClientController();
    private final HotelController hotelController = new HotelController();
    private final BookingController bookingController = new BookingController();

    public void start() {
        this.showMenuOptions();
    }

    private void showMenuOptions() {

        Object[] options = { "Create client", "Update client","View all clients", "Delete client", "Find client by personal ID",
                "Create Hotel", "Update Hotel", "View all Hotels", "Delete Hotel", "Find Hotel by ID",
                "Create Booking", "Update Booking","View all Bookings", "Delete Booking", "Find Booking by personal ID", "EXIT"};

       int userChoice = JOptionPane.showOptionDialog(null, "Make your choice!", "Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);

        System.out.println(userChoice);

//        switch (userChoice) {
//            case "1":
//                this.clientController.createClient();
//                break;
//            case "2":
//                this.clientController.updateClient();
//                break;
//            case "3":
//                this.clientController.viewAllMyClients();
//                break;
//            case "4":
//                this.clientController.deleteClient();
//                break;
//            case "5":
//                this.clientController.findClientByPersonalId();
//                break;
//            case "6":
//                this.hotelController.makeNewHotel();
//                break;
//            case "7":
//                this.hotelController.updateHotel();
//                break;
//            case "8":
//                this.hotelController.deleteHotel();
//                break;
//            case "9":
//                this.hotelController.displayAllHotels();
//                break;
//            case "10":
//                this.bookingController.createNewBooking();
//                break;
//            case "11":
//                this.bookingController.updateBooking();
//                break;
//            case "12":
//                this.bookingController.deleteBooking();
//                break;
//            case "13":
//                this.bookingController.viewAllMyBookings();
//                break;
//            case "0":
//                System.exit(0);
//                break;
//            default:
//                System.out.println("Please choose an option from the list");
//                break;
//        }
//        this.start();

    }

}
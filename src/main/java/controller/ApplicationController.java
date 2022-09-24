package controller;

import javax.swing.*;

public class ApplicationController {

    private final ClientController clientController = new ClientController();


    public void start() {
        this.showMenuOptions();
    }

    private void showMenuOptions() {
        String userChoice = JOptionPane.showInputDialog("Choose an option!\n"
                + " To create client please enter 1\n"
                + " To update client please enter 2\n"
                + " To see all clients please enter 3\n"
                + " To create Hotel please enter 4\n"
                + " To update Hotel please enter 5\n"
                + " To see all Hotels please enter 6\n"
                + " To create booking please enter 7\n"
                + " To update booking please enter 8\n"
                + " To see all bookings please enter 9\n"
                + " To EXIT please enter 0");

        switch (userChoice) {
            case "1":
                this.clientController.createClient();
                break;
            case "2":
                this.clientController.updateClient();
                break;
            case "3":
                this.clientController.viewAllMyClients();
                break;
            case "4":
                this.manager.createHotel();
                break;
            case "5":
                this.manager.updateHotel();
                break;
            case "6":
                this.manager.viewAllMyHotels();
                break;
            case "7":
                this.manager.createBooking();
                break;
            case "8":
                this.manager.updateBooking();
                break;
            case "9":
                this.manager.viewAllMyBookings();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Please choose an option from the list");
                break;
        }
        this.start();

    }

}

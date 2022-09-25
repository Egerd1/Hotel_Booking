package controller;

import javax.swing.*;

public class UserController {
    ClientController clientController = new ClientController();
    BookingController bookingController = new BookingController();

    public void start(){
        this.showUserOptions();
    }


    public void showUserOptions (){
        String userChoice = JOptionPane.showInputDialog("Please choose what you would like to do from the list!\n"
                +" To create client please enter 1\n"
                +" To update client please enter 2\n"
                +" To create booking please enter 3\n"
                +" To update booking please enter 4\n"
                +" To EXIT please enter 0");

        switch (userChoice){
            case "1":
                this.clientController.createClient();
                break;
            case "2":
                this.clientController.updateClient();
                break;
            case "3":
                this.bookingController.createNewBooking();
                break;
            case "4":
                this.bookingController.updateBooking();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Please choose from the menu");
                break;

        }



    }
}
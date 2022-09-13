package controller;

import model.Client;
import model.Hotel;
import repository.BookingRepository;
import repository.ClientRepository;
import repository.HotelRepository;

import javax.swing.*;

public class Manager {

    private final ClientRepository clientRepository = new ClientRepository();
    private final HotelRepository hotelRepository = new HotelRepository();
    private final BookingRepository bookingRepository = new BookingRepository();

    public void createClient() {
        Client client = new Client();
        client.setFirstName(this.getUserInput("Please enter your name: "));
        client.setLastName(this.getUserInput("Please enter your last name: "));
        client.setAge(this.getUserInput("Please enter your age: "));
        clientRepository.createClient(client);
    }

    public void deleteClient() {
        int chosenId = Integer.parseInt(this.getUserInput("Please enter the client id to be removed"));
        clientRepository.deleteClientFromDB(chosenId);
    }

    public void updateClient() {
        Long chosenId = Long.valueOf(Integer.parseInt(this.getUserInput("Please enter the client id to be updated")));
        clientRepository.updateClientFromDB(chosenId);
    }

    public void createHotel() {
        Hotel hotel = new Hotel();
        hotel.setHotelName(this.getUserInput("Please enter Hotel name: "));
        hotel.setAddress(this.getUserInput("Please enter Hotel address: "));
        hotel.setNumberOfRooms(Integer.parseInt(this.getUserInput("Please enter number how many rooms the hotel contains: ")));
        hotel.setPrice(Double.valueOf(this.getUserInput("Enter the price for one night")));
        hotelRepository.createHotelToDB(hotel);
    }

    public void findClientById() {
        int chosenId = Integer.parseInt(this.getUserInput("To view client, please enter the client id"));
        clientRepository.findClientFromDBById(chosenId);
    }

    public void viewAllMyClients() {
        int userChoice = Integer.parseInt(JOptionPane.showInputDialog(" To view all Clients enter 1"));
        if (userChoice == 1) {
            System.out.println(clientRepository.showAllMyClientsFromDB());
        } else {
            System.out.println("Maybe next time!");
            System.exit(0);
        }
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}

package controller;

import model.Bookings;
import model.Client;
import model.Hotel;
import repository.BookingRepository;
import repository.ClientRepository;
import repository.HotelRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;

public class Manager {

    private final ClientRepository clientRepository = new ClientRepository();
    private final HotelRepository hotelRepository = new HotelRepository();
    private final BookingRepository bookingRepository = new BookingRepository();

    //--------------------------CLIENT--------------------------------------
    public Client createClient() {
        Client client = new Client();
        client.setPersonalId(Integer.parseInt(this.getUserInput("Please enter your personal id code: ")));
        client.setFirstName(this.getUserInput("Please enter your name: "));
        client.setLastName(this.getUserInput("Please enter your last name: "));
        client.setAge(this.getUserInput("Please enter your age: "));
        clientRepository.createClient(client);
        return client;
    }

    public void deleteClient() {
        int chosenId = Integer.parseInt(this.getUserInput("Please enter the client personal id code to be removed"));
        clientRepository.deleteClientFromDB(chosenId);
    }

    public void updateClient() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("Please enter the client id to be updated"));
        clientRepository.updateClientFromDB(chosenId);
    }

    public void findClientById() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("To view client, please enter the client personal id"));
        clientRepository.findClientByPersonalIdCode(chosenId);
    }

    public void findClientByName() {
        String clientName = this.getUserInput("Enter the name of the customer you want to find");
        clientRepository.findClientByFirstName(clientName);
    }

    public void viewAllMyClients() {
            System.out.println(clientRepository.showAllMyClientsFromDB());
    }

    //------------------------------HOTEL-------------------------------------------
    public void createHotel() {
        Hotel hotel = new Hotel();
        hotel.setHotelName(this.getUserInput("Please enter Hotel name: "));
        hotel.setAddress(this.getUserInput("Please enter Hotel address: "));
        hotel.setNumberOfRooms(Integer.parseInt(this.getUserInput("Please enter number how many rooms the hotel contains: ")));
        hotel.setPrice(Double.valueOf(this.getUserInput("Enter the price for one night")));
        hotelRepository.createHotelToDB(hotel);
    }

    public void deleteHotel() {
        int chosenId = Integer.parseInt(this.getUserInput("Please enter the Hotel id to be removed"));
        clientRepository.deleteClientFromDB(chosenId);
    }

    public void updateHotel() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("Please enter the Hotel id to be updated"));
        clientRepository.updateClientFromDB(chosenId);
    }

    public int findHotelById() {
        int chosenId = Integer.parseInt(this.getUserInput("To view Hotel, please enter the Hotel id"));
        hotelRepository.findHotelFromDBById(chosenId);
        return chosenId;
    }

    public void viewAllMyHotels() {
            System.out.println(clientRepository.showAllMyClientsFromDB());

    }

    //----------------------------BOOKING----------------------------------------
    public void createBooking() {
        Long userIdCode = Long.valueOf(this.getUserInput("Please enter your personal ID code"));
        Client foundClient = clientRepository.findClientByPersonalIdCode(userIdCode);
        if (foundClient == null) {
           foundClient = this.createClient();
        }
        createNewBookingToHotel(foundClient);
    }

    public void createNewBookingToHotel(Client client) {
        Bookings booking = new Bookings();
        Hotel hotel = hotelRepository.findHotelFromDBById(this.findHotelById());
        booking.setHotel(hotel);
        booking.setClient(client);
        LocalDate arrivalDate = LocalDate.parse(this.getUserInput("please enter your arrival date in the following format 2022-12-30"));
        LocalDate leaveDate = LocalDate.parse(this.getUserInput("please enter your leaving date in the following format 2022-12-30"));
        booking.setArrivalDate(arrivalDate);
        booking.setLeaveDate(leaveDate);
        int duration = Period.between(arrivalDate, leaveDate).getDays();
        Double totalCostAmount = duration * hotel.getPrice();
        booking.setTotalAmount(totalCostAmount);
        hotelRepository.updateHotelAvailableRooms(this.findHotelById());
        bookingRepository.createBookingToDB(booking);

    }

    public void deleteBooking() {
        int chosenId = Integer.parseInt(this.getUserInput("Please enter the Booking id to be removed"));
        bookingRepository.deleteBookingsFromDB(chosenId);
    }

    public void updateBooking() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("Please enter the Booking id to be updated"));
        bookingRepository.updateBookingFromDB(chosenId);
    }
    public void viewAllMyBookings() {
        System.out.println(bookingRepository.showAllMyBookingsFromDB());
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}

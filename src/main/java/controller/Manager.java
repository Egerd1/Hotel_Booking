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

    public void deleteClient() {
        int chosenId = Integer.parseInt(this.getUserInput("Please enter the client personal id code to be removed"));
        clientRepository.deleteClientFromDB(chosenId);
    }

    public void updateClient() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("Please enter the client id from database to be updated"));
        clientRepository.updateClientInfo(chosenId);
    }

    public void findClientByPersonalId() {
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
        hotelRepository.deleteHotelFromDB(chosenId);
    }

    public void updateHotel() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("Please enter the Hotel id to be updated"));
        hotelRepository.updateHotelFromDB(chosenId);
    }

    public int findHotelById() {
        int chosenId = Integer.parseInt(this.getUserInput("Please enter the Hotel id"));
        hotelRepository.findHotelFromDBById(chosenId);
        return chosenId;
    }

    public void viewAllMyHotels() {
        System.out.println(hotelRepository.showAllMyHotelsFromDB());

    }

    //----------------------------BOOKING----------------------------------------
    public void verifyClient() {
        Long userIdCode = Long.valueOf(this.getUserInput("Please enter your personal ID code"));
        Client foundClient = clientRepository.findClientByPersonalIdCode(userIdCode);
        if (foundClient == null) {
            foundClient = this.createClient();
        }
        createNewBooking(foundClient);
    }

    private void createNewBooking(Client client) {
        Bookings booking = new Bookings();
        System.out.println(hotelRepository.showAllMyHotelsFromDB());
        int userChoice = Integer.parseInt(this.getUserInput("Please choose your hotel from following hotel list"));
        Hotel hotel = hotelRepository.findHotelFromDBById(userChoice);
        booking.setHotel(hotel);
        booking.setClient(client);
        LocalDate arrivalDate = LocalDate.parse(this.getUserInput("please enter your arrival date in the following format 2022-12-30"));
        LocalDate leaveDate = LocalDate.parse(this.getUserInput("please enter your leaving date in the following format 2022-12-30"));
        booking.setArrivalDate(arrivalDate);
        booking.setLeaveDate(leaveDate);
        booking.setTotalAmount(Period.between(booking.getArrivalDate(),booking.getLeaveDate()).getDays() * booking.getHotel().getPrice());
        hotelRepository.updateHotelAvailableRooms(userChoice);
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

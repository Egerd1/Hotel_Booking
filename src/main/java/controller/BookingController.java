package controller;

import com.toedter.calendar.JDateChooser;
import model.Bookings;
import model.Client;
import model.Hotel;
import repository.BookingRepository;
import repository.ClientRepository;
import repository.HotelRepository;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class BookingController {

    private final BookingRepository bookingRepository = new BookingRepository();
    private final ClientRepository clientRepository = new ClientRepository();
    private final HotelRepository hotelRepository = new HotelRepository();
    private final ClientController clientController = new ClientController();
    private final HotelController hotelController = new HotelController();


    public Client verifyClient() {
        Long userIdCode = Long.valueOf(this.getUserInput("Please enter your personal ID code"));
        Client foundClient = clientRepository.findClientByPersonalIdCode(userIdCode);
        if (foundClient == null) {
            foundClient = clientController.createClient();
        }
        if (foundClient.getAge() < 18) {
            JOptionPane.showMessageDialog(null, "Sorry but you are too young to book a room");
            this.createNewBooking();
        }
        return foundClient;
    }

    public void createNewBooking() {
        Bookings booking = new Bookings();
        Long userChoice = Long.valueOf(this.getUserInput("Please choose your hotel from following hotel list"));
        Hotel hotel = hotelRepository.findHotelFromDBById(userChoice);
        if (hotel.getNumberOfRooms() > 0) {
            booking.setHotel(hotel);
            booking.setClient(verifyClient());
            LocalDate arrivalDate = this.getDateFromCustomer("Arrival Date");
            //LocalDate arrivalDate = LocalDate.parse(this.getUserInput("please enter your arrival date in the following format 2022-12-30"));
            LocalDate leaveDate = this.getDateFromCustomer("Leave Date");
            booking.setArrivalDate(arrivalDate);
            booking.setLeaveDate(leaveDate);
            booking.setTotalAmount(Period.between(booking.getArrivalDate(), booking.getLeaveDate()).getDays() * booking.getHotel().getPrice());
            hotelRepository.updateHotelAvailableRooms(userChoice);
            bookingRepository.createBookingToDB(booking);
        } else {
            JOptionPane.showMessageDialog(null, "Sorry this hotel does not have available room");
        }


    }

    public void deleteBooking() {
        int chosenId = Integer.parseInt(this.getUserInput("Please enter the Booking id to be removed"));
        bookingRepository.deleteBookingsFromDB(chosenId);
    }

    public void updateBooking() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("Please enter the Booking id to be updated"));
        Bookings updatedBooking = bookingRepository.findBookingFromDBById(chosenId);
        int userChoice = Integer.parseInt(JOptionPane.showInputDialog("Please specify what Booking info you want to update:\n"
                + "Arrival Date enter 1\n"
                + "Leaving Date enter 2\n"
                + "To change client enter 3\n"
                + "To change hotel enter 4\n"
                + "To make more updates enter 5\n"
                + "To EXIT enter 6"));

        switch (userChoice) {
            case 1:
                LocalDate newArrivalDate = this.getDateFromCustomer("New Arrival Date");
                updatedBooking.setArrivalDate(newArrivalDate);
                updatedBooking.setTotalAmount(Period.between(updatedBooking.getArrivalDate(), updatedBooking.getLeaveDate()).getDays() * updatedBooking.getHotel().getPrice());
                break;
            case 2:
                LocalDate newLeaveDate = this.getDateFromCustomer("New Leave Date");
                updatedBooking.setLeaveDate(newLeaveDate);
                updatedBooking.setTotalAmount(Period.between(updatedBooking.getArrivalDate(), updatedBooking.getLeaveDate()).getDays() * updatedBooking.getHotel().getPrice());
                break;
            case 3:
                Long myClientId = Long.valueOf(this.getUserInput("Please enter new client personal id:"));
                Client foundClient = clientRepository.findClientByPersonalIdCode(myClientId);
                if (foundClient == null) {
                    foundClient = clientController.createClient();
                }
                updatedBooking.setClient(foundClient);
                break;
            case 4:
                hotelController.displayAllHotels();
                Long myHotelId = Long.valueOf(this.getUserInput("Please enter new hotel id:"));
                Hotel foundHotel = hotelRepository.findHotelFromDBById(myHotelId);
                updatedBooking.setHotel(foundHotel);
                break;
            case 5:
                this.updateBooking();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Choose an option from the list");

                break;
        }


    }

    public void viewAllMyBookings() {
        String myText;
        List<Bookings> myBookings;
        myBookings = bookingRepository.showAllMyBookingsFromDB();
        StringBuilder builder = new StringBuilder();
        for (Bookings b : myBookings) {
            builder.append(b);
        }
        myText = builder.toString();

        JOptionPane.showMessageDialog(null, myText);
//        System.out.println(bookingRepository.showAllMyBookingsFromDB());
    }

    public Bookings findBookingById() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("To view booking, please enter the booking id"));
        return bookingRepository.findBookingFromDBById(chosenId);
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

    private LocalDate getDateFromCustomer(String arrivalOrLeaveDate) {
        JDateChooser jdc = new JDateChooser();
        String message = "Please choose dates from the calendar ";
        Object[] params = {message, jdc};
        JOptionPane.showConfirmDialog(null, params, arrivalOrLeaveDate, JOptionPane.PLAIN_MESSAGE);
        String s = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        s = sdf.format(((JDateChooser) params[1]).getDate());
        LocalDate localDate = LocalDate.parse(s);
        return localDate;
    }

}
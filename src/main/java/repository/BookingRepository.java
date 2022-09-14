package repository;

import controller.Manager;
import model.Bookings;
import model.Client;
import model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingRepository {

    private static SessionFactory factory = SessionManager.getFactory();

    public void createBooking(Bookings booking) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(booking);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }
    }

    // -------------------VAJA TEHA---------------
    public void deleteBookingsFromDB(int id) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Bookings booking = session.find(Bookings.class, id);
            session.remove(booking);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void updateBookingFromDB(Long bookingId) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Bookings foundBooking = session.find(Bookings.class, bookingId);
            int userChoice = Integer.parseInt(JOptionPane.showInputDialog("Please specify what Booking info you want to update:\n"
                    + "Arrival Date enter 1\n"
                    + "Leaving Date enter 2\n"
                    + "Total price amount enter 3\n"
                    + "To change client enter 4\n"
                    + "To change hotel enter 5\n"
                    + "To make more updates enter 6\n"
                    + "To EXIT enter 7"));

            switch (userChoice) {
                case 1:
                    String inputArrival = JOptionPane.showInputDialog("Please enter new arrival date: MM.dd.yy");
                    DateFormat inputFormatArrival = new SimpleDateFormat("MM.dd.yy");
                    Date newDateArrival = inputFormatArrival.parse(inputArrival);
                    foundBooking.setArrivalDate(newDateArrival);
                    break;
                case 2:
                    String inputLeaving = JOptionPane.showInputDialog("Please enter new leaving date: MM.dd.yy");
                    DateFormat inputFormatLeaving = new SimpleDateFormat("MM.dd.yy");
                    Date newDateLeaving = inputFormatLeaving.parse(inputLeaving);
                    foundBooking.setLeaveDate(newDateLeaving);
                    break;
                case 3:
                    foundBooking.setTotalAmount(Double.parseDouble(this.getUserInput("Please enter new total price:")));
                    break;
                case 4:
                    int myClientId = Integer.parseInt(this.getUserInput("Please enter new client id:"));
                    Client foundClient = session.find(Client.class, myClientId);
                    foundBooking.setClient(foundClient);
                    break;
                case 5:
                    int myHotelId = Integer.parseInt(this.getUserInput("Please enter new hotel id:"));
                    Hotel foundHotel = session.find(Hotel.class, myHotelId);
                    foundBooking.setHotel(foundHotel);
                    break;
                case 6:
                    Manager newManager = new Manager();
                    newManager.updateBooking();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose an option from the list");
                    break;
            }

            session.merge(foundBooking); //Makes the update
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}

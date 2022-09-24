package repository;

import controller.Manager;
import model.Bookings;
import model.Client;
import model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class BookingRepository {

    private static SessionFactory factory = SessionManager.getFactory();

    public void createBookingToDB(Bookings booking) {
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
                    + "To change client enter 3\n"
                    + "To change hotel enter 4\n"
                    + "To make more updates enter 5\n"
                    + "To EXIT enter 6"));

            switch (userChoice) {
                case 1:
                    LocalDate newArrivalDate = LocalDate.parse(this.getUserInput("Please enter new arrival date in the following format 2022-12-30"));
                    foundBooking.setArrivalDate(newArrivalDate);
                    foundBooking.setTotalAmount(Period.between(foundBooking.getArrivalDate(),foundBooking.getLeaveDate()).getDays() * foundBooking.getHotel().getPrice());
                    break;
                case 2:
                    LocalDate newLeaveDate = LocalDate.parse(this.getUserInput("please enter your leaving date in the following format 2022-12-30"));
                    foundBooking.setLeaveDate(newLeaveDate);
                    foundBooking.setTotalAmount(Period.between(foundBooking.getArrivalDate(),foundBooking.getLeaveDate()).getDays() * foundBooking.getHotel().getPrice());
                    break;
                case 3:
                    int myClientId = Integer.parseInt(this.getUserInput("Please enter new client id:")); // also personal id, if there are no client I make new client
                    Client foundClient = session.find(Client.class, myClientId);
                    foundBooking.setClient(foundClient);
                    break;
                case 4:
                    int myHotelId = Integer.parseInt(this.getUserInput("Please enter new hotel id:")); // show the hotel list
                    Hotel foundHotel = session.find(Hotel.class, myHotelId);
                    foundBooking.setHotel(foundHotel);
                    break;
                case 5:
                    Manager newManager = new Manager();
                    newManager.updateBooking();
                    break;
                case 6:
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

    public List<Bookings> showAllMyBookingsFromDB() {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Bookings> myBookings = null;
        try {
            transaction = session.beginTransaction();
            myBookings = session.createQuery("FROM bookings", Bookings.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }

        return myBookings;
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}

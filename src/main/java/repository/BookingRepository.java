package repository;

import model.Bookings;
import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
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

    public void deleteBookingsFromDB(Long id) {
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

    public Bookings findBookingFromDBById(Long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
       Bookings booking = null;
        try {
            transaction = session.beginTransaction();
            booking = session.find(Bookings.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }
        return booking;
    }
    public List<Bookings> findBookingsFromDBByPersonalId(Long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Bookings> booking = null;
        Client client = null;
        ClientRepository clientRepository = new ClientRepository();
        try {
            transaction = session.beginTransaction();
            client = clientRepository.findClientByPersonalIdCode(id);
            Query<Bookings> query = session.createQuery("FROM bookings WHERE client =:client",Bookings.class);
            query.setParameter("client",client);
            booking=query.getResultList();
            //booking =session.createQuery("FROM bookings WHERE client ="+client,Bookings.class).getSingleResultOrNull();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }
        return booking;
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}

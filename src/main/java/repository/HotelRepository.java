package repository;

import model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.util.List;


public class HotelRepository {

    private static final SessionFactory factory = SessionManager.getFactory();

    public Hotel createHotelToDB(Hotel hotel) {
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(hotel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            JOptionPane.showMessageDialog(null, "Hotel " + hotel.getHotelName() + " created successfully!");
        }
        return hotel;
    }

    public Hotel deleteHotelFromDB(Long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Hotel hotel = null;

        try {
            transaction = session.beginTransaction();
            hotel = session.find(Hotel.class, id);
            if (hotel != null){
                session.remove(hotel);
               JOptionPane.showMessageDialog(null, "Hotel deleted successfully!");
            }else {
                JOptionPane.showMessageDialog(null, "Sorry, we don't have this hotel!");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }
        return hotel;
    }

    public void updateHotelFromDB(Hotel hotel) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.merge(hotel); // merge update info
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

    public Hotel findHotelFromDBById(Long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Hotel hotel = null;
        try {
            transaction = session.beginTransaction();
            hotel = session.find(Hotel.class, id);
            if (hotel != null) {
                JOptionPane.showMessageDialog(null, hotel.toString());
            } else {
                JOptionPane.showMessageDialog(null,"Sorry, but we don't have hotel with this id");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }
        return hotel;

    }

    public List<Hotel> getAllHotelsFromDB() {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Hotel> myHotels = null;
        try {
            transaction = session.beginTransaction();
            myHotels = session.createQuery("FROM hotels", Hotel.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }

        return myHotels;
    }

    public void updateHotelAvailableRooms(Long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Hotel hotel = session.find(Hotel.class, id);
            if (hotel.getNumberOfRooms() > 0) {
                hotel.setNumberOfRooms((hotel.getNumberOfRooms()) - 1);
            } else {
                JOptionPane.showMessageDialog(null, "Sorry, but we don't have available rooms!");
            }
            session.merge(hotel);
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
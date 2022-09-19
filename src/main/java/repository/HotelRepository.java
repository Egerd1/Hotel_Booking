package repository;

import model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.util.ArrayList;

public class HotelRepository {

    private static SessionFactory factory = SessionManager.getFactory();

    public void createHotelToDB(Hotel hotel) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(hotel);
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

    public void deleteHotelFromDB(int id) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Hotel hotel = session.find(Hotel.class, id);
            session.remove(hotel);
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

    public void updateHotelFromDB(int chosenId) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Hotel foundHotel = session.find(Hotel.class, chosenId);
            int userChoice = Integer.parseInt(JOptionPane.showInputDialog("Please specify what info you want to update:\n"
                    + " for address enter 1\n"
                    + " for Hotel name enter 2\n"
                    + " for number of rooms enter 3\n"
                    + " for change price enter 4\n"));

            if (userChoice == 1) {
                foundHotel.setAddress(this.getUserInput("Please enter new address: "));
            } else if (userChoice == 2) {
                foundHotel.setHotelName(this.getUserInput("Please enter new Hotel name: "));
            } else if (userChoice == 3) {
                int numberOfRooms = Integer.parseInt(this.getUserInput("Please enter new number of rooms: "));
                foundHotel.setNumberOfRooms(numberOfRooms);
            } else if (userChoice == 4) {
                Double pricePerDay = Double.parseDouble(this.getUserInput("Please enter new price: "));
                foundHotel.setPrice(pricePerDay);
            } else {
                System.out.println("Something went wrong!");
                System.exit(0);
            }
            session.merge(foundHotel); // merge update info
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
    public void findHotelFromDBById(int id) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Hotel hotel = session.find(Hotel.class, id);
            System.out.println(hotel);
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
    public ArrayList<Hotel> showAllMyHotelsFromDB(){
        Session session = factory.openSession();
        Transaction transaction = null;
        ArrayList<Hotel> myHotels = null;
        try {
            transaction = session.beginTransaction();
            myHotels = (ArrayList<Hotel>) session.createQuery("FROM hotels", Hotel.class).getResultList();
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
    public void updateHotelAvailableRooms (int id){
        Session session = factory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Hotel hotel = session.find(Hotel.class,id);
            if (hotel.getNumberOfRooms() > 0) {
                hotel.setNumberOfRooms(hotel.getNumberOfRooms() - 1);
            }else {
                System.out.println("Sorry, but we have no available rooms!");
            }
            session.merge(hotel);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        }
        finally {
            session.close();
        }
    }
    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

}

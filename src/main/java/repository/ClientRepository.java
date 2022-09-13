package repository;

import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.util.ArrayList;

public class ClientRepository {

    private static SessionFactory factory = SessionManager.getFactory();

    public void createClient(Client client) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(client);
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

    public void deleteClientFromDB(int id) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Client client = session.find(Client.class, id);
            session.remove(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }
        System.out.println("You deleted client with id " + id);
    }

    public void updateClientFromDB(Long clientId) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Client foundClient = session.find(Client.class, clientId);
            int userChoice = Integer.parseInt(JOptionPane.showInputDialog("Please specify what info you want to update:3\n" + " for firstName enter 1\n" + " for lastname enter 2\n" + " for age enter 3"));

            if (userChoice == 1) {
                foundClient.setFirstName(this.getUserInput("Please enter new firstname: "));
            } else if (userChoice == 2){
                foundClient.setLastName(this.getUserInput("Please enter new lastname: "));
            }else if (userChoice == 3){
                foundClient.setAge(this.getUserInput("Please enter new age: "));
            }else{
                System.out.println("Something went wrong!");
                System.exit(0);
            }
            session.merge(foundClient);
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

    public void findClientFromDBById(int id) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Client client = session.find(Client.class, id);
            System.out.println(client);
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

    public ArrayList<Client> showAllMyClientsFromDB(){
        Session session = factory.openSession();
        Transaction transaction = null;
        ArrayList<Client> petFoods = null;
        try {
            transaction = session.beginTransaction();
            petFoods = (ArrayList<Client>) session.createQuery("FROM clients", Client.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }

        return petFoods;
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}

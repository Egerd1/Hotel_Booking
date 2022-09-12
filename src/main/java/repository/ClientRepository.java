package repository;

import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;

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
            int userChoice = Integer.parseInt(JOptionPane.showInputDialog("Please specify what info you want to update\n"+" for firstName enter 1 and for lastname enter 2"));

            if (userChoice == 1) {
                foundClient.setFirstName(this.getUserInput("Please enter new firstname: "));
            } else if (userChoice == 2){
                foundClient.setLastName(this.getUserInput("Please enter new lastname: "));
            }else{
                System.out.println("Something went wrong!");
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

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}

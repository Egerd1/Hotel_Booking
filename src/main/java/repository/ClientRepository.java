package repository;

import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.util.List;

public class ClientRepository {

    private static SessionFactory factory = SessionManager.getFactory();

    public Client createClientToDB(Client client) {
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
        return client;
    }

    public void deleteClientFromDB(int id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Client client = null;
        try {
            transaction = session.beginTransaction();
            client = session.createQuery("FROM clients WHERE personalId = " + id, Client.class).getSingleResultOrNull();
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
    }

    public void updateClientInfo(Client client) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.merge(client);
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

    public Client findClientByPersonalIdCode(Long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Client client = null;

        try {
            transaction = session.beginTransaction();
            client = session.createQuery("FROM clients WHERE personalId = " + id, Client.class).getSingleResultOrNull();
            if (client != null) {
                JOptionPane.showMessageDialog(null, "Hello again " + client.getFirstName());
            } else {
                JOptionPane.showMessageDialog(null,"You don't have an account with us please follow steps to register in our system");
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
        return client;
    }

    public List<Client> showAllMyClientsFromDB() {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Client> myClients = null;
        try {
            transaction = session.beginTransaction();
            myClients = session.createQuery("FROM clients", Client.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + " : " + e.getMessage());
        } finally {
            session.close();
        }

        return myClients;
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}

package repository;

import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        try {

            Client foundClient = session.find(Client.class, clientId);
            System.out.println(foundClient);
            System.out.println("please specify what what info you want to update \n " + "for firstName enter 1 and for lastname enter 2");
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice == 1) {
                System.out.println("please enter your new name");
                foundClient.setFirstName(scanner.nextLine());
            } else {
                System.out.println("please enter you new lastname");
                foundClient.setLastName(scanner.nextLine());
            }
            session.merge(foundClient);
            transaction.commit();


        } catch (Exception e) {
            System.out.println(e.getClass() + " : " + e.getMessage());

        } finally {
            session.close();
        }

    }
}

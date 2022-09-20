package repository;

import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
        Client client = null;
        try {
            transaction = session.beginTransaction();
            client = session.createQuery("FROM clients WHERE personalID = " + id, Client.class).getSingleResultOrNull();
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
            int userChoice = Integer.parseInt(JOptionPane.showInputDialog("Please specify what info you want to update:\n"
                    + " for personal ID enter 1\n"
                    + " for firstname enter 2\n"
                    + " for lastname enter 3\n"
                    + " for age enter 4"));
            if (userChoice == 1) {
                int personalIdCode = Integer.parseInt(this.getUserInput("Please enter new personal ID code: "));
                foundClient.setPersonalId(personalIdCode);
            } else if (userChoice == 2) {
                foundClient.setFirstName(this.getUserInput("Please enter new firstname: "));
            } else if (userChoice == 3) {
                foundClient.setLastName(this.getUserInput("Please enter new lastname: "));
            } else if (userChoice == 4) {
                foundClient.setAge(this.getUserInput("Please enter new age: "));
            } else {
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

    public void findClientByPersonalIdCode(Long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Client client = null;

        try {
            transaction = session.beginTransaction();
            client = session.createQuery("FROM clients WHERE personalID = " + id, Client.class).getSingleResultOrNull();
            if (client != null) {
                System.out.println("thanks for choosing us again " + client.getFirstName());
            } else {
                System.out.println("you don't have an account with us please follow steps to register in our system");
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
    }

    public Client findClientByFirstName(String clientName) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Client client = null;
        List<Client> allMyClients = null;
        try {
            transaction = session.beginTransaction();
            Query<Client> myQuery = session.createQuery("FROM clients WHERE firstName =:firstName ", Client.class);
            myQuery.setParameter("firstName", clientName);
            allMyClients = myQuery.getResultList();
            client = allMyClients.get(0);
            if (client != null) {
                System.out.println("thanks for choosing us again " + client.getFirstName());
            } else {
                System.out.println("you don't have an account with us please follow steps to register in our system");
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

    public ArrayList<Client> showAllMyClientsFromDB() {
        Session session = factory.openSession();
        Transaction transaction = null;
        ArrayList<Client> myClients = null;
        try {
            transaction = session.beginTransaction();
            myClients = (ArrayList<Client>) session.createQuery("FROM clients", Client.class).getResultList();
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

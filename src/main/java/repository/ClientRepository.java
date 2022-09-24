package repository;

import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class ClientRepository {

    private static SessionFactory factory = SessionManager.getFactory();

    public Client createClient(Client client) {
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

    public void updateClientInfo(Long clientId) { // Not id but personal id
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
                long personalIdCode = Long.parseLong(this.getUserInput("Please enter new personal ID code: "));
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

    public Client findClientByPersonalIdCode(Long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Client client = null;

        try {
            transaction = session.beginTransaction();
            client = session.createQuery("FROM clients WHERE personalId = " + id, Client.class).getSingleResultOrNull();
            if (client != null) {
                System.out.println("Hello again " + client.getFirstName());
            } else {
                System.out.println("You don't have an account with us please follow steps to register in our system");
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
                System.out.println(client.getFirstName());
            } else {
                System.out.println("Sorry, but you are not in our system");
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

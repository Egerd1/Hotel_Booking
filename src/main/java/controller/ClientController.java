package controller;

import model.Client;
import repository.ClientRepository;

import javax.swing.*;

public class ClientController {

   private final ClientRepository clientRepository = new ClientRepository();


    public Client createClient() {
        Client client = new Client();
        client.setPersonalId(Long.parseLong(this.getUserInput("Please enter your personal id code: ")));
        client.setFirstName(this.getUserInput("Please enter your name: "));
        client.setLastName(this.getUserInput("Please enter your last name: "));
        client.setAge(this.getUserInput("Please enter your age: "));

        return clientRepository.createClientToDB(client);
    }

    public void deleteClient() {
        int chosenId = Integer.parseInt(this.getUserInput("Please enter the client personal id code to be removed"));
        clientRepository.deleteClientFromDB(chosenId);
    }

    public void updateClient() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("Please enter the client personal id from database to be updated"));
        Client foundClient = clientRepository.findClientByPersonalIdCode(chosenId);
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
        clientRepository.updateClientInfo(foundClient);
    }

    public Client findClientByPersonalId() {
        Long chosenId = (long) Integer.parseInt(this.getUserInput("To view client, please enter the client personal id"));
        return clientRepository.findClientByPersonalIdCode(chosenId);
    }

    public void viewAllMyClients() {
        JOptionPane.showInputDialog(clientRepository.showAllMyClientsFromDB());
//        System.out.println(clientRepository.showAllMyClientsFromDB());
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

}

package controller;

import model.Client;
import repository.ClientRepository;

import javax.swing.*;

public class ClientController {

    ClientRepository clientRepository = new ClientRepository();

    public Client createClient() {
        Client client = new Client();
        client.setPersonalId(Long.parseLong(this.getUserInput("Please enter your personal id code: ")));
        client.setFirstName(this.getUserInput("Please enter your name: "));
        client.setLastName(this.getUserInput("Please enter your last name: "));
        client.setAge(this.getUserInput("Please enter your age: "));

        return clientRepository.createClient(client);
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}

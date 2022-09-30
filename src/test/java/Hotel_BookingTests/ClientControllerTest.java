package Hotel_BookingTests;

import controller.ClientController;
import model.Client;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.ClientRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
    private  ClientController clientController;

    @Mock
    private static ClientRepository clientRepositoryMock;

    @BeforeEach
    void setUp(){
        clientController = new ClientController(clientRepositoryMock);
    }


    @Test
    @Order(1)
    void testCreateClient() {

        Client client = new Client(1l,1222l,"hasan","karim",30);
        clientRepositoryMock.createClientToDB(client);
        ArgumentCaptor<Client> myClient = ArgumentCaptor.forClass(Client.class);
        verify(clientRepositoryMock).createClientToDB(myClient.capture());
        Client client1 = myClient.getValue();
        assertThat(client).isEqualTo(client1);
        Assertions.assertEquals(client,client1);
    }

    @Test
    @Order(2)
    void testDeleteClient() {
        Client client = new Client(2l,1234l,"david","anderson",30);
        clientController.deleteClient();
        Mockito.verify(clientRepositoryMock).deleteClientFromDB(1234l);
    }

    @Test
    @Order(3)
    void testUpdateClient() {
        Client client = new Client(3l,1235l,"john","didi",38);
        client.setAge(41);
        client.setLastName("dodo");
        clientRepositoryMock.updateClientInfo(client);

        ArgumentCaptor<Client> myClient = ArgumentCaptor.forClass(Client.class);

        verify(clientRepositoryMock).updateClientInfo(myClient.capture());

        Client mockClient = myClient.getValue();
        assertThat(mockClient).isEqualTo(client);
        Assertions.assertEquals(mockClient,client);

    }

    @Test
    @Order(4)
    void testFindClientByPersonalId() {
        Client client = new Client(3l,1235l,"james","andra",20);
        clientController.findClientByPersonalId();
        verify(clientRepositoryMock).findClientByPersonalIdCode(1235l);
    }

    @Test
    @Order(5)
    void viewAllMyClients() {
        clientController.viewAllMyClients();
        verify(clientRepositoryMock).showAllMyClientsFromDB();
    }
}
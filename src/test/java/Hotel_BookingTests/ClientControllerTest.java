package Hotel_BookingTests;

import controller.ClientController;
import model.Client;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.ClientRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
    @Mock
    private ClientRepository clientRepository;
    private ClientController underTest;

    @BeforeEach
    void setUp() {
        underTest = new ClientController(clientRepository);
    }


    @Test
    @Order(1)
    void testCreateClient() {
        // given
        Client client = new Client(1l, 1222l, "hasan", "karim", 30);

        // when
        clientRepository.createClientToDB(client);

        // then
        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).createClientToDB(clientArgumentCaptor.capture());
        Client capturedClient = clientArgumentCaptor.getValue();
        assertThat(capturedClient).isEqualTo(client);

    }

    @Test
    @Order(2)
    void testDeleteClient() {
        // given
        Client client = new Client(2l, 1234l, "david", "anderson", 30);

        // when
        underTest.deleteClient();

        // then
        verify(clientRepository).deleteClientFromDB(1234l);
    }

    @Test
    @Order(3)
    void testUpdateClient() {
        // given
        Client client = new Client(3l, 1235l, "john", "didi", 38);

        // when
        client.setAge(41);
        client.setLastName("dodo");
        clientRepository.updateClientInfo(client);

        // then
        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).updateClientInfo(clientArgumentCaptor.capture());
        Client capturedClient = clientArgumentCaptor.getValue();
        assertThat(capturedClient).isEqualTo(client);

    }

    @Test
    @Order(4)
    void testFindClientByPersonalId() {
        // given
        Client client = new Client(3l, 1235l, "james", "andra", 20);

        // when
        underTest.findClientByPersonalId();

        // then
        verify(clientRepository).findClientByPersonalIdCode(1235l);
    }

    @Test
    @Order(5)
    void viewAllMyClients() {
        // when
        underTest.viewAllMyClients();

        // then
        verify(clientRepository).showAllMyClientsFromDB();
    }
}
package Hotel_BookingTests;

import controller.HotelController;
import model.Hotel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.HotelRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {
    @Mock
    private HotelRepository hotelRepository;
    private HotelController hotelController;

    @BeforeEach
    public void setUp() {
        hotelController = new HotelController(hotelRepository);
    }

    @Test
    @Order(1)
    void testMakeNewHotel() {
        Hotel hotel = new Hotel(1L, "Kolm Kuningat", "Paide", 50, 79.0);
        hotelRepository.createHotelToDB(hotel);

        ArgumentCaptor<Hotel> myHotel = ArgumentCaptor.forClass(Hotel.class);

        verify(hotelRepository).createHotelToDB(myHotel.capture());

        Hotel capturedHotel = myHotel.getValue();
        assertThat(capturedHotel).isEqualTo(hotel);
    }

    @Test
    @Order(2)
    void testUpdateHotel() {
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);
        hotel.setNumberOfRooms(45);
        hotel.setPrice(69.0);
        hotelRepository.updateHotelFromDB(hotel);

        ArgumentCaptor<Hotel> myHotel = ArgumentCaptor.forClass(Hotel.class);

        verify(hotelRepository).updateHotelFromDB(myHotel.capture());

        Hotel capturedHotel = myHotel.getValue();
        assertThat(capturedHotel).isEqualTo(hotel);

    }

    @Test
    @Order(3)
    void testDeleteHotel() {
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);
        hotelController.deleteHotel();
        verify(hotelRepository).deleteHotelFromDB(2L);

    }

    @Test
    @Order(4)
    void testGetAllHotels() {
        List<Hotel> hotels = hotelRepository.getAllHotelsFromDB();
        assertThat(hotels).isNotNull();

    }

    @Test
    @Order(5)
    void testFindHotelById() {
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);
        hotelController.findHotelById();
        verify(hotelRepository).findHotelFromDBById(2L);
    }

}


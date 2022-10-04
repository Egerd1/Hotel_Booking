package Hotel_BookingTests;

import controller.HotelController;
import model.Hotel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.HotelRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class HotelControllerTest {
    @Mock
    private HotelRepository hotelRepository;
    private HotelController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new HotelController(hotelRepository);
    }

    @Test
    @Order(1)
    void testMakeNewHotel() {
        // given
        Hotel hotel = new Hotel(1L, "Kolm Kuningat", "Paide", 50, 79.0);

        // when
        hotelRepository.createHotelToDB(hotel);

        // then
        ArgumentCaptor<Hotel> hotelArgumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).createHotelToDB(hotelArgumentCaptor.capture());
        Hotel capturedHotel = hotelArgumentCaptor.getValue();
        assertThat(capturedHotel).isEqualTo(hotel);
    }

    @Test
    @Order(2)
    void testUpdateHotel() {
        // given
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);

        // when
        hotel.setNumberOfRooms(45);
        hotel.setPrice(69.0);
        hotelRepository.updateHotelFromDB(hotel);

        // then
        ArgumentCaptor<Hotel> hotelArgumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).updateHotelFromDB(hotelArgumentCaptor.capture());
        Hotel capturedHotel = hotelArgumentCaptor.getValue();
        assertThat(capturedHotel).isEqualTo(hotel);
    }

    @Test
    @Order(3)
    void testDeleteHotel() {
        // given
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);

        // when
        underTest.deleteHotel();

        // then
        verify(hotelRepository).deleteHotelFromDB(hotel.getId());

    }

    @Test
    @Order(4)
    void testGetAllHotels() {
        // when
        underTest.getAllHotels();

        // then
        verify(hotelRepository).getAllHotelsFromDB();

    }

    @Test
    @Order(5)
    void testFindHotelById() {
        // given
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);

        // when
        underTest.findHotelById();

        // then
        verify(hotelRepository).findHotelFromDBById(hotel.getId());
    }

}


package Hotel_BookingTests;


import controller.BookingController;
import model.Bookings;
import model.Client;
import model.Hotel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.BookingRepository;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

    @Mock
    private BookingRepository bookingRepository;
    private BookingController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BookingController(bookingRepository);
    }

    @Test
    @Order(1)
    void testCreateNewBooking() {
        // given
        Client client = new Client(1L, 1222L, "hasan", "karim", 30);
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);
        Bookings myBooking = new Bookings(1L, LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 3), client, hotel, 237.0);

        // when
        bookingRepository.createBookingToDB(myBooking);

        // then
        ArgumentCaptor<Bookings> bookingsArgumentCaptor = ArgumentCaptor.forClass(Bookings.class);
        verify(bookingRepository).createBookingToDB(bookingsArgumentCaptor.capture());
        Bookings capturedBooking = bookingsArgumentCaptor.getValue();
        assertThat(capturedBooking).isEqualTo(myBooking);
    }

    @Test
    @Order(2)
    void testDeleteBooking() {
        // given
        Client client = new Client(1L, 1222L, "hasan", "karim", 30);
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);
        Bookings myBooking = new Bookings(1L, LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 3), client, hotel, 237.0);

        // when
        underTest.deleteBooking();

        // then
        verify(bookingRepository).deleteBookingsFromDB(myBooking.getId());

    }

    @Test
    @Order(3)
    void testUpdateBooking() {
        // given
        Client client = new Client(1L, 1222L, "hasan", "karim", 30);
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);
        Bookings myBooking = new Bookings(1L, LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 3), client, hotel, 237.0);
        Hotel hotel1 = new Hotel(3L, "Neli Kuningat", "Paide", 70, 89.0);myBooking.setHotel(hotel1);

       // when
        bookingRepository.updateBookingFromDB(myBooking);

        // then
        ArgumentCaptor<Bookings> bookingsArgumentCaptor = ArgumentCaptor.forClass(Bookings.class);
        verify(bookingRepository).updateBookingFromDB(bookingsArgumentCaptor.capture());
        Bookings capturedBooking = bookingsArgumentCaptor.getValue();
        assertThat(capturedBooking).isEqualTo(myBooking);
    }

    @Test
    @Order(4)
    void testViewAllMyBookings() {
        // when
        underTest.viewAllMyBookings();

        // then
        verify(bookingRepository).showAllMyBookingsFromDB();
    }

    @Test
    @Order(5)
    void testFindBookingById() {
        // given
        Client client = new Client(3L, 1235L, "james", "andra", 20);
        Hotel hotel = new Hotel(1L, "Radisson", "Tallinn", 40, 59.00);
        double totalAmount = 236.00;
        Bookings bookings = new Bookings(1L, LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 5), client, hotel, totalAmount);

        // when
        underTest.findBookingById();

        // then
       verify(bookingRepository).findBookingFromDBById(bookings.getId());

    }
}

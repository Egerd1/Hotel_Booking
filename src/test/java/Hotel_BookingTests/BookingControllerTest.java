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

import static org.mockito.Mockito.verify;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

    @Mock
    private BookingRepository bookingRepository;
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        bookingController = new BookingController(bookingRepository);
    }

    @Test
    @Order(1)
    void testCreateNewBooking() {
        Client client = new Client(1l, 1222l, "hasan", "karim", 30);
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);
        Bookings myBooking = new Bookings(1l, LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 3), client, hotel, 237.0);
        bookingRepository.createBookingToDB(myBooking);
        ArgumentCaptor<Bookings> booking = ArgumentCaptor.forClass(Bookings.class);

        verify(bookingRepository).createBookingToDB(booking.capture());

        Bookings madeBooking = booking.getValue();
        Assertions.assertEquals(madeBooking, myBooking);
    }

    @Test
    @Order(2)
    void testDeleteBooking() {
        Client client = new Client(1l, 1222l, "hasan", "karim", 30);
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);
        Bookings myBooking = new Bookings(1l, LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 3), client, hotel, 237.0);
        bookingController.deleteBooking();
        verify(bookingRepository).deleteBookingsFromDB(1L);

    }

    @Test
    @Order(3)
    void testUpdateBooking() {
        Client client = new Client(1l, 1222l, "hasan", "karim", 30);
        Hotel hotel = new Hotel(2L, "Kolm Kuningat", "Paide", 50, 79.0);
        Bookings myBooking = new Bookings(1l, LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 3), client, hotel, 237.0);
        Hotel hotel1 = new Hotel(3L, "Neli Kuningat", "Paide", 70, 89.0);
        myBooking.setHotel(hotel1);
        bookingRepository.updateBookingFromDB(myBooking);

        ArgumentCaptor<Bookings> booking = ArgumentCaptor.forClass(Bookings.class);

        verify(bookingRepository).updateBookingFromDB(booking.capture());

        Bookings capturedBooking = booking.getValue();
        Assertions.assertEquals(capturedBooking, myBooking);
    }

    @Test
    @Order(4)
    void testViewAllMyBookings() {
        bookingController.viewAllMyBookings();
        verify(bookingRepository).showAllMyBookingsFromDB();
    }
    @Test
    @Order(5)
    void testFindBookingById() {
        Client client = new Client(3l, 1235l, "james", "andra", 20);
        Hotel hotel = new Hotel(1l, "Radisson", "Tallinn", 40, 59.00);
        double totalAmount = 236.00;
        Bookings bookings = new Bookings(1l, LocalDate.of(2022, 10, 01), LocalDate.of(2022, 10, 05), client, hotel, totalAmount);
        bookingController.findBookingById();
        bookingRepository.findBookingFromDBById(1l);

    }
}

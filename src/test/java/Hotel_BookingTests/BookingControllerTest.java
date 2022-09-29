package Hotel_BookingTests;


import controller.BookingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.BookingRepository;

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
    void testCreateNewBooking() {
    }

    @Test
    void testDeleteBooking() {
    }

    @Test
    void testUpdateBooking() {
    }

    @Test
    void testviewAllMyBookings() {
    }

    @Test
    void testFindBookingById() {
    }
}

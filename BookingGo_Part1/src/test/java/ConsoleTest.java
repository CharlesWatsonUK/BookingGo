import com.BookingGo.Global;
import com.BookingGo.Main;
import com.BookingGo.Ride;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Test the console application with JUnit5 tests.
 * Testing with various erroneous and valid parameters.
 *
 * Here the standard output (console output) is redirected to a stream
 * for our test processing.
 *
 */

public class ConsoleTest {

    private static final PrintStream stdOut = System.out;
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private Main application;

    @BeforeAll
    static void setup() throws UnsupportedEncodingException {
        System.setOut(new PrintStream(output, true, "UTF-8"));
    }

    @BeforeEach
    void beforeEach(){
        // Make sure we reset the stream before each test.
        output.reset();
    }

    @Test
    @DisplayName("Testing valid input...")
    void testValidInput1() {
        application.main(new String[] {"51.470020,-0.454295", "51.00000,1.0000", "3"});
        String[] outputLines = TestUtils.splitStringLines(output.toString());
        List<Ride> rides = TestUtils.serializeRides(outputLines);
        Assertions.assertTrue(TestUtils.testAll(rides, 3));
    }

    @Test
    @DisplayName("Testing valid input...")
    void testValidInput2() {
        application.main(new String[] {"51.470020,-0.454295", "51.00000,1.0000", "6"});
        String[] outputLines = TestUtils.splitStringLines(output.toString());
        List<Ride> rides = TestUtils.serializeRides(outputLines);
        System.out.println(outputLines[1]);
        Assertions.assertTrue(TestUtils.testAll(rides, 6));
    }

    @Test
    @DisplayName("Testing valid input...")
    void testValidInput3() {
        application.main(new String[] {"51.470020,-0.454295", "51.00000,1.0000", "16"});
        String[] outputLines = TestUtils.splitStringLines(output.toString());
        List<Ride> rides = TestUtils.serializeRides(outputLines);
        Assertions.assertTrue(TestUtils.testAll(rides, 16));
    }

    @Test
    @DisplayName("Testing invalid input - passengers > 16")
    void testInvalidPassengers(){
        application.main(new String[] {"51.470020,-0.454295", "51.00000,1.0000", "18"});
        String[] outputLines = TestUtils.splitStringLines(output.toString());
        // Multi line message - check match on first line.
        Assertions.assertEquals(outputLines[0], Global.MESSAGE_INVALID_INPUT.split("\\r?\\n")[0]);
    }

    @Test
    @DisplayName("Testing invalid input - bad coordinates")
    void testInvalidCoordinates(){
        application.main(new String[] {"51.470020", "51.00000,1.0000", "4"});
        String[] outputLines = TestUtils.splitStringLines(output.toString());
        // Mutli line message - check match on first line.
        Assertions.assertEquals(outputLines[0], Global.MESSAGE_INVALID_INPUT.split("\\r?\\n")[0]);
    }

    @AfterAll
    static void cleanup(){
        // Revert output to stdout.
        System.setOut(stdOut);
    }

}

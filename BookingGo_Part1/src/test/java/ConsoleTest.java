import com.BookingGo.CarType;
import com.BookingGo.Main;
import com.BookingGo.Ride;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleTest {

    private static final PrintStream stdOut = System.out;
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private Main application;

    @BeforeAll
    static void setup() throws UnsupportedEncodingException {
        System.setOut(new PrintStream(output, true, "UTF-8"));
    }

    @Test
    void test() {
        application.main(new String[] {"51.470020,-0.454295", "51.00000,1.0000", "4"});
        System.setOut(stdOut);
        String[] outputLines = TestUtils.splitStringLines(output.toString());
        List<Ride> rides = TestUtils.serializeRides(outputLines);

        Assertions.assertTrue(TestUtils.testAll(rides, 4));
    }

    @AfterAll
    static void cleanup(){
        System.setOut(stdOut);
    }

}

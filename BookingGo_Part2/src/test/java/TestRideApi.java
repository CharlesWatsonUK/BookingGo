import com.BookingGo.Application;
import com.BookingGo.api.RideApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(RideApi.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=Application.class)
public class TestRideApi {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RideApi rideApi;

    /**
     * Test to ensure the API gives us a 200
     * with valid JSON when the params are valid.
     *
     * @throws Exception
     */
    @Test
    public void testValid() throws Exception {
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/ride?pickup=51.470020,-0.454295&dropoff=51.470021,-0.454280&passengers=4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

import com.BookingGo.Application;
import com.BookingGo.Global;
import com.BookingGo.model.response.ResponseOk;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Spring Boot integration test.
 * Tests the entire MVC application end to end.
 *
 * Note: for more rigorous testing of different ride
 *       search parameters please see the unit test
 *       RideServiceTest.
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {Application.class})
public class RideControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test a valid request to the ride api.
     *  - Check response status 200.
     *  - Check JSON structure of response.
     *  - Check rides returned are valid, filtered and
     *     in order.
     *
     * @throws Exception
     */
    @Test
    public void test_valid_request() throws Exception{
        String pickup = "51.470020,-0.454295";
        String dropoff = "51.470021,-0.454280";
        int passengers = 4;

        // Check structure of response JSON.
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ride?pickup={0}&dropoff={1}&passengers={2}",
                pickup, dropoff, passengers)
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.pickup", is(pickup)))
            .andExpect(jsonPath("$.dropoff", is(dropoff)))
            .andExpect(jsonPath("$.passengers", is(passengers)))
            .andReturn();

        // Check rides returned are valid, filtered and in order.
        ResponseOk response = serializeOkResponse(result.getResponse().getContentAsString());
        boolean rideResultsValid = TestUtils.testAll(response.getRides(), passengers);
        Assert.assertTrue(rideResultsValid);
    }

    /**
     * Test an invalid request to the api.
     *  - Check response status 400.
     *  - Check JSON structure of response.
     *  - Check message returned is correct.
     *
     * @throws Exception
     */
    @Test
    public void test_invalid_request() throws Exception{
        String pickup = "51.470020,-0.454295";
        String dropoff = "51.470021,-0.454280";
        int passengers = 20;

        // Check structure of response JSON.
        mockMvc.perform(MockMvcRequestBuilders.get("/ride?pickup={0}&dropoff={1}&passengers={2}",
                pickup, dropoff, passengers)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.pickup", is(pickup)))
                .andExpect(jsonPath("$.dropoff", is(dropoff)))
                .andExpect(jsonPath("$.passengers", is(passengers)))
                .andExpect(jsonPath("$.message", is(Global.MESSAGE_INVALID_INPUT)))// Check message is correct.
                .andReturn();
    }


    /**
     * Helper method to serialize an OK response.
     *
     * @param response
     * @return
     */
    private ResponseOk serializeOkResponse(String response){
        Gson gson = new Gson();
        return gson.fromJson(response, ResponseOk.class);
    }

}
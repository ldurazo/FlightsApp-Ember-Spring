package test;

import com.models.TripOption.TripOption;
import com.utils.InputStreamProcessor;
import com.utils.TestHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FlightsControllerTest {
    private static String uri = "http://localhost:8080/mvn-webapp-flights/getAvailableFlights";
    private static TestHttpClient clientForTest;
    private static HttpResponse response;


    @BeforeClass
    public static void setUp() throws Exception {
        clientForTest = new TestHttpClient(uri);
        response = clientForTest.getHttpGetResponse();
        System.out.println(response);
    }

    //Dummy link to perform a getAvailableFlights with this API change date if it already passed
    //http://localhost:8080/mvn-webapp-flights/getAvailableFlights?origin=SFO&destination=MEX&passengers=1&date=2014-12-19
    @Test
    public void searchResultShouldBeOk() throws ClientProtocolException {
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void serializeResponse() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = InputStreamProcessor.readStream(response.getEntity().getContent());
        System.out.println(responseBody);
        TripOption tripOption = objectMapper.readValue(responseBody, TripOption.class);
        assertNotNull(tripOption);
    }
}

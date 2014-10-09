package test;

import com.controllers.ReservationController;
import com.utils.TestHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class ReservationControllerTest {
    private static String uri = "http://localhost:8080/mvn-webapp-flights/" + ReservationController.RESERVATION_ENDPOINT;
    private static String jsonString = "{\"name\": \"luis\",\"last_name\":\"Durazo\",\"cost\":\"254.5\",\"email\":\"ldurazo@nearsoft.com\"}";
    private static String jsonStringWithFlights = "{ \"name\": \"luis\",  \"last_name\": \"durazo\",   \"passengers\": 4, \"flights\": [ { \"departure_date\": \"2014/10/31\", \"arrival_date\": \"2014/11/01\",  \"departure_airport\": \"MEX\",   \"arrival_airport\": \"LAX\", \"travel_minutes\": \"230\"  }, { \"departure_date\": \"2014/10/31\",  \"arrival_date\": \"2014/11/01\",  \"departure_airport\": \"LAX\", \"arrival_airport\": \"SFO\",   \"travel_minutes\": \"100\" }, {   \"departure_date\": \"2014/10/31\",     \"arrival_date\": \"2014/11/01\",  \"departure_airport\": \"SFO\", \"arrival_airport\": \"KOR\", \"travel_minutes\": \"400\"} ],\"cost\": \"3456.45\",\"email\": \"ldurazo@nearsoft.com\"}";
    private static TestHttpClient clientForTest;
    private static HttpResponse response;

    @BeforeClass
    public static void setUp() throws Exception {
        clientForTest = new TestHttpClient(uri, jsonString);
        response = clientForTest.getHttpPostResponse();
    }

    @Test
    public void responseShouldBeOk() throws Exception {
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void serializeResponse() throws IOException {
        assertNotEquals(String.valueOf(response.getEntity().getContent()), -1);
    }
}

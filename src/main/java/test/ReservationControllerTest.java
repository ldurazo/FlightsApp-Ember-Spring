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
    private static String jsonStringWithFlights = "{ \"name\": \"luis\",  \"last_name\": \"durazo\",   \"passengers\": 4, \"flights\": [ { \"departureTime\": \"2014/10/31\", \"arrivalTime\": \"2014/11/01\",  \"origin\": \"MEX\",   \"destination\": \"LAX\"  }],\"cost\": \"3456.45\",\"email\": \"ldurazo@nearsoft.com\"}";
    private static TestHttpClient clientForTest;
    private static HttpResponse response;

    @BeforeClass
    public static void setUp() throws Exception {
        clientForTest = new TestHttpClient(uri, jsonStringWithFlights);
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

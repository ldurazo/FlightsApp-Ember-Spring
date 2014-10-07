package test;

import com.controllers.ReservationController;
import com.utils.TestHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class ReservationControllerTest {
    private String uri = "http://localhost:8080/mvn-webapp-flights/" + ReservationController.RESERVATION_ENDPOINT;
    private String jsonString = "{\"name\":\"luis\"}";
    private TestHttpClient clientForTest;
    private HttpResponse response;

    @Before
    public void setUp() throws Exception {
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

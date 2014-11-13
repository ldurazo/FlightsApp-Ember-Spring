package test;

import com.services.AutocompleteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
public class AutocompleteServiceTest {
    private String userInput = "San F";

    @Autowired
    private static AutocompleteService autocompleteService;

    @Test
    public void shouldReturnSuggestions() throws Exception {
        assertNotNull(autocompleteService.getAirportSuggestions(userInput));
    }

}

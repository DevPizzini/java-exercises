package it.iol.ws;

import it.iol.ws.util.IApplicationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApplicationPropertiesTest {

    @Autowired
    private IApplicationProperties applicationProperties;

    @Test
    public void appProp() {
        // lookup in application-it.properties
        assertEquals(applicationProperties.getFoo(), "it");
    }
}
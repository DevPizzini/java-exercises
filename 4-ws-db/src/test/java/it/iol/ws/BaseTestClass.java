package it.iol.ws;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class BaseTestClass {

    @Autowired
    private Environment environment;

    @Autowired
    @Qualifier("jdbcTemplate1")
    protected JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeAll() {
        assertEquals("it", environment.getProperty("spring.profiles.active"), "use -Dspring.profiles.active=it");
        try {
            jdbcTemplate.execute("DROP TABLE employee");
        } catch (Exception e) {
            // ignore if table doesn't exist
        }
        try {
            jdbcTemplate.execute("CREATE TABLE employee (\n" +
                    "name varchar(25) NOT NULL,\n" +
                    "role varchar(25) NOT null,\n" +
                    "department varchar(15) null,\n" +
                    "CONSTRAINT name_pkey PRIMARY KEY (name)\n" +
                    ");");
        } catch (Exception e) {
            assert e.getMessage().contains("already exists") : "error on creating table " + e.getMessage();
        }
    }

}

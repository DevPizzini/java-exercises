package it.iol.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.iol.ws.util.JsonHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.*;

@SpringBootTest
public class JsonTest {

    private static Staff getStaff() {
        val position = Arrays.asList("manager", "hr");

//      val skills = List.of("foo", "bar"); //java 11
        val skills = new ArrayList<>(Arrays.asList("foo", "bar"));

        val salary = new HashMap<String, Integer>();
        salary.put("abc", 10000);
        salary.put("def", 20000);

        return new Staff("bob", 30, position, skills, salary);
    }

    /**
     * Java object to JSON string
     *
     * @throws IOException
     */
    @Test
    public void objectToJsonString() throws IOException {
        val staff = getStaff();
        val jsonString = JsonHelper.objectToString(staff);

        assert (jsonString.equals("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":{\"abc\":10000,\"def\":20000}}"));
    }

    /**
     * JSON string to java object
     *
     * @throws IOException
     */
    @Test
    public void jsonStringToObject() throws JsonProcessingException {
        val staff = getStaff();
        val jsonString = JsonHelper.objectToString(staff);
        val s = JsonHelper.stringToObject(jsonString, Staff.class);
        assert (s.toString().equals(staff.toString()));

    }

    /**
     * Java object to JSON string
     *
     * @throws IOException
     */
    @Test
    public void objectToJsonStringSalaryNull() throws JsonProcessingException {
        val staff = getStaff();
        staff.setSalary(null);
        val jsonString = JsonHelper.objectToString(staff);
        assert (jsonString.equals("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":null}"));
    }

    /**
     * java object to jsonNode
     */
    @Test
    public void objectToJson() {
        val staff = getStaff();
        val json = JsonHelper.objectToJson(staff);
        assert ("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":{\"abc\":10000,\"def\":20000}}".equals(json.toString()));
    }

    /**
     * jsonNode to java object
     */
    @Test
    public void jsonToObject() {
        val staff = getStaff();
        val json = JsonHelper.objectToJson(staff);
        val staff2 = JsonHelper.jsonToObject(json, Staff.class);
        assert (staff.equals(staff2));
    }
}

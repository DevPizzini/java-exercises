import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.*;

import it.iol.json.JsonHelper;
import it.iol.json.model.Staff;
import org.junit.jupiter.api.Test;

public class JsonTest {
    private static Staff getStaff() {
        List<String> position = Arrays.asList("manager", "hr");

        java.util.List<String> skills = Arrays.asList("foo", "bar");

        Map<String, Integer> salary = new HashMap();
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
    public void javaToJsonString() throws IOException {
        Staff staff = getStaff();
        String jsonString = JsonHelper.objectToString(staff);
        System.out.println("jsonString: " + JsonHelper.objectToPrettyString(staff));

        assert (jsonString.equals("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":{\"abc\":10000,\"def\":20000}}"));
    }

    /**
     * JSON string to java object
     *
     * @throws IOException
     */
    @Test
    public void jsonStringToJava() throws JsonProcessingException {
        Staff staff = getStaff();
        String jsonString = JsonHelper.objectToString(staff);
        Staff s = JsonHelper.stringToObject(jsonString, Staff.class);
        assert (s.toString().equals(staff.toString()));

    }

    /**
     * Java object to JSON string
     *
     * @throws IOException
     */
    @Test
    public void javaToJsonStringSalaryNull() throws JsonProcessingException {
        Staff staff = getStaff();
        staff.setSalary(null);
        String jsonString = JsonHelper.objectToString(staff);
        System.out.println("jsonString: " + JsonHelper.objectToPrettyString(staff));

        assert (jsonString.equals("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":null}"));
    }

    /**
     * java object to jsonNode
     */
    @Test
    public void javaToJson() {
        Staff staff = getStaff();
        JsonNode json = JsonHelper.objectToJson(staff);
        System.out.println(json.elements());
        assert ("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":{\"abc\":10000,\"def\":20000}}".equals(json.toString()));
    }

    @Test
    public void jsonNodeEquals(){
        Staff a=getStaff();
        JsonNode json1=JsonHelper.objectToJson(a);
        JsonNode json2=JsonHelper.objectToJson(a);
        assert JsonHelper.equals(json1,json2);
    }

    @Test
    public void jsonNodenotEquals(){
        Staff a=getStaff();
        JsonNode json1=JsonHelper.objectToJson(a);
        //a.setAge(1);
        List<String> posi=new ArrayList<>();
        posi.add("hr");
        posi.add("diverso");
        a.setPosition(posi);
        JsonNode json2=JsonHelper.objectToJson(a);
        assert !JsonHelper.equals(json1,json2);
    }

    /**
     * jsonNode to java object
     */
    @Test
    public void jsonTojava() {
        Staff staff = getStaff();
        JsonNode json = JsonHelper.objectToJson(staff);
        System.out.println(json.toString());
        Staff staff2 = JsonHelper.jsonToObject(json, Staff.class);
        assert (staff.equals(staff2));
    }
}
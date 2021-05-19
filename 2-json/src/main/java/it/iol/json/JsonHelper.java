package it.iol.json;

/**
 * Giuseppe Cannella
 * Helper class for Json
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import it.iol.json.model.Staff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public interface JsonHelper {

    ObjectMapper objectMapper = new ObjectMapper();

    static <T> JsonNode objectToJson(T o) {
        return objectMapper.valueToTree(o);
    }

    static <T> T stringToObject(String jsonString, Class<T> o) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, o);
    }

    static String objectToString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    static String objectToPrettyString(Object o) {
        return objectToJson(o).toPrettyString();
    }

    static <T> T jsonToObject(JsonNode json, Class<T> o) {
        return objectMapper.convertValue(json, o);
    }

    static <T> boolean equals(JsonNode json1, JsonNode json2){
        Iterator<Map.Entry<String,JsonNode>> fields1=json1.fields();
        boolean check=false;
        while(fields1.hasNext()){
            //TODO: elementi nested
            Map.Entry<String,JsonNode> line1=fields1.next();
            System.out.print(line1);
            Iterator<Map.Entry<String,JsonNode>> fields2=json2.fields();
            while(fields2.hasNext()){
                check=false;
                Map.Entry<String,JsonNode> line2=fields2.next();
                if (line1.getKey().equals(line2.getKey())
                        && line1.getValue().getNodeType().equals(line2.getValue().getNodeType())) {
                    //System.out.println(line2.getValue().getNodeType());
                    if (line1.getValue().isArray()) {
                        Iterator<JsonNode> array1 = line1.getValue().iterator();
                        while (array1.hasNext()) {
                            JsonNode arrayline1 = array1.next();
                            Iterator<JsonNode> array2 = line2.getValue().iterator();
                            while (array2.hasNext()) {
                                //System.out.println(arrayline1.isValueNode());
                                if (!arrayline1.isContainerNode()){
                                    if(arrayline1.equals(array2.next())){
                                        check=true;
                                        break;
                                    }

                                }else {
                                    check = equals(arrayline1, array2.next());
                                    if (check) break;
                                }
                            }
                            if (!check) {
                                return false;
                            }
                        }
                        //check = (line1.getValue().equals(line2.getValue()))
                    }
                    if (line1.getValue().isContainerNode()) {
                        check = equals(line1.getValue(), line2.getValue());
                        if (check) break;
                    }
                }
                if (line1.equals(line2)) {
                    check = true;
                    break;
                }
            }
            System.out.println(check);
            if (!check){
                return false;
            }
        }
        return true;
    }
}
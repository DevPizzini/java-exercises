package it.iol.ws.controller;

import com.fasterxml.jackson.databind.JsonNode;
import it.iol.ws.sql.DaoEmployee;
import it.iol.ws.validator.ValidationException;
import it.iol.ws.model.Employee;
import it.iol.ws.service.EmployeeService;
import it.iol.ws.util.JsonHelper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * https://spring.io/guides/gs/rest-service/
 */

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate;

    /**
     * @param employee
     * @return
     */
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    ResponseEntity<JsonNode> addEmployee(@RequestBody Employee employee) {
        log.debug("received {} ", employee);
        try {
            val report = EmployeeService.validateAndInsert(jdbcTemplate, employee);
            return new ResponseEntity<>(JsonHelper.objectToJson(report), HttpStatus.OK);
        } catch (ValidationException errors) {
            log.error("addEmployee {}", errors.getErrors());
            return new ResponseEntity<>(JsonHelper.objectToJson(errors.getErrors()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("addEmployee", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<JsonNode> deleteEmployee(@PathVariable String id) {
        try {
            log.debug("received {} ", id);
            val report = EmployeeService.delete(jdbcTemplate, UUID.fromString(id));
            return new ResponseEntity(JsonHelper.objectToJson(report), HttpStatus.OK);
        } catch (ValidationException errors){
            log.error("deletedEmployee {}", errors.getErrors());
            return new ResponseEntity<>(JsonHelper.objectToJson(errors.getErrors()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("deletedEmployee", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/{id}", produces = "application/json")
    ResponseEntity<JsonNode> getEmployee2(@PathVariable String id) {
        log.debug("received id: {}", id);
        val json = EmployeeService.getById(jdbcTemplate, UUID.fromString(id));
        if (json.isPresent()) {
            val e = json.get();
            return new ResponseEntity<>(JsonHelper.objectToJson(e), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path = "role/{role}", produces = "application/json")
    ResponseEntity<JsonNode> getEmployeeByRole(@PathVariable String role) {
        log.debug("received id: {}", role);
        val list = EmployeeService.getByRole(jdbcTemplate, role);
        return new ResponseEntity<>(JsonHelper.objectToJson(list), HttpStatus.OK);
    }
}

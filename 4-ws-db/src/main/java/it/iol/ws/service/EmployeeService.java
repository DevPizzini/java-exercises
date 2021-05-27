package it.iol.ws.service;

import it.iol.ws.sql.EmployeeEntity;
import it.iol.ws.validator.ValidationException;
import it.iol.ws.sql.DaoEmployee;
import it.iol.ws.model.Employee;
import it.iol.ws.model.Report;
import it.iol.ws.validator.ValidatorEmployee;
import lombok.NonNull;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Logger log = LoggerFactory.getLogger(EmployeeService.class);

    static Report noValidateAndInsert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) throws Exception {
        try {
            val emp = new Employee(employee.getName().toUpperCase(), employee.getRole().toUpperCase(), employee.getDepartment());
            DaoEmployee.insert(jdbcTemplate, emp);
            return new Report("OK", "stored new employee " + emp.getName());
        } catch (DuplicateKeyException e) {
            log.warn("insert error {}", e);
            throw new ValidationException("Entity exists");
        } catch (Exception e) {
            log.error("insert error {}", e);
            throw new ValidationException(e.getMessage());
        }
    }

    static Report validateAndInsert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) throws Exception {
        val validator = new ValidatorEmployee(employee);
        val emp = validator.validate();
        return noValidateAndInsert(jdbcTemplate, emp);
    }

    static List<Employee> getByRole(@NonNull JdbcTemplate jdbcTemplate, @NonNull String role) {
        val l = DaoEmployee.readByRole(jdbcTemplate, role.toUpperCase());

//      return l.stream().map(entity -> new Employee(entity)).collect(Collectors.toList());

        val ll = new ArrayList<Employee>();
        for (EmployeeEntity entity : l) {
            ll.add(new Employee(entity));
        }
        return ll;
    }

    static Optional<Employee> getById(@NonNull JdbcTemplate jdbcTemplate, @NonNull String name) {
        val e = DaoEmployee.readById(jdbcTemplate, name.toUpperCase());
//        return e.map(entity -> new Employee(entity));
        if (e.isPresent()) return Optional.ofNullable(new Employee(e.get()));
        return Optional.empty();
    }
}

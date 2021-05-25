package it.iol.ws.sql;

import it.iol.ws.model.Employee;
import lombok.NonNull;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Employee table
 */
public interface DaoEmployee {
    Logger log = LoggerFactory.getLogger(DaoEmployee.class);

    static void insert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) throws DataAccessException {
        val sql = "insert into employee(name,role,department) values(?,?,?)";
        log.debug("insert: {} {} {} {}", sql, employee.getName(), employee.getRole(), employee.getDepartment());
        jdbcTemplate.update(sql, employee.getName(), employee.getRole(), employee.getDepartment());
    }

    static List<EmployeeEntity> readByRole(@NonNull JdbcTemplate jdbcTemplate, @NonNull String role) {
        val sql = String.format("select name,role,department from employee where role = '%s'", role);
        log.debug("readByRole: {} {}", sql, role);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(EmployeeEntity.class));
    }

    static Optional<EmployeeEntity> readById(@NonNull JdbcTemplate jdbcTemplate, @NonNull String name) {
        val sql = "select name,role,department from employee where name = ?";
        log.debug("readById: {} {}", sql, name);
        try {
            val emp = (EmployeeEntity) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(EmployeeEntity.class), name);
            return Optional.ofNullable(emp);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }
}

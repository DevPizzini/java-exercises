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
import java.util.UUID;

/**
 * Employee table
 */
public interface DaoEmployee {
    Logger log = LoggerFactory.getLogger(DaoEmployee.class);

    static void insert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) throws DataAccessException {
        val sql = "insert into employee(id,name,role,department) values(?,?,?,?)";
        log.debug("insert: {} {} {} {} {}", sql, employee.getId(), employee.getName(), employee.getRole(), employee.getDepartment());
        jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getRole(), employee.getDepartment());
    }


    static void delete(@NonNull JdbcTemplate jdbcTemplate, @NonNull UUID id) throws DataAccessException {
        val sql = "delete from employee where id =?";
        log.debug("delete: {} {}", sql, id);
        jdbcTemplate.update(sql, id);
    }

    static List<EmployeeEntity> readByRole(@NonNull JdbcTemplate jdbcTemplate, @NonNull String role) {
        val sql = String.format("select id,name,role,department from employee where role = '%s'", role);
        log.debug("readByRole: {} {}", sql, role);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(EmployeeEntity.class));
    }

    static Optional<EmployeeEntity> readById(@NonNull JdbcTemplate jdbcTemplate, UUID id) {
        val sql = "select id,name,role,department from employee where id = ?";
        log.debug("readById: {} {}", sql, id);
        try {
            val emp = (EmployeeEntity) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(EmployeeEntity.class), id);
            return Optional.ofNullable(emp);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }
}

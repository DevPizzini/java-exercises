package it.iol.ws.model;

import it.iol.ws.sql.EmployeeEntity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
final public class Employee {

    @NonNull
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String role;

    private String department;

    private Employee() {
    }

    public Employee(@NonNull EmployeeEntity entity) {
        this.id=entity.getId();
        this.name = entity.getName();
        this.role = entity.getRole();
        this.department = entity.getDepartment();
    }

    public Employee(@NonNull UUID id, @NonNull String name, @NonNull String role, String department) {
        this.id=id;
        this.name = name;
        this.role = role;
        this.department = department;
    }



}

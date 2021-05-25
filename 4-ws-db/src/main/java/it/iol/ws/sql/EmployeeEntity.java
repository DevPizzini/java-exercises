package it.iol.ws.sql;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmployeeEntity {

    @NonNull
    private String name;

    @NonNull
    private String role;

    private String department;

    // spring needs empty constructor
    public EmployeeEntity() {
    }

    public EmployeeEntity(@NonNull String name,@NonNull String role, String department) {
        this.name = name;
        this.role = role;
        this.department = department;
    }

}
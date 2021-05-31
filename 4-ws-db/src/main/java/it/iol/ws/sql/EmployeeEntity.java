package it.iol.ws.sql;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmployeeEntity {

    @NonNull
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String role;

    private String department;

    // spring needs empty constructor
    public EmployeeEntity() {
    }

    public EmployeeEntity(@NonNull UUID id, @NonNull String name,@NonNull String role, String department) {
        this.id=id;
        this.name = name;
        this.role = role;
        this.department = department;
    }

}
package it.iol.ws;

import lombok.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Staff {

    @NonNull
    private String name;

    private int age;

    @NonNull
    private List<String> position;                  // list

    @NonNull
    private List<String> skills;                    // List

    private Map<String, Integer> salary;            // Map

    private Staff() {
    }

    public Staff(@NonNull String name, int age, @NonNull List<String> position, @NonNull List<String> skills, Map<String, Integer> salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.skills = skills;
        this.salary = salary;
    }

}

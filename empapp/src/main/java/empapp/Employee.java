package empapp;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@NamedQuery(name = "listEmployee", query = "select distinct e from Employee e left join fetch e.skills")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    private List<String> skills;

    public Employee(String name) {
        this.name = name;
    }

}

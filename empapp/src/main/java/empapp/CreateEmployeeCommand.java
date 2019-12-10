package empapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

    @NotBlank
    private String name;

    private List<String> skills;

    public CreateEmployeeCommand(@NotBlank String name) {
        this.name = name;
    }
}

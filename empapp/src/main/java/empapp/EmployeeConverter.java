package empapp;

import java.util.Arrays;

public class EmployeeConverter {

    public Employee convert(CreateEmployeeCommand command) {
        Employee employee = new Employee();
        employee.setName(command.getName());
        return employee;
    }
}

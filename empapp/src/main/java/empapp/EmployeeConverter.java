package empapp;

public class EmployeeConverter {

    public Employee convert(CreateEmployeeCommand command) {
        Employee employee = new Employee();
        employee.setName(command.getName());
        employee.setSkills(command.getSkills());
        return employee;
    }
}

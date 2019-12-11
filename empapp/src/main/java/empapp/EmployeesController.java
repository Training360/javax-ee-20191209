package empapp;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@Model
public class EmployeesController {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private Messages messages;

    private List<EmployeeDto> employees;

    private CreateEmployeeCommand command = new CreateEmployeeCommand();

    @PostConstruct
    public void listEmployees() {
        employees = employeeService.listEmployees();
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public String addEmployee() {
            employeeService.createEmployee(command);
        messages.addFlashMessage("Employee has created");
        return "employees.xhtml?faces-redirect=true";
    }

    public CreateEmployeeCommand getCommand() {
        return command;
    }

    public void setCommand(CreateEmployeeCommand command) {
        this.command = command;
    }
}

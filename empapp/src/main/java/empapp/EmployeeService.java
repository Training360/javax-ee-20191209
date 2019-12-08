package empapp;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private EmployeeDao employeeDao;

    @Inject
    private LogEntryService logEntryService;

    @Transactional
    public void createEmployee(CreateEmployeeCommand command) {
        logEntryService.createLogEntry("Create employee with name "
                + command.getName());

        if (!employeeDao.existsEmployeeWithName(command.getName())) {
            Employee employee = new EmployeeConverter().convert(command);
            employeeDao.insertEmployee(employee);
        }
    }

    public List<EmployeeDto> listEmployees() {
        return employeeDao.listEmployees()
                .stream()
                .map(e -> new EmployeeDto(e.getId(), e.getName()))
                .collect(Collectors.toList());
    }

}

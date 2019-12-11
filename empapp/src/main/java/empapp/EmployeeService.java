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
    private NameTrimmer nameTrimmer;

    @Inject
    private LogEntryService logEntryService;

    @Transactional
    public void createEmployee(CreateEmployeeCommand command){
        String name = nameTrimmer.trimName(command.getName());
        logEntryService.createLogEntry("Create employee with name "
                + name);

        if (!employeeDao.existsEmployeeWithName(name)) {
            command.setName(name);
            Employee employee = new EmployeeConverter().convert(command);
            employeeDao.insertEmployee(employee);
        }
    }

    public List<EmployeeDto> listEmployees() {
        return employeeDao.listEmployees()
                .stream()
                .map(e -> new EmployeeDto(e.getId(), e.getName(), e.getSkills()))
                .collect(Collectors.toList());
    }

}

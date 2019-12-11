package empapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeDao employeeDao;

    @Mock
    LogEntryService logEntryService;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void test_Create_Employee_Should_Insert() throws Exception {
        employeeService.createEmployee(new CreateEmployeeCommand("John Doe"));
        verify(employeeDao).insertEmployee(argThat(e -> e.getName().equals("John Doe")));
    }

    @Test
    void test_Create_Employee_Should_Log() throws Exception {
        employeeService.createEmployee(new CreateEmployeeCommand("John Doe"));
        verify(logEntryService).createLogEntry(argThat(s -> s.startsWith("Create") &&
                s.contains("John Doe")));
    }

}

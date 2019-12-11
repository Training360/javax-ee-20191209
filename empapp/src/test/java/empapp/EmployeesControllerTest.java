package empapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeesControllerTest {

    @Mock
    EmployeeService employeeService;

    @Mock
    Messages messages;

    @InjectMocks
    EmployeesController employeesController;

    @Test
    void testCreateEmployee() throws Exception {
        employeesController.getCommand().setName("John Doe");
        String target = employeesController.addEmployee();

        verify(messages).addFlashMessage(eq("Employee has created"));
        verify(employeeService).createEmployee(argThat(c -> c.getName().equals("John Doe")));
        assertEquals(target, "employees.xhtml?faces-redirect=true");
    }
}

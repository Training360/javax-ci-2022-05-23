package employees.controller;

import employees.service.CreateEmployeeCommand;
import employees.service.EmployeeDto;
import employees.service.EmployeesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeesControllerTest {

    @Mock
    EmployeesService employeesService;

    @InjectMocks
    EmployeesController employeesController;

    @Test
    void testCreateEmployee() {
        when(employeesService.createEmployee(any())).thenReturn(new EmployeeDto(1L, "John Doe"));

        EmployeeDto employeeDto = employeesController.createEmployee(new CreateEmployeeCommand("John Doe"));

        verify(employeesService).createEmployee(argThat(command -> command.getName().equals("John Doe")));
        assertEquals(1L, employeeDto.getId());
        assertEquals("John Doe", employeeDto.getName());
    }

}

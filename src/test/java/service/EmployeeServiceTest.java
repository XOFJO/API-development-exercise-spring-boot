package service;


import com.oocl.training.Model.Employee;
import com.oocl.training.service.EmployeeService;
import com.oocl.training.DAO.EmployeeRepo;
import exception.OutsideAgeRangeEmployee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void should_create_employee_successfully() {
        // Given
        Employee employee = new Employee("John Doe", 22, "MALE", 3000, true);
        Employee mockedReturedFromDataBaseEmployee = new Employee(1, "John Doe", 22, "MALE", 3000, true);
        Mockito.when(employeeRepo.save(Mockito.any(Employee.class))).thenReturn(mockedReturedFromDataBaseEmployee);
        // When
        Employee createdEmployee = employeeService.addEmployee(employee);

        // Then
        assertNotNull(createdEmployee.getId());
        assertEquals(employee.getName(), createdEmployee.getName());
        assertEquals(employee.getAge(), createdEmployee.getAge());
        assertEquals(employee.getGender(), createdEmployee.getGender());
        assertEquals(employee.getSalary(), createdEmployee.getSalary());
        assertEquals(employee.getStatus(), createdEmployee.getStatus());
    }

    @Test
    void should_throw_exception_when_create_employee_with_age_below_18() {
        // Given
        Employee employee = new Employee("John Doe", 17, "MALE", 3000, true);

        //when / then
        OutsideAgeRangeEmployee exception = assertThrows(OutsideAgeRangeEmployee.class, () -> employeeService.addEmployee(employee));
        assertEquals("Employee age must be between 18 and 65.", exception.getMessage());
    }


    @Test
    void should_throw_exception_when_create_employee_with_age_above_65() {
        // Given
        Employee employee = new Employee("John Doe", 80, "MALE", 3000, true);

        //when / then
        OutsideAgeRangeEmployee exception = assertThrows(OutsideAgeRangeEmployee.class, () -> employeeService.addEmployee(employee));
        assertEquals("Employee age must be between 18 and 65.", exception.getMessage());
    }
}

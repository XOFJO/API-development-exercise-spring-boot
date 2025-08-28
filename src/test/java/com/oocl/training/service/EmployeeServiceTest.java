package com.oocl.training.service;


import com.oocl.training.dao.EmployeeDbRepository;
import com.oocl.training.model.Employee;
import exception.OutsideAgeRangeEmployee;
import exception.Over30YearsOldSalaryLessThan20000;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeDbRepository employeeDbRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void should_create_employee_successfully() {
        // Given
        Employee employee = new Employee("John Doe", 22, "MALE", 3000, true);
        Employee mockedReturedFromDataBaseEmployee = new Employee(1, "John Doe", 22, "MALE", 3000, true);
        Mockito.when(employeeDbRepository.save(Mockito.any(Employee.class))).thenReturn(mockedReturedFromDataBaseEmployee);
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

    @Test
    void should_throw_exception_when_create_employee_with_age_above_30_and_salary_less_than_20000() {
        // Given
        Employee employee = new Employee("John Doe", 31, "MALE", 18000, true);

        //when / then
        Over30YearsOldSalaryLessThan20000 exception = assertThrows(Over30YearsOldSalaryLessThan20000.class, () -> employeeService.addEmployee(employee));
        assertEquals("Employees over 30 must have a salary of at least 10000.", exception.getMessage());
    }

    @Test
    void should_get_employee_by_id() {
        // Given
        int id = 1;
        Employee mockedReturedFromDataBaseEmployee = new Employee(1, "John Doe", 22, "MALE", 3000, true);
        Mockito.when(employeeDbRepository.findById(Mockito.anyInt())).thenReturn(mockedReturedFromDataBaseEmployee);
        // When
        Employee receivedEmployee = employeeService.getEmployeeById(id);

        // Then
        assertNotNull(receivedEmployee.getId());
        assertEquals(mockedReturedFromDataBaseEmployee.getName(), receivedEmployee.getName());
        assertEquals(mockedReturedFromDataBaseEmployee.getAge(), receivedEmployee.getAge());
        assertEquals(mockedReturedFromDataBaseEmployee.getGender(), receivedEmployee.getGender());
        assertEquals(mockedReturedFromDataBaseEmployee.getSalary(), receivedEmployee.getSalary());
        assertEquals(mockedReturedFromDataBaseEmployee.getStatus(), receivedEmployee.getStatus());
    }

    @Test
    void should_get_all_employees() {
        // Given
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(new Employee(1, "John", 25, "Male", 8000, true));
        mockedEmployees.add(new Employee(2, "Lily", 20, "Female", 8000, true));
        mockedEmployees.add(new Employee(3, "Bob", 30, "Male", 9000, true));
        mockedEmployees.add(new Employee(4, "Alice", 28, "Female", 10000, true));
        Mockito.when(employeeDbRepository.findAll()).thenReturn(mockedEmployees);
        // When
        List<Employee> receivedEmployees = employeeService.getAllEmployees();

        // Then

        Employee receivedEmployee = receivedEmployees.get(0);
        Employee mockedReturnedFromDataBaseEmployee = mockedEmployees.get(0);
        assertNotEquals(0, receivedEmployee.getId());
        assertEquals(mockedReturnedFromDataBaseEmployee.getName(), receivedEmployee.getName());
        assertEquals(mockedReturnedFromDataBaseEmployee.getAge(), receivedEmployee.getAge());
        assertEquals(mockedReturnedFromDataBaseEmployee.getGender(), receivedEmployee.getGender());
        assertEquals(mockedReturnedFromDataBaseEmployee.getSalary(), receivedEmployee.getSalary());
        assertEquals(mockedReturnedFromDataBaseEmployee.getStatus(), receivedEmployee.getStatus());

    }

    @Test
    void should_update_employee_successfully() {
        // Given
        int id = 1;
        Employee updatedEmployee = new Employee("Jane Doe", 22, "Male", 4000, true);
        Employee mockedReturnedFromDataBaseEmployee = new Employee(1, "Jane Doe", 22, "Male", 4000, true);

        Mockito.when(employeeDbRepository.findById(Mockito.anyInt())).thenReturn(mockedReturnedFromDataBaseEmployee);

        Mockito.when(employeeDbRepository.updateById(Mockito.anyInt(), Mockito.any(Employee.class))).thenReturn(mockedReturnedFromDataBaseEmployee);


        // When
        Employee receivedEmployee = employeeService.updateEmployee(id, updatedEmployee);

        // Then
        assertNotEquals(0, receivedEmployee.getId());
        assertEquals(mockedReturnedFromDataBaseEmployee.getName(), receivedEmployee.getName());
        assertEquals(mockedReturnedFromDataBaseEmployee.getAge(), receivedEmployee.getAge());
        assertEquals(mockedReturnedFromDataBaseEmployee.getGender(), receivedEmployee.getGender());
        assertEquals(mockedReturnedFromDataBaseEmployee.getSalary(), receivedEmployee.getSalary());
        assertEquals(mockedReturnedFromDataBaseEmployee.getStatus(), receivedEmployee.getStatus());

    }


    @Test
    void should_delete_employee_successfully() {
        // Given
        int id = 1;
        Employee existingEmployee = new Employee(1, "John Doe", 25, "Male", 3000, true);
        Employee expectedUpdatedEmployee = new Employee("John Doe", 25, "Male", 3000, false);

        Mockito.when(employeeDbRepository.findById(id)).thenReturn(existingEmployee);

        // When
        employeeService.deleteEmployee(id);

        // Then
        Mockito.verify(employeeDbRepository, Mockito.times(1)).findById(id);
        Mockito.verify(employeeDbRepository, Mockito.times(1)).updateById(id, existingEmployee);
    }
}

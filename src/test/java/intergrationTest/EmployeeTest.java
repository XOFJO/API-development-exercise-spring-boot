package intergrationTest;
import com.oocl.training.dao.JpaCompanyRepository;
import com.oocl.training.dao.JpaEmployeeRepository;
import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;

@SpringBootTest(classes = com.oocl.training.SpringbootExerciseApplication.class)
@AutoConfigureMockMvc
public class EmployeeTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private JpaEmployeeRepository employeeDbRepository;

    @Autowired
    private JpaCompanyRepository companyRepository;

    @BeforeEach
    public void setup() {
        // Clear existing data
        employeeDbRepository.deleteAll();
        companyRepository.deleteAll();

        Company company = new Company();
        company.setName("Test Company");
        company = companyRepository.save(company); // Assign the saved company back

        employeeDbRepository.save(new Employee("John", 25, "Male", 8000, true, company));
        employeeDbRepository.save(new Employee("Lily", 28, "Female", 9000, true));
        employeeDbRepository.save(new Employee("Bob", 32, "Male", 8500, false));
        employeeDbRepository.save(new Employee("Alice", 30, "Female", 9500, true));
        employeeDbRepository.save(new Employee("Tom", 27, "Male", 7800, false));
    }

    @Test
    public void should_return_employee_when_get_all_employees() throws Exception {
        // given
        List<Employee> givenEmployees = employeeDbRepository.findAll();

        // when
        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/employees"));

        // then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(givenEmployees.get(0).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(givenEmployees.get(0).getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].age").value(givenEmployees.get(0).getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].gender").value(givenEmployees.get(0).getGender()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[0].salary").value(givenEmployees.get(0).getSalary()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(givenEmployees.get(1).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id").value(givenEmployees.get(2).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[3].id").value(givenEmployees.get(3).getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.[4].id").value(givenEmployees.get(4).getId()));
    }

    @Test
    public void should_return_employee_when_get_employee_by_id() throws Exception {
        // given
        List<Employee> employees = employeeDbRepository.findAll();
        Employee givenEmployee = employees.get(0); // Use the first employee from the list

        // when
        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/employees/" + givenEmployee.getId()));

        // then
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(givenEmployee.getId()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(givenEmployee.getName()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(givenEmployee.getAge()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.gender").value(givenEmployee.getGender()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(givenEmployee.getSalary()));
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(givenEmployee.getStatus()));
    }
}

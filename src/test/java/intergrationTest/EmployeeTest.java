package intergrationTest;
import com.oocl.training.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.oocl.training.dao.EmployeeRepo;
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
    private EmployeeRepo employeeRepository;

    @BeforeEach
    public void setup() {
        employeeRepository.clearAll();
        employeeRepository.save(new Employee(1, "John", 25, "Male", 8000, true));
        employeeRepository.save(new Employee(2, "Lily", 28, "Female", 9000, true));
        employeeRepository.save(new Employee(3, "Bob", 32, "Male", 8500, false));
        employeeRepository.save(new Employee(4, "Alice", 30, "Female", 9500, true));
        employeeRepository.save(new Employee(5, "Tom", 27, "Male", 7800, false));
    }

    @Test
    public void should_return_employee_when_get_all_employees() throws Exception {
        // given
        List<Employee> givenEmployees = employeeRepository.findAll();

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
        Employee givenEmployee = employeeRepository.findById(1);

        // when
        ResultActions perform = client.perform(MockMvcRequestBuilders.get("/api/v1/employees/1"));

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

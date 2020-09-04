package com.example.demo;

import com.example.demo.Model.DAO.Department;
import com.example.demo.Model.DAO.Employee;
import com.example.demo.Model.DAO.Job;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Service.EmployeeService;
import com.example.demo.Service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@EnableWebMvc
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void shouldReturnEmployee() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/employee/getEmployeeById/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getEmployeeById", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
    }
    @Test
    public void shouldReturnAllEmployees() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/employee/getAllEmployees")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getAllEmployees", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
    }

    @Test
    public void shouldReturnAllEmployeesByDepartment() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/employee/getAllEmployeesByDepartment/{id}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getAllEmployeesByDepartment", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
    }

    @Test
    public void shouldReturnAllEmployeesByJob() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/employee/getAllEmployeesByJob/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getAllEmployeesByJob", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
    }

    @Test
    public void shouldReturnEmployeeDto() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/employee/getEmployeeDtoById/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getEmployeeDtoById", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
    }
    @Test
    public void shouldReturnAllEmployeesDto() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/employee/getAllEmployeesDto")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getAllEmployeesDto", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
    }

    @Test
    public void employeeDeleteTest() throws Exception {
        this.mockMvc.perform(delete("http://localhost:8080/api/employee/deleteEmployee/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("employeeDelete",
                        pathParameters(
                                parameterWithName("id").description("The id of the input to delete")
                        )));
    }

    @Test
    void addEmployee() throws Exception {
        Job job = new Job();
        job.setName("Test");
        Department department = new Department();
        department.setName("Test");
        LocalDate localDate = LocalDate.of(2020, 1, 8);

        Employee employee = new Employee();
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");
        employee.setJobCategory(job);
        employee.setDepartment(department);
        employee.setManager(false);
        employee.setStartDate(localDate);
        //employee.setEndDate(employee.getEndDate());
        employee.setActive(true);
        employee.setAddress("Test Address");
        employee.setEmail("test@email.com");
        employee.setTelephone("0746425789");
        employee.setPostalCode(212312);
        employee.setBirthday(localDate);
        employee.setNoChildern(2);
        employee.setStudies("Test Studies");
        employee.setSalary(20012.31);
        employee.setSocialSecurityNumber(1212132312);
        employee.setHasDrivingLicense(true);

      /*  when(this.employeeRepository.save(any(Employee.class))).then((Answer<Employee>) invocationOnMock -> {
            if (invocationOnMock.getArguments().length > 0 && invocationOnMock.getArguments()[0] instanceof Employee) {
                Employee mockEmployee = (Employee) invocationOnMock.getArguments()[0];
                mockEmployee.setFirstName(employee.getFirstName());
                mockEmployee.setLastName(employee.getLastName());
                mockEmployee.setJobCategory(employee.getJobCategory());
                mockEmployee.setDepartment(employee.getDepartment());
                mockEmployee.setManager(employee.isManager());
                mockEmployee.setStartDate(employee.getStartDate());
                //employee.setEndDate(employee.getEndDate());
                mockEmployee.setActive(employee.isActive());
                mockEmployee.setAddress(employee.getAddress());
                mockEmployee.setEmail(employee.getEmail());
                mockEmployee.setTelephone(employee.getTelephone());
                mockEmployee.setPostalCode(employee.getPostalCode());
                mockEmployee.setBirthday(employee.getBirthday());
                mockEmployee.setNoChildern(employee.getNoChildern());
                mockEmployee.setStudies(employee.getStudies());
                mockEmployee.setSalary(employee.getSalary());
                mockEmployee.setSocialSecurityNumber(employee.getSocialSecurityNumber());
                mockEmployee.setHasDrivingLicense(employee.isHasDrivingLicense());
                return mockEmployee;
            }
            return null;
        });*/

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("http://localhost:8080/api/employee/addEmployee")
                .content(this.objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.model().attribute("name", "DepartmentTest"))
                //.andExpect(MockMvcResultMatchers.model().size(1))
                //.andExpect(jsonPath("$.id").value("2"))
                //.andExpect(jsonPath("$.name").value("DepartmentTest"))
                .andDo(document("createEmployee"));

    }

    @Test
    void testUpdate() throws Exception {
        Job job = new Job();
        job.setName("Test");
        Department department = new Department();
        department.setName("Test");
        LocalDate localDate = LocalDate.of(2020, 1, 8);

        Employee employee = new Employee();
        employee.setFirstName("FirstName");
        employee.setLastName("Last name");
        employee.setJobCategory(job);
        employee.setDepartment(department);
        employee.setManager(false);
        employee.setStartDate(localDate);
        //employee.setEndDate(employee.getEndDate());
        employee.setActive(true);
        employee.setAddress("Test Address");
        employee.setEmail("test@email.com");
        employee.setTelephone("0746425789");
        employee.setPostalCode(212312);
        employee.setBirthday(localDate);
        employee.setNoChildern(2);
        employee.setStudies("Test Studies");
        employee.setSalary(20012.31);
        employee.setSocialSecurityNumber(1212132312);
        employee.setHasDrivingLicense(true);


        this.mockMvc.perform(put("http://localhost:8080/api/employee/updateEmployee/{id}",1)
                .content(this.objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("updateEmployee", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
         /*       .andDo(document("updateDepartment", pathParameters(
                        parameterWithName("id").description("The id of the input to update")
                )));*/
    }
}

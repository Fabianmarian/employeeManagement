package com.example.demo;

import com.example.demo.Controller.DepartmentController;
import com.example.demo.Model.DAO.Department;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DepartmentController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
//@SpringBootTest
public class EmployeeManagementApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DepartmentRepository departmentRepository;

	@MockBean
	private DepartmentService departmentService;

/*	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).dispatchOptions(true).build();
	}*/


	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	@Test
	public void testCreateOneDepartment() throws Exception {

		Department department = new Department();
		department.setName("DepartmentTest");

		when(this.departmentRepository.save(any(Department.class))).thenReturn(department);

		FieldDescriptor[] customerDescriptor = getDepartmentFieldDescriptor();

		this.mockMvc.perform(post("http://localhost:8080/api/departments/addDepartment").content(this.objectMapper.writeValueAsString(department))
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isAccepted())
				.andExpect(jsonPath("$.name").value("DepartmentTest"))
				.andDo(document("shouldCreateDepartment",
						requestFields(customerDescriptor),
						responseFields(customerDescriptor)));

	}

	@Test
	public void testGetOneDepartment() throws Exception {

		Department mockDepartment = new Department();
		mockDepartment.setId(1);
		when(this.departmentRepository.findById(1)).thenReturn(Optional.of(mockDepartment));

		this.mockMvc.perform(RestDocumentationRequestBuilders.get("http://localhost:8080/api/departments/getDepartmentById/{id}", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Test"))
				.andDo(document("shouldGetOneDepartment",
						pathParameters(
								parameterWithName("id").description("The id of the department to retrieve")
						),
						responseFields(this.getDepartmentFieldDescriptor())));
	}

	private FieldDescriptor[] getDepartmentFieldDescriptor() {
		return new FieldDescriptor[]{fieldWithPath("id").description("The id of the department").type(Integer.class.getSimpleName()),
				fieldWithPath("name").description("The name of the department").type(String.class.getSimpleName())};
	}

}

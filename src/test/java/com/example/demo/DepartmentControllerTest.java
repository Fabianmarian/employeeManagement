package com.example.demo;

import com.example.demo.Controller.DepartmentController;
import com.example.demo.Model.DAO.Department;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@AutoConfigureRestDocs // defaults to target/generated-snippets
//@WebMvcTest(DepartmentController.class)

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@EnableWebMvc
public class DepartmentControllerTest {


    //@Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void shouldReturnDepartment() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/departments/getDepartmentById/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getDepartmentById", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
    }

    @Test
    public void shouldReturnAllDepartments() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/departments/getAllDepartments")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getAllDepartments", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
    }


    @Test
    public void departmentDeleteExample() throws Exception {
        this.mockMvc.perform(delete("http://localhost:8080/api/departments/deleteDepartment/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("deleteDepartment",
                        pathParameters(
                                parameterWithName("id").description("The id of the input to delete")
                        )));
    }

    @Test
    void createDepartment() throws Exception {

        Department department = new Department();
        department.setName("");

        when(this.departmentRepository.save(any(Department.class))).then((Answer<Department>) invocationOnMock -> {
            if (invocationOnMock.getArguments().length > 0 && invocationOnMock.getArguments()[0] instanceof Department) {
                Department mockDepartment = (Department) invocationOnMock.getArguments()[0];
                //mockDepartment.setId(3);
                return mockDepartment;
            }
            return null;
        });

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("http://localhost:8080/api/departments/addDepartment").content(this.objectMapper.writeValueAsString(department))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                //.andExpect(MockMvcResultMatchers.model().attribute("name", "DepartmentTest"))
                //.andExpect(MockMvcResultMatchers.model().size(1))
                //.andExpect(jsonPath("$.id").value("2"))
                //.andExpect(jsonPath("$.name").value("DepartmentTest"))
                .andDo(document("createDepartmentBadRequest"));

    }

    @Test
    void testUpdate() throws Exception {

        Department department = new Department();
        department.setName("Test");
            this.mockMvc.perform(put("http://localhost:8080/api/departments/updateDepartment/{id}/{name}",4,department.getName())
                .content(this.objectMapper.writeValueAsString(department))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andDo(document("updateDepartmentBadRequest", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
         /*       .andDo(document("updateDepartment", pathParameters(
                        parameterWithName("id").description("The id of the input to update")
                )));*/
    }

}

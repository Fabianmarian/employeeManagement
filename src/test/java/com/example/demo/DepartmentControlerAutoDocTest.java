package com.example.demo;
import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors;
import com.example.demo.Controller.DepartmentController;
import com.example.demo.Model.DAO.Department;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(DepartmentController.class)
@EnableSpringDataWebSupport
public class DepartmentControlerAutoDocTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private DepartmentService departmentService;

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

/*    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
               // .addFilters(springSecurityFilterChain)
                .alwaysDo(JacksonResultHandlers.prepareJackson(objectMapper))
                .alwaysDo(MockMvcRestDocumentation.document("{class-name}/{method-name}",
                        Preprocessors.preprocessRequest(),
                        Preprocessors.preprocessResponse(
                                ResponseModifyingPreprocessors.replaceBinaryContent(),
                                ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
                                Preprocessors.prettyPrint())))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8080)
                        .and().snippets()
                        .withDefaults(CliDocumentation.curlRequest(),
                                HttpDocumentation.httpRequest(),
                                HttpDocumentation.httpResponse(),
                                AutoDocumentation.requestFields(),
                                AutoDocumentation.responseFields(),
                                AutoDocumentation.pathParameters(),
                                AutoDocumentation.requestParameters(),
                                AutoDocumentation.description(),
                                AutoDocumentation.methodAndPath(),
                                AutoDocumentation.section()))
                .build();
    }*/

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    void createDepartment() throws Exception {

        Department department = new Department();
        department.setName("DepartmentTest");

        when(this.departmentRepository.save(any(Department.class))).then((Answer<Department>) invocationOnMock -> {
            if (invocationOnMock.getArguments().length > 0 && invocationOnMock.getArguments()[0] instanceof Department) {
                Department mockDepartment = (Department) invocationOnMock.getArguments()[0];
                mockDepartment.setId(3);
                return mockDepartment;
            }
            return null;
        });

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("http://localhost:8080/api/departments/addDepartment").content(this.objectMapper.writeValueAsString(department))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.name").value("DepartmentTest"));

    }

    @Test
    void testUpdate() throws Exception {

        Department department = new Department();
        department.setName("Test department");
;

        this.mockMvc.perform(put("http://localhost:8080/api/departments/updateDepartment/{id}/{name}",1,department.getName())
                .content(this.objectMapper.writeValueAsString(department))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isAccepted())
                .andDo(document("deleteDepartment", pathParameters(
                        parameterWithName("id").description("The id of the input to update")
                )));
    }

/*    @Test
    void testFindOne() throws Exception {

        this.mockMvc.perform(get("/api/departments/getAllDepartments"))
                .andExpect(status().isOk());
    }*/

/*    @Test
    void testFindAll() throws Exception {

        when(this.departmentRepository.findAll()).thenReturn((List<Department>) new PageImpl<>(Collections.singletonList(new Department())));

        this.mockMvc.perform(get("/api/departments/getAllDepartments"))
                .andExpect(status().isOk());
    }*/
}

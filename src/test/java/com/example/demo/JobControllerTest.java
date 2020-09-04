package com.example.demo;


import com.example.demo.Model.DAO.Department;
import com.example.demo.Model.DAO.Job;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Service.DepartmentService;
import com.example.demo.Service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@WebAppConfiguration
public class JobControllerTest {

    private MockMvc mockMvc;
    //@Rule
    //public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JobRepository jobRepository;

    @MockBean
    private JobService jobService;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void shouldReturnJob() throws Exception {
        Job job = new Job();
       // job = jobRepository.findById(1).get();
        objectMapper.readValue(objectMapper.writeValueAsString(job), Job.class);
        this.mockMvc.perform(get("http://localhost:8080/api/jobcategories/getJobCategoryById/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(job))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.model().attribute("name", "Test"))
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getJobById", preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }
    @Test
    public void shouldReturnAllJobs() throws Exception {
        List<Job> jobs = new ArrayList<>();
        jobs=jobRepository.findAll();
        //Page<Job> jobPage = new PageImpl<>(jobs);
        when(jobRepository.findAll()).thenReturn(jobs);
        this.mockMvc.perform(get("http://localhost:8080/api/jobcategories/getAllJobCategories")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1))
                //.andExpect(jsonPath("$.name").value("Test"))
                .andDo(document("getAllJobs", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
    }

    @Test
    public void jobDeleteExample() throws Exception {
        this.mockMvc.perform(delete("http://localhost:8080/api/jobcategories/deleteJobCategory/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("deleteJob",
                        pathParameters(
                                parameterWithName("id").description("The id of the input to delete")
                        )));
    }

    @Test
    void createJob() throws Exception {

        Job job = new Job();
        job.setName("Test");

/*        when(this.jobRepository.save(any(Job.class))).then((Answer<Job>) invocationOnMock -> {
            if (invocationOnMock.getArguments().length > 0 && invocationOnMock.getArguments()[0] instanceof Job) {
                Job mockJob = (Job) invocationOnMock.getArguments()[0];
                //mockDepartment.setId(3);
                return mockJob;
            }
            return null;
        });*/

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("http://localhost:8080/api/jobcategories/addJobCategory")
                .content(this.objectMapper.writeValueAsString(job))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.model().attribute("name", "DepartmentTest"))
                //.andExpect(MockMvcResultMatchers.model().size(1))
                //.andExpect(jsonPath("$.id").value("2"))
                //.andExpect(jsonPath("$.name").value("DepartmentTest"))
                .andDo(document("createJob"));

    }

    @Test
    void testUpdate() throws Exception {

        Job job = new Job();
        job.setName("Test");
        this.mockMvc.perform(put("http://localhost:8080/api/jobcategories/updateJob/{id}/{name}",1,job.getName())
                .content(this.objectMapper.writeValueAsString(job))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("updateJob", responseFields(fieldWithPath("name")
                        .description("A description goes here."))));
         /*       .andDo(document("updateDepartment", pathParameters(
                        parameterWithName("id").description("The id of the input to update")
                )));*/
    }

}

package com.divya.JunitExample;

import com.divya.JunitExample.controller.StudentController;
import com.divya.JunitExample.model.Student;
import com.divya.JunitExample.repo.StudentRepo;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

@RunWith(SpringRunner.class)
public class StudentControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    StudentController controller;
    @Mock
    StudentRepo repo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllStudent() throws Exception {
        Map<Integer, Student> students = new HashMap<>();
        students.put(12, new Student(12, "Divya", "CS", "Ghaziabad"));
        Mockito.when(repo.getAllStudents()).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders.get("/allStudents"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getStudentById() throws Exception {
        Student student = new Student(12, "Divya", "CS", "UP");
        Mockito.when(repo.getStudentById(12)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.get("/Student/12")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Divya")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.branch", Matchers.is("CS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.is("UP")));
        Mockito.verify(repo).getStudentById(12);
    }


    @Test
    public void addStudent() throws Exception {
        String jsonString = "{\n" +
                "\"studentId\":1,\n" +
                "\"name\":\"Divya\",\n" +
                "\"branch\":\"CS\"\n" +
                "\"address\":\"UP\"\n" +
                "}";
        Student student =new Student(12,"Divya","CS","UP");
        mockMvc.perform(MockMvcRequestBuilders.post("/addStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId",Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Divya")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.branch",Matchers.is("CS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address",Matchers.is("UP")));


    }
    @Test
    public void updateStudent() throws Exception {
        String jsonString = "{\n" +
                "\"studentId\":12,\n" +
                "\"name\":\"Divya\",\n" +
                "\"branch\":\"CS\"\n" +
                "\"address\":\"UP\"\n" +
                "}";
        Student student = new Student(14,"Divya","CS","GZB");
        mockMvc.perform(MockMvcRequestBuilders.put("/updateStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.studentId", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Divya")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.branch",Matchers.is("CS")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address",Matchers.is("GZB")));
    }

    @Test
    public void deleteItem() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteStudent/4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}

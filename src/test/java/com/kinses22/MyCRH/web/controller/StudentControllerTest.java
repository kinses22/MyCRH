package com.kinses22.MyCRH.web.controller;

import com.kinses22.MyCRH.StudentController;
import com.kinses22.MyCRH.model.Student;
import com.kinses22.MyCRH.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Before
    public void setup(){
        mockMvc = standaloneSetup(studentController).build();

    }

    @Test
    public void index_shouldDisplayStudents() throws Exception{
        // Arrange the mock behaviour
        List<Student> students = Arrays.asList(
                new Student(),
                new Student()
        );

        when(studentService.getAllStudents()).thenReturn(students);

        //Act - Perform the Request and Assert the results
        mockMvc.perform(get("/"))
                .andExpect(view().name("student/index"))
                .andExpect(model().attribute("students", students));
            verify(studentService).getAllStudents();
    }

    @Test
    public void showASingleStudent() throws Exception{
        // Arrange the mock behaviour
        Student student = new Student();
        when(studentService.findById(1L)).thenReturn(student);

        //Act - Perform the Request and Assert the results
        mockMvc.perform(get("/students/1"))
                .andExpect(view().name("student/details"))
                .andExpect(model().attribute("student", student));
            verify(studentService).findById(1L);
    }

}

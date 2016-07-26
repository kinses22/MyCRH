package com.kinses22.MyCRH.web.controller;


import com.kinses22.MyCRH.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    // Home page - index of all Students
    @RequestMapping("/")
    public String listStudents(Model model) {
        // TODO: Get all gifs
        List<Student> students = new ArrayList<>();

        model.addAttribute("students", students);
        return "gif/index";
    }

    // Single Student page
    @RequestMapping("/students/{studentId}")
    public String gifDetails(@PathVariable Long studentId, Model model) {
        // TODO: Get student whose id is studentID
        Student student = null;

        model.addAttribute("student", student);
        return "gif/student-details";
    }

    // Student image data
    @RequestMapping("/students/{studentId}.jpg")
    @ResponseBody
    public byte[] StudentImage(@PathVariable Long studentId) {
        // TODO: Return image data as byte array of the Student whose id is StudentId
        return null;
    }

    // Upload a new Student Image
    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public String addStudent() {
        // TODO: Upload new Student if data is valid

        // TODO: Redirect browser to new Student's detail view
        return null;
    }

    // Form for uploading a new Student
    @RequestMapping("/upload")
    public String formNewGif(Model model) {
        // TODO: Add model attributes needed for new Student upload form

        return "student/form";
    }

    // Form for editing an existing Student
    @RequestMapping(value = "/students/{dgifI}/edit")
    public String formEditStudent(@PathVariable Long gifId, Model model) {
        // TODO: Add model attributes needed for edit form

        return "student/form";
    }

    // Update an existing Student
    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.POST)
    public String updateStudent() {
        // TODO: Update Student if data is valid

        // TODO: Redirect browser to updated Student's detail view
        return null;
    }

    // Delete an existing Student
    @RequestMapping(value = "/students/{studentId}/delete", method = RequestMethod.POST)
    public String deleteStudent(@PathVariable Long StudentId) {
        // TODO: Delete the student whose id is StudentId

        // TODO: Redirect to app root
        return null;
    }
}
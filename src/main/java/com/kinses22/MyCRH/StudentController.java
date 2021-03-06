package com.kinses22.MyCRH;


import com.kinses22.MyCRH.model.Student;
import com.kinses22.MyCRH.service.ClassroomService;
import com.kinses22.MyCRH.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private StudentService studentService;

    // Home page - index of all Students -- TESTED.
    @RequestMapping("/")
    public String listStudents(Model model) {
        // TODO: Get all students
        List<Student> students = studentService.getAllStudents();

        model.addAttribute("students", students);
        return "student/index";
    }

    // Single Student page -- TESTED
    @RequestMapping("/students/{studentId}")
    public String studentDetails(@PathVariable Long studentId, Model model) {
        // TODO: Get student whose id is studentID
        Student student = studentService.findById(studentId);

        model.addAttribute("student", student);
        return "student/details";
    }

    // Student image data
    @RequestMapping("/students/{studentId}.jpg")
    @ResponseBody
    public byte[] StudentImage(@PathVariable Long studentId) {
        // TODO: Return image data as byte array of the Student whose id is StudentId
        return studentService.findById(studentId).getBytes();
    }

    // Upload a new Student Image
    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public String addStudent(Student student, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        // TODO: Upload new Student if data is valid
        studentService.save(student, file);

        // TODO: Redirect browser to new Student's detail view
        return String.format("redirect:/students/%s", student.getId());
    }

    // Form for uploading a new Student
    @RequestMapping("/upload")
    public String formNewStudent(Model model) {

       if(!model.containsAttribute("student")) {
           model.addAttribute("student", new Student());
       }
        model.addAttribute("classrooms", classroomService.getAllClassrooms());
        model.addAttribute("action", "/students");
        model.addAttribute("heading", "Upload");
        model.addAttribute("submit", "Add");
        return "student/form";
    }

    // Form for editing an existing Student
    @RequestMapping(value = "/students/{studentID}/edit")
    public String formEditStudent(@PathVariable Long studentID, Model model) {
        // TODO: Add model attributes needed for edit form
        if(!model.containsAttribute("student")) {
            model.addAttribute("student", studentService.findById(studentID));
        }
        model.addAttribute("classrooms", classroomService.getAllClassrooms());
        model.addAttribute("action", String.format("/students/%s", studentID));
        model.addAttribute("heading", "Edit");
        model.addAttribute("submit", "Edit");
        return "student/form";
    }

    // Update an existing Student
    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.POST)
    public String updateStudent(@Valid Student student, MultipartFile file, RedirectAttributes redirectAttributes, BindingResult res) {
        // TODO: Update Student if data is valid

        studentService.save(student,file);

        // TODO: Redirect browser to updated Student's detail view
        return "redirect:/";
    }

    // Delete an existing Student
    @RequestMapping(value = "/students/{studentId}/delete", method = RequestMethod.POST)
    public String deleteStudent(@PathVariable Long studentId) {
        // TODO: Delete the student whose id is StudentId
        Student student = studentService.findById(studentId);
        studentService.delete(student);
        // TODO: Redirect to app root
        return "redirect:/";
    }
}
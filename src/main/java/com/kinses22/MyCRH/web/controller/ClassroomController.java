package com.kinses22.MyCRH.web.controller;

import com.kinses22.MyCRH.model.Classroom;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ClassroomController {
    @Autowired
    private SessionFactory sessionFactory;

    // Index of all classrooms
    @SuppressWarnings("unchecked")
    @RequestMapping("/classrooms")
    public String listClassrooms(Model model) {

        Session session = sessionFactory.openSession();
        List<Classroom> classrooms = session.createCriteria(Classroom.class).list();

        model.addAttribute("classrooms",classrooms);
        return "classroom/index";
    }

    // Single classroom page
    @RequestMapping("/classrooms/{classroomID}")
    public String classroom(@PathVariable Long classroomID, Model model) {
        // TODO: Get the classroom given by classroomID
        Classroom classroom = null;

        model.addAttribute("classroom", classroom);
        return "classroom/student-details";
    }

    // Form for adding a new classroom
    @RequestMapping("classrooms/add-student")
    public String formForNewClassroom(Model model) {
        // TODO: Add model attributes needed for new form

        return "classroom/form";
    }

    // Form for editing an existing classroom
    @RequestMapping("classrooms/{classroomID}/edit-student")
    public String formForEditingClassroom(@PathVariable Long classroomID, Model model) {
        // TODO: Add model attributes needed for edit form

        return "classroom/form";
    }

    // Update an existing classroom
    @RequestMapping(value = "/classrooms/{classroomID}", method = RequestMethod.POST)
    public String updateClassroom() {
        // TODO: Update classroom if valid data was received

        // TODO: Redirect browser to /categories
        return null;
    }

    // Add a classroom
    @RequestMapping(value = "/classrooms", method = RequestMethod.POST)
    public String addClassroom() {
        // TODO: Add classroom if valid data was received

        // TODO: Redirect browser to /classrooms
        return null;
    }

    // Delete an existing category
    @RequestMapping(value = "/classrooms/{classroomID}/delete", method = RequestMethod.POST)
    public String deleteClassroom(@PathVariable Long classroomID) {
        // TODO: Delete classroom if it contains no GIFs

        // TODO: Redirect browser to /classrooms
        return null;
    }
}
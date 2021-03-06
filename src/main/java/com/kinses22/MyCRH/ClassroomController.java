package com.kinses22.MyCRH;

import com.kinses22.MyCRH.model.Classroom;

import com.kinses22.MyCRH.service.ClassroomService;
import com.kinses22.MyCRH.web.Colour;
import com.kinses22.MyCRH.web.feedbackMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@Controller
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    // Index of all classrooms
    @SuppressWarnings("unchecked")
    @RequestMapping("/classrooms")
    public String listClassrooms(Model model) {

        List<Classroom> classrooms = classroomService.getAllClassrooms();

        model.addAttribute("classrooms",classrooms);
        return "classroom/index";
    }

    // Single classroom page
    @RequestMapping("/classrooms/{classroomID}")
    public String classroom(@PathVariable Long classroomID, Model model) {
        // TODO: Get the classroom given by classroomID
        Classroom classroom = classroomService.findByClassroomID(classroomID);

        model.addAttribute("classroom", classroom);
        return "classroom/details";
    }

    // Form for adding a new classroom
    @RequestMapping("classrooms/add-classroom")
    public String formForNewClassroom(Model model) {
        // TODO: Add model attributes needed for new form
        if(!model.containsAttribute("classroom")){
        model.addAttribute("classroom",new Classroom());
        }
        model.addAttribute("action", "/classrooms");
        model.addAttribute("heading", "New classroom");
        model.addAttribute("submit", "Add");
        model.addAttribute("colours", Colour.values());



        return "classroom/form";
    }

    // Form for editing an existing classroom
    @RequestMapping("classrooms/{classroomID}/edit-classroom")
    public String formForEditingClassroom(@PathVariable Long classroomID, Model model) {
        // TODO: Add model attributes needed for edit form
        if(!model.containsAttribute("classroom")){
            model.addAttribute("classroom",classroomService.findByClassroomID(classroomID));
        }
        model.addAttribute("action", String.format("/classrooms/%s", classroomID));
        model.addAttribute("heading", "Edit classroom");
        model.addAttribute("submit", "Edit");
        model.addAttribute("colours", Colour.values());
        return "classroom/form";
    }

    // Update an existing classroom
    @RequestMapping(value = "/classrooms/{classroomID}", method = RequestMethod.POST)
    public String updateClassroom(@Valid Classroom classroom, RedirectAttributes redirectAttributes, BindingResult res) {
        // TODO: Update classroom if valid data was received
        // TODO: Add classroom if valid data was received
        if(res.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classroom", res);
            redirectAttributes.addFlashAttribute("classroom", classroom);
            return String.format("redirect:/classrooms/%s/edit-classroom", classroom.getId());
        }

        classroomService.save(classroom);

        redirectAttributes.addFlashAttribute("flash", new feedbackMessages("Classroom Updated Successfully", feedbackMessages.Status.PASS));
        // TODO: Redirect browser to /classrooms
        return "redirect:/classrooms";
    }

    // Add a classroom
    @RequestMapping(value = "/classrooms", method = RequestMethod.POST)
    public String addClassroom(@Valid Classroom classroom, BindingResult res, RedirectAttributes redirectAttributes) {

        // TODO: Add classroom if valid data was received
        if(res.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classroom", res);
            redirectAttributes.addFlashAttribute("classroom", classroom);
            return "redirect:/classrooms/add-classroom";
        }

            classroomService.save(classroom);

            redirectAttributes.addFlashAttribute("flash", new feedbackMessages("Classroom Added Successfully", feedbackMessages.Status.PASS));
        // TODO: Redirect browser to /classrooms
        return "redirect:/classrooms";
    }

    // Delete an existing category
    @RequestMapping(value = "/classrooms/{classroomID}/delete", method = RequestMethod.POST)
    public String deleteClassroom(@PathVariable Long classroomID) {
        // TODO: Delete classroom if it contains no GIFs
        Classroom classroom = classroomService.findByClassroomID(classroomID);
        if(classroom.getStudents().size() > 0){
            return String.format("redirect:/classrooms/%s/edit-classroom", classroomID);

        }
            classroomService.detele(classroom);
        // TODO: Redirect browser to /classrooms

        return "redirect:/classrooms";
    }
}
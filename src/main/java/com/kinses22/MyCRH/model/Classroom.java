package com.kinses22.MyCRH.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 12)
    private String className;

    // TODO: fix form template so u can use this validation
   // @NotNull
   // @Pattern(regexp = "#[0-9a-fA-F]{6}")
    private String color;

    @OneToMany(mappedBy = "classroom")
    private List<Student> students = new ArrayList<>();

    public Classroom (){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Student> getStudents() {
        return students;
    }
}

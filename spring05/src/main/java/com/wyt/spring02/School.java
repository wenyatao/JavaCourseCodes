package com.wyt.spring02;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class School implements ISchool {
    
    Klass class1;
    
    Student student;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student);

    }

    public void setClass1(Klass class1) {
        this.class1 = class1;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

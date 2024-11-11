package com.example.cs264.controller;

import com.example.cs264.model.Login;
import com.example.cs264.model.Student;
import com.example.cs264.repository.LoginRepository;
import com.example.cs264.repository.StudentRepository;
import com.example.cs264.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin

public class teacherPageController {
    @Autowired
    LoginRepository loginRepository;
    
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    //Get each student who had subject with teacher and also has not been checked yet(subjectTeacherCheck=false)
    @GetMapping("/tget")
    public List<Student> getStudentByTeacher(@RequestParam(name = "teacher", required = true) String teacher){
        return teacherRepository.getStudentByTeacher(teacher);
    }

    //Use to approve or deny student's subject(subjectTeacherApprove true or false) and change subjectTeacherCheck to true
    @PostMapping("/tcheck")
    public boolean teacherApprove(@RequestParam(name = "check", required = true) boolean check, @RequestParam(name = "subjectCode", required = true) String[] subjectCode, @RequestBody Student  student){
        try {
            teacherRepository.teacherApprove(student, check, subjectCode);
            return  true;
        }catch (Exception e){
            return false;
        }
    }
}

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
@RequestMapping("/api/student")
@CrossOrigin
public class studentPageController {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;


    //Login authentication by input username(studentID) and password through body
    @PostMapping("/login")
    public String loginConfirm(@RequestBody Login login){
        return loginRepository.loginConfirmation(login);
    }

    //Create new student subject data
    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentRepository.createStudent(student);
    }

    //Get student by username(studentID)
    @GetMapping("/req")
    public List<Student> reqStudent(@RequestParam(name = "username", required = true) String username){
        return studentRepository.getStudentByUsername(username);
    }

    //Update student information
    @PostMapping("/update")
    public void updateStudent(@RequestBody Student  student, @RequestParam String studentId){
        studentRepository.updateStudent(student, studentId);
    }

    //Delete student from table
    @GetMapping("/del")
    public void deleteById(@RequestParam String studentId){
        studentRepository.deleteById(studentId);
    }


}

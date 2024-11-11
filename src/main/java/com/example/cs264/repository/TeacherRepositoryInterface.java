package com.example.cs264.repository;

import com.example.cs264.model.Student;
import com.example.cs264.model.Teacher;

import java.util.List;

public interface TeacherRepositoryInterface {
    public List<Student> getStudentByTeacher(String teacher);

    public void teacherApprove(List<Student> student, boolean check, String subjectCode);
}

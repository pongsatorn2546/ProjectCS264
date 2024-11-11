package com.example.cs264.model;
import lombok.*;

@Getter
@Setter
public class Subject {
    private String studentID;
    private String subjectCode;
    private String subjectName;
    private String subjectSection;
    private String subjectDate;
    private String subjectCredit;
    private String subjectTeacher;
    //Check if teacher has checked this subject for this student or not (true is checked(can be approved or denied) and false if not checked)
    private boolean subjectTeacherCheck;
    //Check if teacher approved or denied student
    private boolean subjectTeacherApprove;
    private String registeration_type;

    public boolean getSubjectTeacherCheck() {
        return subjectTeacherCheck;
    }    public boolean getSubjectTeacherApprove() {
        return subjectTeacherApprove;
    }

}

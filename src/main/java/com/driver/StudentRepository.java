package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class StudentRepository {
    private Map<String, Student> studentMap = new HashMap<>();
    private Map<String, Teacher> teacherMap = new HashMap<>();
    private Map<String, List<String>> studentTeacherPairMap = new HashMap<>();
    public void addStudent(Student student) {
        studentMap.put(student.getName(), student);
    }
    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }
    public void addStudentTeacherPair(String student, String teacher) {
        if(!studentMap.containsKey(student) || !teacherMap.containsKey(teacher)) return;
        List<String> studentList = new ArrayList<>();
        if(studentTeacherPairMap.containsKey(teacher)) studentList = studentTeacherPairMap.get(teacher);
        studentList.add(student);
        studentTeacherPairMap.put(teacher, studentList);
        teacherMap.get(teacher).setNumberOfStudents(teacherMap.get(teacher).getNumberOfStudents()+1);
    }
    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }
    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }
    public List<String> getStudentsByTeacherName(String teacher) {
        return studentTeacherPairMap.get(teacher);
    }
    public List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for(String student : studentMap.keySet()) studentList.add(student);
        return studentList;
    }
    public void deleteTeacherByName(String teacher) {
        List<String> studentList = studentTeacherPairMap.get(teacher);
        studentTeacherPairMap.remove(teacher);
        teacherMap.remove(teacher);
        for(String student : studentList) {
            studentMap.remove(student);
        }
    }
    public void deleteAllTeachers() {
        for(String teacher : studentTeacherPairMap.keySet()) {
            List<String> studentList = studentTeacherPairMap.get(teacher);
            studentTeacherPairMap.remove(teacher);
            for(String student : studentList) studentMap.remove(student);
        }
        for(String teacher : teacherMap.keySet()) {
            teacherMap.remove(teacher);
        }
    }
}

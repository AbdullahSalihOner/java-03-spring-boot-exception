package com.salih.service;

import com.salih.model.Student;
import com.salih.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    // Dependency Injection
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /*
    getAllStudent
    getOneStudent
    createStudent
    updateStudent
    deleteStudent
     */

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getOneStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student createStudent(Student student) {
        System.out.println(student.getFirstName() + " " + student.getLastName() + " is adding...");
        return studentRepository.save(student);
    }

    // We use Map to return a message to the user.
    public Map<String, Boolean> deleteStudent(Long id) {

        studentRepository.deleteById(id);
        Map<String, Boolean> deleteResponse = new HashMap<>();
        deleteResponse.put("Deleted ID: " + id, Boolean.TRUE);
        return deleteResponse;

    }


    public Student updateStudent(Long id,Student student) {

        System.out.println(student.getFirstName() + " " + student.getLastName() + " is updating...");
        student.setId(id);
        return studentRepository.save(student);

    }


}

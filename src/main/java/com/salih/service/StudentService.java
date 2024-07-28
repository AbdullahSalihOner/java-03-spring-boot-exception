package com.salih.service;

import com.salih.exception.ResourceNotFoundException_404;
import com.salih.model.Student;
import com.salih.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
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

    public Student getOneStudentV1(Long id) {

        return studentRepository.findById(id).get();
    }

    public ResponseEntity<Student> getOneStudent(Long id) throws ResourceNotFoundException_404 {
        //Control ID
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException_404(" Error: ID not found!"));
        return ResponseEntity.ok().body(student);
    }

    public Student createStudent(Student student) {
        if (studentRepository.findById(student.getId()).isPresent()) {
            System.out.println("ID: " + student.getId() + " is already exist!");

        }
        return studentRepository.save(student);
    }

    // We use Map to return a message to the user.
    public Map<String, Boolean> deleteStudent(Long id)  throws ResourceNotFoundException_404 {

        //Control ID
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException_404(" Error: ID not found!"));


        studentRepository.deleteById(id);
        Map<String, Boolean> deleteResponse = new HashMap<>();
        deleteResponse.put("Deleted ID: " + id, Boolean.TRUE);
        return deleteResponse;

    }


    public Student updateStudent(Long id,Student student) throws ResourceNotFoundException_404 {

        //Control ID
        Student studentInfo = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException_404(" Error: ID not found!"));



        System.out.println(student.getFirstName() + " " + student.getLastName() + " is updating...");
        student.setId(id);
        return studentRepository.save(student);

    }

    public ResponseEntity<Student> updateStudentV2(Long id,Student student) throws ResourceNotFoundException_404 {

        //Control ID
        Student studentInfo = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException_404(" Error: ID not found!"));



        System.out.println(student.getFirstName() + " " + student.getLastName() + " is updating...");
        student.setId(id);
        return ResponseEntity.ok(studentRepository.save(student));

    }


}

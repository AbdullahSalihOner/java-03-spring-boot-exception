package com.salih.service;

import com.salih.exception.ResourceNotFoundException_404;
import com.salih.model.Teacher;
import com.salih.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {

    // Dependency Injection
    private final TeacherRepository teacherRepository;
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /*
    getAllTeacher
    getOneTeacher
    createTeacher
    updateTeacher
    deleteTeacher
     */

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getOneTeacherV1(Long id) {

        return teacherRepository.findById(id).get();
    }

    public ResponseEntity<Teacher> getOneTeacher(Long id) throws ResourceNotFoundException_404 {
        //Control ID
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException_404(" Error: ID not found!"));
        return ResponseEntity.ok().body(teacher);
    }

    public Teacher createTeacher(Teacher teacher) {
        if (teacherRepository.findById(teacher.getId()).isPresent()) {
            System.out.println("ID: " + teacher.getId() + " is already exist!");

        }
        return teacherRepository.save(teacher);
    }

    // We use Map to return a message to the user.
    public Map<String, Boolean> deleteTeacher(Long id)  throws ResourceNotFoundException_404 {

        //Control ID
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException_404(" Error: ID not found!"));


        teacherRepository.deleteById(id);
        Map<String, Boolean> deleteResponse = new HashMap<>();
        deleteResponse.put("Deleted ID: " + id, Boolean.TRUE);
        return deleteResponse;

    }


    public Teacher updateTeacher(Long id,Teacher teacher) throws ResourceNotFoundException_404 {

        //Control ID
        Teacher teacherInfo = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException_404(" Error: ID not found!"));



        System.out.println(teacher.getFirstName() + " " + teacher.getLastName() + " is updating...");
        teacher.setId(id);
        return teacherRepository.save(teacher);

    }

    public ResponseEntity<Teacher> updateTeacherV2(Long id,Teacher teacher) throws ResourceNotFoundException_404 {

        //Control ID
        Teacher teacherInfo = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException_404(" Error: ID not found!"));



        System.out.println(teacher.getFirstName() + " " + teacher.getLastName() + " is updating...");
        teacher.setId(id);
        return ResponseEntity.ok(teacherRepository.save(teacher));

    }


}

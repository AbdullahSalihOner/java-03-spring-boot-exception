package com.salih.controller;

import com.salih.model.Student;
import com.salih.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//APIs are created in this class.
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    // Dependency Injection

    /* Old version
    @Autowired
    private StudentService studentService;
    */

    //New version ;
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    // GET - SELECT ALL
    // http://localhost:8090/api/v1/student
    @GetMapping("/student")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    // GET - SELECT WHERE
    // http://localhost:8090/api/v1/student/{id}
    @GetMapping("/student/{id}")
    public Student getOneStudent(@PathVariable Long id){
        return studentService.getOneStudent(id);
    }

 /* an example of using multiple PathVariable
    @GetMapping("/student/{id}/{firstName}")
    public Student getOneStudent(@PathVariable Long id,@PathVariable( name = "firstName") Student firstName){
        return studentService.getOneStudent(id);
    }
*/

    // POST - INSERT
    // http://localhost:8090/api/v1/student
    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    // DELETE - DELETE
    // http://localhost:8090/api/v1/student/{id}
    @DeleteMapping("/student/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    // PUT - UPDATE
    // http://localhost:8090/api/v1/student/{id}
    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student) {
        return studentService.updateStudent(id,student);
    }

}

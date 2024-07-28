package com.salih.controller;

import com.salih.exception.ResourceNotFoundException_404;
import com.salih.model.Student;
import com.salih.service.StudentService;
import org.springframework.http.ResponseEntity;
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
    // http://localhost:8090/api/v1/student/v1/{id}
    @GetMapping("/student/v1/{id}")
    public Student getOneStudentV1(@PathVariable Long id){
        return studentService.getOneStudentV1(id);
    }


    // GET - SELECT WHERE
    // http://localhost:8090/api/v1/student/{id}
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getOneStudent(@PathVariable Long id) throws ResourceNotFoundException_404 {
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
    public Map<String, Boolean> deleteStudent(@PathVariable Long id)  throws ResourceNotFoundException_404 {
        return studentService.deleteStudent(id);
    }

    // PUT - UPDATE
    // http://localhost:8090/api/v1/student/{id}
    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student) throws ResourceNotFoundException_404 {
        return studentService.updateStudent(id,student);
    }


    // PUT - UPDATE
    // http://localhost:8090/api/v1/student/v1/{id}
    @PutMapping("/student/v1/{id}")
    public ResponseEntity<Student> updateStudentV2(@PathVariable Long id,
                                 @RequestBody Student student) throws ResourceNotFoundException_404 {
        return studentService.updateStudentV2(id,student);
    }

}

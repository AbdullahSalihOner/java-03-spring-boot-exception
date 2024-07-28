package com.salih.controller;

import com.salih.exception.ResourceNotFoundException_404;
import com.salih.model.Teacher;
import com.salih.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//APIs are created in this class.
@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    // Dependency Injection

    /* Old version
    @Autowired
    private TeacherService teacherService;
    */

    //New version ;
    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    // GET - SELECT ALL
    // http://localhost:8090/api/v1/teacher
    @GetMapping("/teacher")
    public List<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    // GET - SELECT WHERE
    // http://localhost:8090/api/v1/teacher/v1/{id}
    @GetMapping("/teacher/v1/{id}")
    public Teacher getOneTeacherV1(@PathVariable Long id){
        return teacherService.getOneTeacherV1(id);
    }


    // GET - SELECT WHERE
    // http://localhost:8090/api/v1/teacher/{id}
    @GetMapping("/teacher/{id}")
    public ResponseEntity<Teacher> getOneTeacher(@PathVariable Long id) throws ResourceNotFoundException_404 {
        return teacherService.getOneTeacher(id);
    }

 /* an example of using multiple PathVariable
    @GetMapping("/teacher/{id}/{firstName}")
    public Teacher getOneTeacher(@PathVariable Long id,@PathVariable( name = "firstName") Teacher firstName){
        return teacherService.getOneTeacher(id);
    }
*/

    // POST - INSERT
    // http://localhost:8090/api/v1/teacher
    @PostMapping("/teacher")
    public Teacher createTeacher(@RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }

    // DELETE - DELETE
    // http://localhost:8090/api/v1/teacher/{id}
    @DeleteMapping("/teacher/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable Long id)  throws ResourceNotFoundException_404 {
        return teacherService.deleteTeacher(id);
    }

    // PUT - UPDATE
    // http://localhost:8090/api/v1/teacher/{id}
    @PutMapping("/teacher/{id}")
    public Teacher updateTeacher(@PathVariable Long id,
                                 @RequestBody Teacher teacher) throws ResourceNotFoundException_404 {
        return teacherService.updateTeacher(id,teacher);
    }


    // PUT - UPDATE
    // http://localhost:8090/api/v1/teacher/v1/{id}
    @PutMapping("/teacher/v1/{id}")
    public ResponseEntity<Teacher> updateTeacherV2(@PathVariable Long id,
                                 @RequestBody Teacher teacher) throws ResourceNotFoundException_404 {
        return teacherService.updateTeacherV2(id,teacher);
    }

}

package com.student.manager.controller;

import com.student.manager.entities.Student;
import com.student.manager.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<Student> createUser(@RequestBody Student student) {
        Student user = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // all student get
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllUser() {
        List<Student> allUser = studentService.getAllStudent();
        return ResponseEntity.ok(allUser);
    }


    // Single Book
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Long studentId) {

        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(student);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long studentId) {

        try {
            this.studentService.deleteStudent(studentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // Update book handler
    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("studentId") Long studentId) {
        try {
            studentService.updateStudent(student, studentId);
            return ResponseEntity.ok().body(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

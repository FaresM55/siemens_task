package com.siemens_task.controller;

import com.siemens_task.dto.TeacherDTO;
import com.siemens_task.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers/")
public class TeacherController {

    private TeacherService teacherService;


    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return new ResponseEntity<>(teacherService.createTeacher(teacherDTO), HttpStatus.CREATED);
    }

    @GetMapping("find-by-email")
    public ResponseEntity<TeacherDTO> getTeacherByEmail(@RequestParam String email) {
        return new ResponseEntity<>(teacherService.getTeacherByEmail(email), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/find-by-first-letter")
    public ResponseEntity<List<TeacherDTO>> getTeachersWithFirstOrLastNameStartsWithLetter(@RequestParam String letter) {
        return new ResponseEntity<>(teacherService.getTeachersByFirstNameOrLastNameLike(letter), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable Long id) {
        teacherService.deleteTeacherById(id);
        return new ResponseEntity<>("Teacher deleted successfully", HttpStatus.OK);
    }

}

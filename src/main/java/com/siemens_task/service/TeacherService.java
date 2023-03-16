package com.siemens_task.service;

import com.siemens_task.dto.TeacherDTO;

import java.util.List;


public interface TeacherService {
    TeacherDTO createTeacher(TeacherDTO teacherDTO);
    TeacherDTO getTeacherByEmail(String email);
    List<TeacherDTO> getTeachersByFirstNameOrLastNameLike(String letter);
    void deleteTeacherById(Long id);

    List<TeacherDTO> getAllTeachers();
}

package com.siemens_task.service;

import com.siemens_task.dto.TeacherDTO;
import com.siemens_task.entity.Teacher;
import com.siemens_task.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    private ModelMapper modelMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {

        Teacher teacher = mapToEntity(teacherDTO);
        Teacher newTeacher = teacherRepository.save(teacher);
        return mapToDTO(newTeacher);
    }

    @Override
    public TeacherDTO getTeacherByEmail(String email) {
        return mapToDTO(teacherRepository.findTeacherByEmail(email));
    }

    @Override
    public List<TeacherDTO> getTeachersByFirstNameOrLastNameLike(String letter) {

        List<Teacher>  teachers = teacherRepository.findByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(letter, letter);
        List<TeacherDTO> teacherDTOS = teachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).collect(Collectors.toList());
        return teacherDTOS;

    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toList());
    }


    private TeacherDTO mapToDTO(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDTO.class);
    }

    private Teacher mapToEntity(TeacherDTO teacherDTO) {
        return modelMapper.map(teacherDTO, Teacher.class);
    }
}

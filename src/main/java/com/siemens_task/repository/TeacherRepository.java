package com.siemens_task.repository;

import com.siemens_task.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Teacher findTeacherByEmail(String email);

    List<Teacher> findByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(String firstName, String lastName);

}

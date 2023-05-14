package com.university.managemant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.managemant.model.Student;
import com.university.managemant.model.Teacher;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Student findByEmail(String email);

	List<Student> findByTeacher(Teacher teacher);

}

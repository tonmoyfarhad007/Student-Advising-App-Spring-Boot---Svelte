package com.university.managemant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.managemant.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	Teacher findByEmail(String email);

}

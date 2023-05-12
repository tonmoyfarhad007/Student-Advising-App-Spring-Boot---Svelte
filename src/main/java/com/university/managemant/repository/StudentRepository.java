package com.university.managemant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.managemant.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Student findByEmail(String email);

}

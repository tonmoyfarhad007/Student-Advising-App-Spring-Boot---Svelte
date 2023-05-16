package com.university.managemant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.managemant.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

	Request findByStudentEmail(String studentEmail);

	List<Request> findByTeacherEmail(String teacherEmail);

}

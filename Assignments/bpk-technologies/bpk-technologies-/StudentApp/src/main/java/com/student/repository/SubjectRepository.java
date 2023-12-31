package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.demo.model.Employee;

public interface EmployeeRepositary extends JpaRepository<Employee, Long> {

}
	
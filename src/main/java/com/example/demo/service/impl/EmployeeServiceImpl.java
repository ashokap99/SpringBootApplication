package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepositary;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepositary  employeeRepositary;
	

	public EmployeeServiceImpl(EmployeeRepositary employeeRepositary) {
		super();
		this.employeeRepositary = employeeRepositary;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepositary.save(employee);
	}


	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepositary.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		/*
		 * Optional<Employee> employee = employeeRepositary.findById(id);
		 * if(employee.isPresent()) { return employee.get(); }else { throw new
		 * ResourceNotFound("Employee", "Id", "id"); }
		 */
		//lamda expression
		return employeeRepositary.findById(id).orElseThrow(() -> new ResourceNotFound("Employee", "Id", "id"));
		}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		//we need to check whether the employee with id existing or not in database
		Employee existingEmployee = employeeRepositary.findById(id).orElseThrow(
				() -> new ResourceNotFound("Employee", "Id", "id"));	
		
		existingEmployee.setName(employee.getName());
		existingEmployee.setDesignation(employee.getDesignation());
		existingEmployee.setEmail(employee.getEmail());
		
		employeeRepositary.save(existingEmployee);
	return existingEmployee;
	}


	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		//we need to check whether the employee with id existed or not in DB
		employeeRepositary.findById(id).orElseThrow(() -> new ResourceNotFound("Employee", "Id", "id"));
		employeeRepositary.deleteById(id);
		
	}

}

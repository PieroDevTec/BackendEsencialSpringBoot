package com.example.rh.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rh.app.model.Employee;
import com.example.rh.app.model.EmployeeByDeparment;
import com.example.rh.app.repository.IEmployeeRepo;
import com.example.rh.app.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private IEmployeeRepo employeeRepo;

	@Override
	public Employee getEmployee(Integer cod_employee) {
		return employeeRepo.getEmployee(cod_employee);
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepo.getEmployees();
	}

	@Override
	public List<EmployeeByDeparment> listEmployeeByDeparment() {
		// TODO Auto-generated method stub
		return employeeRepo.getEmployeeByDepartment();
	}

	
	
}

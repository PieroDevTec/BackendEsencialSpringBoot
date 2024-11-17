package com.example.rh.app.service;

import java.util.List;

import com.example.rh.app.model.Employee;
import com.example.rh.app.model.EmployeeByDeparment;

public interface IEmployeeService {
	public Employee getEmployee(Integer cod_employee);
	public List<Employee> getEmployees();
	public List<EmployeeByDeparment> listEmployeeByDeparment();
}

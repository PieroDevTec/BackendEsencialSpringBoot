package com.example.rh.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rh.app.model.Employee;
import com.example.rh.app.model.EmployeeByDeparment;
import com.example.rh.app.service.IEmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping(value = "getEmployee",method = RequestMethod.GET)
	public Employee getEmployee(@RequestParam Integer cod_employee) {
		return employeeService.getEmployee(cod_employee);
	}
	@RequestMapping(value = "getEmployees",method = RequestMethod.GET)
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@RequestMapping(value = "listEmployeeByDepartment",method = RequestMethod.GET)
	public List<EmployeeByDeparment> listEmployeeByDeparment(){
		return employeeService.listEmployeeByDeparment();
	}

}

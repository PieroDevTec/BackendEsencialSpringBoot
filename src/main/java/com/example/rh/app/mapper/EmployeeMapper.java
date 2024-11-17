package com.example.rh.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.rh.app.model.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmployee_id(rs.getInt("employee_id"));
		employee.setFirst_name(rs.getString("first_name"));
		employee.setLast_name(rs.getString("last_name"));
		employee.setEmail(rs.getString("email"));
		employee.setPhone_number(rs.getString("phone_number"));
		return employee;
	}
	
}

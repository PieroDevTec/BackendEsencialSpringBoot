package com.example.rh.app.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.rh.app.mapper.EmployeeMapper;
import com.example.rh.app.model.Employee;
import com.example.rh.app.model.EmployeeByDeparment;
import com.example.rh.app.repository.IEmployeeRepo;

import oracle.jdbc.OracleTypes;


@Repository
public class EmployeeRepoImpl extends JdbcDaoSupport implements IEmployeeRepo {
	
	@Autowired
	private ApplicationContext context;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void dataSource(DataSource setDataSource) {
		setDataSource(setDataSource);
		this.jdbcTemplate = context.getBean("db_desa",JdbcTemplate.class);
	}

	@Override
	public Employee getEmployee(Integer cod_employee) {
		String sql = "select employee_id, first_name,last_name,email,phone_number from employees where employee_id = ?";
		Employee employee = new Employee();
		employee = jdbcTemplate.queryForObject(sql,new EmployeeMapper(),cod_employee);
		return employee;
	}

	@Override
	public List<Employee> getEmployees() {
		String sql = "select e.employee_id, e.first_name,e.last_name,e.email,e.phone_number from hr.employees e where e.salary > 15000";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public List<EmployeeByDeparment> getEmployeeByDepartment() {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName("HR")
				.withProcedureName("SP_LIST_EMPLOYEE_BY_DEPARTMENT")
				.declareParameters(
						new SqlParameter("pin_department_id",OracleTypes.INTEGER),
						new SqlOutParameter("DATA_EMPLOYEES_DEPARTMENT",OracleTypes.REF_CURSOR))
				.returningResultSet("DATA_EMPLOYEES_DEPARTMENT", BeanPropertyRowMapper.newInstance(EmployeeByDeparment.class));
		
		
		SqlParameterSource inputParameters = new MapSqlParameterSource()
				.addValue("pin_department_id",100);
		
		Map<String, Object> outParameter = simpleJdbcCall.execute(inputParameters);
		List<EmployeeByDeparment> employeeByDeparment = new ArrayList<>();
		employeeByDeparment = (List<EmployeeByDeparment>) outParameter.get("DATA_EMPLOYEES_DEPARTMENT");
		return employeeByDeparment;
	}
	
}

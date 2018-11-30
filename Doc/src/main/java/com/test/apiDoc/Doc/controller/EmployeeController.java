package com.test.apiDoc.Doc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.apiDoc.Doc.model.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee Management System")
public class EmployeeController {

	public static List<Employee> employeeList = new ArrayList<Employee>();

	@ApiOperation(value = "View a list of available employees", response = List.class)
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {

		for (int i = 0; i < 5; i++) {
			Employee employee = new Employee(i, "Dummy" + i, "Data", "mailNotFound" + i + "@gmail.com");
			employeeList.add(employee);
		}

		return employeeList;

	}

	@ApiOperation(value = "Find a particular employee By Id", response = ResponseEntity.class)
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		Employee employee = (Employee) employeeList.stream().filter(emp -> emp.getId() == employeeId);
		return ResponseEntity.ok().body(employee);
	}

	@ApiOperation(value = "create new Employee", response = String.class)
	@PostMapping("/employees")
	public String createEmployee(@Valid @RequestBody Employee employee) {
		employeeList.add(employee);
		return "New Employee added";
	}

}

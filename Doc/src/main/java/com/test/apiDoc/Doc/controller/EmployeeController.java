package com.test.apiDoc.Doc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

	@PostConstruct
	public void createEmplooyeList() {
		for (int i = 0; i < 5; i++) {
			Employee employee = new Employee(i, "Dummy" + i, "Data", "mailNotFound" + i + "@gmail.com");
			employeeList.add(employee);
		}
	}

	@ApiOperation(value = "View a list of available employees", response = List.class, consumes = "Nothing")
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeList;

	}

	@ApiOperation(value = "Find a particular employee By Id", response = ResponseEntity.class, consumes = "Integer Type employee Id")
	@GetMapping("/employees/{id}")
	@Cacheable(cacheNames="findById",key="{#id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") int employeeId) {

		Optional<Employee> employee = employeeList.stream().filter(emp -> emp.getId() == employeeId).findFirst();
		if (employee.isPresent()) {
			System.out.println("Getting response from filteration.....");
			Employee emp = employee.get();
			return ResponseEntity.ok().body(emp);
		} else {
			return null;
		}

	}

	@ApiOperation(value = "create new Employee", response = String.class, consumes = "Expected Employee object")
	@PostMapping("/employees")
	public String createEmployee(@Valid @RequestBody Employee employee) {
		employeeList.add(employee);
		return "New Employee added";
	}
	
	@GetMapping("/clearCache")
	//To clear cache by concurrentHashMap Id
	@CacheEvict(cacheNames="findById",allEntries=true)
	public void clearCatche() {}

}

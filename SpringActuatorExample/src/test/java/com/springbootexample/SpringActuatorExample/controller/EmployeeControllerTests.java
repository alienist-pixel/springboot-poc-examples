package com.springbootexample.SpringActuatorExample.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootexample.SpringActuatorExample.entity.Employee;
import com.springbootexample.SpringActuatorExample.service.EmployeeService;

@WebMvcTest(value = EmployeeController.class)
@RunWith(SpringRunner.class) //This will initiate MockBean
public class EmployeeControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {

		Employee employee = new Employee("Ramesh","Fadatare","ramesh@gmail.com");
		given(employeeService.saveEmployee(any(Employee.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(post("/api/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employee)))
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(employee.getLastName())))
				.andExpect(jsonPath("$.email", is(employee.getEmail())));

	}

	@Test
	public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
		// given - precondition or setup
		List<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees.add(new Employee("Ramesh","Fadatare","ramesh@gmail.com"));
		listOfEmployees.add(new Employee("Ramesh1","Fadatare1","ramesh1@gmail.com"));
		given(employeeService.getAllEmployees()).willReturn(listOfEmployees);

		ResultActions response = mockMvc.perform(get("/api/employees"));
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(listOfEmployees.size())));

	}

	// positive scenario - valid employee id JUnit test for GET employee by id REST API
	@Test
	public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
		// given - precondition or setup
		long employeeId = 1L;
		Employee employee = new Employee("Ramesh","Fadatare","ramesh@gmail.com");;
		given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

		ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(employee.getLastName())))
				.andExpect(jsonPath("$.email", is(employee.getEmail())));

	}

	@Test
	public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception {
		// given - precondition or setup
		long employeeId = 1L;
		Employee employee = new Employee("Ramesh","Fadatare","ramesh@gmail.com");
		given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId));

		// then - verify the output
		response.andExpect(status().isNotFound()).andDo(print());

	}

	// JUnit test for update employee REST API - positive scenario
	@Test
	public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdateEmployeeObject() throws Exception {
		// given - precondition or setup
		long employeeId = 1L;
		Employee savedEmployee = new Employee("Ramesh","Fadatare","ramesh@gmail.com");
		Employee updatedEmployee = new Employee("Ramesh","Fadatare","ramesh@gmail.com");
		given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(savedEmployee));
		given(employeeService.updateEmployee(any(Employee.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(put("/api/employees/{id}", employeeId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedEmployee)))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(updatedEmployee.getLastName())))
				.andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())));
	}

	// JUnit test for update employee REST API - negative scenario
	@Test
	public void givenUpdatedEmployee_whenUpdateEmployee_thenReturn404() throws Exception {
		// given - precondition or setup
		long employeeId = 1L;
		Employee savedEmployee = new Employee("Ramesh","Fadatare","ramesh@gmail.com");
		Employee updatedEmployee = new Employee("Ramesh","Fadatare","ramesh@gmail.com");
		given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
		given(employeeService.updateEmployee(any(Employee.class))).willAnswer((invocation) -> invocation.getArgument(0));

		ResultActions response = mockMvc.perform(put("/api/employees/{id}", employeeId)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedEmployee)));

		response.andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception {
		// given - precondition or setup
		long employeeId = 1L;
		willDoNothing().given(employeeService).deleteEmployee(employeeId);
		ResultActions response = mockMvc.perform(delete("/api/employees/{id}", employeeId));
		response.andExpect(status().isOk()).andDo(print());
	}
}

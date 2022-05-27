package com.springbootexample.SpringActuatorExample.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmployeePaymentServiceTest {
	private int TESTWRKINGDAYS = 25;
	private int TESTSALARYPERDAY = 1000;
	private int TESTSALARY = 25000;
	private String EMPID = "emp100";
	
	@Mock
	private EmployeePaymentService employeePaymentService;

	@Before
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetSalary() {
		// Tell mockito to mock all the three methods
		when(employeePaymentService.getNoOfWorkingDays(anyString())).thenReturn(TESTWRKINGDAYS);
		when(employeePaymentService.getSalaryPerDay(anyString())).thenReturn(TESTSALARYPERDAY);
		int returnedWrkingDays = employeePaymentService.getNoOfWorkingDays(EMPID);
		Assert.assertEquals(returnedWrkingDays, TESTWRKINGDAYS);
		int returnedSalaryPerDay = employeePaymentService.getSalaryPerDay(EMPID);
		Assert.assertEquals(returnedSalaryPerDay, TESTSALARYPERDAY);
		int returnedSalary = employeePaymentService.processPay(EMPID, TESTWRKINGDAYS, TESTSALARYPERDAY);
		Assert.assertEquals(returnedSalary, TESTSALARY);
	}
}

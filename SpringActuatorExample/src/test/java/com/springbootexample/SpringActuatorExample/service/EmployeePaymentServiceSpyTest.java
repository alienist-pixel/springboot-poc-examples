package com.springbootexample.SpringActuatorExample.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class EmployeePaymentServiceSpyTest {
	private int TESTWRKINGDAYS = 25;
	private int TESTSALARYPERDAY = 1000;
	private int TESTSALARY = 25000;
	private String EMPID = "emp100";
	
	@Spy
	private EmployeePaymentService employeePaymentService;

	@Before
	public void beforeMethod() {
		employeePaymentService = new EmployeePaymentService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
    public void testGetSalary() {
        //Tell mockito to mock only the processPay Method
        when(employeePaymentService.processPay(anyString(), anyInt(), anyInt())).thenReturn(TESTSALARY);
        
        //Actual call made
        int returnedWrkingDays = employeePaymentService.getNoOfWorkingDays(EMPID);
        Assert.assertEquals(returnedWrkingDays, TESTWRKINGDAYS);
        int returnedSalaryPerDay = employeePaymentService.getSalaryPerDay(EMPID);
        Assert.assertEquals(returnedSalaryPerDay, TESTSALARYPERDAY);
        
        //Mock call
        int returnedSalary = employeePaymentService.processPay(EMPID, TESTWRKINGDAYS, TESTSALARYPERDAY);
        Assert.assertEquals(returnedSalary, TESTSALARY);

    }
}

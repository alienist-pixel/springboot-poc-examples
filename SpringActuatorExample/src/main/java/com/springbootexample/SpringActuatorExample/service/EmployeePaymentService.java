package com.springbootexample.SpringActuatorExample.service;

public class EmployeePaymentService {

	public int getNoOfWorkingDays(String empId) {
        int noOfWorkingDays = 0;
        noOfWorkingDays = 25;
        return noOfWorkingDays;
    }

    public int getSalaryPerDay(String empId) {
        int salaryPerDay = 0;
        salaryPerDay = 1000;

        return salaryPerDay;
    }
    public int processPay(String empId, int empWrkingDays, int empSalaryPerDay) {
        // code for processing is not done internally here, but a third party external API call is made. third party API call
    	// return thirdparty.getSalaryThirdPartyCall(empId,empWrkingDays,empSalaryPerDay); returning some default value
        return 20000;
    }
}

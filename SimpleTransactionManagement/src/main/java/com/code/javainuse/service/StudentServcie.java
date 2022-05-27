package com.code.javainuse.service;

import java.util.List;

import com.code.javainuse.model.Student;

public interface StudentServcie {

	public List<Student> retrieve();
	public String insert(Student student);
	public String delete(int StudentId);
	String insertPropagateBy(Student student);
	String insertPropagateByOther(Student student);
}

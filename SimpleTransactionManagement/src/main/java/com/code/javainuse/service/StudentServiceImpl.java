package com.code.javainuse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.code.javainuse.model.Student;
import com.code.javainuse.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentServcie {

	@Autowired
	StudentRepository studentRepository;

	@Override
	@Transactional
	// A Proxy is Created that wraps the function retrieve
	// BeginTransaction
	public List<Student> retrieve() {
		List<Student> studentList = (List<Student>) studentRepository.findAll();
		return studentList;
	}
	// Commit Transaction
	
	@Transactional(propagation = Propagation.REQUIRED)
	public String insert(Student student) {
		insertPropagateBy(student);
		insertPropagateByOther(new Student(student.getStudentId()+1, student.getStudentName()+"_propagate_2", 0));
		return "All Student inserted in the database";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String insertPropagateBy(Student student) {
		Student save = studentRepository.save(student); // Call to the Repository
		if (save.getStudentId() == 5) {
			int a = 1 / 0;
			//The record will be rollover db even there is an exception, when propagation is REQUIRED, 
			//The record will be db even there is an exception, when propagation is SUPPORTS, 
		}
		if (save != null) {
			return "The Student is successfully inserted within the database";
		}
		return "Failed to insert Student within the database";
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String insertPropagateByOther(Student student) {
			Student save = studentRepository.save(student); // Call to the Repository
			if (save.getStudentId() == 5) {
				int a = 1 / 0;
				//The record will be rollover db even there is an exception, when propagation is REQUIRED, 
				//The record will be db even there is an exception, when propagation is SUPPORTS, 
			}
			if (save != null) {
				return "The Student is successfully inserted within the database";
			}
		return "Failed to insert Student within the database";
	}

	@Override
	@Transactional
	// A Proxy is Created that wraps the function delete
	// BeginTransaction
	public String delete(int StudentId) {
		studentRepository.deleteById(StudentId);
		return "The Student is successfully deleted from the database";
	}
	// Commit Transaction

}

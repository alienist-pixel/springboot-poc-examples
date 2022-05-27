package com.code.javainuse.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.javainuse.model.Student;
import com.code.javainuse.repository.StudentRepository;
import com.code.javainuse.service.StudentServcie;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

	@Autowired
	StudentServcie studentService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/admin")
	@RolesAllowed("admin")
    public String home() {
        return "Welcome, admin";
    }

	@GetMapping("/")
    public String homepage() {
		studentRepository.findAll();
        return "Welcome, home";
    }
	
	@RolesAllowed("user")
	@GetMapping("/retrieve")
	public List<Student> retrieve() {
		List<Student> studentList = studentService.retrieve();
		return studentList;
	}

	@RolesAllowed("user")
	@PostMapping("/insert")
	public String insert(@RequestBody Student student) {
		if (student == null) {
			return "Failed to insert Student within the database";
		} else {
			String result = studentService.insert(student);
			return result;
		}
	}

	@DeleteMapping("/delete")
	public String delete(int studentId) {
		String result = studentService.delete(studentId);
		return result;
	}
}

package com.springbootexample.SpringActuatorExample;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootexample.SpringActuatorExample.entity.Employee;
import com.springbootexample.SpringActuatorExample.repository.EmployeeRepository;
import com.springbootexample.SpringActuatorExample.todo.ToDo;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ToDOTests {
	
    private JacksonTester<ToDo> json;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }
	
	@Test
    public void toDoDeserializeTest() throws Exception {
        String content = "{\"description\":\"Read a Book\",\"completed\": true }";
        assertTrue(this.json.parseObject(content).getDescription().equals("Read a Book"));
    }
	
	@Test
	public void whenFindByName_thenReturnEmployee() {
	    // given
	    Employee alex = new Employee("Sebastian","Bach","sebastian@mail.com");
	    entityManager.persist(alex);
	    entityManager.flush();
	    Optional<Employee> found = employeeRepository.findById(alex.getId());
	   ;
	}
}

package com.code.javainuse.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.code.javainuse.model.Manager;
import com.code.javainuse.model.Person;
import com.code.javainuse.model.Student1;

@Configuration
public class PersonFactory {

    @Bean("student")
    //@Qualifier("studnet")
    public Person createStudent() {
        return new Student1();
    }
    
    @Bean("manager")
    //@Qualifier("manager")
    public Person createManager() {
        return new Manager();
    }    
}
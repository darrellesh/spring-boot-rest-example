package com.javarticle.spring.rest;

import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTControllerExample {
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Collection<Employee> getEmployeeNames() {
        return EmployeeSource.getEmployees();
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") String id) {
        return EmployeeSource.getEmployee(id);
    }

}

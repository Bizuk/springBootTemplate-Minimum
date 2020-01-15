package com.github.mhzhou95.javaSpringBootTemplate.controller;

import com.github.mhzhou95.javaSpringBootTemplate.model.Person;
import com.github.mhzhou95.javaSpringBootTemplate.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/people")
public class PersonController {
    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        Iterable<Person> allPeople = service.findAll();
        List<Person> list = new ArrayList<>();
        allPeople.forEach( (person)-> list.add(person));
        ResponseEntity<?> responseFindAll = new ResponseEntity<>(allPeople, HttpStatus.OK);
        return responseFindAll;
    }

    public ResponseEntity<?> createPerson(@RequestBody Person person){
        Person responseBody = service.createPerson(person);
        ResponseEntity<?> responseCreatePerson = new ResponseEntity<>(responseBody, HttpStatus.OK);
        return responseCreatePerson;
    }

}

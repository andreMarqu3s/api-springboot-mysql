package com.example.apispringbootmysql.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.apispringbootmysql.controller.dto.PersonRq;
import com.example.apispringbootmysql.controller.dto.PersonRs;
import com.example.apispringbootmysql.model.Person;
import com.example.apispringbootmysql.repository.PersonRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public List<PersonRs> findAll() {
        var persons = personRepository.findAll();
        return persons
                .stream()
                .map(PersonRs::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonRs findById(@PathVariable("id") Long id) {
        var person = personRepository.getById(id);
        return PersonRs.converter(person);
    }

    @PostMapping("/")
    public void savePerson(@RequestBody PersonRq person) {
        var p = new Person();
        p.setName(person.getName());
        p.setSurname(person.getSurname());
        personRepository.save(p);
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable("id") Long id, @RequestBody PersonRq person) throws Exception {
        var p = personRepository.findById(id);

        if(p.isPresent()) {
            var personSave = p.get();
            personSave.setName(person.getName());
            personSave.setSurname(person.getSurname());
            personRepository.save(personSave);
        } else {
            throw new Exception("Person not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personRepository.deleteById(id);
    }
 }

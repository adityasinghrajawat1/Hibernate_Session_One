package com.springboot.session.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.session.model.Person;
import com.springboot.session.service.PersonService;

@RestController
@RequestMapping("/session")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("start")
	public String getStarted()
	{
		return "i am starting...........................";
	}
	
	@PostMapping("save")
	public Person savePerson(@RequestBody Person person)
	{
		return personService.savePerson(person);
	}
	
	@GetMapping("getAll")
	public List<Person> getAllPerson()
	{
		return personService.getAllPerson();
	}
	
	@GetMapping("getById/{id}")
	public Person getPersonById(@PathVariable Long id)
	{
		return personService.getPersonById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deletePerson(@PathVariable Long id)
	{
		   personService.deletePerson(id);
		   return "Person removed with id"+id;
	}
	
	@PutMapping("/update/{id}")
	public Person updatePerson(@RequestBody Person person,@PathVariable Long id)
	{
		return personService.updatePerson(person, id);
	}
}

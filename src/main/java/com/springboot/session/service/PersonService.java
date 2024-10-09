package com.springboot.session.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.session.model.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



@Service
public class PersonService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public Person savePerson(Person person)
	{
		//get the hibernate session from the entity manager
		Session session = entityManager.unwrap(Session.class);
		
		//Save OR Update
		session.saveOrUpdate(person);
		
		return person;
	}
	
	@Transactional
	public List<Person> getAllPerson()
	{
		// get  the hibernate session from the entity manager
		Session session = entityManager.unwrap(Session.class);
		
		//using the session to fetch all Persons
		return session.createQuery("from Person",Person.class).getResultList();
		
	}
	
	@Transactional
	public Person getPersonById(Long id)
	{
		// get the hibernate session from the entity manager
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(Person.class, id);
	}
	
	@Transactional
	public boolean deletePerson(Long id)
	{
		//get the hibernate session from the entity manager
		Session session = entityManager.unwrap(Session.class);
		
		Person person = session.get(Person.class, id);
		
		if(person != null)
		{
			session.delete(person);
			return true;
		}
		
		throw new IllegalArgumentException("No person found with id"+id);
	}
	
	
	@Transactional
	public Person updatePerson(Person person, Long id)
	{
		//get the hibernate session from the entity manager
		Session session = entityManager.unwrap(Session.class);
		
		Person existingPerson = session.get(Person.class, id);
		
		if(existingPerson != null)
		{
			existingPerson.setFirstName(person.getFirstName());
			existingPerson.setLastName(person.getLastName());
			
			session.update(existingPerson);
		}
		
		return existingPerson;
	}
}

package com.my.app.repository;

import com.my.app.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

/**
 * Created by mgiec on 9/6/2016.
 */
@Repository
public class PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void savePerson(Person person){
        entityManager.persist(person);
        entityManager.flush();
        System.out.println(person);
        person.setFirstName("test!");

        Person newPerson = findById(person.getId());
        System.out.println(person == newPerson);
        System.out.println(newPerson);


    }

    @Transactional
    public Person test1(){
        Person newPerson = findById(54);
        System.out.println(newPerson);
        newPerson.setFirstName(UUID.randomUUID().toString());
        return newPerson;
    }

    @Transactional
    public void test2(){
        Person newPerson = findById(54);
        System.out.println(newPerson);
    }

    public Person findById(long id){
        return entityManager.find(Person.class, id);
    }

    public List<Person> findByNameAndLastname(String firstname, String lastname){
        TypedQuery query = entityManager.createQuery("select p from Person  join fetch p.contacts where p.firstName=:firstname and p.lastname=:lastname",Person.class);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        List<Person> people = query.getResultList();
        return people;
    }


//    public void cost(){
//        savePerson(da);
//        saveContact(da);
//    }
}

package com.my.app.repository;

import com.my.app.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mgiec on 9/6/2016.
 */
@Repository
public class PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Person savePerson(Person person){
        entityManager.persist(person);
        entityManager.flush();
        Person personFromDb = findById(person.getId());
        return personFromDb;
    }

    public Person findById(long id){
        return entityManager.find(Person.class, id);
    }

    public List<Person> findByNameAndLastname(String firstname, String lastname){
        TypedQuery query = entityManager.createQuery("select p from Person p where p.firstName=:firstname and p.lastname=:lastname",Person.class);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        List<Person> people = query.getResultList();
        return people;
    }
}

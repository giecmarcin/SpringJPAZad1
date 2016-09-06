package com.my.app.repository;

import com.my.app.model.Contact;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mgiec on 9/6/2016.
 */

@Repository
public class ContactDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Contact saveContact(Contact contact){
        entityManager.persist(contact);
        entityManager.flush();
        return  findContactById(contact.getId());
    }

    public Contact findContactById(long id){
        return entityManager.find(Contact.class, id);
    }
}

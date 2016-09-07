package com.my.app;

import com.my.app.config.AppConfig;
import com.my.app.model.Contact;
import com.my.app.model.Person;
import com.my.app.repository.PersonDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PersonDao personDao = context.getBean("personDao", PersonDao.class);


        String personFirstname = UUID.randomUUID().toString();
        String personLastname = UUID.randomUUID().toString();
        Person person = new Person(personFirstname, personLastname);
        List<Contact> contacts = new ArrayList<>();
        for(int i=0; i<4;i++){
            contacts.add(new Contact(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        }
        person.setContacts(contacts);
        personDao.savePerson(person);

        for(Contact c: person.getContacts()){
            System.out.println(c.toString());
        }

//        Person person = personDao.test1();
//        person.setFirstName("OUTER SCOPE!");
//        personDao.test2();
    }
}

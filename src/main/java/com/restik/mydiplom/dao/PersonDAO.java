package com.restik.mydiplom.dao;

import com.restik.mydiplom.entity.Person;
import com.restik.mydiplom.exception.ProjException;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

public class PersonDAO extends DAO{
    public PersonDAO() {
    }

    public Person get(String username, String password)
            throws ProjException {
        try {
            begin();
            Query q = getSession().createQuery("from Person where username = :username AND password = :password ");
            q.setParameter("username", username);
            q.setParameter("password", password);


            Person person = (Person) q.uniqueResult();
            commit();
            return person;

        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Could not Login. Check if username and password are correctly entered " + e.getMessage());


        }


    }



}

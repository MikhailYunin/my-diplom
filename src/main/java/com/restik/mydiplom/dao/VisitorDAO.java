package com.restik.mydiplom.dao;

import com.restik.mydiplom.entity.Person;
import com.restik.mydiplom.entity.Visitor;
import com.restik.mydiplom.exception.ProjException;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

public class VisitorDAO extends DAO {

    public VisitorDAO() {
    }


    public Visitor create(String firstName, String lastName, String username, String password, String roleType)
            throws ProjException {
        if(findUser(username))  {

            System.out.println("User already exists");
            return null;

        }
        else{
            try {
                begin();
                System.out.println("inside visitor DAO");

                Visitor user=new Visitor();

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setUsername(username);
                user.setPassword(password);
                user.setRoleType(roleType);

                getSession().save(user);

                commit();
                return user;
            }


            catch (HibernateException e) {
                rollback();
                //throw new AdException("Could not create user " + username, e);
                throw new ProjException("Exception while creating user: " + e.getMessage());
            }


        }

    }
    public void delete(Visitor visitor)
            throws ProjException {
        try {
            begin();
            getSession().delete(visitor);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Could not delete user " + visitor.getUsername(), e);
        }
    }

    public boolean findUser(String username){
        begin();
        Query q = getSession().createQuery("from Person where username=:username");
        q.setParameter("username", username);
        Person person = (Person) q.uniqueResult();
        if(person!=null){
            return true;
        } else{
            return false;
        }
    }
}

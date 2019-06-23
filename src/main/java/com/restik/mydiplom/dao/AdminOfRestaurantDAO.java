package com.restik.mydiplom.dao;

import com.restik.mydiplom.entity.AdminOfRestaurant;
import com.restik.mydiplom.entity.Person;
import com.restik.mydiplom.exception.ProjException;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

public class AdminOfRestaurantDAO extends DAO {

    public AdminOfRestaurantDAO() {

    }

    public AdminOfRestaurant create(String firstName, String lastName, String username, String password, String roleType) throws ProjException {

        if (findRestAdmin(username)) {
            System.out.println("Username already exists");
            return null;
        } else {
            try {
                begin();
                System.out.println("inside DAO");
                AdminOfRestaurant restAd = new AdminOfRestaurant();
                restAd.setFirstName(firstName);
                restAd.setLastName(lastName);
                restAd.setUsername(username);
                restAd.setPassword(password);
                restAd.setRoleType(roleType);
                getSession().save(restAd);

                commit();
                return restAd;
            } catch (HibernateException e) {
                rollback();
                throw new ProjException("Exception while creating user: " + e.getMessage());
            }
        }
    }

    public void delete(AdminOfRestaurant restAd)
            throws ProjException {
        try {
            begin();
            getSession().delete(restAd);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Could not delete user " + restAd.getUsername(), e);
        }
    }

    public boolean findRestAdmin(String username) {
        begin();
        Query q = getSession().createQuery("from Person where username=:username");
        q.setParameter("username", username);
        Person person = (Person) q.uniqueResult();
        if (person != null) {
            return true;
        } else {
            return false;
        }
    }
}

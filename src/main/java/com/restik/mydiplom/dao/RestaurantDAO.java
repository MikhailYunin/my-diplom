package com.restik.mydiplom.dao;

import com.restik.mydiplom.entity.AdminOfRestaurant;
import com.restik.mydiplom.entity.Restaurant;
import com.restik.mydiplom.exception.ProjException;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;

public class RestaurantDAO extends DAO {

    public RestaurantDAO(){
    }

    public Restaurant create(String restName, AdminOfRestaurant restAdmin)
            throws ProjException {
        try {
            begin();
            System.out.println("inside DAO");

            Restaurant rest = new Restaurant();
            rest.setRestName(restName);
            rest.setRestAdmin(restAdmin);
            restAdmin.setRestaurant(rest);

            getSession().merge(restAdmin);

            commit();
            return rest;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create restaurant " + restName, e);
            throw new ProjException("Exception while creating restaurant: " + e.getMessage());
        }
    }

    public List<Restaurant> getAll()
            throws ProjException {
        try {
            begin();
            // TODO: 16.06.2019
            Query q = getSession().createQuery("from Restaurant");
            //Restaurant restaurant =(Restaurant) q.uniqueResult();
            List<Restaurant> result = q.list();

            commit();
            return  result;

        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Could not find match " + e.getMessage());
        }
    }

    public Restaurant getMyRestaurant(AdminOfRestaurant restAdmin)
            throws ProjException {
        try {
            begin();
            Query q = getSession().createQuery("from Restaurant where restAdminID=:restAdminID  ");
            q.setParameter("restAdminID", restAdmin.getPersonID());
            Restaurant restaurant =(Restaurant) q.uniqueResult();

            commit();
            return restaurant;

        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Could not find match " + e.getMessage());
        }
    }

    public Restaurant fetchMyRestaurant(String restName)
    {
        try {
            begin();
            Query q = getSession().createQuery("from Restaurant where restName=:restName");
            q.setParameter("restName",restName);
            Restaurant restaurant =(Restaurant) q.uniqueResult();

            commit();
            return restaurant;

        } catch (HibernateException e) {
            rollback();
            // throw new ProjException("Could not find match " + e.getMessage());
            return null;
        }
    }
}

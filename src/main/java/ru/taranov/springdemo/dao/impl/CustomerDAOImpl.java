package ru.taranov.springdemo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.taranov.springdemo.dao.CustomerDAO;
import ru.taranov.springdemo.entity.Customer;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getCustomers() {

        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create a query
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer", Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }
}

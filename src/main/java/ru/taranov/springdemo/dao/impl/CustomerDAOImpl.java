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
    public List<Customer> getCustomers() {

        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create a query ...  sort by  last name
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName", Customer.class);

        // execute query and get result list
        // and
        // return the results
        return theQuery.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        // return the results
        return currentSession.get(Customer.class, theId);
    }

    @Override
    public void deleteCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery =
                currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);

        theQuery.executeUpdate();
    }
}

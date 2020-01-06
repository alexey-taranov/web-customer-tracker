package ru.taranov.springdemo.dao;

import ru.taranov.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();
}

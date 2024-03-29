package com.project.credit.customer.service;

import com.project.credit.LoginDto;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer) throws CustomerException;

    List<Customer> viewAllCustomers() throws CustomerException;

    Customer getCustomerById(Long customerId) throws CustomerException;

    Customer updateCustomer(Customer customer) throws CustomerException;

    Customer deleteCustomerById(Long customerId) throws CustomerException;

    Customer loginCustomer(LoginDto loginDto) throws CustomerException;
}




package com.zikozee.springboot.cruddemo.Customer.dao;

import com.zikozee.springboot.cruddemo.Customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(path = "members") ==>>> to change base path to members instead of employees(ENTITY),
//not difference btw context path == after port,base path== name it should hold

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}

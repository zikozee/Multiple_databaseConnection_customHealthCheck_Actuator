package com.zikozee.springboot.cruddemo.Product.dao;

import com.zikozee.springboot.cruddemo.Product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource(path = "members") ==>>> to change base path to members instead of employees(ENTITY),
//not difference btw context path == after port,base path== name it should hold
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // that's it  ... no need to write any code LOL!
}

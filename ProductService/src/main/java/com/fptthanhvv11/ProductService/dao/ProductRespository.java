package com.fptthanhvv11.ProductService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptthanhvv11.ProductService.entity.Product;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long>{

}

package com.fptthanhvv11.ProductService.service;

import com.fptthanhvv11.ProductService.model.ProductRequest;
import com.fptthanhvv11.ProductService.model.ProductResponse;

public interface ProductService {

	public long addProduct(ProductRequest productRequest);
	
	public ProductResponse getProductById(Long productId);

	public void reduceQuantity(long productId, long quantity);
}
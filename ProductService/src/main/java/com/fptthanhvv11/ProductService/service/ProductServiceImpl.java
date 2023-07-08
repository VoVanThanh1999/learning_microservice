package com.fptthanhvv11.ProductService.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptthanhvv11.ProductService.dao.ProductRespository;
import com.fptthanhvv11.ProductService.entity.Product;
import com.fptthanhvv11.ProductService.exception.ProductServiceCustomException;
import com.fptthanhvv11.ProductService.model.ProductRequest;
import com.fptthanhvv11.ProductService.model.ProductResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRespository productRespository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("Adding Product ...");

		Product product = Product.builder().name(productRequest.getName()).quantity(productRequest.getQuantity())
				.price(productRequest.getPrice()).build();

		productRespository.save(product);

		log.info("Product Created");

		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(Long productId) {
		log.info("Get the product for productId:{}" + productId);

		Product product = productRespository.findById(productId)
				.orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
		
		ProductResponse productResponse = new ProductResponse();
		
		BeanUtils.copyProperties(product, productResponse);
		
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		log.info("Reduce Quantity {} for Id", quantity, productId);
		
		Product product = productRespository.findById(productId)
										.orElseThrow(()-> new ProductServiceCustomException("Product with given Id not found", "PRODUCT_NOT_FOUND"));
		
		if(product.getQuantity() < quantity) {
			throw new ProductServiceCustomException("Product does not have suffcient Quantity", "INSUFFICIENT_QUANTITY");
		}
		
		product.setQuantity(product.getQuantity() - quantity);
		productRespository.save(product);
		
		log.info("SUCCESS SAVE PRODUCT");
	}

}

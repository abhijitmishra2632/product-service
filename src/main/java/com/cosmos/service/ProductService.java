package com.cosmos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.model.Product;
import com.cosmos.model.Products;
import com.cosmos.repository.ProuctRepository;

@Service
@Slf4j
public class ProductService {
	@Autowired
	private ProuctRepository productRepository;
	/*
	 * @Autowired private Category category;
	 */

	public Product saveProduct(Product product) {
		product.setActive(true);
		product.setNow(LocalDate.now());
		product.setAddedToCart(false);
		return productRepository.save(product);
	}

	public Products getAllProducts() {
		// TODO Auto-generated method stub
		log.info("getAllProducts called");
		Products product = new Products();
		List<Product> products = productRepository.findAllEnabled();
		log.info("Number of active products =" +products.size());
		product.setProducts(products);
		return product;
	}

	public Product updateProduct(int productId, Product product) {
		return productRepository.save(product);
	}

	public Product cloneProduct(Product product) {
		System.out.println(product.toString());
		product.setProductId(0);
		product.setNow(LocalDate.now());
		product.setAddedToCart(false);
		return productRepository.save(product);
	}

	public String deleteProduct(int productId) {
		// TODO Auto-generated method stub
		
		Product product = productRepository.findById(productId).get();
		product.setActive(false);
		productRepository.save(product);
		String response = "Successfully Deleted the product "+product.getProductName() +". It will be available in DB till 30 days incase u want to use in future";
		return response;
	}

	public Optional<Product> getProductById(int productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId);
	}

	public Products getAllDeletedProducts() {
		Products products = new Products();
		products.setProducts(productRepository.findAllDissabled());
		return products;
	}

	public String undoProduct(int productId) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(productId).get();
		product.setActive(true);
		productRepository.save(product);
		String response = "Successfully undone the product "+product.getProductName() ;
		return response;
	}

	public Products getAllStoreProducts() {
		// TODO Auto-generated method stub
		Products products = new Products();
		products.setProducts(productRepository.findAllEnabledAndStore());
		return products;
	}

}

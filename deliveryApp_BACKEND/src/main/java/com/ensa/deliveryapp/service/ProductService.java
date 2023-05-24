package com.ensa.deliveryapp.service;

import com.ensa.deliveryapp.dto.ProductDto;
import com.ensa.deliveryapp.model.Category;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
@Transactional
public class ProductService {
    public final ProductRepository productRepository;
    public List<Product> listProductsByCategory(String categoryName){
    return productRepository.findByCategory_Name(categoryName).get();
    }
    public Product findProduct(String name){
        return productRepository.findByName(name).get();
    }
    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
    return productRepository.findById(id).get();
    }
    public ProductDto convertToDTO(Product product) {
        ProductDto productDTO = new ProductDto();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(productDTO.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(product.getCategory().getName());

        return productDTO;
    }
}

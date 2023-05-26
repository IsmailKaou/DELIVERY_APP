package com.ensa.deliveryapp.service;

import com.ensa.deliveryapp.dto.ProductDto;
import com.ensa.deliveryapp.model.Category;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.repository.CategoryRepository;
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
    public final CategoryRepository categoryRepository;
    public List<Product> listProductsByCategory(String categoryName){
    return productRepository.findByCategory_Name(categoryName).get();
    }
    public Product findProduct(String name){
        return productRepository.findByName(name).get();
    }
    public List<Product> listAllProducts(){
//        for (Product product: productRepository.findAll()
//             ) {
//            System.out.println(product.getPrice());
//        }
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

    public void initProducts() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Cat1");
        category.setDescription("Desc cat1");
        category.setImageUrl("img Cat1");
        categoryRepository.save(category);


        Product product = new Product();
        product.setCategory(categoryRepository.findById(1L).get());
        product.setId(1);
        product.setName("Prod1");
        product.setDescription("Desc prod1");
        product.setPrice(13);
        productRepository.save(product);
    }
}

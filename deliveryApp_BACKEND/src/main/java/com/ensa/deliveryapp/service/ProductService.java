package com.ensa.deliveryapp.service;

import com.ensa.deliveryapp.dto.ProductDto;
import com.ensa.deliveryapp.model.Category;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.repository.CategoryRepository;
import com.ensa.deliveryapp.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


        String [] productImages={
                "product1.jpeg",
                "product2.jpeg",
                "product3.jpeg",
                "product4.jpeg",
                "product5.jpeg",
                "product6.jpeg",
                "product.jpg",
                "backpack.jpg",
                "product1.jpeg",
                "product2.jpeg",

        };
        String[] productNames = {
                "Backpack",
                "Travel Bag",
                "Hiking Pack",
                "Daypack",
                "Laptop Bag",
                "Duffel Bag",
                "Tote Bag",
                "Messenger Bag",
                "Rolling Backpack",
                "Hydration Pack"
        };

        String[] productDescriptions = {
                "Ideal for outdoor adventures and camping trips. This backpack is made with durable materials and features multiple compartments for easy organization.",
                "A versatile travel bag that's perfect for your next trip. It offers ample storage space, a comfortable design, and convenient features like a built-in USB charger.",
                "Designed for hiking enthusiasts, this hiking pack is lightweight yet sturdy. It features a suspension system for added comfort and plenty of storage options.",
                "A reliable daypack that's suitable for everyday use. It has a sleek design, padded shoulder straps, and a dedicated laptop compartment.",
                "Keep your laptop safe and secure with this stylish laptop bag. It offers padded protection, multiple pockets, and a comfortable carrying handle.",
                "A spacious duffel bag that's great for gym sessions or weekend getaways. It has a sturdy construction, multiple compartments, and a detachable shoulder strap.",
                "Carry your essentials in style with this trendy tote bag. It's made with high-quality materials, features a spacious interior, and has convenient storage pockets.",
                "Stay organized on the go with this functional messenger bag. It has multiple compartments, a padded laptop sleeve, and an adjustable shoulder strap.",
                "Get ready for your next adventure with this rolling backpack. It combines the convenience of a backpack with the ease of mobility provided by wheels.",
                "Stay hydrated during your outdoor activities with this hydration pack. It features a built-in water reservoir, adjustable straps, and a lightweight design."
        };


        double[] productPrices = {
                49.99,
                79.99,
                109.99,
                39.99,
                59.99,
                69.99,
                29.99,
                44.99,
                89.99,
                54.99
        };

        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setCategory(categoryRepository.findById(1L).get());
            product.setId(i);
            product.setImageURL(productImages[i-1]);
            product.setName(productNames[i - 1]);
            product.setDescription(productDescriptions[i - 1]);
            product.setPrice(productPrices[i - 1]);
            productRepository.save(product);
        }



    }
}

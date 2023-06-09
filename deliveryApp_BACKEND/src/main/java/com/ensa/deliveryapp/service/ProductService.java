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
       // productDTO.setCategory(product.getCategory().getName());

        return productDTO;
    }

    public void initProducts() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Cat1");
        category.setDescription("Desc cat1");
        category.setImageUrl("img Cat1");
        categoryRepository.save(category);



        String[] productNames = {
                "Recon Backpack",
                "Surge Backpack",
                "Router Backpack",
                "Borealis Backpack",
                "Base Camp Voyager Travel Pack—S",
                "Base Camp Voyager Travel Pack—L",
                "Mountain Daypack—S",
                "Base Camp Voyager Sling",
                "Jester Backpack",
                "Mountain Daypack—L"
        };

        String[] productDescriptions = {
                "An icon reimagined, the Recon excels as a daypack with its large mesh storage pocket, and daisy-chain attachment points.",
                "With a 31-liter capacity, the Surge Backpack is a spacious, streamlined pack you can take almost anywhere. Clever design features like a water-repellent finish, 15'' laptop sleeve, and the ability to stand unsupported make it indispensable.",
                "The Router, our largest lifestyle pack, has a 40-liter capacity, is water-repellent, and can fit laptops up to 17''. It also has a sought-after endorsement from the American Chiropractic Association.",
                "The classic Borealis Backpack is a best-seller for good reason. With its iconic bungee cord system and large interior compartment, you can keep your items secure, inside and out. This go-anywhere pack also features a stand-up design, sternum strap, removable waist belt and protective laptop compartment.",
                "A new addition to our fan-favorite Base Camp Voyager line. The Base Camp Voyager Travel Pack—S is ideal for a weekend away or as your everyday travel bag. Made using water- and abrasion-resistant  fabrics, this 26-liter pack features a large main compartment to help you fit clothing, footwear, gear or other important items.",
                "A new addition to our fan-favorite Base Camp Voyager line. The Base Camp Voyager Travel Pack—L is ideal for a weekend away or as your everyday travel bag. Made using water- and abrasion-resistant  fabrics, this 35-liter pack has a dividable main compartment to help you organize clothing, footwear, gear and other important items.",
                "The 18-liter Mountain Daypack—S combines modern organization with a retro design inspired by the earliest days of The North Face®. This water-repellent pack features a recycled body fabric and a padded sleeve for laptops up to 13''.",
                "Part of our Base Camp Voyager Collection, this sling is perfect for your summer travels or around-town commutes. Featuring a padded tablet pocket, reflective detailing, stabilizer strap, and fully recycled fabrics, this bag is easy to carry and easier on the planet.",
                "GPart of our Base Camp Voyager Collection, this sling is perfect for your summer travels or around-town commutes. Featuring a padded tablet pocket, reflective detailing, stabilizer strap, and fully recycled fabrics, this bag is easy to carry and easier on the planet.",
                "The 20-liter Mountain Daypack—L combines modern organization with a retro design inspired by the earliest days of The North Face®. This water-repellent pack features a recycled body fabric and a padded sleeve for laptops up to 13''."
        };


        double[] productPrices = {
                109.00,
                139.00,
                159.99,
                259.99,
                189.99,
                139.99,
                149.99,
                112.00,
                129.00,
                189.00
        };

        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setCategory(categoryRepository.findById(1L).get());
            product.setId(i);
            product.setImageURL(productNames[i - 1]);

            product.setName(productNames[i - 1]);
            product.setDescription(productDescriptions[i - 1]);
            product.setPrice(productPrices[i - 1]);
            productRepository.save(product);
        }



    }
}

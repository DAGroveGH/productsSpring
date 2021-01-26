package com.hcl.david.products.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductCrudRepository productCrudRepository;

    @GetMapping(path = "/contact")
    String contact() {
        return "phone: 444-444-4444";
    }

    @GetMapping(path="/createProduct", produces = "text/html")
    String showProductForm() {
        return "<form action='' method='POST'>" +
                    "Name: <input name='productName' type='text' /> <br/>" +
                    "Manufacturer: <input name='manufacturer' type='text' /> <br/>" +
                    "Price: <input name='price' type='text' /> <br/>" +
                    "Weight: <input name='weight' type='text' /> <br/>" +
                    "Available: <br /> " +
                        "<input name='available' type='radio' value='0'/> NO <br/>" +
                        "<input name='available' type='radio' value='1' /> YES <br />" +
                    "<input name='submit' type='submit' />" +
                "</form>";
    }

    @PostMapping(path="/createProduct")
    void createProduct(@ModelAttribute Product product) {
        if(product == null || product.getProductName() == null) {
            throw new RuntimeException("Name required");
        }
        if(product.getManufacturer() == null){
            throw new RuntimeException("Manufacturer required");
        }
        if(product.getPrice() < 0) {
            throw new RuntimeException("Price must be larger than 0");
        }
        if (product.getWeight() < 0) {
            throw new RuntimeException("Weight must be larger than 0");
        }

        productCrudRepository.save(product);
    }
}

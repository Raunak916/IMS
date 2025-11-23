package com.example.IMS.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.IMS.entities.Cart;
import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    // Additional query methods can be defined here if needed
    // For example, to find carts by user or product, you can add methods like:
    // List<Cart> findByUser(User user);
    // List<Cart> findByProduct(Product product);
List<Cart> findByUserId(Integer userId);

}

package com.example.IMS.services;
import com.example.IMS.entities.Cart;
public interface CartService {
    void addToCart(Cart cart);
    void removeFromCart(Integer cartId);
    Cart viewCart(Integer cartId);
}

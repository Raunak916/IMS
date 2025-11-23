package com.example.IMS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IMS.entities.Cart;
import com.example.IMS.repositories.CartRepository;

@Service
public class CartServiceImplementation implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(Integer cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public Cart viewCart(Integer cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }
}

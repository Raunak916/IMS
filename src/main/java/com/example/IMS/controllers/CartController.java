// package com.example.IMS.controllers;

// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class CartController {

// }

package com.example.IMS.controllers;

import java.util.List;
import com.example.IMS.entities.Cart;
import com.example.IMS.entities.Product;
import com.example.IMS.entities.User;
import com.example.IMS.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

// @RestController
//@RestController — that implicitly adds @ResponseBody to all methods.
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private com.example.IMS.repositories.ProductRepository productRepository;
    @Autowired
    private com.example.IMS.repositories.UserRepository userRepository;
    @Autowired
    private com.example.IMS.repositories.CartRepository cartRepository;

    //  Add item to cart
    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Integer productId,
                            @RequestParam("userId") Integer userId) {
        // cartService.addToCart(cart);
        // return "Item added to cart successfully!";
          Product product = productRepository.findById(productId.longValue()).orElse(null);
          User user = userRepository.findById(userId).orElse(null);

    if (product != null && user != null) {
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cartService.addToCart(cart);
    }

    // return "redirect:/userPage/" + userId; // or wherever your user page is mapped
    // return "redirect:/userPage?userId=" + userId;

      // ✅ Redirect to cart page after adding
    return "redirect:/cart/view/" + userId;


    }

    //  Remove item from cart
    // @DeleteMapping("/remove/{id}")
    // public String removeFromCart(@PathVariable("id") Integer id) {
    //     cartService.removeFromCart(id);
    //     return "Item removed from cart successfully!";
    // }

    @PostMapping("/remove/{id}")// added @PostMapping instead of @DeleteMapping
    // because HTML forms do not support DELETE method directly
    public String removeFromCart(@PathVariable("id") Integer id, @RequestParam("userId") Integer userId) {
        cartService.removeFromCart(id);
        return "redirect:/cart/view/" + userId;
  }


    //  View item in cart by ID
    @GetMapping("/view/{userId}")
    public String viewCart(@PathVariable("userId") Integer userId, Model model) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);

        double total = 0;
        for (Cart item : cartItems) {
            if (item.getProduct() != null && item.getProduct().getPrice() != null) {
                total += item.getProduct().getPrice();
            }
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("userId", userId); // so we can preserve user identity
        model.addAttribute("total", total); // add total to model
        return "cart"; // loads cart.html
}

    @GetMapping("/payment-success")
    public String paymentSuccess(@RequestParam("userId") Integer userId, Model model) {
        model.addAttribute("userId", userId);
        return "paymentSuccess";
    }




@GetMapping("/test-success")
public String testSuccess() {
    return "paymentSuccess";
}


}


// When you use @ResponseBody, Spring Boot doesn’t perform the redirect, it literally writes "redirect:/..." as plain text to the browser.
package com.jackliu.OnlineShop.cart;

import com.jackliu.OnlineShop.security.User;
import com.jackliu.OnlineShop.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    private UserRepository userRepository;

    private CartService cartService;

    @Autowired
    public CartController(UserRepository userRepository, CartService cartService) {
        this.userRepository = userRepository;
        this.cartService = cartService;
    }

    @GetMapping("")
    public String cartHome(Model model, Principal principal) {
        String name = principal.getName();
        Optional<User> byUsername = userRepository.findByUsername(name);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            Cart cartByUserId = this.cartService.findCartByUserId(user);
            model.addAttribute("cart", cartByUserId);
        }

        return "cart";
    }

    @GetMapping("/addItemToCart")
    public String addItemToCart(@RequestParam("productId") int productId, Principal principal) {

        String name = principal.getName();
        Optional<User> byUsername = userRepository.findByUsername(name);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            Cart cart = this.cartService.findCartByUserId(user);
            this.cartService.addItemToCart(cart, productId);
        }


        return "redirect:/shop";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
}

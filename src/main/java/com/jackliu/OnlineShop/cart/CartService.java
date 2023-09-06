package com.jackliu.OnlineShop.cart;

import com.jackliu.OnlineShop.security.User;
import com.jackliu.OnlineShop.security.UserRepository;
import com.jackliu.OnlineShop.shop.Product;
import com.jackliu.OnlineShop.shop.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    private CartRepository cartRepository;

    private ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }


    @Transactional
    public Cart findCartByUserId(User user){
        Optional<Cart> cartByUserId = this.cartRepository.findCartByUser_Id(user.getId());
        Cart cart;

        if(cartByUserId.isPresent()){
            cart = cartByUserId.get();
        } else {
            cart = new Cart();
            cart.setItems(new ArrayList<CartItem>());
            cart.setUser(user);
            this.cartRepository.save(cart);
        }

        return cart;
    }

    @Transactional
    public void addItemToCart(Cart cart, int productId) {
        List<CartItem> items = cart.getItems();
        boolean found = false;

        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                found = true;
                item.setCount(item.getCount() + 1);
            }
        }

        if (!found) {
            CartItem cartItem = new CartItem(cart, 1);
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                cartItem.setProduct(product);
                cartItem.setPrice(product.getPrice());
                cartItem.setName(product.getName());
                cart.getItems().add(cartItem);
            }

        }

        this.cartRepository.save(cart);

    }
}

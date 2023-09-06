package com.jackliu.OnlineShop.cart;

import com.jackliu.OnlineShop.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    /*@Query( "SELECT CartItem FROM CartItem WHERE CartItem.id = :cartId")
    List<CartItem> findByCart(@Param("cartId") int cartId);*/

    List<CartItem> findCartItemsByCart_Id(int cartId);
}

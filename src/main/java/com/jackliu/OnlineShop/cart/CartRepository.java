package com.jackliu.OnlineShop.cart;

import com.jackliu.OnlineShop.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    /*@Query( "SELECT Cart FROM Cart WHERE Cart.user.id = :customerId")
    Optional<Cart> findByUser(@Param("customerId") int customerId);*/

    Optional<Cart> findCartByUser_Id(int customerId);
}

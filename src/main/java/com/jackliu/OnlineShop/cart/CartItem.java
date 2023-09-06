package com.jackliu.OnlineShop.cart;

import com.jackliu.OnlineShop.shop.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "cartitem")
public class CartItem {
    /*
    * 一对一对于Product来说
    * 多对一toCart*/

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "counter")
    private int count;

    @Column(name = "price")
    private int price;

    @Column(name = "name")
    private String name;




    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    public CartItem() {
    }

    public CartItem(Cart cart, int count) {
        this.cart = cart;
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

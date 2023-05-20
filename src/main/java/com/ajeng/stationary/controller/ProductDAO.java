/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ajeng.stationary.controller;

import com.ajeng.stationary.model.Product;
import java.util.List;

/**
 *
 * @author ajeng
 */
public interface ProductDAO {
    public void insert(Product product);
    public void update(Product product);
    public void delete(Product product);
    public Product getProduct(int productId);
    public List<Product> list();
}

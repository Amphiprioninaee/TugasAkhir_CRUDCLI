/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ajeng.stationary.controller;

import com.ajeng.stationary.DB.ConnectionDB;
import com.ajeng.stationary.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajeng
 */

public class ProductDAOImp implements ProductDAO {
    @Override
    public void insert(Product product) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "INSERT INTO products (productCategory,productName,price,qty) VALUES (?,?,?,?)";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1, product.getProductCategory());
            ps.setString(2, product.getProductName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQty());
            ps.executeUpdate();
            System.out.println("Data has been saved");
        }  
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot insert into table");
        }
    }   

    @Override
    public void update(Product product) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "UPDATE products SET productCategory =?, productName=?, price=?, qty=? WHERE productId=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1, product.getProductCategory());
            ps.setString(2, product.getProductName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQty());
            ps.setInt(5, product.getProductId());
            ps.executeUpdate();
            System.out.println("Data has been update");
        }  
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot update into table");
        }
    }   

    @Override
    public void delete(Product product) {
        try {
            Connection conDB =  ConnectionDB.getConnection();
            String sql = "DELETE FROM product_hp WHERE productId=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, product.getProductId());
            ps.executeUpdate();
            System.out.println("Data has been delete");
        }  
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot delete into table");
        }
    }   

    @Override
    public Product getProduct(int productId) {
        Product product = new Product();
        try {
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM products WHERE productId=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                product.setProductId(rs.getInt("productId"));
                product.setProductCategory(rs.getString("productCategory"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getInt("price"));
                product.setQty(rs.getInt("qty"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table by using productId");
        }
        return product;
    }

    @Override
    public List<Product> list() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDB.getConnection();
            String sql = "SELECT * FROM products";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductCategory(rs.getString("productCategory"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getInt("price"));
                product.setQty(rs.getInt("qty"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot get data from table");
        }
        return listProduct;
    }
}


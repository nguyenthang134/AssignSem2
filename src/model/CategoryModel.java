/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Dao;
import entity.Authors;
import entity.Books;
import entity.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author nnd2890
 */
public class CategoryModel {
     // Insert Catagory
    public void insertCategory(Categories category) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            String query = "insert into categories ("
                    + " name,"
                    + " description,"
                    + " created_at,"
                    + " updated_at)"
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = Dao.getConnection().prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // List Catagory
    public ArrayList<Categories> listCategory() {
        ArrayList<Categories> categoryList = new ArrayList<>();
        try {
            System.out.println();
            Statement statement = Dao.getConnection().createStatement();
            String query = "select * from categories";
            ResultSet rs = statement.executeQuery(query);
            Categories category;
            while (rs.next()) {
                category = new Categories();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setCreated_at(rs.getDate("created_at").toString());
                category.setUpdated_at(rs.getDate("updated_at").toString());
                category.setStatus(rs.getInt("status"));
                categoryList.add(category);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return categoryList;
    }

    // Update Catagory
    public void updateCategory(Categories category) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = Dao.getConnection().createStatement();
            String query = "update categories "
                    + "set name='" + category.getName() + "'"
                    + ", description = '" + category.getDescription() + "'"
                    + ", updated_at = '" + timestamp + "'"
                    + " where id='" + category.getId() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    // Delete Catagory
    public void deleteCategory(Categories category) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = Dao.getConnection().createStatement();
            String query = "delete from categories "
                    + " where id='" + category.getId() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    // Query trong bang book_category join voi bang book de lay ra tat ca sach thuoc 1 category nao do.
    public ArrayList<Books> getBooksByCategoryId(int categoryId){
        ArrayList<Books> result = new ArrayList<>();
        return result;
    }
    
    // Lay ra tat ca category cua mot quyen sach.
    public ArrayList<Categories> getBookCategories(int bookId){
        ArrayList<Categories> result = new ArrayList<>();
        return result;
    }
}

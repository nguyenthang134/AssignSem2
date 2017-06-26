/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.DatabaseLibConnection;
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
                    + " created_at,"
                    + " updated_at)"
                    + "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = DatabaseLibConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // List Catagory
    public ArrayList<Categories> listCategory() {
        ArrayList<Categories> categoryList = new ArrayList<>();
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "select * from categories";
            ResultSet rs = statement.executeQuery(query);
            Categories category;
            while (rs.next()) {
                category = new Categories();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
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
    
     // get list from databse limit row 
    public ArrayList<Categories> listCategoryLimit(int limit, int offset) {
        ArrayList<Categories> categoryList = new ArrayList<>();
        try {
            String sql = "select * from categories limit " + limit + " OFFSET " + offset + "";
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Categories category;
            while (rs.next()) {
                category = new Categories();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setCreated_at(rs.getString("created_at"));
                category.setUpdated_at(rs.getString("updated_at"));
                category.setStatus(rs.getInt("status"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return categoryList;
    }
    
    // count database row
    public int countRow() throws SQLException {
        int total = 0;
        String sql = "select count(*) from categories";
        Statement statement = DatabaseLibConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            total = rs.getInt(1);
        }
        return total;
    }

    // Update Catagory
    public void updateCategory(Categories category) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "update categories "
                    + "set name='" + category.getName() + "'"
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
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "delete from categories "
                    + " where id='" + category.getId() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    // check name that was exist
    public boolean checkName(String tblName, String name) {
        boolean bl = true;
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String sql = "select * from " + tblName + " where name = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                bl = true;
            }else {
                bl = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bl;
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

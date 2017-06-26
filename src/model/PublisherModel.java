/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Categories;
import controller.DatabaseLibConnection;
import entity.Publisher;
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
public class PublisherModel {
     // Insert Catagory
    public void insertPublisher(Publisher publisher) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            String query = "insert into publishers ("
                    + " name,"
                    + " address,"
                    + " phone,"
                    + " created_at,"
                    + " updated_at)"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = DatabaseLibConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.setString(2, publisher.getAddress());
            preparedStatement.setInt(3, publisher.getPhone());
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.setTimestamp(5, timestamp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // List Catagory
    public ArrayList<Publisher> listPublisher() {
        ArrayList<Publisher> publisherList = new ArrayList<>();
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "select * from publishers";
            ResultSet rs = statement.executeQuery(query);
            Publisher publisher;
            while (rs.next()) {
                publisher = new Publisher();
                publisher.setId(rs.getInt("id"));
                publisher.setName(rs.getString("name"));
                publisher.setAddress(rs.getString("address"));
                publisher.setPhone(rs.getInt("phone"));
                publisher.setCreated_at(rs.getDate("created_at").toString());
                publisher.setUpdated_at(rs.getDate("updated_at").toString());
                publisher.setStatus(rs.getInt("status"));
                publisherList.add(publisher);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return publisherList;
    }
    
     // get list from databse limit row 
    public ArrayList<Publisher> listPublisherLimit(int limit, int offset) {
        ArrayList<Publisher> publisherList = new ArrayList<>();
        try {
            String sql = "select * from publishers limit " + limit + " OFFSET " + offset + "";
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Publisher publisher;
            while (rs.next()) {
                publisher = new Publisher();
                publisher.setId(rs.getInt("id"));
                publisher.setName(rs.getString("name"));
                publisher.setAddress(rs.getString("address"));
                publisher.setPhone(rs.getInt("phone"));
                publisher.setCreated_at(rs.getString("created_at"));
                publisher.setUpdated_at(rs.getString("updated_at"));
                publisher.setStatus(rs.getInt("status"));
                publisherList.add(publisher);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return publisherList;
    }
    
    // count database row
    public int countRow() throws SQLException {
        int total = 0;
        String sql = "select count(*) from publishers";
        Statement statement = DatabaseLibConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            total = rs.getInt(1);
        }
        return total;
    }

    // Update Catagory
    public void updatePublisher(Publisher publisher) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "update publishers "
                    + "set name='" + publisher.getName() + "'"
                    + ", address = '" + publisher.getAddress()+ "'"
                    + ", phone = '" + publisher.getPhone()+ "'"
                    + ", updated_at = '" + timestamp + "'"
                    + " where id='" + publisher.getId() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    // Delete Catagory
    public void deletePublisher(Publisher publisher) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "delete from publishers "
                    + " where id='" + publisher.getId() + "'";
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
}

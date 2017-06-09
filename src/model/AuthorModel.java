/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Dao;
import entity.Authors;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author nnd2890
 */
public class AuthorModel {

    // Insert Author
    public static void insertAuthors(Authors author) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            String query = "insert into authors ("
                    + " name,"
                    + " description,"
                    + " created_at,"
                    + " updated_at)"
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = Dao.getConnection().prepareStatement(query);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getDescription());
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // List author
    public static ArrayList<Authors> listAuthor() {
        ArrayList<Authors> authorList = new ArrayList<>();
        try {
            Statement statement = Dao.getConnection().createStatement();
            String query = "select * from authors";
            ResultSet rs = statement.executeQuery(query);
            Authors author;
            while (rs.next()) {
                author = new Authors();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setDescription(rs.getString("description"));
                author.setCreated_at(rs.getDate("created_at").toString());
                author.setUpdated_at(rs.getDate("updated_at").toString());
                author.setStatus(rs.getInt("status"));
                authorList.add(author);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return authorList;
    }

    // Update author
    public static void updateAuthor(Authors author) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = Dao.getConnection().createStatement();
            String query = "update authors "
                    + "set name='" + author.getName() + "'"
                    + ", description = '" + author.getDescription() + "'"
                    + ", updated_at = '" + timestamp + "'"
                    + " where id='" + author.getId() + "'";
            System.out.println("SQL " + query);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    // Delete author
    public static void deleteAuthor(Authors author) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = Dao.getConnection().createStatement();
            String query = "delete from authors "
                    + " where id='" + author.getId() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
//    public static void main(String[] args) {
//        AuthorModel.insertAuthors();
//    }
}

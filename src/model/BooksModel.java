/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Dao;
import entity.Books;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nnd2890
 */
public class BooksModel {
    // Insert books

    public static void insertBooks(Books book) throws ParseException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");  
            String query = "insert into books ("
                    + " name,"
                    + " author_id,"
                    + " publisher_id,"
                    + " category_id,"
                    + " price,"
                    + " quantity,"
                    + " publish_date,"
                    + " created_at,"
                    + " updated_at,"
                    + " status)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = Dao.getConnection().prepareStatement(query);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPublisher());
            preparedStatement.setInt(4, book.getCategory());
            preparedStatement.setInt(5, book.getPrice());
            preparedStatement.setInt(6, book.getQuantity());
            preparedStatement.setDate(7, new java.sql.Date(format.parse(book.getPublishDate()).getTime()));
            preparedStatement.setDate(8, new java.sql.Date(format.parse(book.getCreatedDate()).getTime()));
            preparedStatement.setDate(9, new java.sql.Date(format.parse(book.getCreatedDate()).getTime()));
            preparedStatement.setInt(10, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

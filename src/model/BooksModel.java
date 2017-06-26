/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.DatabaseLibConnection;
import entity.Books;
import entity.Categories;
import entity.Publisher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author nnd2890
 */
public class BooksModel {

    // Select List book_category by book_name
    public  ArrayList<Books> getBookCategory(String tblName, String name) {
        ArrayList<Books> bookList = new ArrayList<>();
        Books book;  
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String sql = "select * from " + tblName + " where book_name = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                book = new Books() ;
                book.setName(rs.getString("book_name"));
                book.setCategory(rs.getInt("category_id"));
                bookList.add(book);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bookList;
    }

    // Insert books
    public static void insertBooks(Books book) throws ParseException {
        try {
            java.util.Date date = new java.util.Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            String query = "insert into books ("
                    + " name,"
                    + " author_id,"
                    + " publisher_id,"
                    + " price,"
                    + " publish_date,"
                    + " created_at,"
                    + " updated_at,"
                    + " status)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = DatabaseLibConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPublisher());
            preparedStatement.setInt(4, book.getPrice());

            //convert and insert date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date pubishDate = dateFormat.parse(book.getPublishDate());
            java.sql.Date sqlDate = new java.sql.Date(pubishDate.getTime());
            preparedStatement.setDate(5, sqlDate);
            preparedStatement.setTimestamp(6, timestamp);
            preparedStatement.setTimestamp(7, timestamp);
            preparedStatement.setInt(8, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // insert book_category
    public void insertBookCategory(String book_name, int category_id) {
        try {
            String query = "insert into book_categories ("
                    + " book_name,"
                    + " category_id)"
                    + "VALUES (?, ?)";
            PreparedStatement preparedStatement = DatabaseLibConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, book_name);
            preparedStatement.setInt(2, category_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // get list from databse limit row 
    public ArrayList<Books> listBookLimit(int limit, int offset) {
        ArrayList<Books> bookList = new ArrayList<>();
        try {
            String sql = "select * from books limit " + limit + " OFFSET " + offset + "";
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Books book;
            while (rs.next()) {
                book = new Books();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getInt("author_id"));
                book.setPublisher(rs.getInt("publisher_id"));
                book.setCategory(offset);
                book.setPrice(rs.getInt("price"));
                book.setPublishDate(rs.getDate("publish_date").toString());
                book.setCreatedDate(rs.getDate("created_at").toString());
                book.setUpdateDate(rs.getDate("updated_at").toString());
                book.setStatus(rs.getInt("status"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return bookList;
    }

    // count database row
    public int countRow() throws SQLException {
        int total = 0;
        String sql = "select count(*) from books";
        Statement statement = DatabaseLibConnection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            total = rs.getInt(1);
        }
        return total;
    }

    // Select id by name
    public int getId(String tblName, String name) {
        int id = 0;
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String sql = "select * from " + tblName + " where name = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    // Select id by name into table book_categories
    public int getIdBookCategory(String tblName, String name) {
        int id = 0;
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String sql = "select * from " + tblName + " where book_name = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                id = rs.getInt("category_id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    // Select name by id
    public String getName(String tblName, int id) {
        String name = "";
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String sql = "select * from " + tblName + " where id = '" + id + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }

    // Array for comboBox
    public ArrayList<String> comboBox(String from) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String sql = "select * from " + from + "";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                list.add(name);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Update
    public void updateBook(Books book) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "update books "
                    + "set name='" + book.getName() + "'"
                    + ", author_id = '" + book.getAuthor() + "'"
                    + ", publisher_id = '" + book.getPublisher() + "'"
                    + ", price = '" + book.getPrice() + "'"
                    + ", publish_date = '" + book.getPublishDate() + "'"
                    + ", updated_at = '" + timestamp + "'"
                    + " where id='" + book.getId() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
 // Update status books 
    public void updateBookStatus(Books book) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "update books "
                    + "set status = 2"
                    + " where id='" + book.getId() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Delete book
    public void deleteBook(Books book) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "delete from books "
                    + " where id='" + book.getId() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Delete book_category
    public void deleteBookCategory(String bookName) {
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String query = "delete from book_categories "
                    + " where book_name='" + bookName + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
     // check book_name in book_categories that was exist
    public boolean checkName(String tblName,String where, String name) {
        boolean bl = true;
        try {
            Statement statement = DatabaseLibConnection.getConnection().createStatement();
            String sql = "select * from " + tblName + " where " + where + " = '" + name + "'";
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

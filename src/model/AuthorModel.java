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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nnd2890
 */
public class AuthorModel {

    // Insert Author
    public void insertAuthors(Authors author) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            String query = "insert into authors ("
                    + " name,"
                    + " created_at,"
                    + " updated_at)"
                    + "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = Dao.getConnection().prepareStatement(query);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // List author
    public ArrayList<Authors> listAuthor() {
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

    // get list from databse limit row 
    public ArrayList<Authors> listAuthorLimit(int limit, int offset) {
        ArrayList<Authors> authorList = new ArrayList<>();
        try {
            String sql = "select * from authors limit " + limit + " OFFSET " + offset + "";
            Statement statement = Dao.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Authors author;
            while (rs.next()) {
                author = new Authors();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setCreated_at(rs.getString("created_at"));
                author.setUpdated_at(rs.getString("updated_at"));
                author.setStatus(rs.getInt("status"));
                authorList.add(author);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return authorList;
    }

    // Update author
    public void updateAuthor(Authors author) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            Statement statement = Dao.getConnection().createStatement();
            String query = "update authors "
                    + "set name='" + author.getName() + "'"
                    + ", updated_at = '" + timestamp + "'"
                    + " where id='" + author.getId() + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Delete author
    public void deleteAuthor(Authors author) {
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

    // count database row
    public int countRow() throws SQLException {
        int total = 0;
        String sql = "select count(*) from authors";
        Statement statement = Dao.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            total = rs.getInt(1);
        }
        return total;
    }


    // check name that was exist
    public boolean checkName(String tblName, String name) {
        boolean bl = true;
        try {
            Statement statement = Dao.getConnection().createStatement();
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

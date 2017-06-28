/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Books;
import entity.Categories;
import controller.ButtonController;
import controller.DatabaseLibConnection;
import entity.Publisher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.BooksModel;
import model.CategoryModel;
import model.PublisherModel;

/**
 *
 * @author nnd2890
 */
public class BookViewJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BookViewJPanel
     */
    // set next/previous page 
    private int currentPage = 1;
    private int limit = 5;
    private int totalPage;

    public BookViewJPanel() {
        initComponents();
        this.add(bc.btnBack());
        this.add(bc.btnExit());
        
        // Add list to comboBox
        listComboBox();
        listJList();

        // show listPage
        loadData();
        jButton_currentPage.setText(String.valueOf(this.currentPage));
    }

    // Add list to comboBox
    public void listComboBox() {
        BooksModel bookModel = new BooksModel();
        ArrayList list = bookModel.comboBox("authors");
        jComboBox_author.setModel(new DefaultComboBoxModel(list.toArray()));
        list = bookModel.comboBox("publishers");
        jComboBox_publisher.setModel(new DefaultComboBoxModel(list.toArray()));
//        list = bookModel.comboBox("categories");
//        jComboBox_category.setModel(new DefaultComboBoxModel(list.toArray()));
    }

    // Add list to jList
    public void listJList() {
        BooksModel bookModel = new BooksModel();
        ArrayList list = bookModel.comboBox("categories");
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Object object : list) {
            model.addElement((String) object);
        }
        jList_category.setModel(model);
        jList_category.setSelectedIndex(0);
        jList_category.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    // insert books's name and mutiple category_id into book_category table
    public void insertBookCategory() {
        BooksModel bookModel = new BooksModel();
        // get selected categories
        List<String> listCategory = jList_category.getSelectedValuesList();
        for (String categoryName : listCategory) {
            // get category Id
            int categoryId = bookModel.getId("categories", categoryName);
            bookModel.insertBookCategory(jTextField_name.getText(), categoryId);
        }
    }

    // Resest funtion
    public void resetFuntion() {
        jTextField_name.setText("");
        jTextField_price.setText("");
        jDateChooser_publishDate.setDate(null);
    }

    // next/previous page
    public void doFirst() {
        this.currentPage = 1;
        jButton_first.setEnabled(false);
        jButton_previous.setEnabled(false);
        jButton_next.setEnabled(true);
        jButton_last.setEnabled(true);
        jButton_currentPage.setText(this.currentPage + "");
        loadData();
    }

    public void doPrevious() {
        if (this.currentPage > 1) {
            this.currentPage--;
            jButton_last.setEnabled(true);
            jButton_next.setEnabled(true);
        } else if (this.currentPage == 1) {
            jButton_first.setEnabled(false);
            jButton_previous.setEnabled(false);
        }
        jButton_currentPage.setText(String.valueOf(this.currentPage));
        loadData();
    }

    public void doNext() {
        this.totalPage = countPage();
        if (this.currentPage < this.totalPage) {
            this.currentPage++;
            this.jButton_first.setEnabled(true);
            this.jButton_previous.setEnabled(true);
        } else if (this.currentPage == this.totalPage) {
            jButton_next.setEnabled(false);
            jButton_last.setEnabled(false);
        }
        jButton_currentPage.setText(this.currentPage + "");
        loadData();
    }

    public void doLast() {
        this.totalPage = countPage();
        this.currentPage = this.totalPage;
        jButton_first.setEnabled(true);
        jButton_previous.setEnabled(true);
        jButton_next.setEnabled(false);
        jButton_last.setEnabled(false);
        jButton_currentPage.setText(this.totalPage + "");
        loadData();
    }

    public void loadData() {
        BooksModel bookModel = new BooksModel();
        ArrayList<Books> list = bookModel.listBookLimit(this.limit, (this.currentPage - 1) * this.limit);
        DefaultTableModel model = (DefaultTableModel) jTable_book.getModel();
        model.setRowCount(0);
        for (Books book : list) {
            String nameAuthor = bookModel.getName("authors", book.getAuthor());
            String namePublsiher = bookModel.getName("publishers", book.getPublisher());

            // get nameCategory
            int idCategory = bookModel.getIdBookCategory("book_categories", book.getName());
            String nameCategory = bookModel.getName("categories", idCategory);

            Object[] row = {
                book.getId(),
                book.getName(),
                nameAuthor,
                namePublsiher,
                nameCategory,
                book.getPrice(),
                book.getPublishDate(),
                book.getCreatedDate(),
                book.getUpdateDate(),
                book.getStatus()};
            model.addRow(row);
        }
    }

    public int countPage() {
        try {
            BooksModel bookModel = new BooksModel();
            int countPage = bookModel.countRow();
            if (countPage % this.limit == 0) {
                this.totalPage = countPage / this.limit;
            } else {
                this.totalPage = countPage / this.limit + 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this.totalPage;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
        jTextField_price = new javax.swing.JTextField();
        jButton_insert = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_resest = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_book = new javax.swing.JTable();
        jComboBox_author = new javax.swing.JComboBox<>();
        jComboBox_publisher = new javax.swing.JComboBox<>();
        jDateChooser_publishDate = new com.toedter.calendar.JDateChooser();
        jButton_first = new javax.swing.JButton();
        jButton_previous = new javax.swing.JButton();
        jButton_currentPage = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jButton_last = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList_category = new javax.swing.JList<>();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Name");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setText("Author");

        jLabel3.setText("Publisher");

        jLabel4.setText("Category");

        jLabel5.setText("Price");

        jLabel6.setText("Publish Date");

        jTextField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nameActionPerformed(evt);
            }
        });

        jTextField_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_priceActionPerformed(evt);
            }
        });

        jButton_insert.setBackground(new java.awt.Color(0, 153, 153));
        jButton_insert.setForeground(new java.awt.Color(255, 255, 255));
        jButton_insert.setText("Insert");
        jButton_insert.setPreferredSize(new java.awt.Dimension(85, 23));
        jButton_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_insertActionPerformed(evt);
            }
        });

        jButton_update.setBackground(new java.awt.Color(0, 153, 153));
        jButton_update.setForeground(new java.awt.Color(255, 255, 255));
        jButton_update.setText("Update");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jButton_delete.setBackground(new java.awt.Color(0, 153, 153));
        jButton_delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_delete.setText("Delete");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jButton_resest.setBackground(new java.awt.Color(0, 153, 153));
        jButton_resest.setForeground(new java.awt.Color(255, 255, 255));
        jButton_resest.setText("Reset");
        jButton_resest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_resestActionPerformed(evt);
            }
        });

        jTable_book.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Author", "Publisher", "Category", "Price", "Publish Date", "Created Date", "Update Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_book.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_bookMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_book);
        if (jTable_book.getColumnModel().getColumnCount() > 0) {
            jTable_book.getColumnModel().getColumn(0).setResizable(false);
            jTable_book.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable_book.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTable_book.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable_book.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTable_book.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTable_book.getColumnModel().getColumn(5).setPreferredWidth(40);
            jTable_book.getColumnModel().getColumn(6).setPreferredWidth(80);
            jTable_book.getColumnModel().getColumn(7).setPreferredWidth(80);
            jTable_book.getColumnModel().getColumn(8).setPreferredWidth(80);
            jTable_book.getColumnModel().getColumn(9).setPreferredWidth(40);
        }

        jComboBox_author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_authorActionPerformed(evt);
            }
        });

        jComboBox_publisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_publisherActionPerformed(evt);
            }
        });

        jButton_first.setText("<<");
        jButton_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_firstActionPerformed(evt);
            }
        });

        jButton_previous.setText("<");
        jButton_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_previousActionPerformed(evt);
            }
        });

        jButton_next.setText(">");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });

        jButton_last.setText(">>");
        jButton_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_lastActionPerformed(evt);
            }
        });

        jList_category.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList_category);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox_publisher, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton_resest, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jDateChooser_publishDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_price, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_author, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton_delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(651, 651, 651)
                        .addComponent(jButton_first)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_previous)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_currentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_next)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_last)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_first)
                            .addComponent(jButton_previous)
                            .addComponent(jButton_currentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_next)
                            .addComponent(jButton_last)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jButton_update)
                            .addComponent(jComboBox_author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jButton_delete)
                            .addComponent(jComboBox_publisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_resest)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser_publishDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addContainerGap(260, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_authorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_authorActionPerformed
    }//GEN-LAST:event_jComboBox_authorActionPerformed

    private void jComboBox_publisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_publisherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_publisherActionPerformed

    private void jButton_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_insertActionPerformed
        // get date
        java.util.Date d = jDateChooser_publishDate.getDate();
        Books book = new Books();
        BooksModel bookModel = new BooksModel();

        // check book_name was exist or not
        boolean bl = bookModel.checkName("book_categories","book_name", jTextField_name.getText());

        if (jTextField_name.getText().equals("") || jTextField_price.getText().equals("") || d == null) {
            JOptionPane.showMessageDialog(null, "Your characters are not valid", "Error", JOptionPane.ERROR_MESSAGE);
            resetFuntion();
        } else {
            String name = jTextField_name.getText();
            book.setName(name);
            // Author Id
            String tblName = "authors";
            int authorId = bookModel.getId(tblName, jComboBox_author.getSelectedItem().toString());
            book.setAuthor(authorId);

            // Publisher Id
            tblName = "publishers";
            int publisherId = bookModel.getId(tblName, jComboBox_publisher.getSelectedItem().toString());
            book.setPublisher(publisherId);

            // set price
            int price = Integer.parseInt(jTextField_price.getText());
            book.setPrice(price);

            // set publishDate
            java.util.Date publishDate = jDateChooser_publishDate.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String reportDate = df.format(publishDate);
            book.setPublishDate(reportDate);

            // check book_name was exit in book_category
            if (bl == true) {
                // don't insert more bookCategory
                try {
                    // insert Book
                    bookModel.insertBooks(book);

                    JOptionPane.showMessageDialog(null, "Successful!");
                    doLast();
                    resetFuntion();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else {
                try {
                    // insert more book_category
                    // insert Book
                    bookModel.insertBooks(book);

                    // insert books's name and mutiple category_id into book_category table
                    insertBookCategory();

                    JOptionPane.showMessageDialog(null, "Successful!");
                    doLast();
                    resetFuntion();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton_insertActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        // Update
        int op = JOptionPane.showConfirmDialog(null, "Do you really want update", "Update", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            BooksModel bookModel = new BooksModel();
            int i = jTable_book.getSelectedRow();
            TableModel model = jTable_book.getModel();
            int id = Integer.parseInt(model.getValueAt(i, 0).toString());
            String name = jTextField_name.getText();
            int authorId = bookModel.getId("authors", jComboBox_author.getSelectedItem().toString());// Get author id
            int publsiherId = bookModel.getId("publishers", jComboBox_publisher.getSelectedItem().toString());// Get publsiher id
            int price = Integer.parseInt(jTextField_price.getText());

            // set date
            java.util.Date publishDate = jDateChooser_publishDate.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String reportDate = df.format(publishDate);
            String publisDate = reportDate;

            Books book = new Books();
            book.setId(id);
            book.setName(name);
            book.setAuthor(authorId);
            book.setPublisher(publsiherId);
            book.setPrice(price);
            book.setPublishDate(publisDate);
            try {
                // update book
                bookModel.updateBook(book);

                // delete book_category by book_name
                bookModel.deleteBookCategory(name);

                // reinsert book_category by book_name
                insertBookCategory();

                loadData();
                resetFuntion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jTextField_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nameActionPerformed

    private void jTable_bookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_bookMouseClicked
        // Display selected row into JTextField
        int i = jTable_book.getSelectedRow();
        TableModel model = jTable_book.getModel();
        jTextField_name.setText(model.getValueAt(i, 1).toString());
        jComboBox_author.setSelectedItem(model.getValueAt(i, 2).toString());
        jComboBox_publisher.setSelectedItem(model.getValueAt(i, 3).toString());
//        jComboBox_category.setSelectedItem(model.getValueAt(i, 4).toString());
        jTextField_price.setText(model.getValueAt(i, 5).toString());

        // set date
        String dateValue = model.getValueAt(i, 6).toString();
        java.util.Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
            jDateChooser_publishDate.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(BookViewJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable_bookMouseClicked

    private void jButton_resestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_resestActionPerformed
        resetFuntion();
    }//GEN-LAST:event_jButton_resestActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        // Delete
        int op = JOptionPane.showConfirmDialog(null, "Do you really want delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            BooksModel bookModel = new BooksModel();
            Books book = new Books();
            // get list book_category by book_name where book_categories table
            ArrayList<Books> list = bookModel.getBookCategory("book_categories", jTextField_name.getText());
            try {
                // get id book
                int i = jTable_book.getSelectedRow();
                TableModel model = jTable_book.getModel();
                int id = Integer.parseInt(model.getValueAt(i, 0).toString());
                book.setId(id);

                // delete book_category by name
                bookModel.deleteBookCategory(jTextField_name.getText());

                // change status of book when choose delete
                bookModel.updateBookStatus(book);

                
                // check book_name was exist or not
                boolean bl = bookModel.checkName("books","name", jTextField_name.getText());
                if (bl == true) {
                    // reinset book_category 
                    for (Books bookList : list) {
                        bookModel.insertBookCategory(jTextField_name.getText(), bookList.getCategory());
                    }
                }

                JOptionPane.showMessageDialog(null, "Successful!");
                loadData();
                resetFuntion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        doNext();
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_firstActionPerformed
        doFirst();
    }//GEN-LAST:event_jButton_firstActionPerformed

    private void jButton_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previousActionPerformed
        doPrevious();
    }//GEN-LAST:event_jButton_previousActionPerformed

    private void jButton_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_lastActionPerformed
        doLast();
    }//GEN-LAST:event_jButton_lastActionPerformed

    private void jTextField_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_priceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_currentPage;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_first;
    private javax.swing.JButton jButton_insert;
    private javax.swing.JButton jButton_last;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_resest;
    private javax.swing.JButton jButton_update;
    private javax.swing.JComboBox<String> jComboBox_author;
    private javax.swing.JComboBox<String> jComboBox_publisher;
    private com.toedter.calendar.JDateChooser jDateChooser_publishDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList_category;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_book;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_price;
    private ButtonController bc = new ButtonController();
    // End of variables declaration//GEN-END:variables

}

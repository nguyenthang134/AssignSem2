/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.AuthorModel;
import entity.Authors;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import entity.Dao;

/**
 *
 * @author nnd2890
 */
public class AuthorViewPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    // Initialize localDate veriable
    private DateFormat dtf;
    private Date localDate;

    // set next/previous page
    private int currentPage = 1;
    private int limit = 5;
    private int totalPage;

    public AuthorViewPanel() {
        initComponents();
//        showList();
        loadData();
        jButton_currentPage.setText(String.valueOf(this.currentPage));
    }

    // Show list of authors into table
    public void showList() {
        AuthorModel authorModel = new AuthorModel();
        ArrayList<Authors> list = authorModel.listAuthor();
        DefaultTableModel model = (DefaultTableModel) jTableAuthor.getModel();
        model.setRowCount(0);
        for (Authors author : list) {
            Object[] row = {author.getId(),
                author.getName(),
                author.getCreated_at(),
                author.getUpdated_at(),
                author.getStatus()};
            model.addRow(row);
        }
    }

    // Reset
    public void resetFuntion() {
        jTextField_id.setText("");
        JTextField_name.setText("");
        jTextField_createdDate.setText("");
        jTextField_updatedDate.setText("");
        jTextField_status.setText("");
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
        AuthorModel authorModel = new AuthorModel();
        ArrayList<Authors> list = authorModel.listAuthorLimit(this.limit, (this.currentPage - 1) * this.limit);
        DefaultTableModel model = (DefaultTableModel) jTableAuthor.getModel();
        model.setRowCount(0);
        for (Authors author : list) {
            Object[] row = {author.getId(),
                author.getName(),
                author.getCreated_at(),
                author.getUpdated_at(),
                author.getStatus()};
            model.addRow(row);
        }
    }

    public int countPage() {
        try {
            AuthorModel authorModel = new AuthorModel();
            int countPage = authorModel.countRow();
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

    public static void main(String[] args) throws SQLException {
        AuthorViewPanel a = new AuthorViewPanel();
        System.out.println(a.countPage());
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
        JTextField_name = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAuthor = new javax.swing.JTable();
        jButton_insert = new javax.swing.JButton();
        jButton_Udate = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_reset = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_id = new javax.swing.JTextField();
        jTextField_createdDate = new javax.swing.JTextField();
        jTextField_updatedDate = new javax.swing.JTextField();
        jTextField_status = new javax.swing.JTextField();
        jButton_first = new javax.swing.JButton();
        jButton_previous = new javax.swing.JButton();
        jButton_currentPage = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jButton_last = new javax.swing.JButton();

        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.disabledShadow"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 650));
        setVerifyInputWhenFocusTarget(false);

        jLabel1.setText("Author's name");

        jTableAuthor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Author's name", "Created date", "Updated date", "Status"
            }
        ));
        jTableAuthor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAuthorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAuthor);
        if (jTableAuthor.getColumnModel().getColumnCount() > 0) {
            jTableAuthor.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jButton_insert.setBackground(new java.awt.Color(0, 153, 153));
        jButton_insert.setForeground(new java.awt.Color(255, 255, 255));
        jButton_insert.setText("Insert");
        jButton_insert.setToolTipText("");
        jButton_insert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_insertMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton_insertMousePressed(evt);
            }
        });
        jButton_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_insertActionPerformed(evt);
            }
        });

        jButton_Udate.setBackground(new java.awt.Color(0, 153, 153));
        jButton_Udate.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Udate.setText("Update");
        jButton_Udate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UdateActionPerformed(evt);
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

        jButton_reset.setBackground(new java.awt.Color(0, 153, 153));
        jButton_reset.setForeground(new java.awt.Color(255, 255, 255));
        jButton_reset.setText("Resest");
        jButton_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_resetActionPerformed(evt);
            }
        });

        jLabel3.setText("Id");

        jLabel5.setText("Created date");

        jLabel4.setText("Updated Date");

        jLabel6.setText("Status");

        jTextField_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_idActionPerformed(evt);
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

        jButton_currentPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_currentPageActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(JTextField_name, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(jTextField_createdDate, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_updatedDate, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_status)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_insert, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(jButton_Udate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton_first)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_previous)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_currentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_next)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_last)
                        .addGap(262, 262, 262))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_insert)
                            .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jButton_Udate))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_delete)
                            .addComponent(jTextField_createdDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_reset)
                            .addComponent(jTextField_updatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(42, 140, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_next, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_last, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_previous, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_first, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_currentPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(253, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_UdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UdateActionPerformed
        // Update
        int id = Integer.parseInt(jTextField_id.getText());
        String name = JTextField_name.getText();
        Authors au = new Authors();
        au.setId(id);
        au.setName(name);
        try {
            AuthorModel authorModel = new AuthorModel();
            authorModel.updateAuthor(au);
            JOptionPane.showMessageDialog(null, "Successful!");
//            showList();
            loadData();
            resetFuntion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton_UdateActionPerformed

    private void jButton_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_resetActionPerformed
        resetFuntion();
    }//GEN-LAST:event_jButton_resetActionPerformed

    private void jButton_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_insertActionPerformed
        // Insert 
        AuthorModel authorModel = new AuthorModel();
        boolean bl = authorModel.checkName("authors", JTextField_name.getText());
        if (JTextField_name.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Your characters are not valid", "Error", JOptionPane.ERROR_MESSAGE);
            resetFuntion();
        } else if(bl == true ){
            JOptionPane.showMessageDialog(null, "Your author's name was existed", "Error", JOptionPane.ERROR_MESSAGE);
            resetFuntion();
        }else {
            String name = JTextField_name.getText();
            Authors au = new Authors();
            au.setName(name);
            try {
                authorModel.insertAuthors(au);
                doLast();
                resetFuntion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_insertActionPerformed

    private void jTableAuthorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAuthorMouseClicked
        // Display selected row into JTextField
        int i = jTableAuthor.getSelectedRow();
        TableModel model = jTableAuthor.getModel();
        jTextField_id.setText(model.getValueAt(i, 0).toString());
        JTextField_name.setText(model.getValueAt(i, 1).toString());
        jTextField_createdDate.setText(model.getValueAt(i, 2).toString());
        jTextField_updatedDate.setText(model.getValueAt(i, 3).toString());
        jTextField_status.setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_jTableAuthorMouseClicked

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        // Delete
        int op = JOptionPane.showConfirmDialog(null, "Do you really want to delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            try {
                int id = Integer.parseInt(jTextField_id.getText());
                Authors au = new Authors();
                au.setId(id);
                AuthorModel authorModel = new AuthorModel();
                authorModel.deleteAuthor(au);
                JOptionPane.showMessageDialog(null, "Successful!");
//                showList();
                loadData();
                resetFuntion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_insertMouseClicked

    }//GEN-LAST:event_jButton_insertMouseClicked

    private void jButton_insertMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_insertMousePressed

    }//GEN-LAST:event_jButton_insertMousePressed

    private void jButton_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_firstActionPerformed
        doFirst();
    }//GEN-LAST:event_jButton_firstActionPerformed

    private void jButton_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previousActionPerformed
        doPrevious();
    }//GEN-LAST:event_jButton_previousActionPerformed

    private void jButton_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_lastActionPerformed
        doLast();
    }//GEN-LAST:event_jButton_lastActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        doNext();
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jTextField_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_idActionPerformed

    private void jButton_currentPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_currentPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_currentPageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextField_name;
    private javax.swing.JButton jButton_Udate;
    private javax.swing.JButton jButton_currentPage;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_first;
    private javax.swing.JButton jButton_insert;
    private javax.swing.JButton jButton_last;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_reset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAuthor;
    private javax.swing.JTextField jTextField_createdDate;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_status;
    private javax.swing.JTextField jTextField_updatedDate;
    // End of variables declaration//GEN-END:variables
}

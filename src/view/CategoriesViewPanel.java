/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Authors;
import entity.Categories;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.AuthorModel;
import model.CategoryModel;

/**
 *
 * @author nnd2890
 */
public class CategoriesViewPanel extends javax.swing.JPanel {

    /**
     * Creates new form CategoriesViewPanel
     */
    public CategoriesViewPanel() {
        initComponents();
        showList();
    }
    
    // Show list of Categories into table
    public void showList() {
        CategoryModel categoryModel = new CategoryModel();
        ArrayList<Categories> list = categoryModel.listCategory();
        DefaultTableModel model = (DefaultTableModel) jTable_category.getModel();
        model.setRowCount(0);
        for (Categories catergory : list) {
            Object[] row = {catergory.getId()
                    , catergory.getName()
                    , catergory.getDescription()
                    , catergory.getCreated_at()
                    , catergory.getUpdated_at()
                    , catergory.getStatus()};
            model.addRow(row);
        }
    }
    
    // Reset
    public void resetFuntion() {
        jTextField_id.setText("");
        JTextField_name.setText("");
        jTextArea_description.setText("");
        jTextField_createdDate.setText("");
        jTextField_updatedDate.setText("");
        jTextField_status.setText("");
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
        JTextField_name = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_category = new javax.swing.JTable();
        jButton_insert = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_resest = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_id = new javax.swing.JTextField();
        jTextField_createdDate = new javax.swing.JTextField();
        jTextField_updatedDate = new javax.swing.JTextField();
        jTextField_status = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_description = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Name");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel2.setText("Description");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 20));

        JTextField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextField_nameActionPerformed(evt);
            }
        });

        jTable_category.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Description", "Created date", "Updated date", "Status"
            }
        ));
        jTable_category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_categoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_category);

        jButton_insert.setText("Insert");
        jButton_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_insertActionPerformed(evt);
            }
        });

        jButton_update.setText("Update");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jButton_delete.setText("Delete");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jButton_resest.setText("Resest");
        jButton_resest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_resestActionPerformed(evt);
            }
        });

        jLabel3.setText("Id");

        jLabel4.setText("Created Date");

        jLabel5.setText("Updated Date");

        jLabel6.setText("Status");

        jTextField_createdDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_createdDateActionPerformed(evt);
            }
        });

        jTextArea_description.setColumns(20);
        jTextArea_description.setRows(5);
        jScrollPane3.setViewportView(jTextArea_description);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(JTextField_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(jTextField_id, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jTextField_createdDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_updatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_status, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_resest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_update, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(jButton_insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_insert)
                            .addComponent(jLabel3)
                            .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_update)
                            .addComponent(JTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_delete)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_resest))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_createdDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_updatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void JTextField_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextField_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextField_nameActionPerformed

    private void jButton_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_insertActionPerformed
        // Insert Authors
        String name = JTextField_name.getText();
        String description = jTextArea_description.getText();
        Categories ca = new Categories();
        ca.setName(name);
        ca.setDescription(description);
        try {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.insertCategory(ca);
            JOptionPane.showMessageDialog(null, "Successful!");
            showList();
            resetFuntion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton_insertActionPerformed

    private void jTextField_createdDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_createdDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_createdDateActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        // Update
        int id = Integer.parseInt(jTextField_id.getText());
        String name = JTextField_name.getText();
        String description = jTextArea_description.getText();
        Categories category = new Categories();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);
        try {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.updateCategory(category);
            JOptionPane.showMessageDialog(null, "Successful!");
            showList();
            resetFuntion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
         // Delete
        try {
            CategoryModel categoryModel = new CategoryModel();
            int id = Integer.parseInt(jTextField_id.getText());
            Categories category = new Categories();
            category.setId(id);
            categoryModel.deleteCategory(category);
            JOptionPane.showMessageDialog(null, "Successful!");
            DefaultTableModel model = (DefaultTableModel) jTable_category.getModel();
            model.setRowCount(0);
            showList();
            resetFuntion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_resestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_resestActionPerformed
        resetFuntion();
    }//GEN-LAST:event_jButton_resestActionPerformed

    private void jTable_categoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_categoryMouseClicked
        // Display selected row into JTextField
        int i = jTable_category.getSelectedRow();
        TableModel model = jTable_category.getModel();
        jTextField_id.setText(model.getValueAt(i, 0).toString());
        JTextField_name.setText(model.getValueAt(i, 1).toString());
        jTextArea_description.setText(model.getValueAt(i, 2).toString());
        jTextField_createdDate.setText(model.getValueAt(i, 3).toString());
        jTextField_updatedDate.setText(model.getValueAt(i, 4).toString());
        jTextField_status.setText(model.getValueAt(i, 5).toString());
    }//GEN-LAST:event_jTable_categoryMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextField_name;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_insert;
    private javax.swing.JButton jButton_resest;
    private javax.swing.JButton jButton_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_category;
    private javax.swing.JTextArea jTextArea_description;
    private javax.swing.JTextField jTextField_createdDate;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_status;
    private javax.swing.JTextField jTextField_updatedDate;
    // End of variables declaration//GEN-END:variables
}
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

import controller.ButtonController;
import model.AuthorModel;
import model.CategoryModel;

/**
 *
 * @author nnd2890
 */
public class CategoriesViewPanel extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form CategoriesViewPanel
     */
    // set next/previous page
    private int currentPage = 1;
    private int limit = 5;
    private int totalPage;

    public CategoriesViewPanel() {
        initComponents();
        this.add(bc.btnBack());
        this.add(bc.btnExit());
//        showList();

        loadData();
        jButton_currentPage.setText(String.valueOf(this.currentPage));
    }

//    // Show list of Categories into table
//    public void showList() {
//        CategoryModel categoryModel = new CategoryModel();
//        ArrayList<Categories> list = categoryModel.listCategory();
//        DefaultTableModel model = (DefaultTableModel) jTable_category.getModel();
//        model.setRowCount(0);
//        for (Categories catergory : list) {
//            Object[] row = {catergory.getId(),
//                catergory.getName(),
//                catergory.getDescription(),
//                catergory.getCreated_at(),
//                catergory.getUpdated_at(),
//                catergory.getStatus()};
//            model.addRow(row);
//        }
//    }
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
        CategoryModel categoryModel = new CategoryModel();
        ArrayList<Categories> list = categoryModel.listCategoryLimit(this.limit, (this.currentPage - 1) * this.limit);
        DefaultTableModel model = (DefaultTableModel) jTable_category.getModel();
        model.setRowCount(0);
        for (Categories category : list) {
            Object[] row = {
                category.getId(),
                category.getName(),
                category.getCreated_at(),
                category.getUpdated_at(),
                category.getStatus()};
            model.addRow(row);
        }
    }

    public int countPage() {
        try {
            CategoryModel categoryModel = new CategoryModel();
            int countPage = categoryModel.countRow();
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
        jButton_first = new javax.swing.JButton();
        jButton_previous = new javax.swing.JButton();
        jButton_currentPage = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jButton_last = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1150, 650));

        jLabel1.setText("Name");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 20));

        JTextField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextField_nameActionPerformed(evt);
            }
        });

        jTable_category.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Created date", "Updated date", "Status"
            }
        ));
        jTable_category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_categoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_category);
        if (jTable_category.getColumnModel().getColumnCount() > 0) {
            jTable_category.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        jButton_insert.setBackground(new java.awt.Color(0, 153, 153));
        jButton_insert.setForeground(new java.awt.Color(255, 255, 255));
        jButton_insert.setText("Insert");
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

        jTextField_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_statusActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(JTextField_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(jTextField_id, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_status, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_updatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_createdDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_resest, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(630, 630, 630)
                        .addComponent(jButton_first)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_previous)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_currentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_next)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_last)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton_delete)
                                    .addComponent(jTextField_createdDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addComponent(jButton_resest))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField_updatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_previous)
                    .addComponent(jButton_first)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_next)
                        .addComponent(jButton_last))
                    .addComponent(jButton_currentPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(227, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void JTextField_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextField_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextField_nameActionPerformed

    private void jButton_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_insertActionPerformed
        // Insert 
        CategoryModel categoryModel = new CategoryModel();
        boolean bl = categoryModel.checkName("categories", JTextField_name.getText());
        if (JTextField_name.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Your characters are not valid", "Error", JOptionPane.ERROR_MESSAGE);
            resetFuntion();
        } else if (bl == true) {
            JOptionPane.showMessageDialog(null, "Your author's name was existed", "Error", JOptionPane.ERROR_MESSAGE);
            resetFuntion();
        } else {
            String name = JTextField_name.getText();
            Categories ca = new Categories();
            ca.setName(name);
            try {
                categoryModel.insertCategory(ca);
                doLast();
                resetFuntion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_insertActionPerformed

    private void jTextField_createdDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_createdDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_createdDateActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        // Update
        int op = JOptionPane.showConfirmDialog(null, "Do you really want update", "Update", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            int id = Integer.parseInt(jTextField_id.getText());
            String name = JTextField_name.getText();
            Categories category = new Categories();
            category.setId(id);
            category.setName(name);
            try {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.updateCategory(category);
                JOptionPane.showMessageDialog(null, "Successful!");
//                showList();
                loadData();
                resetFuntion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        // Delete
        int op = JOptionPane.showConfirmDialog(null, "Do you really want delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            try {
                CategoryModel categoryModel = new CategoryModel();
                int id = Integer.parseInt(jTextField_id.getText());
                Categories category = new Categories();
                category.setId(id);
                categoryModel.deleteCategory(category);
                JOptionPane.showMessageDialog(null, "Successful!");
                loadData();
                resetFuntion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
        jTextField_createdDate.setText(model.getValueAt(i, 2).toString());
        jTextField_updatedDate.setText(model.getValueAt(i, 3).toString());
        jTextField_status.setText(model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_jTable_categoryMouseClicked

    private void jTextField_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_statusActionPerformed

    private void jButton_currentPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_currentPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_currentPageActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextField_name;
    private javax.swing.JButton jButton_currentPage;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_first;
    private javax.swing.JButton jButton_insert;
    private javax.swing.JButton jButton_last;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_previous;
    private javax.swing.JButton jButton_resest;
    private javax.swing.JButton jButton_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_category;
    private javax.swing.JTextField jTextField_createdDate;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_status;
    private javax.swing.JTextField jTextField_updatedDate;
    private ButtonController bc = new ButtonController();
    // End of variables declaration//GEN-END:variables
}

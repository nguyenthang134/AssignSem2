/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Categories;
import entity.Publisher;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.AuthorModel;
import model.CategoryModel;
import model.PublisherModel;

/**
 *
 * @author nnd2890
 */
public class PublisherViewPanel extends javax.swing.JPanel {

    /**
     * Creates new form PublisherViewPanel
     */
    
    // set next/previous page 
    private int currentPage = 1;
    private int limit = 5;
    private int totalPage;
    
    public PublisherViewPanel() {
        initComponents();
//        showList();

        loadData();
        jButton_currentPage.setText(String.valueOf(this.currentPage));
        
    }

    // Reset
    public void resetFuntion() {
        jTextField_id.setText("");
        jTextField_name.setText("");
        jTextArea_address.setText("");
        jTextField_phone.setText("");
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
        PublisherModel publisherModel = new PublisherModel();
        ArrayList<Publisher> list = publisherModel.listPublisherLimit(this.limit, (this.currentPage - 1) * this.limit);
        DefaultTableModel model = (DefaultTableModel) jTable_publisher.getModel();
        model.setRowCount(0);
        for (Publisher publisher : list) {
            Object[] row = {
                publisher.getId(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getPhone(),
                publisher.getCreated_at(),
                publisher.getUpdated_at(),
                publisher.getStatus()};
            model.addRow(row);
        }
    }

    public int countPage() {
        try {
            PublisherModel publisherModel = new PublisherModel();
            int countPage = publisherModel.countRow();
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
        jLabel7 = new javax.swing.JLabel();
        jTextField_id = new javax.swing.JTextField();
        jTextField_name = new javax.swing.JTextField();
        jTextField_phone = new javax.swing.JTextField();
        jTextField_createdDate = new javax.swing.JTextField();
        jTextField_updatedDate = new javax.swing.JTextField();
        jTextField_status = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_publisher = new javax.swing.JTable();
        jButton_insert = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_resest = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_address = new javax.swing.JTextArea();
        jButton_first = new javax.swing.JButton();
        jButton_previous = new javax.swing.JButton();
        jButton_currentPage = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jButton_last = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Id");

        jLabel2.setText("Name");

        jLabel3.setText("Address");

        jLabel4.setText("Phone");

        jLabel5.setText("Created Date");

        jLabel6.setText("Updated Date");

        jLabel7.setText("Status");

        jTable_publisher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Address", "Phone", "Created date", "Updated date", "Status"
            }
        ));
        jTable_publisher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_publisherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_publisher);
        if (jTable_publisher.getColumnModel().getColumnCount() > 0) {
            jTable_publisher.getColumnModel().getColumn(0).setPreferredWidth(10);
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

        jTextArea_address.setColumns(20);
        jTextArea_address.setRows(5);
        jTextArea_address.setPreferredSize(new java.awt.Dimension(400, 300));
        jScrollPane3.setViewportView(jTextArea_address);

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_status))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_updatedDate))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_phone)
                                    .addComponent(jTextField_createdDate)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_name))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_resest, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(638, 638, 638)
                        .addComponent(jButton_first)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_previous)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_currentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_next)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_last)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_insert))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_update))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_delete)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_resest))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField_createdDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_updatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_currentPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_first)
                        .addComponent(jButton_previous)
                        .addComponent(jButton_next)
                        .addComponent(jButton_last)))
                .addContainerGap(183, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        // Update
        int op = JOptionPane.showConfirmDialog(null, "Do you really want update", "Update", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            int id = Integer.parseInt(jTextField_id.getText());
            String name = jTextField_name.getText();
            String address = jTextArea_address.getText();
            int phone = Integer.parseInt(jTextField_phone.getText());
            Publisher publisher = new Publisher();
            publisher.setId(id);
            publisher.setName(name);
            publisher.setAddress(address);
            publisher.setPhone(phone);
            try {
                PublisherModel publiserModel = new PublisherModel();
                publiserModel.updatePublisher(publisher);
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
                PublisherModel publisherModel = new PublisherModel();
                int id = Integer.parseInt(jTextField_id.getText());
                Publisher publisher = new Publisher();
                publisher.setId(id);
                publisherModel.deletePublisher(publisher);
                JOptionPane.showMessageDialog(null, "Successful!");
//                showList();
                loadData();
                resetFuntion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_insertActionPerformed
        // Insert
        PublisherModel publisherModel = new PublisherModel();
        boolean bl = publisherModel.checkName("publishers", jTextField_name.getText());
        if (jTextField_name.getText().equals("") || jTextField_phone.getText().equals("") || jTextArea_address.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Your characters are not valid", "Error", JOptionPane.ERROR_MESSAGE);
            resetFuntion();
        } else if(bl == true) {
            JOptionPane.showMessageDialog(null, "Your author's name was existed", "Error", JOptionPane.ERROR_MESSAGE);
            resetFuntion();
        }else {
            String name = jTextField_name.getText();
            String address = jTextArea_address.getText();
            int phone = Integer.parseInt(jTextField_phone.getText());
            Publisher publisher = new Publisher();
            publisher.setName(name);
            publisher.setAddress(address);
            publisher.setPhone(phone);
            try {
                publisherModel.insertPublisher(publisher);
                JOptionPane.showMessageDialog(null, "Successful!");
//                showList();
                doLast();
                resetFuntion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton_insertActionPerformed

    private void jTable_publisherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_publisherMouseClicked
        // Display selected row into JTextField
        int i = jTable_publisher.getSelectedRow();
        TableModel model = jTable_publisher.getModel();
        jTextField_id.setText(model.getValueAt(i, 0).toString());
        jTextField_name.setText(model.getValueAt(i, 1).toString());
        jTextArea_address.setText(model.getValueAt(i, 2).toString());
        jTextField_phone.setText(model.getValueAt(i, 3).toString());
        jTextField_createdDate.setText(model.getValueAt(i, 4).toString());
        jTextField_updatedDate.setText(model.getValueAt(i, 5).toString());
        jTextField_status.setText(model.getValueAt(i, 6).toString());
    }//GEN-LAST:event_jTable_publisherMouseClicked

    private void jButton_resestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_resestActionPerformed
        resetFuntion();
    }//GEN-LAST:event_jButton_resestActionPerformed

    private void jButton_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_firstActionPerformed
        doFirst();
    }//GEN-LAST:event_jButton_firstActionPerformed

    private void jButton_currentPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_currentPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_currentPageActionPerformed

    private void jButton_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previousActionPerformed
        doPrevious();
    }//GEN-LAST:event_jButton_previousActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        doNext();
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_lastActionPerformed
        doLast();
    }//GEN-LAST:event_jButton_lastActionPerformed


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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_publisher;
    private javax.swing.JTextArea jTextArea_address;
    private javax.swing.JTextField jTextField_createdDate;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_phone;
    private javax.swing.JTextField jTextField_status;
    private javax.swing.JTextField jTextField_updatedDate;
    // End of variables declaration//GEN-END:variables
}
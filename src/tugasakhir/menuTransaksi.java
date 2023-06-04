/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class menuTransaksi extends javax.swing.JFrame {

    /**
     * Creates new form menuTransaksi
     */
    String role;
    
    public menuTransaksi() {
        initComponents();
    }
    
    public menuTransaksi(String role) {
        this.role = role;
        initComponents();
    }
    
        public void loadData() {
                try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String input1 = line.substring(0).trim();
                String input2 = reader.readLine().substring(0).trim();
                String input3 = reader.readLine().substring(0).trim();
                String input4 = reader.readLine().substring(0).trim();
                addToTable(input1, input3, input4, input2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addToTable(String nama, String harga, String jumlah, String jenis) {
        DefaultTableModel table = (DefaultTableModel) tableInven.getModel();
        table.addRow(new Object[]{nama, jenis, harga,jumlah});
    }
    
    public void showName() {
                DefaultTableModel table = (DefaultTableModel) tableInven.getModel();

        int baris = tableInven.getSelectedRow();
        
        String nama = table.getValueAt(baris, 0).toString();
        String jenis = table.getValueAt(baris, 1).toString();
        namaField.setText(nama);
        jenisField.setText(jenis);
    }

    public void updateBtn() {
        DefaultTableModel table = (DefaultTableModel) tableInven.getModel();
        DefaultTableModel tableOrd = (DefaultTableModel) tableOrder.getModel();

        int baris = tableInven.getSelectedRow();
        
        if (baris < 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih barang pada tabel", "Error",  JOptionPane.ERROR_MESSAGE);
        } else {
//          stock = stock - sold;
//          String stockStr = Integer.toString(stock);
//          table.setValueAt(stockStr, baris, 3);
            String nama = table.getValueAt(baris, 0).toString();
            String jenis = table.getValueAt(baris, 1).toString();
            String harga = table.getValueAt(baris, 2).toString();
            int stock = Integer.parseInt(table.getValueAt(baris, 3).toString());
        
            if (soldField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Barang terjual tidak boleh nol", "Error",  JOptionPane.ERROR_MESSAGE);
            } else {
                int sold = Integer.parseInt(soldField.getText());
            
                if (sold > stock) {
                    JOptionPane.showMessageDialog(this, "Barang terjual melebihi stock", "Error",  JOptionPane.ERROR_MESSAGE);
                } else {
                    if (tableOrd.getRowCount() <= 0) {
                        Object[] barisBaru = {nama, jenis, harga, soldField.getText()};
                        tableOrd.addRow(barisBaru);
                        
                        namaField.setText("");
                        jenisField.setText("");
                        soldField.setText("");
                    } else {
                        for (int i = 0;i < tableOrd.getRowCount();i++) {
                            if (nama.equals(tableOrd.getValueAt(i, 0).toString())) {
                                if (sold + Integer.parseInt(tableOrd.getValueAt(i, 3).toString()) > stock) {
                                    JOptionPane.showMessageDialog(this, "Barang terjual akan melebihi stock", "Error",  JOptionPane.ERROR_MESSAGE);
                                    break;
                                } else {
                                    int total = sold + Integer.parseInt(tableOrd.getValueAt(i, 3).toString());
//                                  Object[] updateBaris = {nama, jenis, harga, total};
                                    tableOrd.setValueAt(nama, i, 0);
                                    tableOrd.setValueAt(jenis, i, 1);
                                    tableOrd.setValueAt(harga, i, 2);
                                    tableOrd.setValueAt(total, i, 3);
                                    
                                    namaField.setText("");
                                    jenisField.setText("");
                                    soldField.setText("");
                                    break;
                                }
                            } else {
                                baris = tableInven.getSelectedRow();
                                            nama = table.getValueAt(baris, 0).toString();
             jenis = table.getValueAt(baris, 1).toString();
             harga = table.getValueAt(baris, 2).toString();
                                                        Object[] barisBaru = {nama, jenis, harga, soldField.getText()};
                        tableOrd.addRow(barisBaru);
                        break;
                            }
                        }
                    }
                }
            }
        }
    }
    
        public void saveTableData() {
    DefaultTableModel table = (DefaultTableModel) tableInven.getModel();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"))) {
            for (int row = 0; row < table.getRowCount(); row++) {
                for (int col = 0; col < table.getColumnCount(); col++) {
                    String value = (String) table.getValueAt(row, col);
                    writer.write(value);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    public void order() {
        DefaultTableModel tableInv = (DefaultTableModel) tableInven.getModel();
        DefaultTableModel tableOrd = (DefaultTableModel) tableOrder.getModel();
        
        int barisInv = tableInv.getRowCount();
        int barisOrd = tableOrd.getRowCount();
        
        if (barisOrd <= 0) {
            JOptionPane.showMessageDialog(this, "Silakan tambahkan barang", "Error",  JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 0; i < barisOrd; i++ ) {
                for (int j = 0; j < barisInv; j++) {
                    if (tableInv.getValueAt(j, 0).equals(tableOrd.getValueAt(i, 0))) {
                        int stockNow = Integer.parseInt(tableInv.getValueAt(j, 3).toString()) - Integer.parseInt(tableOrd.getValueAt(i, 3).toString());
                        
                        tableInv.setValueAt(Integer.toString(stockNow), j, 3);
                        break;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Order berhasil dilakukan", "Info", JOptionPane.INFORMATION_MESSAGE);
            tableOrd.setRowCount(0);
            namaField.setText("");
            jenisField.setText("");
            soldField.setText("");
        }
        


    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        soldField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableInven = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        namaField = new javax.swing.JTextField();
        jenisField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko Sumber Makmur");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        soldField.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        soldField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jumlah Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 13))); // NOI18N

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tableInven.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        tableInven.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Jenis", "Harga", "Jumlah"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableInven.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableInvenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableInven);

        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jButton2.setText("Tambah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        namaField.setEditable(false);
        namaField.setBackground(new java.awt.Color(255, 255, 255));
        namaField.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        namaField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nama Barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 13))); // NOI18N

        jenisField.setEditable(false);
        jenisField.setBackground(new java.awt.Color(255, 255, 255));
        jenisField.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jenisField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jenis Barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 13))); // NOI18N

        tableOrder.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Jenis", "Harga", "Jumlah"
            }
        ));
        jScrollPane2.setViewportView(tableOrder);

        jButton3.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jButton3.setText("Order");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/storage-stacks.png"))); // NOI18N
        jLabel1.setText("Menu Transaksi");
        jLabel1.setAlignmentY(5.0F);
        jLabel1.setIconTextGap(10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(namaField, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(jenisField)
                            .addComponent(soldField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(133, 133, 133))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(namaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jenisField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(soldField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(118, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        saveTableData();
        this.dispose();
        new mainMenu(role).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_formWindowOpened

    private void tableInvenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableInvenMouseClicked
        // TODO add your handling code here:
        showName();
    }//GEN-LAST:event_tableInvenMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        updateBtn();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        order();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jenisField;
    private javax.swing.JTextField namaField;
    private javax.swing.JTextField soldField;
    private javax.swing.JTable tableInven;
    private javax.swing.JTable tableOrder;
    // End of variables declaration//GEN-END:variables
}

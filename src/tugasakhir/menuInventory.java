/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class menuInventory extends javax.swing.JFrame {

    /**
     * Creates new form menuInventory
     */
    String role;
    
    public menuInventory() {
        initComponents();
    }
    
    public menuInventory(String role) {
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
    
//        private void searchTable(String kata) {
//        // Clear the table before performing a new search
//            DefaultTableModel table = (DefaultTableModel) tableInven.getModel();
//        table.setRowCount(0);
//
//        for (int row = 0; row < table.getRowCount(); row++) {
//            for (int col = 0; col < table.getColumnCount(); col++) {
//                String value = (String) table.getValueAt(row, col);
//                if (value.toLowerCase().contains(kata)) {
//                    String nama = (String) table.getValueAt(row, 0);
//                    String jenis = (String) table.getValueAt(row, 1);
//                    String harga = (String) table.getValueAt(row, 2);
//                    String jumlah = (String) table.getValueAt(row, 4);
//                    addToTable(nama, jenis, harga, jumlah);
//                    break; // Stop searching this row
//                }
//            }
//        }
//    }

        private List<String[]> searchFromFile(String kata) {
        List<String[]> hasil = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            String line;
            String[] rowData = new String[4];
            int count = 0;

            while ((line = reader.readLine()) != null) {
  
                    rowData[count] = line.substring(0).trim();
                    rowData[count + 1] = reader.readLine().substring(0).trim();
                    rowData[count + 2] = reader.readLine().substring(0).trim();
                    rowData[count + 3] = reader.readLine().substring(0).trim();
                    
                    if (rowData[count].toLowerCase().contains(kata) ||
                            rowData[count + 1].toLowerCase().contains(kata) ||
                            rowData[count + 2].toLowerCase().contains(kata) ||
                            rowData[count + 3].toLowerCase().contains(kata)) {
                        hasil.add(rowData.clone());
                    }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hasil;
    }
        
            private void displaySearchResults(List<String[]> hasil) {
                DefaultTableModel table = (DefaultTableModel) tableInven.getModel();
        table.setRowCount(0);

        for (String[] rowData : hasil) {
            table.addRow(rowData);
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
        searchField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableInven = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko Sumber Makmur");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        searchField.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        searchField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cari Barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 13))); // NOI18N
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        tableInven.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        tableInven.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Jenis", "Harga", "Jumlah"
            }
        ));
        tableInven.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tableInvenPropertyChange(evt);
            }
        });
        tableInven.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableInvenKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableInven);

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jButton4.setText("Edit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/storage-stacks.png"))); // NOI18N
        jLabel1.setText("Menu Inventory");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel table = (DefaultTableModel) tableInven.getModel();

        table.setRowCount(0);
        loadData();

        this.dispose();
        new mainMenu(role).setVisible(true);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                DefaultTableModel table = (DefaultTableModel) tableInven.getModel();

        table.setRowCount(0);
        loadData();
        
        this.dispose();
        new tambahInventory(role).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int baris = tableInven.getSelectedRow();
        
        if (baris < 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada baris yang dipilih", "Silakan pilih baris", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel table = (DefaultTableModel) tableInven.getModel();
            table.removeRow(baris);
        }
        
        saveTableData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        saveTableData();
    }//GEN-LAST:event_formWindowClosed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        // TODO add your handling code here:
                    String kata = searchField.getText();
            List<String[]> hasil = searchFromFile(kata);
            displaySearchResults(hasil);
    }//GEN-LAST:event_searchFieldKeyReleased

    private void tableInvenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tableInvenPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tableInvenPropertyChange

    private void tableInvenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableInvenKeyReleased
        // TODO add your handling code here:
        saveTableData();
    }//GEN-LAST:event_tableInvenKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel table = (DefaultTableModel) tableInven.getModel();
//        menuEditInven modal = new menuEditInven(this, rootPaneCheckingEnabled);
        int baris = tableInven.getSelectedRow();
        
        if (baris < 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih data pada tabel", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
                    String nama = table.getValueAt(baris, 0).toString();
        String jenis = table.getValueAt(baris, 1).toString();
        String harga = table.getValueAt(baris, 2).toString();
        String jumlah = table.getValueAt(baris, 3).toString();
        
        new menuEditInven(this, rootPaneCheckingEnabled, nama, jenis, harga, jumlah, table, baris, role).setVisible(true);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    
    public static void addRowToTable(Object[] data) {
        DefaultTableModel telo = (DefaultTableModel) tableInven.getModel();
        
        telo.addRow(data);
    }
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
            java.util.logging.Logger.getLogger(menuInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuInventory().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    private static javax.swing.JTable tableInven;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.awt.Dialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class tambahInventory extends javax.swing.JFrame {

    /**
     * Creates new form tambahInventory
     * @param table
     */
    String role;
    
    public tambahInventory() {
        
        initComponents();
    }
    
    public tambahInventory(String role) {
        this.role = role;
        initComponents();
    }
    
    public void saveFile(String nama, String jenis, String harga, String jumlah) {
        String line;
        String[] data = new String[4];
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt", true))) {
                if (reader.readLine() == null) {
                        writer.write(nama);
                        writer.newLine();
                        writer.write(jenis);
                        writer.newLine();
                        writer.write(harga);
                        writer.newLine();
                        writer.write(jumlah);
                        writer.newLine();
                        System.out.println("User inputs saved to file.");
            
            this.dispose();
            new menuInventory().setVisible(true);
                } else {
                    String line2;
                    String err = "n";
                    try (BufferedReader reader2 = new BufferedReader(new FileReader("inventory.txt"))) {
                        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter("inventory.txt", true))) {
                        while((line2 = reader2.readLine()) != null) {
                    System.out.println("d");
                    data[0] = line2.substring(0).trim();
                    data[1] = reader2.readLine().substring(0).trim();
                    data[2] = reader2.readLine().substring(0).trim();
                    data[3] = reader2.readLine().substring(0).trim();
                    System.out.println(data[0]);
                    if (data[0].toLowerCase().contains(nama)) {
                        JOptionPane.showMessageDialog(this, "Nama barang sudah ada", "Error", JOptionPane.ERROR_MESSAGE);
                        err = "y";
                        break;
                    } 

                }
                    if (err.equals("n")) {
                                                writer2.write(nama);
                        writer2.newLine();
                        writer2.write(jenis);
                        writer2.newLine();
                        writer2.write(harga);
                        writer2.newLine();
                        writer2.write(jumlah);
                        writer2.newLine();
                        System.out.println("User inputs saved to file.");
            
            this.dispose();
            new menuInventory(role).setVisible(true);
                    }    
                        }catch(IOException e) {
            e.printStackTrace();
        }
                        
                    } catch(IOException e) {
            e.printStackTrace();
        }

                }


        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public boolean checkValid() {
        if (namaField.getText().equals("") || hargaField.getText().equals("") || jumlahField.getText().equals("")) {
            return false;
        } return true;
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
        hargaField = new javax.swing.JTextField();
        jenisBox = new javax.swing.JComboBox<>();
        namaField = new javax.swing.JTextField();
        jumlahField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko Sumber Makmur");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        hargaField.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        hargaField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Harga Barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 13))); // NOI18N
        hargaField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hargaFieldKeyTyped(evt);
            }
        });

        jenisBox.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jenisBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paku", "Cat", "Perkakas", "Semen" }));
        jenisBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jenis Barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 13))); // NOI18N

        namaField.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        namaField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nama Barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 13))); // NOI18N

        jumlahField.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jumlahField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jumlah Stok", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 13))); // NOI18N
        jumlahField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlahFieldKeyTyped(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jenisBox, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaField)
                    .addComponent(hargaField)
                    .addComponent(jumlahField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(namaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jenisBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(hargaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jumlahField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new menuInventory(role).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        DefaultTableModel tahu = (DefaultTableModel) tableModel.getModel();
//        
        if (checkValid()) {
        String nama = namaField.getText();
        String harga = hargaField.getText();
        String jumlah = jumlahField.getText();
        String jenis = jenisBox.getSelectedItem().toString();
            System.out.println("ksaldf");
        saveFile(nama, jenis, harga, jumlah);
        

        } else {
            JOptionPane.showMessageDialog(this, "Silakan isi data!", "Data kosong", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void hargaFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_hargaFieldKeyTyped

    private void jumlahFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_jumlahFieldKeyTyped

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(tambahInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(tambahInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(tambahInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(tambahInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new tambahInventory().setVisible(true);
//            }
//        });
//    }
    
    private menuInventory menuInventory;
    private JTable tableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField hargaField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jenisBox;
    private javax.swing.JTextField jumlahField;
    private javax.swing.JTextField namaField;
    // End of variables declaration//GEN-END:variables


}

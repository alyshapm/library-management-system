/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author alyshapm
 */
public class RequestReturn extends javax.swing.JFrame {

    /**
     * Creates new form RequestReturn
     */
    public RequestReturn() {
        initComponents();
    }
    
    // fetch issue book details from db and display to panel
    public void getIssueBookDetails(){
        
        int bookId = Integer.parseInt(txt_bookId.getText());
        String userId = txt_studentId.getText();
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "select * from book_borrowreturn where bookId = ? and userId = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, userId);
            pst.setString(3, "pending");
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                lbl_issueId.setText(rs.getString("borrowId")); // getint?
                lbl_bookName.setText(rs.getString("bookId"));
                lbl_studentName.setText(rs.getString("userId"));
                lbl_issueDate.setText(rs.getString("borrowDate"));
                lbl_dueDate.setText(rs.getString("returnDate"));
                lbl_fine.setText(rs.getString("fine"));
                lbl_bookError.setText("");
            } else {
                lbl_bookError.setText("No record found!");
                
                lbl_issueId.setText("");
                lbl_bookName.setText("");
                lbl_studentName.setText("");
                lbl_issueDate.setText("");
                lbl_dueDate.setText("");
                lbl_fine.setText("");
                
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    public boolean returnBook(){
        boolean isReturned = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        String userId = txt_studentId.getText();
        
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "update book_borrowreturn set status = ? where userId = ? and bookId = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "returned");
            pst.setString(2, userId);
            pst.setInt(3, bookId);
            pst.setString(4, "pending");
            
            int rowCount = pst.executeUpdate(); // query
            if(rowCount > 0){
                isReturned = true;
            } else {
                isReturned = false;
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return isReturned;
    }
    
   
    // updating book count i.e. when a book is borrowed, quantity - 1
    public void updateBookCount(){
        int bookId = Integer.parseInt(txt_bookId.getText());
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "update book set quantity = quantity + 1 where bookId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0){
                JOptionPane.showMessageDialog(this, "Book count updated!");
            } else {
                JOptionPane.showMessageDialog(this, "Count update failed!");
            }
        } catch (Exception e){
            e.printStackTrace();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_dueDate = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_issueId = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_issueDate = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_fine = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        lbl_bookError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(235, 235, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 18)); // NOI18N
        jLabel2.setText("LMS - Return Books");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -20, 270, 100));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel1.setText("BACK");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 50));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jLabel15.setText("Due date:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, 20));

        jLabel3.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jLabel3.setText("BOOK DETAILS");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jLabel14.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jLabel14.setText("Issue date:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, 20));

        jLabel7.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jLabel7.setText("Issue ID:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        lbl_dueDate.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jPanel3.add(lbl_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 110, 30));

        lbl_bookName.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jPanel3.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 110, 30));

        jLabel6.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jLabel6.setText("User name:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel9.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jLabel9.setText("Book name:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        lbl_issueId.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jPanel3.add(lbl_issueId, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 120, 20));

        lbl_studentName.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jPanel3.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 160, 30));

        lbl_issueDate.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jPanel3.add(lbl_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 110, 30));

        jLabel16.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jLabel16.setText("Fine:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, -1, 20));

        lbl_fine.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jPanel3.add(lbl_fine, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 110, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 350, 500));

        jLabel8.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Book ID");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        txt_bookId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_bookId.setPlaceholder("Enter book ID...");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 350, 30));

        jLabel12.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("User ID");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        txt_studentId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_studentId.setPlaceholder("Enter user ID...");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 350, 30));

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle3.setText("Find");
        rSMaterialButtonRectangle3.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 130, 40));

        rSMaterialButtonRectangle2.setText("Return book");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 130, 40));

        lbl_bookError.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(255, 204, 102));
        jPanel1.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 250, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        setSize(new java.awt.Dimension(1000, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost

    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost

    }//GEN-LAST:event_txt_studentIdFocusLost

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
        getIssueBookDetails ();
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        if (returnBook() == true){
            JOptionPane.showMessageDialog(this, "Book returned successfully!");
            updateBookCount();
        } else {
            JOptionPane.showMessageDialog(this, "Book return failed!");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        Home home = new Home(); // open new homepage page
        home.setVisible(true);
        dispose(); // close current page
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(RequestReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RequestReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RequestReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RequestReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RequestReturn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_dueDate;
    private javax.swing.JLabel lbl_fine;
    private javax.swing.JLabel lbl_issueDate;
    private javax.swing.JLabel lbl_issueId;
    private javax.swing.JLabel lbl_studentName;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}

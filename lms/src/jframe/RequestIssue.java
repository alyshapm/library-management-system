/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author alyshapm
 */
public class RequestIssue extends javax.swing.JFrame {

    /**
     * Creates new form RequestIssue
     */
    public RequestIssue() {
        initComponents();
    }
    
    // fetch book details from database and displays it on the panel
    public void getBookDetails(){
        int bookId = Integer.parseInt(txt_bookId.getText());
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            PreparedStatement pst = con.prepareStatement("select * from book where bookId = ?");
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            
            // iterate through all the information and display
            if(rs.next()){
                lbl_bookId.setText(rs.getString("bookId"));
                lbl_bookName.setText(rs.getString("title"));
                lbl_author.setText(rs.getString("author_name"));
                lbl_quantity.setText(rs.getString("quantity"));
            } else {
                lbl_bookError.setText("Invalid book ID!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    // fetch student details from database and displays it on the panel
    public void getStudentDetails(){
        String userId = txt_studentId.getText();
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            PreparedStatement pst = con.prepareStatement("select * from user where userId = ?");
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            
            // iterate through all the information and display
            if(rs.next()){
                lbl_studentId.setText(rs.getString("userId"));
                lbl_studentName.setText(rs.getString("userFname"));
            } else {
                lbl_studentError.setText("Invalid user ID!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    // insert issue book details to database
    public boolean issueBook(){
        boolean isIssued = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        String userId = txt_studentId.getText();
        String adminId = txt_admin.getText();
        
        Date uIssueDate = date_issueDate.getDatoFecha();
        Date uDueDate = date_dueDate.getDatoFecha();
        
        // changes java date to sql date format
        Long l1 = uIssueDate.getTime();
        Long l2 = uDueDate.getTime();
        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "insert into book_borrowreturn(bookId, userId, adminId, borrowDate, returnDate, status, fine) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, userId);
            pst.setString(3, adminId);
            pst.setDate(4, sIssueDate);
            pst.setDate(5, sDueDate);
            pst.setString(6, "pending");
            pst.setInt(7, 0);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0){
                isIssued = true;
            } else {
                isIssued = false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isIssued;
    }
    
    // updating book count i.e. when a book is borrowed, quantity - 1
    public void updateBookCount(){
        int bookId = Integer.parseInt(txt_bookId.getText());
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "update book set quantity = quantity - 1 where bookId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0){
                JOptionPane.showMessageDialog(this, "Book count updated!");
                int initialCount = Integer.parseInt(lbl_quantity.getText()); // updates the display
                lbl_quantity.setText(Integer.toString(initialCount - 1));
            } else {
                JOptionPane.showMessageDialog(this, "Count update failed!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // validate duplication - checks if book has been allocated or not
    public boolean alreadyIssued(){
        boolean alreadyIssued = false;
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
            if(rs.next()){
                alreadyIssued = true;
            } else {
                alreadyIssued = false;
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return alreadyIssued;
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
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        date_dueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel13 = new javax.swing.JLabel();
        date_issueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jlabel1 = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_admin = new app.bolivia.swing.JCTextField();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(235, 235, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 18)); // NOI18N
        jLabel2.setText("LMS - Issue book");
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

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle2.setText("Issue book");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, 170, 40));

        date_dueDate.setColorBackground(new java.awt.Color(0, 0, 0));
        date_dueDate.setColorButtonHover(new java.awt.Color(0, 0, 0));
        date_dueDate.setColorForeground(new java.awt.Color(0, 0, 0));
        date_dueDate.setFont(new java.awt.Font("Shree Devanagari 714", 0, 13)); // NOI18N
        date_dueDate.setFuente(new java.awt.Font("Shree Devanagari 714", 0, 13)); // NOI18N
        date_dueDate.setPlaceholder("Select return date");
        jPanel1.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 350, 30));

        jLabel13.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Return date");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        date_issueDate.setColorBackground(new java.awt.Color(0, 0, 0));
        date_issueDate.setColorDiaActual(new java.awt.Color(153, 0, 51));
        date_issueDate.setColorForeground(new java.awt.Color(0, 0, 0));
        date_issueDate.setFont(new java.awt.Font("Shree Devanagari 714", 0, 13)); // NOI18N
        date_issueDate.setFuente(new java.awt.Font("Shree Devanagari 714", 0, 13)); // NOI18N
        date_issueDate.setPlaceholder("Select date");
        jPanel1.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 350, 30));

        jLabel11.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Issue date");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        txt_studentId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_studentId.setFont(new java.awt.Font("Shree Devanagari 714", 0, 13)); // NOI18N
        txt_studentId.setPlaceholder("Enter user ID...");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 320, 30));

        jLabel12.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("User");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        txt_bookId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_bookId.setFont(new java.awt.Font("Shree Devanagari 714", 0, 13)); // NOI18N
        txt_bookId.setPlaceholder("Enter book ID...");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 320, 30));

        jLabel8.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Book ID");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        lbl_bookError.setFont(new java.awt.Font("Helvetica Neue", 2, 12)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(0, 51, 153));
        jPanel1.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, 210, 40));

        lbl_quantity.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jPanel1.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 230, 110, 30));

        lbl_author.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jPanel1.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, 110, 30));

        lbl_bookName.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jPanel1.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 110, 30));

        lbl_bookId.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jPanel1.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 120, 120, 20));

        jLabel3.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jLabel3.setText("BOOK DETAILS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setText("Book ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, -1, -1));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel9.setText("Book name:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setText("Author:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, -1, -1));

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel14.setText("Quantity:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, -1, 20));

        lbl_studentId.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jPanel1.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 370, 120, 30));

        jlabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jlabel.setText("Student ID:");
        jPanel1.add(jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, -1, -1));

        jlabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jlabel1.setText("Name:");
        jPanel1.add(jlabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, -1, -1));

        lbl_studentName.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jPanel1.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 410, 110, 30));

        jLabel5.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        jLabel5.setText("STUDENT DETAILS");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, -1, -1));

        lbl_studentError.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lbl_studentError.setForeground(new java.awt.Color(0, 51, 153));
        jPanel1.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 540, 240, 20));

        jLabel15.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Admin ID");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        txt_admin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_admin.setFont(new java.awt.Font("Shree Devanagari 714", 0, 13)); // NOI18N
        txt_admin.setPlaceholder("Enter admin ID...");
        txt_admin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_adminFocusLost(evt);
            }
        });
        jPanel1.add(txt_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 320, 30));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 350, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        setSize(new java.awt.Dimension(1000, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        if (lbl_quantity.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Book is not available!");
        } else {
            if (alreadyIssued() == false){
                if (issueBook() == true){
                    JOptionPane.showMessageDialog(this, "Book issued successfully!");
                    updateBookCount();
                } else {
                    JOptionPane.showMessageDialog(this, "Book issue failed!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Book has been issued already!");
            }
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        // checks if field is empty
        if (!txt_studentId.getText().equals("")){
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        // checks if field is empty
        if (!txt_bookId.getText().equals("")){
            getBookDetails();
        }
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_adminFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_adminFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adminFocusLost

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
            java.util.logging.Logger.getLogger(RequestIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RequestIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RequestIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RequestIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RequestIssue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_dueDate;
    private rojeru_san.componentes.RSDateChooser date_issueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabel1;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private app.bolivia.swing.JCTextField txt_admin;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}

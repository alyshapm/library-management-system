/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alyshapm
 */
public class ViewIssuedBooks extends javax.swing.JFrame {

    /**
     * Creates new form ViewIssuedBooks
     */
    
    DefaultTableModel model;
    
    public ViewIssuedBooks() {
        initComponents();
        displaySummary();
    }
    
    // display book data into table
    public void displaySummary(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book_borrowreturn");
            
            while(rs.next()){
                int id = rs.getInt("borrowid");
                int bookId = rs.getInt("bookid");
                int userId = rs.getInt("userid");
                int adminId = rs.getInt("adminid");
                String issueDate = rs.getString("borrowdate");
                String dueDate = rs.getString("returndate");
                String status = rs.getString("status");
                
                Object[] obj = {id, bookId, userId, adminId, issueDate, dueDate, status};
                model =(DefaultTableModel) tbl_summary.getModel(); // create a model which creates a row 
                model.addRow(obj);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // clear table -- used to display table when new information is added
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tbl_summary.getModel();
        model.setRowCount(0);
    }
    
     // fetch record using date fields
    public void search(){
        // first, convert util date format to sql date format
        Date uFromDate = date_fromDate.getDatoFecha();
        Date uToDate = date_toDate.getDatoFecha();
        
        long a = uFromDate.getTime();
        long b = uToDate.getTime();
        
        java.sql.Date fromDate = new java.sql.Date(a);
        java.sql.Date toDate = new java.sql.Date(b);
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "select * from book_borrowreturn where borrowdate BETWEEN ? and ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, fromDate);
            pst.setDate(2, toDate);
            
            //select query 
            ResultSet rs = pst.executeQuery();
            
            if(rs.next() == false) {
                JOptionPane.showMessageDialog(this, "No record found!");
            } else {
                while(rs.next()){
                    int id = rs.getInt("borrowid");
                    int bookId = rs.getInt("bookid");
                    int userId = rs.getInt("userid");
                    int adminId = rs.getInt("adminid");
                    String issueDate = rs.getString("borrowdate");
                    String dueDate = rs.getString("returndate");
                    String status = rs.getString("status");
                
                Object[] obj = {id, bookId, userId, adminId, issueDate, dueDate, status};
                    model =(DefaultTableModel) tbl_summary.getModel(); // create a model which creates a row 
                    model.addRow(obj);
                }
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void searchUser(){
        String userInput = txt_userid.getText();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "select * from book_borrowreturn where userid = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userInput);
            
            //select query 
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("borrowid");
                int bookId = rs.getInt("bookid");
                int userId = rs.getInt("userid");
                int adminId = rs.getInt("adminid");
                String issueDate = rs.getString("borrowdate");
                String dueDate = rs.getString("returndate");
                String status = rs.getString("status");
                
                Object[] obj = {id, bookId, userId, adminId, issueDate, dueDate, status};
                model =(DefaultTableModel) tbl_summary.getModel(); // create a model which creates a row 
                model.addRow(obj);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // fetch record
    public void searchAdmin(){
        
        String adminInput = txt_adminid.getText();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "select * from book_borrowreturn where adminid = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, adminInput);
            
            //select query 
            ResultSet rs = pst.executeQuery();
            
//            if(rs.next() == false) {
//                JOptionPane.showMessageDialog(this, "No record found!");
//            } else {
//                while(rs.next()){
//                    int id = rs.getInt("borrowid");
//                    int bookId = rs.getInt("bookid");
//                    int userId = rs.getInt("userid");
//                    int adminId = rs.getInt("adminid");
//                    String issueDate = rs.getString("borrowdate");
//                    String dueDate = rs.getString("returndate");
//                    String status = rs.getString("status");
//                
//                    Object[] obj = {id, bookId, userId, adminId, issueDate, dueDate, status};
//                    model =(DefaultTableModel) tbl_summary.getModel(); // create a model which creates a row 
//                    model.addRow(obj);
//                }
//            }
            
            while(rs.next()) {
                int id = rs.getInt("borrowid");
                int bookId = rs.getInt("bookid");
                int userId = rs.getInt("userid");
                int adminId = rs.getInt("adminid");
                String issueDate = rs.getString("borrowdate");
                String dueDate = rs.getString("returndate");
                String status = rs.getString("status");
                
                Object[] obj = {id, bookId, userId, adminId, issueDate, dueDate, status};
                model =(DefaultTableModel) tbl_summary.getModel(); // create a model which creates a row 
                model.addRow(obj);
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
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_summary = new rojeru_san.complementos.RSTableMetro();
        jLabel11 = new javax.swing.JLabel();
        date_fromDate = new rojeru_san.componentes.RSDateChooser();
        jLabel12 = new javax.swing.JLabel();
        date_toDate = new rojeru_san.componentes.RSDateChooser();
        rSButtonHover1 = new rojerusan.RSButtonHover();
        rSButtonHover3 = new rojerusan.RSButtonHover();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_userid = new app.bolivia.swing.JCTextField();
        txt_adminid = new app.bolivia.swing.JCTextField();
        rSButtonHover2 = new rojerusan.RSButtonHover();
        rSButtonHover4 = new rojerusan.RSButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(235, 235, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 18)); // NOI18N
        jLabel2.setText("LMS - View Issued Books");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -20, 270, 100));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("BACK");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 50));

        jScrollPane2.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N

        tbl_summary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Book ID", "User ID", "Admin ID", "Issue date", "Due date", "Status"
            }
        ));
        tbl_summary.setAltoHead(20);
        tbl_summary.setColorBackgoundHead(new java.awt.Color(235, 235, 235));
        tbl_summary.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_summary.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl_summary.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl_summary.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tbl_summary.setColorSelBackgound(new java.awt.Color(204, 204, 204));
        tbl_summary.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        tbl_summary.setFuenteFilas(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        tbl_summary.setFuenteFilasSelect(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        tbl_summary.setFuenteHead(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        tbl_summary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_summaryMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_summary);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 870, 400));

        jLabel11.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel11.setText("From:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        date_fromDate.setColorBackground(new java.awt.Color(102, 102, 102));
        date_fromDate.setColorButtonHover(new java.awt.Color(153, 153, 153));
        date_fromDate.setColorForeground(new java.awt.Color(0, 0, 0));
        date_fromDate.setFont(new java.awt.Font("Sinhala Sangam MN", 0, 12)); // NOI18N
        date_fromDate.setFuente(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        date_fromDate.setPlaceholder("Select date");
        jPanel1.add(date_fromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 170, 30));

        jLabel12.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel12.setText("To:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));

        date_toDate.setColorBackground(new java.awt.Color(102, 102, 102));
        date_toDate.setColorButtonHover(new java.awt.Color(153, 153, 153));
        date_toDate.setColorForeground(new java.awt.Color(0, 0, 0));
        date_toDate.setFont(new java.awt.Font("Sinhala Sangam MN", 0, 12)); // NOI18N
        date_toDate.setFuente(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        date_toDate.setPlaceholder("Select date");
        jPanel1.add(date_toDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 170, 30));

        rSButtonHover1.setBackground(new java.awt.Color(51, 51, 51));
        rSButtonHover1.setText("Search");
        rSButtonHover1.setFont(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 70, 20));

        rSButtonHover3.setBackground(new java.awt.Color(153, 0, 0));
        rSButtonHover3.setText("Clear");
        rSButtonHover3.setToolTipText("");
        rSButtonHover3.setFont(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        rSButtonHover3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonHover3MouseClicked(evt);
            }
        });
        rSButtonHover3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 70, 20));

        jLabel1.setFont(new java.awt.Font("Shree Devanagari 714", 3, 12)); // NOI18N
        jLabel1.setText("Search by user");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 160, 20));

        jLabel3.setFont(new java.awt.Font("Shree Devanagari 714", 3, 12)); // NOI18N
        jLabel3.setText("Search by admin");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 160, 20));

        txt_userid.setBackground(new java.awt.Color(235, 235, 235));
        txt_userid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_userid.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        txt_userid.setPlaceholder("Enter user ID...");
        txt_userid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_useridFocusLost(evt);
            }
        });
        jPanel1.add(txt_userid, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 200, 30));

        txt_adminid.setBackground(new java.awt.Color(235, 235, 235));
        txt_adminid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_adminid.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        txt_adminid.setPlaceholder("Enter admin ID...");
        txt_adminid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_adminidFocusLost(evt);
            }
        });
        jPanel1.add(txt_adminid, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));

        rSButtonHover2.setBackground(new java.awt.Color(51, 51, 51));
        rSButtonHover2.setText("Search");
        rSButtonHover2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        rSButtonHover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, 70, 20));

        rSButtonHover4.setBackground(new java.awt.Color(51, 51, 51));
        rSButtonHover4.setText("Search");
        rSButtonHover4.setFont(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        rSButtonHover4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 100, 70, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        setSize(new java.awt.Dimension(1000, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_summaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_summaryMouseClicked

    }//GEN-LAST:event_tbl_summaryMouseClicked

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        // if user does not select any dates, display all data
        if (date_fromDate.getDatoFecha() != null & date_toDate.getDatoFecha() != null){
            clearTable(); // removes duplication
            search();
        } else {
            JOptionPane.showMessageDialog(this, "Select a date!");
        }

    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void rSButtonHover3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonHover3MouseClicked
        clearTable();
        displaySummary();
    }//GEN-LAST:event_rSButtonHover3MouseClicked

    private void rSButtonHover3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover3ActionPerformed
        // TODO add your handling code here:
        clearTable();
        displaySummary();
    }//GEN-LAST:event_rSButtonHover3ActionPerformed

    private void txt_useridFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_useridFocusLost

    }//GEN-LAST:event_txt_useridFocusLost

    private void txt_adminidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_adminidFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adminidFocusLost

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
        //    if (txt_author != null){
            //            clearTable(); // removes duplication
            //            searchAuthor();
            //        } else {
            //            JOptionPane.showMessageDialog(this, "Input data!");
            //        }
        String authorInput = txt_userid.getText();
        if (authorInput.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter valid user id!");
        } else {
            clearTable();
            searchUser();
        }
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void rSButtonHover4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover4ActionPerformed
        // TODO add your handling code here:
        String publisherInput = txt_adminid.getText();
        if (publisherInput.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter valid admin id!");
        } else {
            clearTable();
            searchAdmin();
        }
    }//GEN-LAST:event_rSButtonHover4ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Home home = new Home(); // open new homepage page
        home.setVisible(true);
        dispose(); // close current page
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(ViewIssuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewIssuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewIssuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewIssuedBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewIssuedBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_fromDate;
    private rojeru_san.componentes.RSDateChooser date_toDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSButtonHover rSButtonHover1;
    private rojerusan.RSButtonHover rSButtonHover2;
    private rojerusan.RSButtonHover rSButtonHover3;
    private rojerusan.RSButtonHover rSButtonHover4;
    private rojeru_san.complementos.RSTableMetro tbl_summary;
    private app.bolivia.swing.JCTextField txt_adminid;
    private app.bolivia.swing.JCTextField txt_userid;
    // End of variables declaration//GEN-END:variables
}

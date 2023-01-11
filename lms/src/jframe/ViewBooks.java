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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alyshapm
 */
public class ViewBooks extends javax.swing.JFrame {
    
//    String bookName, author, publisher;
//    int bookId, quantity, availability, floor;
    DefaultTableModel model;

    /**
     * Creates new form ViewBooks()
     */
    public ViewBooks() {
        initComponents();
        displayBookToTable();
    }
    
    // display book data into table
    public void displayBookToTable(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book");
            
            while(rs.next()){
                int bookId = rs.getInt("bookId"); // gets book id from DB
                String bookName = rs.getString("title");
                String author = rs.getString("author_name");
                int quantity = rs.getInt("quantity");
                int publishId = rs.getInt("publishId");
                int availability = rs.getInt("availability");
                int floor = rs.getInt("floor");
                String genre = rs.getString("genre");
                String serialNo = rs.getString("serialNo");
                
                Object[] obj = {bookId, bookName, author, publishId, serialNo, quantity, availability, floor, genre};
                model =(DefaultTableModel) tbl_bookDetails.getModel(); // create a model which creates a row 
                model.addRow(obj);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // clear table -- used to display table when new information is added
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);
    }
    
    public void searchPublisher(){
        int publisherInput = Integer.parseInt(txt_publisher.getText());
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "select * from book where publishId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, publisherInput);
            
            //select query 
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                int bookId = rs.getInt("bookId"); // gets book id from DB
                String bookName = rs.getString("title");
                String author = rs.getString("author_name");
                int quantity = rs.getInt("quantity");
                int publishId = rs.getInt("publishId");
                int availability = rs.getInt("availability");
                int floor = rs.getInt("floor");
                String genre = rs.getString("genre");
                String serialNo = rs.getString("serialNo");
                
                Object[] obj = {bookId, bookName, author, publishId, serialNo, quantity, availability, floor, genre};
                model =(DefaultTableModel) tbl_bookDetails.getModel(); // create a model which creates a row 
                model.addRow(obj);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // fetch record
    public void searchAuthor(){
        
        String authorInput = txt_author.getText();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "select * from book where author_name like '%" + authorInput + "%' ";
            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1, authorInput);
            
            //select query 
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                int bookId = rs.getInt("bookId"); // gets book id from DB
                String bookName = rs.getString("title");
                String author = rs.getString("author_name");
                int quantity = rs.getInt("quantity");
                int publishId = rs.getInt("publishId");
                int availability = rs.getInt("availability");
                int floor = rs.getInt("floor");
                String genre = rs.getString("genre");
                String serialNo = rs.getString("serialNo");
                
                Object[] obj = {bookId, bookName, author, publishId, serialNo, quantity, availability, floor, genre};
                model =(DefaultTableModel) tbl_bookDetails.getModel(); // create a model which creates a row 
                model.addRow(obj);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    // fetch record
    public void searchFloor(){
        
        int floorInput = Integer.parseInt(txt_floor.getText());
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "select * from book where floor = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, floorInput);
            
            //select query 
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                int bookId = rs.getInt("bookId"); // gets book id from DB
                String bookName = rs.getString("title");
                String author = rs.getString("author_name");
                int quantity = rs.getInt("quantity");
                int publishId = rs.getInt("publishId");
                int availability = rs.getInt("availability");
                int floor = rs.getInt("floor");
                String genre = rs.getString("genre");
                String serialNo = rs.getString("serialNo");
                
                Object[] obj = {bookId, bookName, author, publishId, serialNo, quantity, availability, floor, genre};
                model =(DefaultTableModel) tbl_bookDetails.getModel(); // create a model which creates a row 
                model.addRow(obj);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    // fetch record
    public void searchGenre(){
        
        String genreInput = txt_genre.getText();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "select * from book where genre like '%" + genreInput + "%' ";
            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1, genreInput);
            
            //select query 
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                int bookId = rs.getInt("bookId"); // gets book id from DB
                String bookName = rs.getString("title");
                String author = rs.getString("author_name");
                int quantity = rs.getInt("quantity");
                int publishId = rs.getInt("publishId");
                int availability = rs.getInt("availability");
                int floor = rs.getInt("floor");
                String genre = rs.getString("genre");
                String serialNo = rs.getString("serialNo");
                
                Object[] obj = {bookId, bookName, author, publishId, serialNo, quantity, availability, floor, genre};
                model =(DefaultTableModel) tbl_bookDetails.getModel(); // create a model which creates a row 
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
        rSButtonHover3 = new rojerusan.RSButtonHover();
        jLabel1 = new javax.swing.JLabel();
        txt_author = new app.bolivia.swing.JCTextField();
        rSButtonHover1 = new rojerusan.RSButtonHover();
        jLabel3 = new javax.swing.JLabel();
        txt_publisher = new app.bolivia.swing.JCTextField();
        rSButtonHover2 = new rojerusan.RSButtonHover();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        txt_floor = new app.bolivia.swing.JCTextField();
        rSButtonHover4 = new rojerusan.RSButtonHover();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_genre = new app.bolivia.swing.JCTextField();
        rSButtonHover5 = new rojerusan.RSButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(234, 235, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonHover3.setBackground(new java.awt.Color(51, 51, 51));
        rSButtonHover3.setText("Search");
        rSButtonHover3.setFont(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        rSButtonHover3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 70, 20));

        jLabel1.setFont(new java.awt.Font("Shree Devanagari 714", 3, 12)); // NOI18N
        jLabel1.setText("Search by author");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 160, 20));

        txt_author.setBackground(new java.awt.Color(235, 235, 235));
        txt_author.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_author.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        txt_author.setPlaceholder("Enter author name...");
        txt_author.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_authorFocusLost(evt);
            }
        });
        jPanel1.add(txt_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 200, 30));

        rSButtonHover1.setBackground(new java.awt.Color(51, 51, 51));
        rSButtonHover1.setText("Search");
        rSButtonHover1.setFont(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 70, 20));

        jLabel3.setFont(new java.awt.Font("Shree Devanagari 714", 3, 12)); // NOI18N
        jLabel3.setText("Search by publisher");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 160, 20));

        txt_publisher.setBackground(new java.awt.Color(235, 235, 235));
        txt_publisher.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_publisher.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        txt_publisher.setPlaceholder("Enter publisher...");
        txt_publisher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_publisherFocusLost(evt);
            }
        });
        jPanel1.add(txt_publisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 200, 30));

        rSButtonHover2.setBackground(new java.awt.Color(204, 0, 51));
        rSButtonHover2.setText("Clear");
        rSButtonHover2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        rSButtonHover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 70, 20));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 18)); // NOI18N
        jLabel2.setText("LMS - View & Search Books");
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

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Title", "Author", "Publisher", "ISBN", "Quantity", "Availability", "Floor", "Genre"
            }
        ));
        tbl_bookDetails.setToolTipText("");
        tbl_bookDetails.setAltoHead(30);
        tbl_bookDetails.setColorBackgoundHead(new java.awt.Color(235, 235, 235));
        tbl_bookDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookDetails.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl_bookDetails.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl_bookDetails.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tbl_bookDetails.setColorSelBackgound(new java.awt.Color(204, 204, 204));
        tbl_bookDetails.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        tbl_bookDetails.setFuenteFilas(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        tbl_bookDetails.setFuenteFilasSelect(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        tbl_bookDetails.setFuenteHead(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        tbl_bookDetails.setRowHeight(30);
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_bookDetails);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 910, 410));

        txt_floor.setBackground(new java.awt.Color(235, 235, 235));
        txt_floor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_floor.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        txt_floor.setPlaceholder("Enter floor no...");
        txt_floor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_floorFocusLost(evt);
            }
        });
        jPanel1.add(txt_floor, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 200, 30));

        rSButtonHover4.setBackground(new java.awt.Color(51, 51, 51));
        rSButtonHover4.setText("Search");
        rSButtonHover4.setFont(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        rSButtonHover4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, 70, 20));

        jLabel5.setFont(new java.awt.Font("Shree Devanagari 714", 3, 12)); // NOI18N
        jLabel5.setText("Search by floor");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 160, 20));

        jLabel6.setFont(new java.awt.Font("Shree Devanagari 714", 3, 12)); // NOI18N
        jLabel6.setText("Search by genre");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 160, 20));

        txt_genre.setBackground(new java.awt.Color(235, 235, 235));
        txt_genre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_genre.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        txt_genre.setPlaceholder("Enter genre no...");
        txt_genre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_genreFocusLost(evt);
            }
        });
        txt_genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_genreActionPerformed(evt);
            }
        });
        jPanel1.add(txt_genre, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 200, 30));

        rSButtonHover5.setBackground(new java.awt.Color(51, 51, 51));
        rSButtonHover5.setText("Search");
        rSButtonHover5.setFont(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        rSButtonHover5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover5ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover5, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, 70, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        setSize(new java.awt.Dimension(1000, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover4ActionPerformed
        // TODO add your handling code here:
        String floorInput = txt_floor.getText();
        if (floorInput.equals("")){ // or not numeric
            JOptionPane.showMessageDialog(this, "Please enter valid floor number!");
        } else {
            clearTable();
            searchFloor();
        }
    }//GEN-LAST:event_rSButtonHover4ActionPerformed

    private void txt_floorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_floorFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_floorFocusLost

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked

    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
        // TODO add your handling code here:
        clearTable();
        displayBookToTable();
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void txt_publisherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_publisherFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_publisherFocusLost

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        //    if (txt_author != null){
            //            clearTable(); // removes duplication
            //            searchAuthor();
            //        } else {
            //            JOptionPane.showMessageDialog(this, "Input data!");
            //        }
        String authorInput = txt_author.getText();
        if (authorInput.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter author name!");
        } else {
            clearTable();
            searchAuthor();
        }
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void txt_authorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_authorFocusLost

    }//GEN-LAST:event_txt_authorFocusLost

    private void rSButtonHover3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover3ActionPerformed
        // TODO add your handling code here:
        String publisherInput = txt_publisher.getText();
        if (publisherInput.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter publisher name!");
        } else {
            clearTable();
            searchPublisher();
        }
    }//GEN-LAST:event_rSButtonHover3ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Home home = new Home(); // open new homepage page
        home.setVisible(true);
        dispose(); // close current page
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txt_genreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_genreFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_genreFocusLost

    private void rSButtonHover5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover5ActionPerformed
        // TODO add your handling code here:
        String genreInput = txt_genre.getText();
        if (genreInput.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter genre!");
        } else {
            clearTable();
            searchGenre();
        }
    }//GEN-LAST:event_rSButtonHover5ActionPerformed

    private void txt_genreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_genreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_genreActionPerformed

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
            java.util.logging.Logger.getLogger(ViewBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSButtonHover rSButtonHover1;
    private rojerusan.RSButtonHover rSButtonHover2;
    private rojerusan.RSButtonHover rSButtonHover3;
    private rojerusan.RSButtonHover rSButtonHover4;
    private rojerusan.RSButtonHover rSButtonHover5;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private app.bolivia.swing.JCTextField txt_author;
    private app.bolivia.swing.JCTextField txt_floor;
    private app.bolivia.swing.JCTextField txt_genre;
    private app.bolivia.swing.JCTextField txt_publisher;
    // End of variables declaration//GEN-END:variables
}

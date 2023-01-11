/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author alyshapm
 */
public class AddBooks extends javax.swing.JFrame {

    /**
     * Creates new form AddBooks
     */
    
    String title, author, genre, regisId, serialNo, regisDate;
    int quantity, shelf, floor, avail, bookId, publishId;
    DefaultTableModel model;
    
    public AddBooks() {
        initComponents();
        displayBookToTable();
    }
    
    // Displays the book data into a table view
    public void displayBookToTable(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book");
            
            while(rs.next()){
                // Gets values from the database
                int bookId = rs.getInt("bookId"); 
                String title = rs.getString("title");
                String author = rs.getString("author_name");
                String serialNo = rs.getString("serialNo");
                int publisher = rs.getInt("publishId");
                int quantity = rs.getInt("quantity");
                String regisDate = rs.getString("regisDate");
                String regisId = rs.getString("adminId");
                int avail = rs.getInt("availability");
                int shelf = rs.getInt("shelfNo");
                int floor = rs.getInt("floor");
                String genre = rs.getString("genre");
                
                Object[] obj = {bookId, title, author, serialNo, publisher, quantity, regisDate, regisId, avail, shelf, floor, genre};
                model =(DefaultTableModel) tbl_bookDetails.getModel(); // create a model which creates a row 
                model.addRow(obj);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // Insert a book into the database
    public boolean insertBook(){
        boolean isAdded = false;
        bookId = Integer.parseInt(txt_bookId.getText());
        title = txt_bookName.getText();
        author = txt_authorName.getText();
        quantity = Integer.parseInt(txt_quantity.getText());
        serialNo = txt_serial.getText();
        publishId = Integer.parseInt(txt_publisher.getText());
        regisId = txt_regisby.getText();
        shelf = Integer.parseInt(txt_shelf.getText());
        floor = Integer.parseInt(txt_floor.getText());
        genre = txt_genre.getText();
        avail = quantity; // availability should be the same as inputted quantity when book is newly inserted
        
        // get today's date for registration date
        long l = System.currentTimeMillis();
        Date today = new Date(l);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "insert into book values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, title);
            pst.setString(3, author);
            pst.setInt(4, publishId);
            pst.setString(5, serialNo);
            pst.setInt(6, quantity);
            pst.setDate(7, today);
            pst.setString(8, regisId);
            pst.setInt(9, avail);
            pst.setInt(10, shelf);
            pst.setInt(11, floor);
            pst.setString(12, genre);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isAdded;
    }
    
    // update book details
    public boolean updateBook(){
        boolean isUpdated = false;
        bookId = Integer.parseInt(txt_bookId.getText());
        title = txt_bookName.getText();
        author = txt_authorName.getText();
        quantity = Integer.parseInt(txt_quantity.getText());
        serialNo = txt_serial.getText();
        publishId = Integer.parseInt(txt_publisher.getText());
        regisId = txt_regisby.getText();
        shelf = Integer.parseInt(txt_shelf.getText());
        floor = Integer.parseInt(txt_floor.getText());
        genre = txt_genre.getText();
        avail = quantity; // availability should be the same as inputted quantity when book is newly inserted
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "update book set title = ?, author_name = ?, serialNo = ?, publishId = ?, quantity = ?, adminId = ?, availability = ?, shelfNo = ?, floor = ?, genre = ? where bookId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setString(3, serialNo);
            pst.setInt(4, publishId);
            pst.setInt(5, quantity);
            pst.setString(6, regisId);
            pst.setInt(7, avail);
            pst.setInt(8, shelf);
            pst.setInt(9, floor);
            pst.setString(10, genre);
            pst.setInt(11, bookId);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isUpdated = true;
            } else {
                isUpdated = false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isUpdated;
    }
    
    // delete table
    public boolean deleteBook(){
        boolean isDeleted = false;
        bookId = Integer.parseInt(txt_bookId.getText());
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "delete from book where bookId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
    
    // clear table -- used to display table when new information is added
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);
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
        jLabel4 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_bookName = new app.bolivia.swing.JCTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_authorName = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_quantity = new app.bolivia.swing.JCTextField();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel8 = new javax.swing.JLabel();
        txt_serial = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_publisher = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_regisby = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_shelf = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_floor = new app.bolivia.swing.JCTextField();
        txt_genre = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(235, 235, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 18)); // NOI18N
        jLabel2.setText("LMS - Add Books");
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

        jLabel4.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel4.setText("Book ID");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        txt_bookId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_bookId.setPlaceholder("Enter book ID...");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 210, 30));

        jLabel5.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel5.setText("Title");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        txt_bookName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_bookName.setPlaceholder("Enter book name...");
        txt_bookName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookNameFocusLost(evt);
            }
        });
        jPanel1.add(txt_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 210, 30));

        jLabel6.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel6.setText("Author");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        txt_authorName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_authorName.setPlaceholder("Enter author name...");
        txt_authorName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_authorNameFocusLost(evt);
            }
        });
        jPanel1.add(txt_authorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 210, 30));

        jLabel7.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel7.setText("Quantity");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        txt_quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_quantity.setPlaceholder("Enter quantity...");
        txt_quantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_quantityFocusLost(evt);
            }
        });
        jPanel1.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 210, 30));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.setLabel("Add");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 500, 120, 50));

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle3.setText("UPDATE");
        rSMaterialButtonRectangle3.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 500, 120, 50));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle1.setText("delete");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 500, 120, 50));

        jLabel8.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel8.setText("Serial no.");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        txt_serial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_serial.setPlaceholder("Enter serial no...");
        txt_serial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_serialFocusLost(evt);
            }
        });
        jPanel1.add(txt_serial, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 210, 30));

        jLabel9.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel9.setText("Publisher");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));

        txt_publisher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_publisher.setPlaceholder("Enter publisher...");
        txt_publisher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_publisherFocusLost(evt);
            }
        });
        jPanel1.add(txt_publisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 210, 30));

        jLabel10.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel10.setText("Registerer ID");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        txt_regisby.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_regisby.setPlaceholder("Enter registerer ID...");
        txt_regisby.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_regisbyFocusLost(evt);
            }
        });
        jPanel1.add(txt_regisby, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 210, 30));

        jLabel11.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel11.setText("Shelf");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, -1));

        txt_shelf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_shelf.setPlaceholder("Enter shelf...");
        txt_shelf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_shelfFocusLost(evt);
            }
        });
        jPanel1.add(txt_shelf, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 210, 30));

        jLabel12.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel12.setText("Floor");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, -1, -1));

        txt_floor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_floor.setPlaceholder("Enter floor...");
        txt_floor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_floorFocusLost(evt);
            }
        });
        jPanel1.add(txt_floor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 210, 30));

        txt_genre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_genre.setPlaceholder("Enter genre...");
        txt_genre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_genreFocusLost(evt);
            }
        });
        jPanel1.add(txt_genre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 210, 30));

        jLabel13.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        jLabel13.setText("Genre");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));

        jScrollPane2.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Title", "Author", "Serial no", "Publisher", "Quantity", "Regis date", "Admin ID", "Availability", "Shelf", "Floor", "Genre"
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
        tbl_bookDetails.setFont(new java.awt.Font("Shree Devanagari 714", 0, 10)); // NOI18N
        tbl_bookDetails.setFuenteFilas(new java.awt.Font("Shree Devanagari 714", 0, 10)); // NOI18N
        tbl_bookDetails.setFuenteFilasSelect(new java.awt.Font("Shree Devanagari 714", 1, 10)); // NOI18N
        tbl_bookDetails.setFuenteHead(new java.awt.Font("Shree Devanagari 714", 1, 10)); // NOI18N
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_bookDetails);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 600, 390));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        setSize(new java.awt.Dimension(1000, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        //        if (checkDuplicateUser() == true) { // if username already exists, prompt error message
            //            JOptionPane.showMessageDialog(this, "Username already exists");
            //        }
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookNameFocusLost

    private void txt_authorNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_authorNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_authorNameFocusLost

    private void txt_quantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_quantityFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityFocusLost

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        if (insertBook() == true){
            JOptionPane.showMessageDialog(this, "Book Added");
            clearTable(); // avoids duplication of data when displayBookToTable is called
            displayBookToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
        if (updateBook() == true){
            JOptionPane.showMessageDialog(this, "Book Updated");
            clearTable(); // avoids duplication of data when displayBookToTable is called
            displayBookToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Update failed");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        if (deleteBook() == true){
            JOptionPane.showMessageDialog(this, "Book deleted");
            clearTable(); // avoids duplication of data when displayBookToTable is called
            displayBookToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed deletion");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void txt_serialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_serialFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_serialFocusLost

    private void txt_publisherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_publisherFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_publisherFocusLost

    private void txt_regisbyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_regisbyFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_regisbyFocusLost

    private void txt_shelfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_shelfFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_shelfFocusLost

    private void txt_floorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_floorFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_floorFocusLost

    private void txt_genreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_genreFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_genreFocusLost

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
        int rowNo = tbl_bookDetails.getSelectedRow();
        TableModel model = tbl_bookDetails.getModel();
        
        txt_bookId.setText(model.getValueAt(rowNo, 0).toString());
        txt_bookName.setText(model.getValueAt(rowNo, 1).toString());
        txt_authorName.setText(model.getValueAt(rowNo, 2).toString());
        txt_serial.setText(model.getValueAt(rowNo, 3).toString());
        txt_publisher.setText(model.getValueAt(rowNo, 4).toString());
        txt_quantity.setText(model.getValueAt(rowNo, 5).toString());
        txt_regisby.setText(model.getValueAt(rowNo, 7).toString());
        txt_shelf.setText(model.getValueAt(rowNo, 9).toString());
        txt_floor.setText(model.getValueAt(rowNo, 10).toString());
        txt_genre.setText(model.getValueAt(rowNo, 11).toString());
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

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
            java.util.logging.Logger.getLogger(AddBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private app.bolivia.swing.JCTextField txt_authorName;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_bookName;
    private app.bolivia.swing.JCTextField txt_floor;
    private app.bolivia.swing.JCTextField txt_genre;
    private app.bolivia.swing.JCTextField txt_publisher;
    private app.bolivia.swing.JCTextField txt_quantity;
    private app.bolivia.swing.JCTextField txt_regisby;
    private app.bolivia.swing.JCTextField txt_serial;
    private app.bolivia.swing.JCTextField txt_shelf;
    // End of variables declaration//GEN-END:variables
}

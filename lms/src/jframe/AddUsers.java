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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author alyshapm
 */
public class AddUsers extends javax.swing.JFrame {

    /**
     * Creates new form AddUsers
     */
    DefaultTableModel model;
    
    String fName, lName, email, address, userId;

    public AddUsers() {
        initComponents();
        displayUsersToTable();
    }
    
    // display book data into table
    public void displayUsersToTable(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user");
            
            while(rs.next()){
                String userId = rs.getString("userId"); // gets book id from DB
                String fName = rs.getString("userFname").substring(0, 1).toUpperCase() + rs.getString("userFname").substring(1); // Makes first letter capital
                String lName = rs.getString("userLname").substring(0, 1).toUpperCase() + rs.getString("userLname").substring(1); // Makes first letter capital
                String birthday = rs.getString("birthday");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String regisdate = rs.getString("regisDate");
                
                Object[] obj = {userId, fName, lName, birthday, email, address, regisdate};
                model =(DefaultTableModel) tbl_studentDetails.getModel(); // create a model which creates a row 
                model.addRow(obj);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // add student to table
    public boolean addUser(){
        boolean isAdded = false;
        userId = txt_studentId.getText();
        fName = txt_fname.getText();
        lName = txt_lname.getText();
        email = txt_email.getText();
        address = txt_address.getText();
     
        Date uBirthday = date_birthday.getDatoFecha();
        long a = uBirthday.getTime();
        java.sql.Date birthday = new java.sql.Date(a);
        
        // get today's date for registration date
        long l = System.currentTimeMillis();
        java.sql.Date today = new java.sql.Date(l);
       
//        major = combo_major.getSelectedItem().toString();
//        semester = Integer.parseInt(combo_sem.getSelectedItem().toString());
    
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            pst.setString(2, fName);
            pst.setString(3, lName);
            pst.setDate(4, birthday);
            pst.setString(5, email);
            pst.setString(6, address);
            pst.setDate(7, today);
            
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
    
    // update student details
    public boolean updateUser(){
        boolean isUpdated = false;
        userId = txt_studentId.getText();
        fName = txt_fname.getText();
        lName = txt_lname.getText();
        email = txt_email.getText();
        address = txt_address.getText();
     
        Date uBirthday = date_birthday.getDatoFecha();
        long a = uBirthday.getTime();
        java.sql.Date birthday = new java.sql.Date(a);

//        major = combo_major.getSelectedItem().toString();
//        semester = Integer.parseInt(combo_sem.getSelectedItem().toString());
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "update user set userFname = ?, userLname = ?, birthday = ?, email = ?, address = ? where userId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, fName);
            pst.setString(2, lName);
            pst.setDate(3, birthday);
            pst.setString(4, email);
            pst.setString(5, address);
            pst.setString(6, userId);
            
            
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
    
    // delete student
    public boolean deleteUser(){
        boolean isDeleted = false;
        userId = txt_studentId.getText();
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "delete from user where userId = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            
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
        DefaultTableModel model = (DefaultTableModel) tbl_studentDetails.getModel();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_studentDetails = new rojeru_san.complementos.RSTableMetro();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        txt_fname = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_lname = new app.bolivia.swing.JCTextField();
        date_birthday = new rojeru_san.componentes.RSDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_email = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_address = new app.bolivia.swing.JCTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(235, 235, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 18)); // NOI18N
        jLabel2.setText("LMS - Add User");
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

        jScrollPane2.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N

        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "First name", "Last name", "Birthday", "Email", "Address", "Regis date"
            }
        ));
        tbl_studentDetails.setAltoHead(30);
        tbl_studentDetails.setColorBackgoundHead(new java.awt.Color(235, 235, 235));
        tbl_studentDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_studentDetails.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl_studentDetails.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl_studentDetails.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tbl_studentDetails.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        tbl_studentDetails.setFuenteFilas(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        tbl_studentDetails.setFuenteFilasSelect(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        tbl_studentDetails.setFuenteHead(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        tbl_studentDetails.setRowHeight(40);
        tbl_studentDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_studentDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_studentDetails);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 560, 380));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle1.setText("delete");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 510, 120, 50));

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle3.setText("UPDATE");
        rSMaterialButtonRectangle3.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 510, 120, 50));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Shree Devanagari 714", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.setLabel("Add");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, 120, 50));

        txt_fname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_fname.setPlaceholder("Enter name...");
        txt_fname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_fnameFocusLost(evt);
            }
        });
        jPanel1.add(txt_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 220, 30));

        jLabel9.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel9.setText("First name");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        txt_studentId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_studentId.setPlaceholder("Enter user ID...");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 220, 30));

        jLabel4.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel4.setText("User ID");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jLabel10.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel10.setText("Birthday");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        txt_lname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_lname.setPlaceholder("Enter name...");
        txt_lname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_lnameFocusLost(evt);
            }
        });
        jPanel1.add(txt_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 220, 30));

        date_birthday.setColorBackground(new java.awt.Color(102, 102, 102));
        date_birthday.setColorButtonHover(new java.awt.Color(153, 153, 153));
        date_birthday.setColorForeground(new java.awt.Color(0, 0, 0));
        date_birthday.setFont(new java.awt.Font("Sinhala Sangam MN", 0, 12)); // NOI18N
        date_birthday.setFuente(new java.awt.Font("Shree Devanagari 714", 1, 12)); // NOI18N
        date_birthday.setPlaceholder("Select date");
        jPanel1.add(date_birthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 220, 30));

        jLabel11.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel11.setText("Last name");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel12.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel12.setText("Email");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        txt_email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_email.setPlaceholder("Enter email...");
        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_emailFocusLost(evt);
            }
        });
        jPanel1.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 220, 30));

        jLabel13.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        jLabel13.setText("Address");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        txt_address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_address.setPlaceholder("Enter address...");
        txt_address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_addressFocusLost(evt);
            }
        });
        jPanel1.add(txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 220, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        setSize(new java.awt.Dimension(1000, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_studentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_studentDetailsMouseClicked
        int rowNo = tbl_studentDetails.getSelectedRow();
        TableModel model = tbl_studentDetails.getModel();

        txt_studentId.setText(model.getValueAt(rowNo, 0).toString());
        txt_fname.setText(model.getValueAt(rowNo, 1).toString());
        txt_lname.setText(model.getValueAt(rowNo, 2).toString());
//        date_birthday.setDatoFecha(model.getValueAt(rowNo, 4).toString());
        txt_email.setText(model.getValueAt(rowNo, 4).toString());
        txt_address.setText(model.getValueAt(rowNo, 5).toString());

    }//GEN-LAST:event_tbl_studentDetailsMouseClicked

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        if (deleteUser() == true){
            JOptionPane.showMessageDialog(this, "User deleted");
            clearTable(); // avoids duplication of data when displayUsersToTable is called
            displayUsersToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed deletion");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
        if (updateUser() == true){
            JOptionPane.showMessageDialog(this, "User Updated");
            clearTable(); // avoids duplication of data when displayUsersToTable is called
            displayUsersToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Update failed");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        if (addUser() == true){
            JOptionPane.showMessageDialog(this, "User Added");
            clearTable(); // avoids duplication of data when displayUsersToTable is called
            displayUsersToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void txt_fnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_fnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fnameFocusLost

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        //        if (checkDuplicateUser() == true) { // if username already exists, prompt error message
            //            JOptionPane.showMessageDialog(this, "Username already exists");
            //        }
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_lnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_lnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lnameFocusLost

    private void txt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailFocusLost

    private void txt_addressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_addressFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_addressFocusLost

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
            java.util.logging.Logger.getLogger(AddUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_birthday;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private rojeru_san.complementos.RSTableMetro tbl_studentDetails;
    private app.bolivia.swing.JCTextField txt_address;
    private app.bolivia.swing.JCTextField txt_email;
    private app.bolivia.swing.JCTextField txt_fname;
    private app.bolivia.swing.JCTextField txt_lname;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}

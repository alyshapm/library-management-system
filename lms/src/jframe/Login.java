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
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
         initComponents();
    }
    
    // validation
    public boolean validateLogin(){
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        
        if (name.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter username");
            return false;
        }
        if (pwd.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter password");
            return false;
        }
        
        return true;
    }
    
    // checks if account exists
    public void login(){
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            
            PreparedStatement pst = con.prepareStatement("select * from admin where username = ? and password = ?");
            
            pst.setString(1, name);
            pst.setString(2, pwd);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()){ // if data exists in DB, open homepage
                String typeLogin = rs.getString("type");
                if (typeLogin.equals("full") ){
                    Home home = new Home();
                    home.setVisible(true);
                } else {
                    HomePart home2 = new HomePart();
                    home2.setVisible(true);
                }
                JOptionPane.showMessageDialog(this, "Login successful");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username or password");
            }
        } catch (Exception e) {
            
        }
    }
    
    //method to insert values ionto users' table
    public void insertSignupDetails(){
        String uname = txt_uname.getText();
        String pwd = txt_pword.getText();
        String fname = txt_fname.getText();
        String lname = txt_lname.getText();
        String email = txt_email.getText();
        int phone = Integer.parseInt(txt_phone.getText());
        String address = txt_address.getText();
        
//        String department = combo_department.getSelectedItem().toString();
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            String sql = "insert into admin(adminfname, adminelname, email, phoneno, username, password, address) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, email);
            pst.setInt(4, phone);
            pst.setString(5, uname);
            pst.setString(6, pwd);
            pst.setString(7, address);
            
            int updatedRowCount = pst.executeUpdate();
            
            if (updatedRowCount > 0){
                JOptionPane.showMessageDialog(this, "Recorded Inserted Successfully");
                Login page = new Login();
                page.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Record Insertion Failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // validation
    public boolean validateSignup(){
        String uname = txt_uname.getText();
        String pwd = txt_pword.getText();
        String fname = txt_fname.getText();
        String lname = txt_lname.getText();
        String email = txt_email.getText();
        String address = txt_address.getText();
        
        if (uname.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter username");
            return false;
        } 
        if (pwd.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter password");
            return false;
        } 
        if (email.equals("") || !email.matches("^.+@.+\\..+$")){
            JOptionPane.showMessageDialog(this, "Please enter a valid email");
            return false;
        } 
        if (fname.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter first name");
            return false;
        } 
        if (lname.equals("")){
            JOptionPane.showMessageDialog(this, "Please enter last name");
            return false;
        } 
        if (address.equals("") ){
            JOptionPane.showMessageDialog(this, "Please enter address");
            return false;
        } 
        return true;
    }
    
    // check duplicate usernames
    public boolean checkDuplicateUser(){
        String name = txt_uname.getText();
        boolean isExist = false;
        
        try {
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
           
           PreparedStatement pst = con.prepareStatement("select * from admin where username = ?"); // checks if name entered by user exists in the DB
           pst.setString(1, name);
           ResultSet rs = pst.executeQuery();
           if (rs.next()){
               isExist = true;
           } else {
               isExist = false;
           }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isExist;
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
        jLabel1 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        txt_password = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_username = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_fname = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_lname = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_email = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_address = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_phone = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_uname = new app.bolivia.swing.JCTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_pword = new app.bolivia.swing.JCTextField();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Shree Devanagari 714", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SIGN UP PAGE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 150, 40));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(204, 204, 204));
        rSMaterialButtonRectangle1.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonRectangle1.setActionCommand("Sign up");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        rSMaterialButtonRectangle1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSMaterialButtonRectangle1.setLabel("sign up");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 500, 170, 50));

        txt_password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_password.setPlaceholder("Enter password...");
        jPanel1.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 350, 30));

        jLabel8.setFont(new java.awt.Font("Shree Devanagari 714", 0, 16)); // NOI18N
        jLabel8.setText("Password");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        txt_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txt_username.setPlaceholder("Enter username...");
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        jPanel1.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 350, 30));

        jLabel4.setFont(new java.awt.Font("Shree Devanagari 714", 0, 16)); // NOI18N
        jLabel4.setText("Username");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel9.setText("First name");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, -1, 20));

        txt_fname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_fname.setPlaceholder("Enter name...");
        txt_fname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_fnameFocusLost(evt);
            }
        });
        jPanel1.add(txt_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 220, 30));

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel11.setText("Last name");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, -1, 20));

        txt_lname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_lname.setPlaceholder("Enter name...");
        txt_lname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_lnameFocusLost(evt);
            }
        });
        jPanel1.add(txt_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 220, 30));

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel12.setText("Email");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, -1, 20));

        txt_email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_email.setPlaceholder("Enter email...");
        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_emailFocusLost(evt);
            }
        });
        jPanel1.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 290, 220, 30));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel13.setText("Address");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, -1, 20));

        txt_address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_address.setPlaceholder("Enter address...");
        txt_address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_addressFocusLost(evt);
            }
        });
        jPanel1.add(txt_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 220, 30));

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel14.setText("Phone");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 340, -1, 20));

        txt_phone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_phone.setPlaceholder("Enter phone number...");
        txt_phone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_phoneFocusLost(evt);
            }
        });
        jPanel1.add(txt_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 220, 30));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel10.setText("Username");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, -1, 20));

        txt_uname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_uname.setPlaceholder("Enter username...");
        txt_uname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_unameFocusLost(evt);
            }
        });
        jPanel1.add(txt_uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 220, 30));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel15.setText("Password");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, -1, 20));

        txt_pword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_pword.setPlaceholder("Enter password...");
        txt_pword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_pwordFocusLost(evt);
            }
        });
        jPanel1.add(txt_pword, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, 220, 30));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(204, 204, 204));
        rSMaterialButtonRectangle2.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonRectangle2.setText("Login");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        rSMaterialButtonRectangle2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 170, 50));

        jLabel2.setFont(new java.awt.Font("Shree Devanagari 714", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LOGIN PAGE");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 150, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        setSize(new java.awt.Dimension(1000, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed

        if (validateSignup() == true) {
            if (checkDuplicateUser() == false) {
                insertSignupDetails();
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists");
            }
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost

    }//GEN-LAST:event_txt_usernameFocusLost

    private void txt_fnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_fnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fnameFocusLost

    private void txt_lnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_lnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lnameFocusLost

    private void txt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailFocusLost

    private void txt_addressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_addressFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_addressFocusLost

    private void txt_phoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_phoneFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_phoneFocusLost

    private void txt_unameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_unameFocusLost
        // TODO add your handling code here:
        if (checkDuplicateUser() == true) { // if username already exists, prompt error message
            JOptionPane.showMessageDialog(this, "Username already exists");
        }
    }//GEN-LAST:event_txt_unameFocusLost

    private void txt_pwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pwordFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pwordFocusLost

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
        if (validateLogin()){
            login();
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private app.bolivia.swing.JCTextField txt_address;
    private app.bolivia.swing.JCTextField txt_email;
    private app.bolivia.swing.JCTextField txt_fname;
    private app.bolivia.swing.JCTextField txt_lname;
    private app.bolivia.swing.JCTextField txt_password;
    private app.bolivia.swing.JCTextField txt_phone;
    private app.bolivia.swing.JCTextField txt_pword;
    private app.bolivia.swing.JCTextField txt_uname;
    private app.bolivia.swing.JCTextField txt_username;
    // End of variables declaration//GEN-END:variables
}

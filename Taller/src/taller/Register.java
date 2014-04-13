/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import security.AES;

/**
 *
 * @author alberto
 */
public class Register extends javax.swing.JFrame {

    private Comunication comunication;
    /**
     * Creates new form Register
     */
    public Register() {
        initComponents();
        comunication = Comunication.getInstance();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfCif = new javax.swing.JTextField();
        tfAddress = new javax.swing.JTextField();
        tfSurname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbSuranme = new javax.swing.JLabel();
        tfPass = new javax.swing.JPasswordField();
        lbDate = new javax.swing.JLabel();
        btCancel = new javax.swing.JButton();
        btRegister = new javax.swing.JButton();
        lbError = new javax.swing.JLabel();
        cbTaller = new javax.swing.JCheckBox();
        tfMonth = new javax.swing.JTextField();
        tfYear = new javax.swing.JTextField();
        tfDay = new javax.swing.JTextField();
        lbCIFError = new javax.swing.JLabel();
        lbDateError = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lbEmailError = new javax.swing.JLabel();
        lbPassError = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mainPane.setBackground(new java.awt.Color(245, 228, 179));

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText("Registro de nuevo usuario");

        tfName.setBackground(new java.awt.Color(252, 247, 232));

        tfCif.setBackground(new java.awt.Color(252, 247, 232));

        tfAddress.setBackground(new java.awt.Color(252, 247, 232));

        tfSurname.setBackground(new java.awt.Color(252, 247, 232));

        jLabel2.setText("NIF/CIF");

        jLabel3.setText("Nombre");

        jLabel4.setText("Direccion");

        jLabel5.setText("Password*");

        lbSuranme.setText("Apellidos");

        tfPass.setBackground(new java.awt.Color(252, 247, 232));

        lbDate.setText("Fecha nac.");

        btCancel.setBackground(new java.awt.Color(252, 247, 232));
        btCancel.setText("Cancelar");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        btRegister.setBackground(new java.awt.Color(252, 247, 232));
        btRegister.setText("Registrar");
        btRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegisterActionPerformed(evt);
            }
        });

        lbError.setForeground(new java.awt.Color(255, 0, 0));

        cbTaller.setText("Registro de taller");
        cbTaller.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbTallerStateChanged(evt);
            }
        });

        tfMonth.setBackground(new java.awt.Color(252, 247, 232));
        tfMonth.setText("mm");
        tfMonth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfMonthFocusGained(evt);
            }
        });

        tfYear.setBackground(new java.awt.Color(252, 247, 232));
        tfYear.setText("aaaa");
        tfYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfYearFocusGained(evt);
            }
        });

        tfDay.setBackground(new java.awt.Color(252, 247, 232));
        tfDay.setText("dd");
        tfDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfDayFocusGained(evt);
            }
        });

        lbCIFError.setForeground(new java.awt.Color(255, 0, 0));

        lbDateError.setForeground(new java.awt.Color(233, 26, 26));
        lbDateError.setText("  ");

        jLabel6.setText("Email");

        lbEmailError.setForeground(new java.awt.Color(223, 36, 36));

        lbPassError.setForeground(new java.awt.Color(255, 0, 0));

        jLabel7.setText("(*) La contraseña ha de contener mayúsculas, mínusculas, números");

        jLabel8.setText("y tener al menos 8 caracteres.");

        javax.swing.GroupLayout mainPaneLayout = new javax.swing.GroupLayout(mainPane);
        mainPane.setLayout(mainPaneLayout);
        mainPaneLayout.setHorizontalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(lbSuranme)
                                    .addComponent(lbDate))
                                .addGap(18, 18, 18)
                                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(mainPaneLayout.createSequentialGroup()
                                        .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfName)
                                    .addComponent(tfCif)
                                    .addComponent(tfAddress)
                                    .addComponent(tfSurname)
                                    .addComponent(tfPass, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(tfEmail)))
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(btRegister)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btCancel))
                            .addComponent(jLabel6)
                            .addComponent(cbTaller)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCIFError, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPassError))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(mainPaneLayout.createSequentialGroup()
                                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDateError, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbEmailError)
                                    .addComponent(lbError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(68, 68, 68))))))
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPaneLayout.setVerticalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbTaller)
                .addGap(14, 14, 14)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(lbCIFError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPassError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSuranme))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDate)
                    .addComponent(tfMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDateError))
                .addGap(18, 18, 18)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmailError))
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbError, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btRegister)
                            .addComponent(btCancel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.PREFERRED_SIZE, 491, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegisterActionPerformed
        // TODO add your handling code here:
        String name=tfName.getText();
        String cif=null;
        String address=tfAddress.getText();
        String pass=tfPass.getText();
        String surname=null;
        String dob=null;
        String email=tfEmail.getText();
        boolean correct=true, dateCorrect=true;

        lbError.setText("");
        lbDateError.setText("");
        lbCIFError.setText("");
        lbEmailError.setText("");
                
        if(name==null || name.equals("") || pass==null || pass.equals("") || address==null || address.equals("") || email==null || email.equals("")) {
            lbError.setText("No puede haber vacíos");
            correct=false;
        }
            
        cif=tfCif.getText();

        if(cif!=null && !cif.isEmpty() && !validateCif(cif,cbTaller.isSelected())) {
            lbCIFError.setText("No es válido");
            correct=false;
        }
        
        if(!validatePass(pass)) {
            lbPassError.setText("No es válida");
            correct=false;
        }
            
        
        if(email!=null && !email.isEmpty() && !email.matches("[a-zA-Z0-9]+\\@[a-z]+\\.[a-z]+")) {
             lbEmailError.setText("Formato incorrecto");
             dateCorrect=false;
        }
        
        if(!cbTaller.isSelected()) {
            surname=tfSurname.getText();

            String day=tfDay.getText(), month=tfMonth.getText(), year=tfYear.getText();

            if(!day.matches("[0-3][0-9]")) {
                lbDateError.setText("Fecha incorrecta");
                dateCorrect=false;
            }

            if(!month.matches("[0-1][0-9]")) {
                lbDateError.setText("Fecha incorrecta");
                dateCorrect=false;
            }

            if(!year.matches("[0-9]{4}")) {
                lbDateError.setText("Fecha incorrecta");
                dateCorrect=false;
            }

            if(dateCorrect) {                   
                Date date = null;
                dob = day+"-"+month+"-"+year;

                try {
                    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    formatter.setLenient(false);
                    date = formatter.parse(dob);

                } catch (ParseException e) { 
                     lbDateError.setText("Fecha incorrecta");
                }                                                              
            }            
        }
        if(correct && dateCorrect) {
            //Pass cifrada en SHA-512
            pass=encryptPass(pass);
            
            //Se cifran los todos datos
            try{
                cif = AES.encrypt(cif, comunication.getAesKey());
                name = AES.encrypt(name, comunication.getAesKey());
                address = AES.encrypt(address, comunication.getAesKey());
                pass = AES.encrypt(pass, comunication.getAesKey());
                surname = AES.encrypt(surname, comunication.getAesKey());
                dob = AES.encrypt(dob, comunication.getAesKey());
                email = AES.encrypt(email, comunication.getAesKey());
            }
            catch(Exception ex){
                
            }
            
            
            String error=registroCli(comunication.getID(), cif,name,address,pass,surname,dob,email);

            if(error.equals("")) {
                comunication.Finish();
                Main main=new Main();
                dispose();
                main.setVisible(true);                
            }
            else
                lbError.setText(error);
        }                
    }//GEN-LAST:event_btRegisterActionPerformed

    private void cbTallerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbTallerStateChanged
        // TODO add your handling code here:
        if(cbTaller.isSelected()) {
            lbSuranme.setEnabled(false);
            tfSurname.setEnabled(false);
            lbDate.setEnabled(false);
            tfDay.setEnabled(false);
            tfMonth.setEnabled(false);
            tfYear.setEnabled(false);
        }
        else {
            lbSuranme.setEnabled(true);
            tfSurname.setEnabled(true);
            lbDate.setEnabled(true);
            tfDay.setEnabled(true);
            tfMonth.setEnabled(true);
            tfYear.setEnabled(true);
        }
            
            
    }//GEN-LAST:event_cbTallerStateChanged

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        // TODO add your handling code here:
        comunication.Finish();
        Main main=new Main();
        dispose();
        main.setVisible(true);
    }//GEN-LAST:event_btCancelActionPerformed

    private void tfDayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDayFocusGained
        // TODO add your handling code here:
        if(tfDay.getText().equals("dd"))
            tfDay.setText("");
    }//GEN-LAST:event_tfDayFocusGained

    private void tfMonthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfMonthFocusGained
        // TODO add your handling code here:
        if(tfMonth.getText().equals("mm"))
            tfMonth.setText("");        
    }//GEN-LAST:event_tfMonthFocusGained

    private void tfYearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfYearFocusGained
        // TODO add your handling code here:
        if(tfYear.getText().equals("aaaa"))
            tfYear.setText("");        
    }//GEN-LAST:event_tfYearFocusGained

    
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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btRegister;
    private javax.swing.JCheckBox cbTaller;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbCIFError;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbDateError;
    private javax.swing.JLabel lbEmailError;
    private javax.swing.JLabel lbError;
    private javax.swing.JLabel lbPassError;
    private javax.swing.JLabel lbSuranme;
    private javax.swing.JPanel mainPane;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfCif;
    private javax.swing.JTextField tfDay;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfMonth;
    private javax.swing.JTextField tfName;
    private javax.swing.JPasswordField tfPass;
    private javax.swing.JTextField tfSurname;
    private javax.swing.JTextField tfYear;
    // End of variables declaration//GEN-END:variables
     
    public boolean validateCif(String cif, boolean isTaller)
    {        
         cif = cif.toUpperCase();
         
         if (cif.length() != 9) 
             return false;
         
         String letters, letter, digits;
         
         if(isTaller) {
            letter = cif.substring(0,1);        
            letters = "ABCDEFGHKLMNPQS";            
            digits = cif.substring(1);
            
         }
         else {
             letter = cif.substring(8);            
             letters="TRWAGMYFPDXBNJZSQVHLCKE";                          
             digits = cif.substring(0, 8);
         }
         
         if (letters.indexOf(letter) == -1) 
               return false;
            
         return digits.matches("[0-9]{8}");         
    }
    
    public boolean validatePass(String pass) {         
         return pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
    }

    private static String registroCli(int id, java.lang.String nif, java.lang.String nombre, java.lang.String direccion, java.lang.String password, java.lang.String apellidos, java.lang.String fecha, java.lang.String email) {
        servicios.RegistroCliente_Service service = new servicios.RegistroCliente_Service();
        servicios.RegistroCliente port = service.getRegistroClientePort();
        return port.registroCli(id, nif, nombre, direccion, password, apellidos, fecha, email);
    }

     public static String encryptPass(String original) {
        String encrypted;
        
        try {
            MessageDigest message= MessageDigest.getInstance("SHA-512");
            byte[] encryptedBytes = message.digest(original.getBytes());

            encrypted=toHexadecimal(encryptedBytes);            
            
            encryptedBytes= message.digest(original.getBytes());
            encrypted=toHexadecimal(encryptedBytes);                        
            
            return encrypted;
        }   
        catch(NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }        
        return null;
    }
    
    private static String toHexadecimal(byte[] digest){
        String hash = "";
        
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
                hash += Integer.toHexString(b);
        }
        return hash;
    }        
}

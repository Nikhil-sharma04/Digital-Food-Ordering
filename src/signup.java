
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class signup extends javax.swing.JFrame {
int otp;
String mn;
    /**
     * Creates new form signup
     */
    public signup() {
        initComponents();
        
        mn=tfmobilenumber.getText();
        setSize(600,600);
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfmobilenumber = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        btverify = new javax.swing.JButton();
        btsubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("MOBILE VERIFICATION");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(180, 20, 140, 30);

        jLabel2.setText("OTP");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 200, 90, 30);
        getContentPane().add(tfmobilenumber);
        tfmobilenumber.setBounds(170, 80, 140, 30);

        jLabel3.setText("MOBILE NUMBER");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 70, 90, 40);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(170, 200, 140, 30);

        btverify.setText("VERIFY");
        btverify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btverifyActionPerformed(evt);
            }
        });
        getContentPane().add(btverify);
        btverify.setBounds(180, 270, 120, 30);

        btsubmit.setText("Submit");
        btsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btsubmit);
        btsubmit.setBounds(180, 140, 110, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btverifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btverifyActionPerformed
        // TODO add your handling code here:
         mn=tfmobilenumber.getText();
        signup2 obj =new signup2(mn);
        

    }//GEN-LAST:event_btverifyActionPerformed

    private void btsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsubmitActionPerformed
        // TODO add your handling code here:
        String mobilenumber=tfmobilenumber.getText();
        if(mobilenumber.equals(""))
        {
            JOptionPane.showMessageDialog(this,"mobile number cannot be empty");
        }
        else if(mobilenumber.length()!=10)
        {
            JOptionPane.showMessageDialog(this,"enter 10 digits only");
        }
        if(mobilenumber.length()==10)
        {
        otp=(int)((1000+(9999-1000))*Math.random());
        String msg="IN order to signup please verify the otp and your otp is"+otp;
        sendsms.send(mobilenumber,msg);
         JOptionPane.showMessageDialog(this,"check your mobile for otp");
         
        }
    }//GEN-LAST:event_btsubmitActionPerformed

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
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btsubmit;
    private javax.swing.JButton btverify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField tfmobilenumber;
    // End of variables declaration//GEN-END:variables
}

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
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
public class adminchangepassword extends javax.swing.JFrame {
String uname1;
    /**
     * Creates new form adminchangepassword
     */
    public adminchangepassword() {
      initComponents();
    }
   public adminchangepassword(String username1) {
         uname1 = username1;
        initComponents();
        lb4.setText("Welcome, " + uname1);
         
        setSize(600,600);
        setVisible(true);
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        p1 = new javax.swing.JPasswordField();
        p3 = new javax.swing.JPasswordField();
        p2 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb1.setText("ADMIN CHANGE PASSWORD");
        getContentPane().add(lb1);
        lb1.setBounds(95, 19, 140, 40);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(130, 80, 0, 0);
        getContentPane().add(lb4);
        lb4.setBounds(90, 74, 160, 40);

        p1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p1ActionPerformed(evt);
            }
        });
        getContentPane().add(p1);
        p1.setBounds(210, 120, 180, 20);
        getContentPane().add(p3);
        p3.setBounds(210, 210, 180, 20);

        p2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p2ActionPerformed(evt);
            }
        });
        getContentPane().add(p2);
        p2.setBounds(210, 160, 180, 20);

        jLabel2.setText("old");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 120, 14, 14);

        jLabel3.setText("new");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(130, 160, 20, 14);

        jLabel4.setText("confirm");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(120, 210, 35, 14);

        jButton1.setText("change password");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(120, 270, 150, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void p2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String o,p,c;
        o=p1.getText();
        p=p2.getText();
        c=p3.getText();
        
        if(o.equals("")||p.equals("")||c.equals(""))
        {
            JOptionPane.showMessageDialog(this, "fields cannot be empty");
        }
        else if(c.equals(p)==false)
                {
                  JOptionPane.showMessageDialog(this, "passwords dont match");
   
                }
        else
        {
         try
       {
          HttpResponse<String> res =  Unirest.get("http://127.0.0.1:8888/adminchangepassword")
                  .queryString("username",uname1)
                   .queryString("password",o)
                  .queryString("newpassword",p)
                  .asString();
          
         //System.out.println(res.getStatusText());
         
         String response=res.getBody();
         if(response.equals("success"))
         {
             JOptionPane.showMessageDialog(this,"password changed");
         }
         else {
             JOptionPane.showMessageDialog(this,"invalidoldpassword");
         }
             }
         catch(Exception ex)
                 {
                     ex.printStackTrace();
                 }            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void p1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p1ActionPerformed

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
            java.util.logging.Logger.getLogger(adminchangepassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminchangepassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminchangepassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminchangepassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminchangepassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb4;
    private javax.swing.JPasswordField p1;
    private javax.swing.JPasswordField p2;
    private javax.swing.JPasswordField p3;
    // End of variables declaration//GEN-END:variables

   
}

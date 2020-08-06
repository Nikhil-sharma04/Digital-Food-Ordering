
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class editfooditems extends javax.swing.JFrame {

    String category1;
    String description, type, price, photo, itemname1;

    /**
     * Creates new form editfooditems
     */
    public editfooditems() {

        initComponents();

    }

    public editfooditems(String cat, String item, String desp, String exp, String type) {
        initComponents();
        setSize(600, 600);
        setVisible(true);

        category1 = cat;
        itemname1 = item;
        description = desp;
        price = exp;
        this.type = type;

        lbcategory.setText(category1);
        lbitemname.setText(itemname1);
        tadescription.setText(description);
        tfprice.setText(price);
        if (type.equals("Veg")) {
            rveg.setSelected(true);
        } else {
            rnonveg.setSelected(true);
        }

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tadescription = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfprice = new javax.swing.JTextField();
        rveg = new javax.swing.JRadioButton();
        rnonveg = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        btupdate = new javax.swing.JButton();
        btbrowse = new javax.swing.JButton();
        lbitemname = new javax.swing.JLabel();
        lbcategory = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("CATEGORY");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 24, 90, 20);

        jLabel2.setText("ITEM NAME");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 60, 100, 30);

        tadescription.setColumns(20);
        tadescription.setRows(5);
        jScrollPane1.setViewportView(tadescription);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(157, 99, 166, 96);

        jLabel3.setText("PRICE");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 234, 50, 20);

        jLabel4.setText("DESCRIPTION");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 108, 70, 30);

        tfprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfpriceActionPerformed(evt);
            }
        });
        getContentPane().add(tfprice);
        tfprice.setBounds(157, 234, 160, 20);

        buttonGroup1.add(rveg);
        rveg.setText("VEG");
        getContentPane().add(rveg);
        rveg.setBounds(159, 272, 45, 20);

        buttonGroup1.add(rnonveg);
        rnonveg.setText("NONVEG");
        rnonveg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rnonvegActionPerformed(evt);
            }
        });
        getContentPane().add(rnonveg);
        rnonveg.setBounds(270, 272, 67, 23);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(397, 234, 127, 83);

        btupdate.setText("UPDATE");
        btupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btupdateActionPerformed(evt);
            }
        });
        getContentPane().add(btupdate);
        btupdate.setBounds(140, 310, 81, 23);

        btbrowse.setText("BROWSE");
        getContentPane().add(btbrowse);
        btbrowse.setBounds(280, 310, 75, 23);
        getContentPane().add(lbitemname);
        lbitemname.setBounds(160, 60, 100, 20);
        getContentPane().add(lbcategory);
        lbcategory.setBounds(160, 24, 100, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfpriceActionPerformed

    private void rnonvegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rnonvegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rnonvegActionPerformed

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btupdateActionPerformed
        // TODO add your handling code here:
        String desc = tadescription.getText();
        String pr = tfprice.getText();
        String types = rveg.isSelected() ? "Veg" : "Non-Veg";
        if (desc.equals("") || pr.equals("") || types.equals("")) {
            JOptionPane.showMessageDialog(this, "ALL FIELDS ARE MANDATORY");

        } else {
            try {
                HttpResponse<String> res = Unirest.get("http://127.0.0.1:8888/updatefooditems")
                        .queryString("category", category1)
                        .queryString("itemname", itemname1)
                        .queryString("description", desc)
                        .queryString("price", pr)
                        .queryString("foodtype", types)
                        .asString();
                String response = res.getBody();
                if (response.equals("success")) {
                    JOptionPane.showMessageDialog(this, "details updated");
                } else {
                    JOptionPane.showMessageDialog(this, "updation failed");
                }
            } catch (UnirestException ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_btupdateActionPerformed

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
            java.util.logging.Logger.getLogger(editfooditems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editfooditems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editfooditems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editfooditems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editfooditems().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbrowse;
    private javax.swing.JButton btupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbcategory;
    private javax.swing.JLabel lbitemname;
    private javax.swing.JRadioButton rnonveg;
    private javax.swing.JRadioButton rveg;
    private javax.swing.JTextArea tadescription;
    private javax.swing.JTextField tfprice;
    // End of variables declaration//GEN-END:variables
}

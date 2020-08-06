
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dell
 */
public class viewcategory extends javax.swing.JFrame {

    ArrayList<category> al = new ArrayList<>();
    categorytablemodel ctm = new categorytablemodel();

    public viewcategory() {

        initComponents();
        setSize(600, 600);
        jTable1.setModel(ctm);
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

        jLabel1 = new javax.swing.JLabel();
        cbcuisine = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("CUISINE");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 90, 110, 20);

        cbcuisine.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FRENCH", "CHINESE", "INDIAN", "SEAFOOD" }));
        getContentPane().add(cbcuisine);
        cbcuisine.setBounds(250, 90, 160, 20);

        jLabel2.setText("VIEW CATEGORIES");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(270, 40, 110, 20);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "name", "description", "photo"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 140, 520, 260);

        jButton1.setText("DELETE CATEGORY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(170, 450, 190, 23);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(460, 90, 73, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a category first");
        } else {

            String ci = al.get(jTable1.getSelectedRow()).name;
            int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Are you sure to delete?", "Delete Confirmation", JOptionPane.OK_CANCEL_OPTION);
            if (showConfirmDialog == JOptionPane.OK_OPTION) {
                try {
                    HttpResponse<String> res = Unirest.get("http://localhost:8888/deletecategory")
                            .queryString("currentcat", ci).asString();

                    String response = res.getBody();
                    if (response.equals("success")) {
                        JOptionPane.showMessageDialog(this, "item deleted");
                        loaddata();
                    } else {
                        JOptionPane.showMessageDialog(this, "item deletion failed");
                    }
                } catch (UnirestException ex) {
                    Logger.getLogger(viewcategory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loaddata();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void loaddata() {
        try {
            // TODO add your handling code here:
            al.clear();
            String cuisine = cbcuisine.getSelectedItem().toString();
            HttpResponse<String> res = Unirest.get("http://localhost:8888/getcategorybycuisine")
                    .queryString("cuisine", cuisine).asString();
            String response = res.getBody();
            StringTokenizer st = new StringTokenizer(response, "~!@");
            int n = st.countTokens();
            for (int i = 1; i <= n; i++) {
                String row = st.nextToken();
                StringTokenizer st2 = new StringTokenizer(row, "#$%");
                String name = st2.nextToken();
                String desc = st2.nextToken();
                String photo = st2.nextToken();
                category obj = new category(name, desc, photo);
                al.add(obj);
            }
            jTable1.setRowHeight(100);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer());
            ctm.fireTableDataChanged();

        } catch (UnirestException ex) {
            Logger.getLogger(viewcategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class category {

        String name, description, photo;

        public category(String name, String description, String photo) {
            this.name = name;
            this.description = description;
            this.photo = photo;
        }
    }

    class categorytablemodel extends AbstractTableModel {

        String[] columns = {"Category", "Description", "Photo"};

        @Override
        public int getRowCount() {
            return al.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return al.get(rowIndex).name;
                case 1:
                    return al.get(rowIndex).description;
                case 2:
                    return al.get(rowIndex).photo;
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }

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
            java.util.logging.Logger.getLogger(viewcategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewcategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewcategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewcategory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewcategory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbcuisine;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    class ImageRenderer extends DefaultTableCellRenderer {

        JLabel lbl = new JLabel();
        ImageIcon icon = new ImageIcon("");
        BufferedImage bufferedImage, newimage;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            try {
                URL url = new URL("http://localhost:8888/GetResource/" + al.get(row).photo);
                bufferedImage = ImageIO.read(url);
                newimage = resizephoto(bufferedImage, 100, 100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            icon = new ImageIcon(newimage);
            lbl.setIcon(icon);
            lbl.setBounds(0, 0, 100, 100);
            return lbl;
        }
    }

    BufferedImage resizephoto(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

}

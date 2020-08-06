
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
public class viewfooditems extends javax.swing.JFrame
{
 
 
    ArrayList<fooditems> al = new ArrayList<>();
    fooditemstablemodel fi = new fooditemstablemodel();
    
    
    /**
     * Creates new form viewfooditems
     */
     public viewfooditems() {
        
        initComponents();
      
        setSize(600, 600);
        jTable1.setModel(fi);
      
       
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

        cbcuisine = new javax.swing.JComboBox<>();
        cbcategory = new javax.swing.JComboBox<>();
        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        btview = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btedit = new javax.swing.JButton();
        btdelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        cbcuisine.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----------------SELECT CUISINE----------------", "INDIAN", "CHINESE", "FRENCH", "SEAFOOD" }));
        cbcuisine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcuisineActionPerformed(evt);
            }
        });
        getContentPane().add(cbcuisine);
        cbcuisine.setBounds(180, 30, 240, 20);

        cbcategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----------SELECT CATEGORY--------" }));
        getContentPane().add(cbcategory);
        cbcategory.setBounds(180, 80, 240, 20);

        lb1.setText("CATEGORY");
        getContentPane().add(lb1);
        lb1.setBounds(80, 70, 80, 30);

        lb2.setText("CUISINE");
        getContentPane().add(lb2);
        lb2.setBounds(80, 30, 80, 30);

        btview.setText("VIEW");
        btview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btviewActionPerformed(evt);
            }
        });
        getContentPane().add(btview);
        btview.setBounds(230, 120, 110, 23);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "item name", "description", "type", "price", "photo"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 170, 452, 180);

        btedit.setText("EDIT");
        btedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteditActionPerformed(evt);
            }
        });
        getContentPane().add(btedit);
        btedit.setBounds(320, 370, 110, 23);

        btdelete.setText("DELETE");
        btdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btdelete);
        btdelete.setBounds(110, 370, 110, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btviewActionPerformed
        // TODO add your handling code here:
          
        loaddata();
    }//GEN-LAST:event_btviewActionPerformed

    private void bteditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteditActionPerformed
        // TODO add your handling code here:
         if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a fooditem first");
         }
      else
         {
         String cats,itemn,des,exp,typ;     
        cats=cbcategory.getSelectedItem().toString();
        itemn=al.get(jTable1.getSelectedRow()).itemname;
        des=al.get(jTable1.getSelectedRow()).description;
        typ=al.get(jTable1.getSelectedRow()).type;
        exp=al.get(jTable1.getSelectedRow()).price;  
        editfooditems obj45 = new editfooditems(cats,itemn,des,exp,typ);
    }//GEN-LAST:event_bteditActionPerformed
    }
    private void btdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdeleteActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a fooditem first");
        } else {
 
            String ci = al.get(jTable1.getSelectedRow()).itemname;
            int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Are you sure to delete?", "Delete Confirmation", JOptionPane.OK_CANCEL_OPTION);
            if (showConfirmDialog == JOptionPane.OK_OPTION) {
                try {
                    HttpResponse<String> res = Unirest.get("http://localhost:8888/deletefooditem")
                            .queryString("currentitem", ci).asString();

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

    }//GEN-LAST:event_btdeleteActionPerformed

    private void cbcuisineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcuisineActionPerformed
        // TODO add your handling code here:
          try {
            // TODO add your handling code here:
            String combo = cbcuisine.getSelectedItem().toString();
          
           if (cbcuisine.getSelectedIndex() >= 1) {
                HttpResponse<String> res = Unirest.get("http://127.0.0.1:8888/getcategories")
                        .queryString("cuisine", combo)
                        .asString();
                String response = res.getBody();
                StringTokenizer st = new StringTokenizer(response, "#$%");
                int n = st.countTokens();
                cbcategory.removeAllItems();
                for (int i = 1; i <= n; i++) {
                    String row = st.nextToken();
                    cbcategory.addItem(row);
                }
            }
          }
           catch (UnirestException ex) {
            Logger.getLogger(addfooditems.class.getName()).log(Level.SEVERE, null, ex);
        }              
    }//GEN-LAST:event_cbcuisineActionPerformed
    private void loaddata() {
        try {
            // TODO add your handling code here:
            al.clear();
            String cat = cbcategory.getSelectedItem().toString();
            HttpResponse<String> res = Unirest.get("http://localhost:8888/getfooditemsbycategory")
                    .queryString("category", cat).asString();
            String response = res.getBody();
            StringTokenizer st = new StringTokenizer(response, "~!@");
            int n = st.countTokens();
            for (int i = 1; i <= n; i++) {
                String row = st.nextToken();
                StringTokenizer st2 = new StringTokenizer(row, "#$%");
                String itemname = st2.nextToken();
                
                String description = st2.nextToken();
                String type = st2.nextToken();
                String price = st2.nextToken();
                String photo = st2.nextToken();
                fooditems obj = new fooditems(itemname, description, type, price, photo);
                al.add(obj);
            }
            jTable1.setRowHeight(100);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());
            fi.fireTableDataChanged();

        } catch (UnirestException ex) {
            Logger.getLogger(viewcategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class fooditems {

        String itemname, description, type, price, photo;

        public fooditems(String itemname, String description, String type, String price, String photo) {
            this.itemname = itemname;
            this.description = description;
            this.type = type;
            this.price = price;
            this.photo = photo;

        }
    }

    class fooditemstablemodel extends AbstractTableModel {

        String[] columns = {"item name", "description", "type", "price", "photo"};

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
                    return al.get(rowIndex).itemname;
                case 1:
                    return al.get(rowIndex).description;
                case 2:
                    return al.get(rowIndex).type;
                case 3:
                    return al.get(rowIndex).price;
                case 4:
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
            java.util.logging.Logger.getLogger(viewfooditems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewfooditems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewfooditems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewfooditems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewfooditems().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btdelete;
    private javax.swing.JButton btedit;
    private javax.swing.JButton btview;
    private javax.swing.JComboBox<String> cbcategory;
    private javax.swing.JComboBox<String> cbcuisine;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    // End of variables declaration//GEN-END:variables
}


import com.vmm.JHTTPServer;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class serverforfoodorder extends JHTTPServer {

    public serverforfoodorder(int port) throws IOException {
        super(port);
    }

    /**
     * This is the common function which will receive the client's request and
     * will serve the response accordingly. for e.g 1. if request is for
     * /GetResource( image preview / any file download ) call
     * sendCompleteFile(uri) 2. if request is for /StreamMedia ( stream audio /
     * video ) call streamFile(uri,method,header) 3. in case of any other custom
     * request prepare your own response and return
     *
     * NOTE: In case of File upload (client to server) call any of the two
     * functions 1. saveFileOnServerWithOriginalName(files,parms,name,abspath)
     * 1. saveFileOnServerWithRandomName(files,parms,name,abspath)
     *
     *
     * @param uri will contain the extracted uri from the complete URL for e.g
     * (/GetResource/c1.jpg) (/GetResource/one.mp3) (/StreamMedia/ome.mp4)
     * @param method contains GET,POST,HEAD
     * @param header contains the extra header data (range, user-agent etc)
     * @param parms contains the query string parameters to extract text data
     * e.g String email = parms.getProperty("email");
     *
     * @param files contains the files in form of file upload(POST request)
     * filename = saveFileOnServerWithRandomName(files, parms, field_name,
     * abs_path);
     * @return
     */
    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {

        Response res = null;

        System.out.println("URI " + uri);

        if (uri.contains("/GetResource")) //request should be of the form /GetResource/src/content/one.jpg
        {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            System.out.println(uri + " *** ");
            res = sendCompleteFile(uri);
        } else if (uri.contains("/StreamMedia")) //request should be of the form /GetResource/one.jpg
        {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            System.out.println(uri + " *** ");
            res = streamFile(uri, method, header);
        } else if (uri.startsWith("/adminlogin")) {
            String u = parms.getProperty("username");
            String p = parms.getProperty("password");

            System.out.println(u + " " + p);

            String ans = "";

            ResultSet rs = dbloader.executeQuery("select * from users where username ='" + u + "' and password='" + p + "'");
            try {
                if (rs.next()) {
                    ans = "success";
                } else {
                    ans = "failed";
                }
            } catch (SQLException ex) {
                Logger.getLogger(serverforfoodorder.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/html", ans);
        } else if (uri.startsWith("/adminchangepassword")) {
            String u = parms.getProperty("username");
            String p = parms.getProperty("password");
            String n = parms.getProperty("newpassword");

            System.out.println(u + " " + p + "" + n);

            String ans = "";
            ResultSet rs = dbloader.executeQuery("select * from users where username ='" + u + "' and password='" + p + "'");
            try {
                if (rs.next()) {
                    rs.updateString("password", n);
                    System.out.println("controlreached");
                    rs.updateRow();
                    System.out.println("controlreached at last");
                    ans = "success";
                } else {
                    ans = "failed";
                }
            } catch (SQLException ex) {
                Logger.getLogger(serverforfoodorder.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/html", ans);
        } else if (uri.equals("/addcategories")) {

            String c = parms.getProperty("cuisine");
            String p = parms.getProperty("categories");
            String n = parms.getProperty("description");
            // String pi= parms.getProperty("photo");
            String photoname = saveFileOnServerWithRandomName(files, parms, "photo", "src/content");

            //System.out.println(u + " " + p+""+n);
            String ans = "";
            ResultSet rs = dbloader.executeQuery("select * from categories where name='" + p + "'");

            try {
                if (rs.next()) {
                    ans = "duplicate";
                } else {
                    rs.moveToInsertRow();
                    rs.updateString("cuisine", c);
                    rs.updateString("name", p);
                    rs.updateString("description", n);
                    rs.updateString("photo", "src/content/" + photoname);
                    System.out.println("controlreached");
                    rs.insertRow();
                    System.out.println("controlreached at last");
                    ans = "success";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/html", ans);
        } else if (uri.equals("/getcategorybycuisine")) {

            String c = parms.getProperty("cuisine");
            String ans = "";
            ResultSet rs = dbloader.executeQuery("select * from categories where cuisine ='" + c + "'");

            try {
                while (rs.next()) {
                    ans += rs.getString("name") + "#$%"
                            + rs.getString("description") + "#$%"
                            + rs.getString("photo") + "~!@";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/html", ans);

        } else if (uri.equals("/deletecategory")) {

            String d = parms.getProperty("currentcat");
            String ans = "";
            ResultSet rs = dbloader.executeQuery("select * from categories where name ='" + d + "'");

            try {
                if (rs.next()) {
                    rs.deleteRow();
                    ans = "success";
                } else {
                    ans = "failed";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/html", ans);

        } else if (uri.equals("/addfooditems")) {

            String ans = "";
            try {
                String c = parms.getProperty("cuisine");
                String p = parms.getProperty("foodcategory");
                String ina = parms.getProperty("itemname");
                String d = parms.getProperty("description");
                String cos = parms.getProperty("price");
                String fp = parms.getProperty("foodtype");
                String photoname = saveFileOnServerWithRandomName(files, parms, "photo", "src/content");

                ResultSet rs = dbloader.executeQuery("select * from fooditems where category ='" + p + "' and itemname='" + ina + "'");

                if (rs.next()) {
                    ans = "faileddd";
                } else {
                    rs.moveToInsertRow();
                    rs.updateString("category", p);
                    rs.updateString("itemname", ina);
                    rs.updateString("description", d);
                    rs.updateString("type", fp);
                    rs.updateString("price", cos);
                    rs.updateString("photo", "src/content/" + photoname);
                    System.out.println("controlreached");
                    rs.insertRow();
                    System.out.println("controlreached at last");
                    ans = "success";

                }
            } catch (SQLException ex) {
                Logger.getLogger(serverforfoodorder.class.getName()).log(Level.SEVERE, null, ex);
            }

            res = new Response(HTTP_OK, "text/html", ans);
        } else if (uri.equals("/getcategories")) {
            String c = parms.getProperty("cuisine");
            String ans = "";
            ResultSet rs = dbloader.executeQuery("select * from categories where cuisine ='" + c + "'");

            try {
                while (rs.next()) {
                    ans += rs.getString("name") + "#$%";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/html", ans);

        } else if (uri.equals("/getfooditemsbycategory")) {

            String c = parms.getProperty("category");
            String ans = "";
            ResultSet rs = dbloader.executeQuery("select * from fooditems where category ='" + c + "'");

            try {
                while (rs.next()) {
                    ans += rs.getString("itemname") + "#$%"
                            + rs.getString("description") + "#$%"
                            + rs.getString("type") + "#$%"
                            + rs.getString("price") + "#$%"
                            + rs.getString("photo") + "~!@";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/html", ans);
        } else if (uri.equals("/deletefooditem")) {
            String d = parms.getProperty("currentitem");
            String ans = "";
            ResultSet rs = dbloader.executeQuery("select * from fooditems where itemname ='" + d + "'");

            try {
                if (rs.next()) {
                    rs.deleteRow();
                    ans = "success";
                } else {
                    ans = "failed";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/html", ans);

        } else if (uri.equals("/getdatabyitemname")) {

            String c = parms.getProperty("itemname");
            String ans = "";
            ResultSet rs = dbloader.executeQuery("select * from fooditems where itemname ='" + c + "'");

            try {
                while (rs.next()) {
                    ans += rs.getString("description") + "#$%"
                            + rs.getString("type") + "#$%"
                            + rs.getString("price") + "#$%"
                            + rs.getString("photo") + "~!@";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/html", ans);
        } else if (uri.equals("/updatefooditems")) {
            String ans = "";
            try {
                String cat = parms.getProperty("category");
                String ina = parms.getProperty("itemname");
                String d = parms.getProperty("description");
                String cos = parms.getProperty("price");
                String fp = parms.getProperty("foodtype");

                ResultSet rs = dbloader.executeQuery("select * from fooditems where category ='" + cat + "' and itemname='" + ina + "'");

                if (rs.next()) {
                    rs.updateString("description", d);
                    rs.updateString("type", fp);
                    rs.updateString("price", cos);
                    System.out.println("controlreached");
                    rs.updateRow();
                    System.out.println("controlreached at last");
                    ans = "success";
                } else {
                    ans = "failed";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/html", ans);
        } else if (uri.startsWith("/customersignup")) {
            try {
                String mn = parms.getProperty("mobilenumber");
                String n = parms.getProperty("name");
                String e = parms.getProperty("email");
                String a = parms.getProperty("address");
                boolean flag = Boolean.parseBoolean(parms.getProperty("flag"));
                String ans = "";
                ResultSet rs = dbloader.executeQuery("select * from customersignup where mobilenumber ='" + mn + "'");
                if (flag == false) //new signup
                {
                    if (rs.next()) {
                        rs.moveToInsertRow();
                        rs.updateString("mobilenumber", mn);
                        rs.updateString("name", n);
                        rs.updateString("email id", e);
                        rs.updateString("address", a);
                        rs.insertRow();
                        ans = "success";
                    } else {
                        ans = "failed";
                    }
                } else {
                    if (rs.next()) {
                        rs.updateString("name", n);
                        rs.updateString("email id", e);
                        rs.updateString("address", a);
                        rs.updateRow();
                        ans = "success";
                    } else {
                        ans = "failed";
                    }
                }

                res = new Response(HTTP_OK, "text/html", ans);
            } catch (SQLException ex) {
                Logger.getLogger(serverforfoodorder.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.equals("/getcustomerdetails")) {

            String mn = parms.getProperty("mobilenumber");
            String ans = "";
            ResultSet rs = dbloader.executeQuery("select * from customersignup where mobilenumber ='" + mn + "'");

            try {
                if (rs.next()) {
                    ans += rs.getString("name") + "~~~"
                            + rs.getString("email id") + "~~~"
                            + rs.getString("address");
                }
                else
                {
                    ans = "failed";
                }
                         
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(ans);
            res = new Response(HTTP_OK, "text/html", ans);
        } else {
            res = new Response(HTTP_OK, "text/html", "<body style='background: #D46A6A; color: #fff'><center><h1>Hello</h1><br> <h3>Welcome To JHTTP Server (Version 1.0)</h3></body></center>");
        }

        return res;
    }
}

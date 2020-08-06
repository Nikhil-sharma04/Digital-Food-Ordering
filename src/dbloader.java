
import java.sql.*;

public class dbloader {

    static ResultSet executeQuery(String sqlstatement) {
        try {
            //  ## CODE //
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver loaded successfully");
            Connection conn
                    = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/admin", "root", "system");
            System.out.println("connection built");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("statement created");

            System.out.println("ResultSet Created");
            ResultSet rs = stmt.executeQuery(sqlstatement);
            return rs;
            //////////////
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    
}
    public static void main(String[] args) {
        executeQuery("select * from users");
    }
}
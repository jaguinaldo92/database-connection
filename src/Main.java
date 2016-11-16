import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by c1542267 on 14/11/2016.
 */

public class Main {
    public static void main(String[] args) {
        try{
            // Load and Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Create Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/olympics", "root", "comsc");

            System.out.println("Database connected");

            // Create a SQL statement
            Statement SQLquery = con.createStatement();

            // Execute the SQL statement and retrieve the result
            ResultSet rs = SQLquery.executeQuery("SELECT olympian_firstname, olympian_surname, event_name " +
                    "FROM olympians o " +
                    "INNER JOIN events_joined ej ON ej.olympian_id = o.olympian_id " +
                    "INNER JOIN olympic_events oe ON oe.event_id = ej.event_id WHERE olympian_firstname = 'Jeremy'");


            // Process the result
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " +
                        rs.getString(3));
            }

            con.close();
            System.out.println("Database disconnected");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

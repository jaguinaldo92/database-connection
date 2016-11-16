import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by c1542267 on 16/11/2016.
 */
public class CallableStatement {
    public static void main(String args[]) {

        Connection con = null;

        try {

            // Step 2: Load and register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Step 3: Create a connection
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/olympics", "root", "comsc");

            System.out.println("Connected to the database");

            // Step 4: Create a Callable Statement statement
            java.sql.CallableStatement cStmt = con.prepareCall("{call types_of_medal_per_country('Gold')}");

            //Step 5: Execute the Callable Statement statement and check if there is a result
            boolean hadResults = cStmt.execute();


            // Step 6: Process the result if present
            while (hadResults) {
                ResultSet rs = cStmt.getResultSet();

                while (rs.next()) {
                    System.out.println(rs.getString(1) + "  " + rs.getString(2));
                }

                hadResults = cStmt.getMoreResults();
            }


        } catch (Exception e) {
            // Exception handling
            System.out.println(e);
        }

        // Step 7: Close the connection in a finally block
        finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Disconnected from the database");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by c1542267 on 16/11/2016.
 */
public class DBConnectionMng {
    private static final String DriverName = "com.mysql.jdbc.Driver";
    private static final String HostName = "jdbc:mysql://localhost:3306";
    private static final String DatabaseName = "university";
    private static final String User = "root";
    private static final String Password = "comsc";


    public static Connection CreateNewConnection() throws SQLException {

        Connection conn = null;

        try {
            // Step 2: Load and register JDBC driver
            Class.forName(DriverName);


        } catch (Exception e) {
            // Exception handling
            System.out.println(e);
        }
        //Step 3: Create a connection
        return conn = DriverManager.getConnection(HostName + "/" + DatabaseName, User, Password);


    }

    public static void CloseConnection(Connection ConnectionToClose) {

        try {
            if (ConnectionToClose != null)
                ConnectionToClose.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
}

import java.sql.*;

/**
 * Created by c1542267 on 16/11/2016.
 */
public class ExamplePreparedStatement {
    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement preparedStatement = null;

        String selectOlympian = "SELECT olympian_id, olympian_firstname, olympian_surname FROM olympians WHERE olympian_id =?";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/olympics", "root", "comsc");

            System.out.println("Database Connected");

            preparedStatement = con.prepareStatement(selectOlympian);
            preparedStatement.setInt(1, 10102);


            //Execute the SQL select statement
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                String olympian_id = rs.getString("olympian_id");
                String olympian_firstname = rs.getString("olympian_firstname");
                String olympian_surname = rs.getString("olympian_surname");

                System.out.println("olympian ID: " +  olympian_id);
                System.out.println("olympian FirstName: " + olympian_firstname);
                System.out.println("olympian SurName: " + olympian_surname);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Database disconnected");
                } catch(Exception e){
                    System.out.println(e);
                }
            }
        }


    }
}

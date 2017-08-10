package hello;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class TestJdbc {
    public static void main(String[] args){
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            ResultSet resultSet = myConn.createStatement().executeQuery("SELECT * FROM student");

            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("first_name");
                String email = resultSet.getString("email");
                System.out.println(id + " " + name + " " + email);
            }
            //myConn.
            System.out.println("Sccuess!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

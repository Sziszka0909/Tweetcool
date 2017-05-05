package pack;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 * Created by sziszka on 2017.05.04..
 */
public class MessageServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String namez = request.getParameter("name");
        String messagez = request.getParameter("message");
        System.out.println(namez + messagez);
        String xy = String.format("INSERT INTO messages(name, message) " +
                "VALUES(\"%s\", \"%s\");", namez, messagez);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tweetcool", "root", "admin");
            Statement statement = connection.createStatement();
            statement.executeUpdate(xy);
            System.out.println("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("./index.html").forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}

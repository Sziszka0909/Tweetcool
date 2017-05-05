package pack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by sziszka on 2017.05.05..
 */
@WebServlet(name = "TweetServlet")
public class TweetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tweetcool", "root", "admin");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM messages");
            PrintWriter out = response.getWriter();
            out.println("<html><head><meta charset='UTF-8'><title>Tweets</title></head>");
            out.println("<body><h1>Tweets</h1><h4>");
            while(result.next()){
                out.println("" + result.getString("name") + ": " + result.getString("message") + "</br>");
            }
            out.println("</h4></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

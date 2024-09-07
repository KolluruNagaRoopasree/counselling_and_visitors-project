package visitors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/VisitorServlet")
public class VisitorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	PrintWriter out =response.getWriter();
        String na = request.getParameter("name");
        String em = request.getParameter("email");
        String ph = request.getParameter("phone");
        String pa= request.getParameter("password");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "roopa");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO visitors VALUES (?, ?, ?, ?)");
            statement.setString(1, na);
            statement.setString(2, em);
            statement.setString(3, ph);
            statement.setString(4, pa);
           int b=statement.executeUpdate();
           response.sendRedirect("index.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

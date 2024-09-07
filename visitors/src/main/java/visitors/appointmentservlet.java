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

@WebServlet("/appointmentservlet")

public class appointmentservlet extends HttpServlet {
	 
    private static final long serialVersionUID = 1L;
    public appointmentservlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String nam = request.getParameter("name");
        String ema= request.getParameter("email");
        String pho = request.getParameter("phone");
        String dat = request.getParameter("date");
        String tim = request.getParameter("time");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "roopa");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO appointments VALUES (?, ?, ?, ?,?)");
            PrintWriter out =response.getWriter();

    statement.setString(1, nam);
    statement.setString(2, ema);
    statement.setString(3, pho);
    statement.setString(4, dat);
    statement.setString(5, tim);
   int b=statement.executeUpdate();
   response.sendRedirect("counselling.html");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Failed to register appointment");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

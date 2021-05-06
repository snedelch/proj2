package inf124.proj2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCartServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(true);
        String session_id = (String) session.getAttribute("UserID");
        String product_id = req.getParameter("p_id");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "demo", "root", "3593");
            Statement stmt = con.createStatement();
            out.println("<html><body>");
            out.println("<h1> User Session: " + session_id + ", PID: "+ product_id + "</h1>");
            out.println("</html></body>");
            String sql = "INSERT INTO cart (session, product_id) VALUE (\'" + session_id + "\', \'" + product_id + "\');";
            stmt.executeUpdate(sql);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
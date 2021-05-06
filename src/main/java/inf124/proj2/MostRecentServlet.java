package inf124.proj2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "MostRecent", value = "/MostRecent")
public class MostRecentServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//        String user_id = (String) session.getAttribute("user_id");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(true);

        out.println(" <div class = \"small-container\">\n" +
                "<h2 class = \"title\"> Featured Products</h2>");

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    +  "demo", "root", "3593");
            Statement stmt = con.createStatement();
            //this query only queries top 5 of ALL orders, will need to filter based on user
            String pro_info = "SELECT p.name, p.img_file, p.price, p.rating, p.id FROM demo.orders as o, demo.product as p " +
                    "where o.product_id = p.id Order by o.time desc limit 5";
            ResultSet rs = stmt.executeQuery(pro_info);


            int i = 0;


            while(rs.next() ){
//                if (i % 3 == 0)  // start of a row
                out.println("<div class = \"topFives\">");

                //every column product
                out.print("<div class=\"col-3\"> \n" +
                        "      <a href = \"/product-detail.html\">\n" +
                        "          <img src = "+ rs.getString("img_file") + ">\n" +
                        "          <h4>" + rs.getString("name") + "</h4>\n" +
                        "      </a>\n" +
                        "      <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "      <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "      <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "      <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "      <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "      <i> " + rs.getString("rating")  + " stars </i>\n" +
                        "      <p>   " + rs.getString("price")+ "</p>\n" +
                        "</div>");
//                if (i% 4 == 3) //end of a row
                ++i;
            }
            out.println("</div>"); //close row
            out.println("</div>");
//            out.println("</body> </html>");

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }


}






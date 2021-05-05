package inf124.proj2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AllProducts", value = "/AllProducts")
public class AllProductsServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "<meta charset=\"UTF-8\">\n" +
                            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "<title>All Products - Carlos Arteaga Ecommerce Website</title>\n" +
                            "<link rel = \"stylesheet\" href = \"style.css\">\n" +
                            "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\n" +
                            "<link href=\"https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap\" rel=\"stylesheet\">\n" +
                            "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<div class = \"container\">\n" +
                            "<div class = \"navbar\">\n" +
                            "<div class = \"logo\">\n" +
                     "<img src  =  \"./images/ciarteagLogo.png\" width = \"190 px\" >\n" +
                    "</div>\n" +
                    "<nav>\n" +
                    "<ul id=\"MenuItems\">\n" +
                    "<li> <a href = \"./login.html\"> Login </a> </li>\n" +
                    "<li> <a href = \"./products.html\"> Products </a> </li>\n" +
                    "<li> <a href = \"./about.html\"> About </a> </li>\n" +
                    "</ul>\n" +
                    "</nav>\n" +
                    "<a href = \"/cart.html\" > <img src =\"./images/cart.png\" width = \"30px\" height = \"30px\"></a>\n" +
                    "<img src =\"./images/menu.png\" class = \"menu-symbol\" onclick=\"menutoggle()\" >\n" +

                    "</div>\n" +
                    "</div>");


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    +  "demo", "root", "3593");
            Statement stmt = con.createStatement();
            String logo = "SELECT p.name, p.price, p.rating, p.img_file\n FROM  product as p";
            ResultSet rs = stmt.executeQuery(logo);


            int i = 0;

            out.println("<div class = \"small-container\">\n" +
                                "<div class = \"row row-2\">\n" +
                                    "<h2> All Products</h2>\n" +
                                    "<select class = \"sort\">\n" +
                                        "<option> Featured</option>\n" +
                                        "<option> Newest</option>\n" +
                                        "<option> Price: Low-High</option>\n" +
                                        "<option> Price: High-Low</option>\n" +
                                    "</select>\n" +
                               "</div>");


            while(rs.next() ){
                if (i % 4 == 0)  // start of a row
                    out.println("<div class = \"row\">");

                //every column product
                out.print("<div class = \"col-prod\"> \n" +
                        "                <a href = \"/product-detail.html\">\n" +
                        "                    <img src = "+ rs.getString("img_file") + ">\n" +
                        "                    <h4>" + rs.getString("name") + "</h4>\n" +
                        "                </a>\n" +
                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
                        "                <i> " + rs.getString("rating")  + " stars </i>\n" +
                        "                <p>   " + rs.getString("price")+ "</p>\n" +
                        "            </div>");


                if (i% 4 == 3) //end of a row
                    out.println("</div>\n");
                ++i;
            }
            out.println("</div>");
            out.println("</body> </html>");

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
}
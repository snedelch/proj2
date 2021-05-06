package inf124.proj2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ProductDetail", value = "/ProductDetail")
public class ProductDetailServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String userId = (String) session.getAttribute("UserID");

        //the product id we are searching for
        String productId = request.getParameter("id");
        System.out.println("The user " + userId + " has clicked on product number " + productId);
        FileWriter fileWriter = new FileWriter(getServletContext().getRealPath("/reportedProducts.txt"), true);
        fileWriter.write("Product dsfdsfdsfdsf ");
        fileWriter.close();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    + "demo", "root", "3593");
            Statement stmt = con.createStatement();
            String sql = "SELECT name, description, img_file, rating, price FROM product WHERE id = \""+ productId + "\"";
            ResultSet rs = stmt.executeQuery(sql);

            //for reference, call paramas like: rs.getString("name");

            PrintWriter writer = response.getWriter();
            writer.println("<!DOCTYPE html>\n" +
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
                    "<body>");
            writer.println("<div class = \"container\">\n" +
                    "<div class = \"navbar\">\n" +
                    "<div class = \"logo\">\n" +
                    "<img src  =  \"./images/ciarteagLogo.png\" width = \"190 px\" >\n" +
                    "</div>\n" +
                    "<nav>\n" +
                    "<ul id=\"MenuItems\">\n" +
                    "<li> <a href = \"./login.html\"> Login </a> </li>\n" +
                    "<li> <a href = \"./\" > Products </a> </li>\n" +
                    "<li> <a href = \"./about.html\"> About </a> </li>\n" +
                    "</ul>\n" +
                    "</nav>\n" +
                    "<a href = \"./CheckOut\" > <img src =\"./images/cart.png\" width = \"30px\" height = \"30px\"></a>\n" +
                    "<img src =\"./images/menu.png\" class = \"menu-symbol\" onclick=\"menutoggle()\" >\n" +

                    "</div>\n" +
                    "</div>");



            if(rs.next()) {


                writer.println("<div class =\"small-container singleProduct\">\n" +
                        "        <div class = \"row\">\n" +
                        "            <div class = \"col-2\">\n" +
                        "                <img src= \"" + rs.getString("img_file") + "\" id =\"product-img\" width = \"100%\" alt= \"" + rs.getString("name")+ "\">\n" +
                        "                <div class = \"small-img-display\">\n" +
                        "                    <div class = \"small-img-col\">\n" +
                        "                        <img src = \"" + rs.getString("img_file") + "\" width = \"100%\" class = \"small-img\">\n" +
                        "                    </div>\n" +
                        "                    <div class = \"small-img-col\">\n" +
                        "                        <img src = \"" + rs.getString("img_file") + "\" width = \"100%\" class = \"small-img\">\n" +
                        "                    </div>\n" +
                        "                    <div class = \"small-img-col\">\n" +
                        "                        <img src = \"" + rs.getString("img_file") + "\" width = \"100%\" class = \"small-img\">\n" +
                        "                    </div>\n" +
                        "                    <div class = \"small-img-col\">\n" +
                        "                        <img src = \"" + rs.getString("img_file") + "\" width = \"100%\" class = \"small-img\">\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "            <div class = \"col-2\">\n" +
                        "                <h1 id=\"productName\">" + rs.getString("name") + "</h1>\n" +
                        "                <p id = \"productPrice\" class = \"price\">" + rs.getString("price") + "</p>\n" +
                        "                <a href = \"./AddToCart?p_id=" + productId + "\" class = \"btn cart\"> Add to Cart </a>\n" +
                        "                <h3> Product Details</h3>\n" +
                        "                <br>\n" +
                        "                <p>" + rs.getString("description") + " </p>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>");
                writer.println("</body> </html>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

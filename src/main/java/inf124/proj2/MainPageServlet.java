package inf124.proj2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "MainPage", value = "/")
public class MainPageServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession session = req.getSession(true);
//        if(session.isNew()){
//            String user_id = String.valueOf(ThreadLocalRandom.current().nextInt());
//            session.setAttribute("user_id", user_id);
//            RequestDispatcher req_dispatch = req.getRequestDispatcher("/Login");
//            req_dispatch.include(req, resp);
//        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        out.print("Welcome to your main page " + name);

        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Carlos Arteaga Ecommerce Website</title>\n" +
                "<link rel = \"stylesheet\" href = \"style.css\">\n" +
                "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\n" +
                "<link href=\"https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap\" rel=\"stylesheet\">\n" +
                "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +

                "</head>\n" +
                "<body>\n" +
                "<div class = \"header\">\n" +

                "<div class = \"container\">\n" +
                "<div class = \"navbar\">\n" +
                "<div class = \"logo\">\n" +
                "<img src  = \"./images/ciarteagLogo.png\" width = \"190 px\" >\n" +
                "</div>\n" +
                "<nav>\n" +
                "<ul id=\"MenuItems\">\n" +
                "<li> <a href = \"./index.html\"> Home </a> </li>\n" +
                "<li> <a href = \"./products.html\"> Products </a> </li>\n" +
                "<li> <a href = \"./about.html\"> About </a> </li>\n" +
                "</ul>\n" +
                "</nav>\n" +
                "<a href = \"./image/cart.html\" > <img src =\"./images/cart.png\" width = \"30px\" height = \"30px\"></a>\n" +
                "<img src =\"./images/menu.png\" class = \"menu-symbol\" onclick=\"menutoggle()\" >\n" +

                "</div>" +
                "<div class = \"row\">\n" +
                "            <div class = \"col-2\">\n" +
                "                <h1>\n" +
                "                    Add style <br> to your kicks!\n" +
                "                </h1>\n" +
                "                <p> Show off your favorite anime characters! <br> Browse from many different artists!</p>\n" +
                "                <a href= \"./products.html\" class = \"btn\"> Explore Now &#8594 </a>\n" +
                "            </div>\n" +
                "            <div class = \"col-2\">\n" +
                "                <img src = \"./images/plainShoe2.png\" class = \"rotate\" alt = \"White Nike Shoe with anime drawing on side\" >\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "    <div class = \"small-container\">\n" +
                "        <h2 class = \"title\" > Most Recent Orders</h2>\n" +
                "<div class = \"topFives\">");


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    +  "demo", "root", "3593");
            Statement stmt = con.createStatement();
            String pro_info = "SELECT p.name, p.img_file, p.price, p.rating, p.id FROM demo.orders as o, demo.product as p " +
                    "where o.product_id = p.id Order by o.time desc limit 5";
            ResultSet rs = stmt.executeQuery(pro_info);


            int i = 0;


            while(rs.next() ){
//                if (i % 3 == 0)  // start of a row
//                    out.println("<div class = \"row\">");

                //every column product
                out.print("<div class=\"col-3\"> \n" +
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
//                if (i% 4 == 3) //end of a row
                ++i;
            }
            out.println("</div>");
            out.println("</div>");
            out.println("</body> </html>");

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
}
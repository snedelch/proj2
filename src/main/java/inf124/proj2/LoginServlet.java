package inf124.proj2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "Login", value = "/Login")
public class LoginServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        //to get user and password working on
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String db_email = "";
        String db_pass = "";




        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
                    +  "demo", "root", "3593");
            Statement stmt = con.createStatement();
            String user_info = "SELECT email, pass FROM user";
            ResultSet rs = stmt.executeQuery(user_info);


            while(rs.next()) {
                db_email = rs.getString("email");
                db_pass = rs.getString("pass");


                if (db_pass.equals(pass) && db_email.equals(email)) {
                    RequestDispatcher mostRecent = req.getRequestDispatcher("/MostRecent");
                    mostRecent.forward(req, resp);//if the username and pass are correct the servlet will be forwarder to the next servlet.
//            req.getRequestDispatcher("AllProducts").include(req, resp);
                } else {
//                    out.print("Incorrect username or password! Please, try again.");
//                    RequestDispatcher rd = req.getRequestDispatcher("./login.html");
//                    rd.include(req, resp);// if the uname and pass are not correct this servlet will be included to the previous servlet
                    //include combines the result of multiple servlets.
                }
            }

//            int i = 0;


//            while(rs.next() ){
////                if (i % 3 == 0)  // start of a row
//                out.println("<div class = \"row\">");
//
//                //every column product
//                out.print("<div class=\"col-3\"> \n" +
//                        "                <a href = \"/product-detail.html\">\n" +
//                        "                    <img src = "+ rs.getString("img_file") + ">\n" +
//                        "                    <h4>" + rs.getString("name") + "</h4>\n" +
//                        "                </a>\n" +
//                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
//                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
//                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
//                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
//                        "                <img class = \"star\" src =\"./images/star.png\" >\n" +
//                        "                <i> " + rs.getString("rating")  + " stars </i>\n" +
//                        "                <p>   " + rs.getString("price")+ "</p>\n" +
//                        "            </div>");
////                if (i% 4 == 3) //end of a row
//                ++i;
//            }
//            out.println("</div>");
//            out.println("</div>");
//            out.println("</body> </html>");
//
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }catch (ServletException e){
            System.out.println(e);
        }

    }
}
package inf124.proj2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "CheckOut", value = "/CheckOut")
public class CheckOutServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(true);
        String user_id = (String) session.getAttribute("user_id");
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
                "<body>");
        out.println("<div class = \"container\">\n" +
                "<div class = \"navbar\">\n" +
                "<div class = \"logo\">\n" +
                "<img src  =  \"./images/ciarteagLogo.png\" width = \"190 px\" >\n" +
                "</div>\n" +
                "<nav>\n" +
                "<ul id=\"MenuItems\">\n" +
                "<li> <a href = \"./login.html\"> Login </a> </li>\n" +
                "<li> <a href = \"./\"> Products </a> </li>\n" +
                "<li> <a href = \"./about.html\"> About </a> </li>\n" +
                "</ul>\n" +
                "</nav>\n" +
                "<a href = \"./CheckOut\" > <img src =\"./images/cart.png\" width = \"30px\" height = \"30px\"></a>\n" +
                "<img src =\"./images/menu.png\" class = \"menu-symbol\" onclick=\"menutoggle()\" >\n" +

                "</div>\n" +
                "</div>");


        out.println("<h1> User Session: " + user_id + "</h1>");
        out.println("    <div id=\"placeOrder\">\n" +
                "        <div class = \"small-container\">\n" +
                "            <div class = \"row\">\n" +
                "                <h2 style=\"padding-bottom:8px;\">Product Order</h2>\n" +
                "            </div>\n" +
                "            <form id=\"myform\" action = \"mailto:buyshoes@uci.edu\" method =\"post\" enctype=\"text/plain\" onsubmit=\"event.preventDefault(); myValidation();\">\n" +
                "                <div class = \"row-3\">\n" +
                "                    <div class = \"col-33\">\n" +
                "                        <h3>Product</h3>\n" +
                "                    </div>\n" +
                "                    <div class = \"col-33\">\n" +
                "                        <h3>Size</h3>\n" +
                "                    </div>\n" +
                "                    <div class = \"col-33\">\n" +
                "                        <h3>Quantity</h3>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class = \"row-3\">\n" +
                "                    <div class = \"col-33\">\n" +
                "                        <select id = \"product\" name = \"Product Name\">\n" +
                "                            <option> Jojo Air Force 1's</option>\n" +
                "                        </select>\n" +
                "                    </div>\n" +
                "                    <div class = \"col-33\">\n" +
                "                        <select id = \"size\" name = \"Shoe Size \" class=\"required\">\n" +
                "                            <option selected disabled> Select Size </option>\n" +
                "                            <option> 4 </option>\n" +
                "                            <option> 4.5 </option>\n" +
                "                            <option> 5 </option>\n" +
                "                            <option> 5.5 </option>\n" +
                "                            <option> 6 </option>\n" +
                "                            <option> 6.5 </option>\n" +
                "                            <option> 7 </option>\n" +
                "                            <option> 7.5 </option>\n" +
                "                            <option> 8 </option>\n" +
                "                            <option> 8.5 </option>\n" +
                "                            <option> 9 </option>\n" +
                "                            <option> 9.5 </option>\n" +
                "                            <option> 10</option>\n" +
                "                            <option> 10.5 </option>\n" +
                "                            <option> 11 </option>\n" +
                "                            <option> 11.5</option>\n" +
                "                            <option> 12 </option>\n" +
                "                            <option> 12.5 </option>\n" +
                "                            <option> 13 </option>\n" +
                "                            <option> 13.5 </option>\n" +
                "                        </select>\n" +
                "                    </div>\n" +
                "                    <div class = \"col-33\">\n" +
                "                        <!-- <label for=\"quant\"></label> -->\n" +
                "                        <input class = \"pass-quantity\" type =\"number\" id = \"quant\" name = \"Quantity \" value = \"1\" min = \"0\" max = \"3\"> \n" +
                "                    </div>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class = \"row-3\">\n" +
                "                    <h3 id = \"totalCost\" class = \"col-50 notbold\" style=\"padding-bottom:10px\"> Total: $288</h3>\n" +
                "                </div>\n" +
                "                <div class = \"row-3\">\n" +
                "                    <div class = \"col-50\">\n" +
                "                        <h3> Billing Information </h3>\n" +
                "                        <label for=\"fname\"> Full Name</label>\n" +
                "                        <input type=\"text\" id=\"fname\" name=\"Full Name \" placeholder=\"Peter Anteater\" class =\"required\">\n" +
                "                        <label for=\"email\"> Email</label>\n" +
                "                        <input type=\"text\" id=\"email\" name=\"Email \" placeholder=\"anteater@uci.edu\">\n" +
                "                        <p id = \"validemail\" class =\"error\"> </p>\n" +
                "                        <label for=\"addr\"> Address</label>\n" +
                "                        <input type=\"text\" id=\"addr\" name=\"Address \" placeholder=\"123 Campus Dr\">  \n" +
                "                       \n" +
                "                        <div class = \"row-3\">\n" +
                "                            <div class = \"col-33\">\n" +
                "                                <label for=\"city\"> City</label>\n" +
                "                                <input type=\"text\" id=\"city\" name=\"City \" placeholder=\"Irvine\">  \n" +
                "                            </div>\n" +
                "                            <div class = \"col-33\">\n" +
                "                                <label for=\"state\"> State</label>\n" +
                "                                <input type=\"text\" id=\"state\" name=\"State \" placeholder=\"CA\">\n" +
                "                            </div>\n" +
                "                            <div class = \"col-33\">\n" +
                "                                <label for=\"zip\"> Zip Code</label>\n" +
                "                                <input type=\"text\" id=\"zip\" name=\"Zip Code \" placeholder=\"12345\">\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class = \"col-50\">\n" +
                "                        <h3> Card Payment</h3>\n" +
                "                        <label for=\"creditname\"> Name on Card</label>\n" +
                "                        <input type=\"text\" id=\"cname\" name=\"Card Owner \" placeholder=\"Peter Anteater\">\n" +
                "                        <label for=\"cardnum\"> Credit Card Number</label>\n" +
                "                        <input type=\"text\" id=\"cardnum\" name=\"Card Number \" placeholder=\"1234 2345 3456 4567\">\n" +
                "\n" +
                "                        <div class = \"row-3\">\n" +
                "                            <div class = \"col-33\">\n" +
                "                                <label for=\"month\"> Exp Month</label>\n" +
                "                                <input type=\"text\" id=\"month\" name=\"Card Expiration Month \" placeholder=\"Jan\">  \n" +
                "                            </div>\n" +
                "                            <div class = \"col-33\">\n" +
                "                                <label for=\"year\"> Exp Year</label>\n" +
                "                                <input type=\"text\" id=\"year\" name=\"Card Expiration Year \" placeholder=\"2030\">\n" +
                "                            </div>\n" +
                "                            <div class = \"col-33\">\n" +
                "                                <label for=\"cvv\"> CVV</label>\n" +
                "                                <input type=\"text\" id=\"cvv\" name=\"Card CVV \" placeholder=\"123\">\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <h3 class=\"notbold\"> Shipping Method</h3>\n" +
                "\n" +
                "                        <div class = \"row-3\">\n" +
                "                            <div class = \"col-50\">\n" +
                "                                <select id = \"shipping\" name = \"Shipping Method \">\n" +
                "                                    <option>Standard Shipping</option>\n" +
                "                                    <option>Express Shipping</option>\n" +
                "                                    <option>First Class Shipping</option>\n" +
                "                                </select>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <!-- <div class=\"row-3\"> -->\n" +
                "                    <input id=\"sendEmail\" type=\"submit\" name =\"submit\" value=\"Send\" class=\"order\">           \n" +
                "                <!-- </div> -->\n" +
                "            </form>     \n" +
                "        </div>\n" +
                "    </div>");
        out.println("</html></body>");
    }
}
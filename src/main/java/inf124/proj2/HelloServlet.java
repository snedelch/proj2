package inf124.proj2;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String userId = (String) session.getAttribute("UserID");

        out.println("<h1> Hello, " + userId + "</h1>");

        ;
    }

    public void destroy() {
    }
}
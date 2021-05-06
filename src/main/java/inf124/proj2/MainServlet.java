package inf124.proj2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "MainServlet", value = "")
public class MainServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        if (session.isNew()){
            String userId = String.valueOf(ThreadLocalRandom.current().nextInt());
            session.setAttribute("UserID", userId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hello-servlet");
            requestDispatcher.include(request, response);
        }
        PrintWriter writer = response.getWriter();
        writer.write("<html> <body> <h1> This is the main servlet</h1>");
        RequestDispatcher rd = request.getRequestDispatcher("/AllProducts");
        rd.include(request, response);
        RequestDispatcher rd2 = request.getRequestDispatcher("/MostRecent");
        rd2.include(request, response);
        //String user_id = (String) session.getAttribute("user_id");

//        writer.println("<html><body>");
        writer.println("</html></body>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

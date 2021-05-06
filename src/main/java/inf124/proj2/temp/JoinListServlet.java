package inf124.proj2.temp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "JoinList", value = "/JoinList")
public class JoinListServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println("uuid = " + uuid);

        PrintWriter writer = resp.getWriter();
        writer.println("<html> <body>");
        String name = req.getParameter("name");
        writer.println("<h3> Your data has been submitted! </h3>");
        writer.println("</body> </html>");
    }
}
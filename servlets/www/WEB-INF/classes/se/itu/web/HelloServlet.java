package se.itu.web;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      request.setCharacterEncoding(UTF_8.name());
      response.setContentType("text/html;charset="+UTF_8.name());
      PrintWriter out =
        new PrintWriter(new OutputStreamWriter(response.getOutputStream(),
                                               UTF_8), true);
      out.println("<!DOCTYPE html>");
      out.println("<html lang=\"en\">");
      out.println("<head><title>Hello Servlet!</title></head>");
      out.println("<body>");
      out.println("<h1>Hello Servlet!</h1>");
      out.println("</body>");
      out.println("</html>");
      out.close();
    }
}

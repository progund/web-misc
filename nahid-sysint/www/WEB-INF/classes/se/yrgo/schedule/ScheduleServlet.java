package se.yrgo.schedule;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ScheduleServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      request.setCharacterEncoding(UTF_8.name());
      ParamParser parser = new ParamParser(request);
      
      response.setContentType(parser.contentType());
      PrintWriter out =
        new PrintWriter(new OutputStreamWriter(response.getOutputStream(),
                                               UTF_8), true);
      Assignments db = AssignmentsFactory.getAssignments();
      List<Assignment> assignments = new ArrayList<>();
      try {
        StringBuilder table;
        switch (parser.type()){
          case ALL:
            assignments = db.all();
            break;
          case TEACHER_ID_AND_DAY:
            assignments = db.forTeacherAt(parser.teacherId(), parser.day());
            break;
          case DAY:
            assignments = db.at(parser.day());
            break;
          case TEACHER_ID:
            assignments = db.forTeacher(parser.teacherId());
        }
      } catch (AccessException e) {
        out.println("Error fetching data: " + e.getMessage());
        System.err.println("Error: " +e);
        e.printStackTrace();
      }
      Formatter formatter = FormatterFactory.getFormatter(parser.format());
      String result = formatter.format(assignments);
      out.println(result);
      out.close();
    }
}

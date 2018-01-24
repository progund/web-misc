package se.yrgo.schedule;

import java.util.List;

/**
 * A class implementing the Formatter interface. Formats a List of Assignment
 * to HTML.
 *
 */
public class HtmlFormatter implements Formatter {
  public String format(List<Assignment> assignments) {
    StringBuffer html = new StringBuffer("<!DOCTYPE html>\n")
      .append("<html lang=\"en\">\n")
      .append("<head><title>Substitutes R Us - Schedule API</title></head>\n")
      .append("<body>\n")
      .append("<h1>Schedule</h1>\n");
    if (assignments.size() == 0) {
      html.append("<em>No assignments found.</em>");
    } else {
      html.append("<table border=\"1\">\n <tr>\n  <th>Teacher</th>\n  <th>date</th>\n  <th>school</th>\n </tr>\n");
      for (Assignment assignment : assignments) {
        html.append(" <tr>\n  <td>").append(assignment.teacher()).append("</td>\n")
          .append("  <td>").append(assignment.date()).append("</td>\n")
          .append("  <td>").append(assignment.school()).append("</td>\n </tr>\n");
      }          
      html.append("</table>\n");
    }
    html.append("</body>\n");
    html.append("</html>\n");
    return html.toString();
  }
}

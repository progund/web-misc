package se.yrgo.schedule;

import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ParamParser {
  enum QueryType {
    ALL,
    TEACHER_ID,
    DAY,
    TEACHER_ID_AND_DAY
  }
  
  private HttpServletRequest request;
  private QueryType type;
  private String teacherId;
  private String day;
  private String contentType;
  private String format;
  
  public ParamParser(HttpServletRequest request) {
    this.request = request;
    parseValues();
    parseType();
    parseContentType();
    /*System.out.printf("Type: %s teacherId: %s day: %s Content-Type: %s Format: %s\n",
      type.toString(), teacherId, day, contentType, format); */
  }

  private void parseContentType() {
    // Default to text/html
    if (format == null) {
      contentType = "text/html;charset=" + UTF_8.name();
    }
  }
  public String contentType() {
    return contentType;
  }
  private void parseType() {
    if (teacherId == null && day == null) {
      type = QueryType.ALL;
    } else if (day != null && teacherId != null) {
      type = QueryType.TEACHER_ID_AND_DAY;
    } else if (day != null && teacherId == null) {
      type = QueryType.DAY;
    } else {
      type = QueryType.TEACHER_ID;
    }
  }
  private void parseValues() {
    this.format = request.getParameter("format");
    if (format != null) {
      format = format.toLowerCase();
    } else {
      format = "html";
    }
    this.day = request.getParameter("day");
    this.teacherId = request.getParameter("substitute_id");    
  }

  public String format() {
    return format;
  }
  
  public String day() {
    return day;
  }

  public String teacherId() {
    return teacherId;
  }
  
  public QueryType type() {
    return type;
  }

  public String toString() {
    return String.format("Type: %s teacherId: %s day: %s Content-Type: %s Format: %s\n",
                         type.toString(), teacherId, day, contentType, format);
  }
}

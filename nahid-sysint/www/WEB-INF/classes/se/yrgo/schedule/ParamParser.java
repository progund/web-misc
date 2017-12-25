package se.yrgo.schedule;

import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>Parses a request for the Servlet</p>
 * <p>Tries to detect:
 * <ul>
 * <li>The type (all|day|teacher_id|day and teacher_id</li>
 * <li>day</li>
 * <li>teacherId</li>
 * <li>contentType</li>
 * <li>format (html|json|xml) (only html is implemented in this example)</li>
 * </ul>
 * </p>
 */
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

  /**
   * Constructs a new ParamParser from the Servlet's request object
   * @param request The Servlet's request, whose GET params will be parsed
   */
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

  /**
   * Returns the content type of the request
   * @return The content-type as a String, or "html" (default) if none is given
   */
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

  /**
   * Returns the format from the request param format, as a String
   * @return The format request parameter, as a String, or null if none is given
   */
  public String format() {
    return format;
  }

  /**
   * Returns the day paramteter of the request
   * @return The day parameter of the request, as a String, or null if none is given
   */
  public String day() {
    return day;
  }

  /**
   * Returns the teacherId (from the substitute_id parameter), as a String
   * @return The teacherId, as a String, or null if none is given
   */
  public String teacherId() {
    return teacherId;
  }

  /**
   * Returns the QueryType of the request, one of ALL, TEACHER_ID, DAY, and,
   * TEACHER_ID_AND_DAY (an enum of this class)
   * @return the QueryType found in this query. See the QueryType enum.
   */
  public QueryType type() {
    return type;
  }

  /**
   * Returns this parser as a String representation. Mostly for debuggin.
   * @return This ParamParser as a String representation.
   */
  @Override
  public String toString() {
    return String.format("Type: %s teacherId: %s day: %s Content-Type: %s Format: %s\n",
                         type.toString(), teacherId, day, contentType, format);
  }
}

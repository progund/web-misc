package se.yrgo.schedule;

/**
 * A factory to get a formatter (only HTML is implemented)
 */
public class FormatterFactory {

  private static Formatter XML_FORMATTER;
  private static Formatter HTML_FORMATTER = new HtmlFormatter();
  private static Formatter JSON_FORMATTER;

  /**
   * Returns a formatter for the given contentType
   * @param The content type you want to format to (HTML is the only implemented)
   * @return A Formatter of the correct type, depending on the provided
   * contentType. Defaults to HTML. Cannot handle null.
   */
  public static Formatter getFormatter(String contentType) {
    if (contentType.contains("xml")) {
      return XML_FORMATTER;
    } else if (contentType.contains("json")) {
      return JSON_FORMATTER;
    } else {
      return HTML_FORMATTER;
    }
  }
}

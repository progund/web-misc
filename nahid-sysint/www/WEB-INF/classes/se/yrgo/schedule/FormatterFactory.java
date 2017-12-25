package se.yrgo.schedule;

public class FormatterFactory {

  private static Formatter XML_FORMATTER;
  private static Formatter HTML_FORMATTER = new HtmlFormatter();
  private static Formatter JSON_FORMATTER;
  
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

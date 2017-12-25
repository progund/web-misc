package se.yrgo.schedule;

/**
 * Represents an assignment for at substitute teacher,
 * with information on the date of the assignment (including
 * start time), the teacher's name and the school of the 
 * assignment (the school where the teacher should teach).
 */
public class Assignment {
  private String teacher;
  private String date;
  private String school;

  public Assignment(String teacher, String date, String school) {
    this.teacher = teacher;
    this.date = date;
    this.school = school;
  }

  public String teacher() {
    return this.teacher;
  }

  public String date() {
    return this.date;
  }

  public String school() {
    return this.school;
  }

  @Override
  public String toString() {
    return new StringBuilder(date)
      .append(" (").append(teacher).append(")")
      .append(" at ").append(school)
      .toString();
  }
}

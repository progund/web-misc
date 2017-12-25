package se.yrgo.schedule;

import java.util.List;

/**
 * <p>A small interface declaring the access methods to the data.</p>
 */
public interface Assignments {
  /**
   * Returns all Assignments for all teachers for all dates
   */
  public List<Assignment> all() throws AccessException;
  /**
   * Returns the Assignmens for the given teacher
   * @param teacherId The id of the teacher
   * @return the Assigments for the given teacher
   */
  public List<Assignment> forTeacher(String teacherId) throws AccessException;

  /**
   * Returns the assignments at a given date
   * @param date The date, as a String (YYYY-mm-ddd)
   * @return The Assignments at the given date
   */
  public List<Assignment> at(String date) throws AccessException;

  /**
   * Returns the assignments for the given teacher at the given date
   * @param teacherId The id of the teacher
   * @param date The date in question
   * @return The assignments for the given teacher at the given date
   */
  public List<Assignment> forTeacherAt(String teacherId, String date) throws AccessException;
}

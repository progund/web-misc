package se.yrgo.schedule;

import java.util.List;

public interface Assignments {
  public List<Assignment> all() throws AccessException;
  public List<Assignment> forTeacher(String teacherId) throws AccessException;
  public List<Assignment> at(String date) throws AccessException;
  public List<Assignment> forTeacherAt(String teacherId, String date) throws AccessException;
}

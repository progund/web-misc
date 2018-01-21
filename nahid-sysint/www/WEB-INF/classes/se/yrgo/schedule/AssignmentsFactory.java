package se.yrgo.schedule;

public class AssignmentsFactory {
  private AssignmentsFactory() {}
  public static Assignments getAssignments() {
    return new DatabaseAssignments();
  }
  
}

package zixuanwen3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

public class Functions {
    
    /*
     *  function: getAllStudents
     *  purpose:  print all information of students
     */
    public static void getAllStudents(Connection connection) {
        try {
            System.out.println("Processing...");
            System.out.println("--------------------------------------------------------------------------------------------");
            // Default command
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            // Spacing settings
            System.out.printf("%-10s %-15s %-15s %-30s %-15s\n", "ID", 
                             "First Name", "Last Name", "Email", "Enrollment Date");
            System.out.println("--------------------------------------------------------------------------------------------");
            
            //  Information printing
            while (resultSet.next()) {
                System.out.printf("%-10d %-15s %-15s %-30s %-15s\n",
                                   resultSet.getInt("student_id"),
                                   resultSet.getString("first_name"),
                                   resultSet.getString("last_name"),
                                   resultSet.getString("email"),
                                   resultSet.getDate("enrollment_date"));
            }
            System.out.println("--------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *  function: addStudent
     *   purpose: add a information of single student to database
     *        in: first_name
     *        in: last_name
     *        in: email
     *        in: enrollment_date
     *        in: connection
     */
    public static void addStudent(String first_name, String last_name, 
                                  String email, String enrollment_date, Connection connection) {
        // Default command
        String SQL = "INSERT INTO students(first_name, last_name, email, enrollment_date) VALUES(?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, Date.valueOf(enrollment_date));
            int rowsInserted = pstmt.executeUpdate();

            //  Check the insertion status
            if (rowsInserted > 0) {
                System.out.println("A new student has been added.");
            } else {
                System.out.println("ERROR: A new student could not be added!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *  function: updateStudentEmail
     *   purpose: update a student's email
     *        in: student_id
     *        in: new_email
     *        in: connection
     */
    public static void updateStudentEmail(int student_id, String new_email, Connection connection) {
        // Default command
        String SQL = "UPDATE students SET email = ? WHERE student_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, new_email);
            pstmt.setInt(2, student_id);
            int rowsUpdated = pstmt.executeUpdate();

            //  Check the update status
            if (rowsUpdated > 0) {
                System.out.println("Student email updated successfully.");
            } else {
                System.out.println("ERROR: Could not update the student's email!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *  function: deleteStudent
     *   purpose: delete a information of student from database
     *        in: student_id
     *        in: connection
     */
    public static void deleteStudent(int student_id, Connection connection) {
        String SQL = "DELETE FROM students WHERE student_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setInt(1, student_id);
            int rowsDeleted = pstmt.executeUpdate();

            //  Check the update status
            if (rowsDeleted > 0) {
                System.out.println("Student with ID " + student_id + " has been deleted successfully.");
            } else {
                System.out.println("ERROR: Could not delete the student with ID " + student_id + "!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


package zixuanwen3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/*
 * function: Main
 * purpose: function to control the whole process
 * @param connection: A connection channel and used in Main and Functions functions
 */
public class Main {
    public static void main(String[] args) {
        // database information
        String url = "jdbc:postgresql://localhost:3526/Assignment03";
        String user = "postgres";
        String password = "3526384";

        try{
            System.out.println("Connecting to the database...");
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            /*
            if(connection != null){
                System.out.println("Connected to the databse");
            } else{
                System.out.println("Failed to connect to the databse");
            } */
            System.out.println("Welcome to SQL database !");

            Scanner scanner = new Scanner(System.in);
            int choice;

            // Create a while loop for continuously manipulate the database
            do{
                System.out.println("\nEnter a number (1-4) to select an operation, or 0 to exit");
                System.out.println("1. Get all students");
                System.out.println("2. Add a student");
                System.out.println("3. Update a student's email");
                System.out.println("4. Delete a student");
                System.out.println("0. Exit");
                System.out.print("Please enter your choice: ");
                
                // Check the type of input 
                if (scanner.hasNextInt()){
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    // Call function in Functions depend on choice
                    switch (choice) {
                        case 1:
                            System.out.print("\n");
                            Functions.getAllStudents(connection);
                            break;

                        case 2:
                            System.out.print("\n");
                            System.out.println("------------------------------------");
                            System.out.print("Enter first name: ");
                            String firstName = scanner.nextLine();
    
                            System.out.print("Enter last name: ");
                            String lastName = scanner.nextLine();
    
                            System.out.print("Enter email: ");
                            String email = scanner.nextLine();
    
                            System.out.print("Enter enrollment date (YYYY-MM-DD): ");
                            String enrollmentDate = scanner.nextLine();
    
                            Functions.addStudent(firstName, lastName, email, enrollmentDate, connection);
                            System.out.println("------------------------------------");
                            break;

                        case 3:
                            System.out.print("\n");
                            System.out.println("------------------------------------");
                            System.out.print("Enter student ID: ");
                            int studentId = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Enter new email: ");
                            String newEmail = scanner.nextLine();
                            Functions.updateStudentEmail(studentId, newEmail, connection);
                            System.out.println("------------------------------------");
                            break;

                        case 4:
                            System.out.print("\n");
                            System.out.println("------------------------------------");
                            System.out.print("Enter student ID to delete: ");
                            int deleteStudentId = scanner.nextInt();
                            Functions.deleteStudent(deleteStudentId, connection);
                            System.out.println("------------------------------------");
                            break;

                        case 0:
                            System.out.print("\n");
                            System.out.println("Exiting...");
                            break;

                        // Detect the number > 4 or number < 0 
                        default:
                            System.out.print("\n");
                            System.out.println("Invalid choice. Please enter a number between 0 and 4.");
                            break;
                    } 
                // Detect the non digital character
                } else {
                    System.out.print("\n");
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                    choice = -1;
                }
                
            } while (choice != 0);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
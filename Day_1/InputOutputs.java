package Day_1;

/**
 * InputOutputs class - Demonstrates input and output operations in Java
 * 
 * This class covers various input and output methods in Java:
 * 
 * Output Types:
 * - System.out.println(): Prints with newline
 * - System.out.print(): Prints without newline
 * - System.out.printf(): Formatted output using format specifiers
 * 
 * Input Types:
 * - Scanner: For reading primitive types and strings from console
 * - BufferedReader: For reading text from input streams
 * 
 * Format Specifiers (for printf):
 * - %d: Integer
 * - %f: Floating-point
 * - %s: String
 * - %c: Character
 * - %b: Boolean
 * - %%: Percent sign
 * - \n: Newline
 * - \t: Tab
 */
public class InputOutputs {
    public static void main(String[] args) {
        // Import Scanner for input
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        // Output examples
        
        // Basic output with println (adds newline)
        System.out.println("This is a line of output.");
        
        // Output without newline using print
        System.out.print("This is ");
        System.out.print("on the same line.");
        
        // Output with newline
        System.out.println("\nThis is on a new line.");
        
        // Using escape sequences
        System.out.println("This is a tab:\tSee?");
        System.out.println("This is a backslash: \\");
        
        // Formatted output using printf
        int age = 25;
        double height = 5.9;
        String name = "John";
        boolean isStudent = true;
        System.out.printf("Name: %s, Age: %d, Height: %.1f, Student: %b%n", name, age, height, isStudent);
        
        // Input examples
        
        // Reading string input
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        
        // Reading integer input
        System.out.print("Enter your age: ");
        int userAge = scanner.nextInt();
        
        // Reading double input
        System.out.print("Enter your height: ");
        double userHeight = scanner.nextDouble();
        
        // Reading boolean input
        System.out.print("Are you a student? (true/false): ");
        boolean userIsStudent = scanner.nextBoolean();
        
        // Output user input
        System.out.printf("Hello %s! You are %d years old, %.1f feet tall, and student status: %b%n", userName, userAge, userHeight, userIsStudent);
        
        // Close scanner
        scanner.close();
    }
}

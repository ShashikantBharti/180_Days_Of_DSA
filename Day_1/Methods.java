package Day_1;

/**
 * Methods class - Demonstrates method creation and usage in Java
 * 
 * Methods in Java are blocks of code that perform specific tasks.
 * They can take parameters, return values, and be called from other parts of the program.
 * 
 * Types of methods:
 * - Static methods: Belong to the class, can be called without an instance.
 * - Instance methods: Belong to objects, require an instance to call.
 * - Void methods: Do not return a value.
 * - Return methods: Return a value of a specified type.
 * 
 * Method components:
 * - Access modifier (public, private, etc.)
 * - Return type (void or data type)
 * - Method name
 * - Parameters (optional)
 * - Method body
 */
public class Methods {
    
    /**
     * Main method - Entry point of the program
     * 
     * @param args Command line arguments (not used)
     * 
     * This method demonstrates calling other methods.
     */
    public static void main(String[] args) {
        // Call a void method
        greet();
        
        // Call a method with parameters and return value
        int sum = add(5, 10);
        System.out.println("Sum: " + sum);
        
        // Call a method that returns a string
        String message = getMessage();
        System.out.println(message);
    }
    
    /**
     * Greet method - Prints a greeting message
     * 
     * This is a static void method that performs an action without returning a value.
     */
    public static void greet() {
        System.out.println("Hello, World!");
    }
    
    /**
     * Add method - Adds two integers
     * 
     * @param a First integer
     * @param b Second integer
     * @return Sum of a and b
     * 
     * This is a static method that takes parameters and returns an integer.
     */
    public static int add(int a, int b) {
        return a + b;
    }
    
    /**
     * GetMessage method - Returns a sample message
     * 
     * @return A string message
     * 
     * This is a static method that returns a string value.
     */
    public static String getMessage() {
        return "This is a sample message from a method.";
    }
}

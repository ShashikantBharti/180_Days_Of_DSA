package Day_1;

/**
 * Basics class - Entry point for Day 1 DSA practice
 * 
 * This class demonstrates a simple Java program that prints
 * "Hello World!" to the console output.
 * 
 * Keywords used in this class:
 * - package: Declares the namespace/directory where this class belongs
 * - class: Defines a blueprint for creating objects
 * - public: Access modifier allowing this method to be called from anywhere
 * - static: Keyword indicating this method belongs to the class itself, not instances
 * - void: Return type indicating this method does not return any value
 * - String: Data type representing a sequence of characters
 */
class Basics {
    
    /**
     * Main method - Entry point of the Java application
     * 
     * @param args Command line arguments passed to the program (String array)
     * @return void - This method does not return any value
     * 
     * Keyword explanations:
     * - public: Access modifier that allows JVM to access this method from outside the class
     * - static: Indicates this is a class method; JVM can call it without instantiating the class
     * - void: Return type specifying that main() does not return any data to the caller
     * - String[]: Parameter type representing an array of String objects for command-line arguments
     * 
     * The JVM automatically looks for this exact method signature to start program execution.
     * Without the 'static' keyword, the JVM would need to create an object first.
     * Without 'public', the JVM would not have access to call this method.
     */
    public static void main(String[] args) {
        // Print "Hello World!" message to the console using System.out.println()
        System.out.println("Hello World!");
    }
}
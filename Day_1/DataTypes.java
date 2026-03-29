package Day_1;

/**
 * DataTypes class - Demonstrates all primitive data types in Java
 * 
 * This class showcases the eight primitive data types in Java:
 * - byte: 8-bit signed integer (-128 to 127)
 * - short: 16-bit signed integer (-32,768 to 32,767)
 * - int: 32-bit signed integer (-2^31 to 2^31-1)
 * - long: 64-bit signed integer (-2^63 to 2^63-1)
 * - float: 32-bit floating-point number
 * - double: 64-bit floating-point number
 * - char: 16-bit Unicode character
 * - boolean: true or false value
 * 
 * Additionally, demonstrates a reference data type (String).
 */
public class DataTypes {
    
    /**
     * Main method - Entry point demonstrating Java data types
     * 
     * @param args Command line arguments (not used)
     * 
     * This method declares variables of all primitive data types,
     * initializes them with sample values, and prints them to the console.
     * Also includes an example of a reference data type (String).
     */
    public static void main(String[] args) {
        // Primitive Data Types
        
        // byte: 8-bit signed integer, range -128 to 127
        byte myByte = 100;
        
        // short: 16-bit signed integer, range -32,768 to 32,767
        short myShort = 30000;
        
        // int: 32-bit signed integer, range -2^31 to 2^31-1
        int myInt = 42;
        
        // long: 64-bit signed integer, range -2^63 to 2^63-1
        long myLong = 123456789L; // Note: 'L' suffix for long literals
        
        // float: 32-bit floating-point number
        float myFloat = 3.14f; // Note: 'f' suffix for float literals
        
        // double: 64-bit floating-point number (default for decimals)
        double myDouble = 3.14159;
        
        // char: 16-bit Unicode character
        char myChar = 'A';
        
        // boolean: represents true or false
        boolean myBoolean = true;
        
        // Reference Data Types
        // String: sequence of characters (reference type)
        String myString = "Hello, World!";
        
        // Output the values of all variables to the console
        System.out.println("Byte: " + myByte);
        System.out.println("Short: " + myShort);
        System.out.println("Integer: " + myInt);
        System.out.println("Long: " + myLong);
        System.out.println("Float: " + myFloat);
        System.out.println("Double: " + myDouble);
        System.out.println("Character: " + myChar);
        System.out.println("Boolean: " + myBoolean);
        System.out.println("String: " + myString);
    }
}

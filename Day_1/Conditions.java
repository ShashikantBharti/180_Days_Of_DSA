package Day_1;

/**
 * Conditions class - Demonstrates conditional statements in Java
 * 
 * Conditional statements control the flow of execution based on conditions:
 * 
 * 1. If Statement: Executes code if condition is true.
 *    Use case: Simple decision making.
 * 
 * 2. If-Else Statement: Executes one block if true, another if false.
 *    Use case: Binary choices.
 * 
 * 3. If-Else If-Else Statement: Checks multiple conditions sequentially.
 *    Use case: Multiple mutually exclusive options.
 * 
 * 4. Switch Statement: Selects one of many code blocks to execute.
 *    Use case: When comparing a variable against multiple values.
 */
public class Conditions {
    
    /**
     * Main method - Demonstrates various conditional statements in Java
     * 
     * @param args Command line arguments (not used)
     * 
     * This method showcases examples of if, if-else, if-else if-else, and switch statements,
     * including their typical use cases and syntax.
     */
    public static void main(String[] args) {
        // If Statement Example
        // Use case: Execute code only if condition is true
        int number = 10;
        if (number > 0) {
            System.out.println("The number is positive.");
        }
        
        // If-Else Statement Example
        // Use case: Choose between two actions based on condition
        if (number > 0) {
            System.out.println("The number is positive.");
        } else {
            System.out.println("The number is not positive.");
        }
        
        // If-Else If-Else Statement Example
        // Use case: Multiple conditions to check sequentially
        if (number > 0) {
            System.out.println("The number is positive.");
        } else if (number < 0) {
            System.out.println("The number is negative.");
        } else {
            System.out.println("The number is zero.");
        }
        
        // Switch Case Example
        // Use case: Select from multiple options based on variable value
        int dayOfWeek = 3;
        switch (dayOfWeek) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day of the week.");
        }
    }
}

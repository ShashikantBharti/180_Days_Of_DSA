package Day_1;

/**
 * Loops class - Demonstrates all types of loops in Java
 * 
 * Java provides several loop constructs for repetitive execution:
 * 
 * 1. For Loop: Used when the number of iterations is known beforehand.
 *    Use case: Iterating over arrays or performing a fixed number of operations.
 * 
 * 2. While Loop: Used when the number of iterations is not known and depends on a condition.
 *    Use case: Reading input until a certain condition is met (e.g., end of file).
 * 
 * 3. Do-While Loop: Similar to while loop, but executes at least once.
 *    Use case: Menus or prompts that must display at least once before checking condition.
 * 
 * 4. Enhanced For Loop (For-Each): Used for iterating over collections or arrays.
 *    Use case: Simplifying iteration over elements without needing an index.
 */
public class Loops {
    
    /**
     * Main method - Demonstrates various loop types in Java
     * 
     * @param args Command line arguments (not used)
     * 
     * This method showcases examples of for, while, do-while, and enhanced for loops,
     * including their typical use cases and syntax.
     */
    public static void main(String[] args) {
        // For Loop Example
        // Use case: When you know the exact number of iterations
        System.out.println("For Loop:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Iteration: " + i);
        }
        
        // While Loop Example
        // Use case: When iterations depend on a runtime condition
        System.out.println("\nWhile Loop:");
        int j = 0;
        while (j < 5) {
            System.out.println("Iteration: " + j);
            j++;
        }
        
        // Do-While Loop Example
        // Use case: When the loop body must execute at least once
        System.out.println("\nDo-While Loop:");
        int k = 0;
        do {
            System.out.println("Iteration: " + k);
            k++;
        } while (k < 5);
        
        // Enhanced For Loop (For-Each) Example
        // Use case: Iterating over arrays or collections without index management
        System.out.println("\nEnhanced For Loop:");
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) {
            System.out.println("Number: " + num);
        }
    }
}

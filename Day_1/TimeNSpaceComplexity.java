package Day_1;

/**
 * TimeNSpaceComplexity class - Demonstrates time and space complexity concepts in DSA
 * 
 * Time Complexity: Measures how the runtime of an algorithm grows with input size.
 * - O(1): Constant time - runtime doesn't change with input size.
 * - O(log n): Logarithmic time - runtime grows slowly (e.g., binary search).
 * - O(n): Linear time - runtime grows linearly with input size.
 * - O(n log n): Linearithmic time - common in sorting algorithms like merge sort.
 * - O(n^2): Quadratic time - runtime grows quadratically (e.g., nested loops).
 * - O(2^n): Exponential time - runtime doubles with each input increase (e.g., recursive Fibonacci).
 * - O(n!): Factorial time - runtime grows factorially (e.g., brute-force permutations).
 * 
 * Space Complexity: Measures memory usage of an algorithm.
 * - O(1): Constant space - uses fixed memory regardless of input.
 * - O(n): Linear space - memory grows linearly with input size.
 * - O(n^2): Quadratic space - memory grows quadratically (e.g., 2D arrays).
 */
public class TimeNSpaceComplexity {
    
    /**
     * Main method - Demonstrates examples of different time and space complexities
     * 
     * @param args Command line arguments (not used)
     * 
     * This method includes examples for various time complexities and briefly touches on space.
     */
    public static void main(String[] args) {
        // Example of O(1) - Constant Time Complexity
        int a = 5;
        int b = 10;
        int sum = a + b; // This operation takes constant time
        System.out.println("Sum: " + sum);
        
        // Example of O(log n) - Logarithmic Time Complexity (Binary Search)
        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 7;
        int index = binarySearch(sortedArr, target); // O(log n) time
        System.out.println("Index of " + target + ": " + index);
        
        // Example of O(n) - Linear Time Complexity
        int[] arr = {1, 2, 3, 4, 5};
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i]; // This loop runs n times
        }
        System.out.println("Total: " + total);
        
        // Example of O(n log n) - Linearithmic Time Complexity (Merge Sort simulation)
        // Note: Actual merge sort would require more code; this is a simplified example
        System.out.println("O(n log n) example: Sorting array");
        // Assume sorting takes O(n log n)
        
        // Example of O(n^2) - Quadratic Time Complexity
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i] + "," + arr[j] + " "); // This nested loop runs n^2 times
            }
            System.out.println();
        }
        
        // Space Complexity Examples
        // O(1) space: Using fixed variables
        int x = 1; // Constant space
        
        // O(n) space: Array of size n
        int[] array = new int[5]; // Linear space
        
        // O(n^2) space: 2D array
        int[][] matrix = new int[3][3]; // Quadratic space
    }
    
    /**
     * Binary Search method - Example of O(log n) time complexity
     * 
     * @param arr Sorted array to search
     * @param target Value to find
     * @return Index of target or -1 if not found
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

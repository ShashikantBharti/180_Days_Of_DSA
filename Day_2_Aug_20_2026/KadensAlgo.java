package Day_2_Aug_20_2026;

/**
 * KadensAlgo
 * Finds the maximum sum of a continuous part of an array (a contiguous subarray) in a single pass. 
 * It works in fast O(n) time and uses O(1) extra space by keeping a running sum and 
 * resetting it to zero if the sum drops below zero.
 * 
 * 
 * arr = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * currentSum = max(arr[i], currentSum + arr[i])
 * maxSum = max(maxSum, currentSum)
 * 
 */
import java.util.*;
public class KadensAlgo {
    
    public static void main(String[] args) {
        int[] arr = {2, 1, -3, 4, -1, -2, 1, -5, 4};
        // printSubArr();
        
        int currentSum = arr[0];
        int maxSum = arr[0];
        
        for(int i = 1; i < arr.length; i++) {
            // currentSum = Math.max(arr[i], currentSum + arr[i]);
            // maxSum = Math.max(maxSum, currentSum);

            // int temp = currentSum + arr[i];
            // if (arr[i] > temp) {
            //     currentSum = arr[i];
            // } else {
            //     currentSum = temp;
            // }

            // if (maxSum < currentSum) {
            //     maxSum = currentSum;
            // }

            currentSum = arr[i] > (arr[i] + currentSum) ? arr[i] : (arr[i] + currentSum);
            maxSum = maxSum > currentSum ? maxSum : currentSum;
        }
        System.out.println("Max Sum Subarray = " + maxSum);
    }

    /**
     * arr = [1, 2, 3, 4];
     * 
     * [1], [2], [3], [4]
     * [1,2], [2,3], [3,4]
     * [1,2,3], [2,3,4]
     * [1,2,3,4]
     */
    public static void printSubArr() {
        int[] arr = {1, 2, 3, 4};
        // int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // For Size of Sub Array
        for(int size = 1; size <= arr.length; size++) {
            int[] subArr = new int[size];

            // for traverse array
            for(int i = 0; i <= arr.length - size; i++) {

                // Actual build Sub Array
                for(int k = 0; k < size; k++) {
                    subArr[k] = arr[i+k];
                }
                System.out.print(Arrays.toString(subArr) + ", ");
            }
            System.out.println();
        }
    }
}

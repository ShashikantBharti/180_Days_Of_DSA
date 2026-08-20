/**
 * DifferenceArray
 * A difference array is used to perform multiple range update queries in O(1) constant time per operation 
 * instead of looping through the entire range in O(N) linear time, 
 * reducing total time complexity from O(Q X N) to O(Q + N)
 * 
 * N = 10
 * arr = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 * Q = [l, r, x]
 * Q1 = [2, 5, 9]
 * 
 * output = [0, 1, 2+9, 3+9, 4+9, 5+9, 6, 7, 8, 9]
 * finaly = [0, 1, 11, 12, 13, 14, 6, 7, 8, 9]
 * 
 * Q2 = [3, 8, 10]
 * 
 * output = [0, 1, 11, 12+10, 13+10, 14+10, 6+10, 7+10, 8+10, 9]
 * finaly = [0, 1, 11, 22, 23, 24, 16, 17, 18, 9]
 * 
 * Complexity = N X Q
 * 
 * Using Difference Array
 * 
 * For Q1
 * 
 * DifferenceArray = [0, 1, 1, 1, 1, 1, 1, 1, 1, 1]
 * output[l] += x
 * output[2] += 9; output[2] = 11 => [0, 1, 10, 1, 1, 1, 1, 1, 1, 1]
 * output[r+1] -= x
 * output[6] -= 9; output[6] = -3 => [0, 1, 10, 1, 1, 1, -8, 1, 1, 1]
 * 
 * for Q2
 * output[3] += 10; output[3] = 13 => [0, 1, 10, 11, 1, 1, -8, 1, 1, 1]
 * output[9] -= 10; output[3] = 13 => [0, 1, 10, 11, 1, 1, -8, 1, 1, -9]
 * 
 * At last calculate prefixArray
 * finalOuput = [0, 1, 11, 22, 23, 24, 16, 17, 18, 9]
 * complexity = N + Q
 * 
 */
import java.util.Arrays;

public class DifferenceArray {

    public static void main(String[] args) {

        // case 1
        // for(n) {
        //     for(q){}
        // }
        // Complexity n X q
        // n = 10000
        // q = 200
        // c = n x q = 2 000 000

        // case 2
        // for(n){}
        // for(q){}
        // Complexity n + q
        // n = 10000
        // q = 200
        // c = 30000

        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = arr.length;
        int[] diff = new int[n];

        diff[0] = arr[0];
        for(int i = 1; i < n; i++) {
            diff[i] = arr[i] - arr[i - 1];
        }
        System.out.println("Difference Array " + Arrays.toString(diff)); // [0, 1, 1, 1, 1, 1, 1, 1, 1, 1]

        // int[] Q1 = {2, 5, 9};
        // int[] Q2 = {3, 8, 10};

        // int l = Q1[0];
        // int r = Q1[1];
        // int x = Q1[2];

        // diff[l] += x;
        // diff[r + 1] -= x;

        // System.out.println("Q1 Array " + Arrays.toString(diff)); // [0, 1, 10, 1, 1, 1, -8, 1, 1, 1]

        // l = Q2[0];
        // r = Q2[1];
        // x = Q2[2];

        // diff[l] += x;
        // diff[r + 1] -= x;
        
        // System.out.println("Q2 Array " + Arrays.toString(diff)); // [0, 1, 10, 11, 1, 1, -8, 1, 1, -9]

        int[][] Q = {{2, 5, 9}, {3, 8, 10}};
        for(int i = 0; i < Q.length; i++) {
            int l = Q[i][0];
            int r = Q[i][1];
            int x = Q[i][2];

            // diff[l] += x;
            // diff[r + 1] -= x;
            for(int j = l; j <= r; j++) {
                arr[j] += x;
            }
        }

        // Prefix Sum
        // int[] output = new int[n];

        // output[0] = diff[0];
        // for(int i = 1; i < n; i++) {
        //     output[i] = diff[i] + output[i-1];
        // }
        // System.out.println("Final " + Arrays.toString(output)); // [0, 1, 11, 22, 23, 24, 16, 17, 18, 9]
        System.out.println("Final Arr " + Arrays.toString(arr)); // [0, 1, 11, 22, 23, 24, 16, 17, 18, 9]
    }
}

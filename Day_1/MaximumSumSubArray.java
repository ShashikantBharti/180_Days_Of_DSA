/**
 * Maximum Sub Array
 * arr = [1,4,3,5,9]
 * subArray = 
 * [1],[4],[3],[5],[9]
 * [1,4],[4,3],[3,5],[5,9]
 * [1,4,3],[4,3,5],[3,5,9]
 * [1,4,3,5],[4,3,5,9]
 * [1,4,3,5,9]
 * 
 */
import java.util.*;
public class MaximumSumSubArray {

   public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = arr.length;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                ArrayList<Integer> subArr = new ArrayList<>();
                int sum = 0;
                for(int k = i; k <= j; k++) {
                    subArr.add(arr[k]);
                    sum += arr[k];
                }
                System.out.print(subArr);
                System.out.print(" " + sum + ", ");
                if(sum > max) {
                    max = sum;
                    list.clear();
                    list.add(subArr);
                }
            }
            System.out.println();
        }
        System.out.println("Max " + max);
        System.out.println("list " + list.get(0));
   }
}

/**
 * Prefix sum
 * arr = [1, 2, 3, 4, 5, 6]
 * prefixSummArr = [1, 3, 6, 10, 15, 21]
 * 
 * sum of range [0, 3] = prefixSumArr[3] = 10
 * sum of range [3, 5] = prefixSumArr[5] - prefixSumArr[3-1] = 21 - 6 = 15
 * sum of range [1, 4] = prefixSumArr[4] - prefixSumArr[1-1] = 15 - 1 = 14
 **/
import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    prefixSum();
	}
	
	public static void prefixSum() {
	    int[] arr = {1, 2, 3, 4, 5, 6};
		int[] prefixSum = new int[arr.length];
		
		// prefixSum[0] = arr[0];
		for(int i = 0; i <= arr.length; i++) {
		  //  if(i == 0) {
		  //      prefixSum[i] = arr[i];
		  //      continue;
		  //  }
		    prefixSum[i] = arr[i] + prefixSum[i - 1];
		} 
		System.out.println("Prefix Sum " + Arrays.toString(prefixSum));
	}
}


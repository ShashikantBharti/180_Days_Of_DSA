/**
 * Prefix sum
 * arr = [1, 2, 3, 4, 5, 6]
 * prefixSummArr = [1, 3, 6, 10, 15, 21]
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


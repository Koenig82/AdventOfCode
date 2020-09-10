package adventOfCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations {
	
	public static <T> List<List<T>> getPermutations(List<T> list) {
		
		List<List<T>> permutations = new ArrayList<>();
		permute(list, permutations, list.size());

		return permutations;
	}
	
	private static <T> void permute(List<T> arr, List<List<T>> comb, int k){
		
		if(k == 1) {
			List<T> newlist = new ArrayList<T>(arr) ;
        	comb.add(newlist);
		}else {
			permute(arr, comb, k-1);
			for(int i = 0; i < k-1; i++){
				
				if(k % 2 == 0) {
					Collections.swap(arr, i, k-1);
					
				}else {
					Collections.swap(arr, 0, k-1);
					
				}
				permute(arr, comb, k-1);
			}
		}
    }
}

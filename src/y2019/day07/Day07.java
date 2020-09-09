package y2019.day07;

import java.util.ArrayList;
import java.util.List;

import adventOfCode.AdventOfCode;
import y2019.intcodeComputer.Threadpool;

public class Day07 extends AdventOfCode{

	public static void main(String[] args) {
		new Day07().run();
	}

	@Override
	public void part1() throws Exception {
		//String program = "src/y2019/day07/day07Input.txt";
		//String program = "src/y2019/day07/testInput";

//		CPUControl cpu = new CPUControl(5);
//		for(int i = 0; i < 5; i++) {
//			cpu.loadProgramAtCoreId(program, i);
//			if(i < 4) {
//				cpu.pipeCoreToCore(i, i+1);
//			}
//		}
//		
//		int highestOutput = 0;
//		int latestOutput;
//		int[] array = {0,1,2,3,4};
//		ArrayList<List<Integer>> combinations = getCombinations(array);
//		
//		for (List<Integer> list : combinations) {
//			
//			cpu.getInputFromCore(0).add(list.get(0));
//			cpu.getInputFromCore(1).add(list.get(1));
//			cpu.getInputFromCore(2).add(list.get(2));
//			cpu.getInputFromCore(3).add(list.get(3));
//			cpu.getInputFromCore(4).add(list.get(4));
//			
//			cpu.getInputFromCore(0).add(0);
//			
//			cpu.executePrograms();
//			latestOutput = cpu.getOutputFromCore(4).take();
//			if(latestOutput > highestOutput) {
//				highestOutput = latestOutput;
//			}
//		}
//		System.out.println("Result = " + highestOutput);
	}

	@Override
	public void part2() throws Exception {
		//String program = "src/y2019/day07/day07Input.txt";
		String program = "src/y2019/day07/testInput";

		Threadpool cpu = new Threadpool(5);
		for(int i = 0; i < 5; i++) {
			cpu.loadProgramAtCoreId(program, i);
			if(i < 4) {
				cpu.pipeCoreToCore(i, i+1);
			}
		}
		cpu.pipeCoreToCore(4,0);
		
		int highestOutput = 0;
		int latestOutput;
		int[] array = {5,6,7,8,9};
		ArrayList<List<Integer>> combinations = getCombinations(array);
		
//		for (List<Integer> list : combinations) {
			
//			cpu.getInputFromCore(0).add(list.get(0));
//			cpu.getInputFromCore(1).add(list.get(1));
//			cpu.getInputFromCore(2).add(list.get(2));
//			cpu.getInputFromCore(3).add(list.get(3));
//			cpu.getInputFromCore(4).add(list.get(4));
			
			cpu.getInputFromCore(0).add(9);
			cpu.getInputFromCore(1).add(8);
			cpu.getInputFromCore(2).add(7);
			cpu.getInputFromCore(3).add(6);
			cpu.getInputFromCore(4).add(5);
			
			cpu.getInputFromCore(0).add(0);
			
			cpu.executePrograms();
			latestOutput = cpu.getOutputFromCore(4).take();
			if(latestOutput > highestOutput) {
				highestOutput = latestOutput;
			}
	
//		}
		System.out.println("Result = " + highestOutput);
	}
	
	public ArrayList<List<Integer>> getCombinations(int[] array) {
		
		ArrayList<List<Integer>> combinations = new ArrayList<>();
		permute(java.util.Arrays.asList(0,1,2,3,4), combinations, 0);

		return combinations;
	}
	
	public void permute(java.util.List<Integer> arr, ArrayList<List<Integer>> comb, int k){
		
        for(int i = k; i < arr.size(); i++){
        	
            java.util.Collections.swap(arr, i, k);
            permute(arr, comb, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        
        if (k == arr.size() -1){
        	List<Integer> newlist = new ArrayList<Integer>(arr) ;
        	comb.add(newlist);
        }
    }
}

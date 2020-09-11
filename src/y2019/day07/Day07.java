package y2019.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import adventOfCode.AdventOfCode;
import adventOfCode.Permutations;
import y2019.intcodeComputer.IntCPU;
import y2019.intcodeComputer.Memory;

public class Day07 extends AdventOfCode {

	public static void main(String[] args) {
		new Day07().run();
	}

	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2019/day07/day07Input.txt");
	}
	
	@Override
	public void part1() throws Exception {

		List<Long> program = Memory.loadProgram(input.get(0));

		LongAccumulator highestOutput = new LongAccumulator(Long::max, 0);
		List<Long> list = LongStream.rangeClosed(0, 4).boxed().collect(Collectors.toList());
		List<List<Long>> permutations = Permutations.getPermutations(list);

		for (List<Long> permutation : permutations) {

				long output = 0;

				List<IntCPU> cpus = new ArrayList<>();
				for (int i = 0; i < 5; i++) {
					cpus.add(new IntCPU(program));
				}

				for (int i = 0; i < 5; i++) {
					cpus.get(i).addInput(permutation.get(i));
					cpus.get(i).addInput(output);
					cpus.get(i).run();
					output = cpus.get(i).getOutput();
				}
				highestOutput.accumulate(output);
		}

		System.out.println("Result = " + highestOutput);
	}

	@Override
	public void part2() throws Exception {
		
		List<Long> program = Memory.loadProgram(input.get(0));

		AtomicLong highestOutput = new AtomicLong(0);
		List<Long> list = LongStream.rangeClosed(5, 9).boxed().collect(Collectors.toList());
		List<List<Long>> permutations = Permutations.getPermutations(list);

		for (List<Long> permutation : permutations) {

			long output = 0;

			List<IntCPU> cpus = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				cpus.add(new IntCPU(program));
				cpus.get(i).addInput(permutation.get(i));
			}
			while (!cpus.get(cpus.size() - 1).isHalted()) {
				for (int i = 0; i < 5; i++) {
					cpus.get(i).addInput(output);
					cpus.get(i).run();
					output = cpus.get(i).getOutput();
				}
			}
			if (highestOutput.getAcquire() < output) {
				highestOutput.setRelease(output);
			}
		}

		System.out.println("Result = " + highestOutput);
	}
}

package y2019;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import adventOfCode.AdventOfCode;

public class Day03 extends AdventOfCode {

	public static void main(String[] args) {
		new Day03().run();
	}

	@Override
	public void part1() throws Exception {
		List<Integer> list = Arrays.asList(5, 3, 4, 17, 6, 10, 29, 33, 456,
				231);
		Map<Integer, Character> col = list.stream()
				.collect(Collectors.toMap(e -> e, e -> (char) e.intValue()));

		System.out.println(col);
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub

	}

}

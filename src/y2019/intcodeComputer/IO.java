package y2019.intcodeComputer;

import java.util.ArrayDeque;
import java.util.Scanner;

public class IO {
	
	private Scanner scanner;
	
	private ArrayDeque<Integer> input;
	private ArrayDeque<Integer> output;
	
	public IO() {
		scanner = new Scanner(System.in);
		input = new ArrayDeque<>();
		output = new ArrayDeque<>();
	}
}

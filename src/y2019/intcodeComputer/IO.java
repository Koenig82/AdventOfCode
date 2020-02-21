package y2019.intcodeComputer;

import java.util.ArrayDeque;
import java.util.Scanner;

public class IO {
	
	private Scanner scanner;
	
	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	private ArrayDeque<Integer> input;
	private ArrayDeque<Integer> output;
	
	public IO() {
		scanner = new Scanner(System.in);
		setInput(new ArrayDeque<>());
		setOutput(new ArrayDeque<>());
	}

	public synchronized ArrayDeque<Integer> getInput() {
		return input;
	}

	public void setInput(ArrayDeque<Integer> input) {
		this.input = input;
	}

	public ArrayDeque<Integer> getOutput() {
		return output;
	}

	public void setOutput(ArrayDeque<Integer> output) {
		this.output = output;
	}
}

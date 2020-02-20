package y2019.cpu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Scanner;

public class IntCodeCPU {
	
	private ArrayDeque<Integer> input;
	private ArrayDeque<Integer> output;
	
	private Scanner scanner;
	public int[] memory;
	private static enum Mode{
		position,
		intermidiate,
		notImplemented1,
		notImplemented2,
		notImplemented3,
		notImplemented4,
		notImplemented5,
		notImplemented6,
		notImplemented7,
		notImplemented8;
	}

	public IntCodeCPU() {
		scanner = new Scanner(System.in);
		input = new ArrayDeque<>();
		output = new ArrayDeque<>();
	}

	public void loadProgram(String path) throws UnsupportedEncodingException, IOException {
		
		String instructions = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
		String[] substrings = instructions.split(",");

		int[] intArray = new int[substrings.length];
		for (int i = 0; i < substrings.length; i++) {
			intArray[i] = Integer.parseInt(substrings[i]);
		}
		this.memory = intArray;
	}
	
	public int executeProgram(Integer input) {
		if(input != null) {
			this.input.add(input);
		}
		int instruction;
		Mode[] modes = Mode.values();
		for(int head = 0;head < memory.length;) {
			instruction = memory[head];
			int opCode = instruction % 100;
			Mode paramModeA = modes[instruction / 100 % Mode.values().length];
			Mode paramModeB = modes[instruction / 1000 % Mode.values().length];
			switch (opCode) {
			case 1:
				head = opcode1(head, paramModeA, paramModeB);
				break;
			case 2:
				head = opcode2(head, paramModeA, paramModeB);
				break;
			case 3:
				head = opcode3(head);
				break;
			case 4:
				head = opcode4(head, paramModeA);
				break;
			case 5:
				head = opcode5(head, paramModeA, paramModeB);
				break;
			case 6:
				head = opcode6(head, paramModeA, paramModeB);
				break;
			case 7:
				head = opcode7(head, paramModeA, paramModeB);
				break;
			case 8:
				head = opcode8(head, paramModeA, paramModeB);
				break;
			case 99:
				for (Integer output : output) {
					System.out.println(output);
				}
				return memory[0];
			default:
				head++;
				break;
			}
		}
		return -1;
	}
	
	public void displayMemory() {
		for (Integer integer : memory) {
			System.out.print(", "+integer);
		}
		System.out.println();
	}
	
	public int getData(int index, Mode parameterMode) {
		switch (parameterMode) {
		case position:
			return memory[index];
		case intermidiate:
			return index;
		default:
			throw new IllegalArgumentException("Unexpected value: " + parameterMode);
		}
		
	}

	public void writeToMemory(int symbol, int index) {

		memory[index] = symbol;
	}

	private int opcode1(int head, Mode paramModeA, Mode paramModeB) {

		int a = getData(memory[head+1], paramModeA);
		int b = getData(memory[head+2], paramModeB);

		writeToMemory(a+b, memory[head+3]);

		return head+4;
	}
	
	private int opcode2(int head, Mode paramModeA, Mode paramModeB) {

		int a = getData(memory[head+1], paramModeA);
		int b = getData(memory[head+2], paramModeB);

		writeToMemory(a*b, memory[head+3]);
		
		return head+4;
	}
	
	private int opcode3(int head) {
		int input;
		if(this.input.isEmpty()) {
			System.out.println("AWAITING INPUT >");
			input = scanner.nextInt();
		}else {
			input = this.input.removeFirst();
		}
		writeToMemory(input, memory[head+1]);
		
		return head+2;
	}
	
	private int opcode4(int head, Mode paramMode) {
		int value = getData(memory[head+1], paramMode);
		output.add(value);

		return head+2;
	}
	
	private int opcode5(int head, Mode paramModeA, Mode paramModeB) {
		
		if(getData(memory[head+1], paramModeA) != 0) {
			return getData(memory[head+2], paramModeB);
		}	
		return head+3;
	}

	private int opcode6(int head, Mode paramModeA, Mode paramModeB) {

		if(getData(memory[head+1], paramModeA) == 0) {
			return getData(memory[head+2], paramModeB);
		}	
		return head+3;
	}
	
	private int opcode7(int head, Mode paramModeA, Mode paramModeB) {

		if(getData(memory[head+1], paramModeA) < getData(memory[head+2], paramModeB)) {
			writeToMemory(1, memory[head+3]);
		}else {
			writeToMemory(0, memory[head+3]);
		}
		return head+4;
	}
	
	private int opcode8(int head, Mode paramModeA, Mode paramModeB) {

		if(getData(memory[head+1], paramModeA) == getData(memory[head+2], paramModeB)) {
			writeToMemory(1, memory[head+3]);
		}else {
			writeToMemory(0, memory[head+3]);
		}
		return head+4;
	}
}

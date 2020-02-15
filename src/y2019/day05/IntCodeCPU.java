package y2019.day05;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Scanner;

public class IntCodeCPU {
	
	private ArrayDeque<Integer> input = new ArrayDeque<>();
	private Scanner scanner;
	public int[] memory;
	private static enum Mode{
		position,
		intermidiate,
		unnkown1,
		unnkown2,
		unnkown3,
		unnkown4,
		unnkown5,
		unnkown6,
		unnkown7,
		unnkown8;
	}

	public IntCodeCPU() {
		scanner = new Scanner(System.in);
	}

	public void loadProgram() throws UnsupportedEncodingException, IOException {

		//String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/day05/testinput2")), "UTF-8");
		//String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/day05/testinput")), "UTF-8");
		//String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/day05/testinput3")), "UTF-8");
		String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/day05/day05Input.txt")), "UTF-8");
		String[] substrings = instructions.split(",");

		int[] intArray = new int[substrings.length];
		for (int i = 0; i < substrings.length; i++) {
			intArray[i] = Integer.parseInt(substrings[i]);
		}
		this.memory = intArray;
	}
	
	public int executeProgram() {
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
			case 99:
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
		
//		System.out.println("writing "+(a+b)+" to memory at index "+ memory[head+3]);
//		System.out.println(a+" "+b+" read from indexes "+(head+1)+" and "+(head+2));
//		System.out.println();
		writeToMemory(a+b, memory[head+3]);

		return head+4;
	}
	
	private int opcode2(int head, Mode paramModeA, Mode paramModeB) {

		int a = getData(memory[head+1], paramModeA);
		int b = getData(memory[head+2], paramModeB);
		
//		System.out.println("writing "+(a*b)+" to memory at index "+ memory[head+3]);
//		System.out.println(a+" "+b+" read from indexes "+(head+1)+" and "+(head+2));
//		System.out.println();
		writeToMemory(a*b, memory[head+3]);
		
		return head+4;
	}
	
	private int opcode3(int head) {
		System.out.println("AWAITING INPUT >");
		int input = scanner.nextInt();
//		System.out.println("writing "+(input)+" to memory at index "+ memory[head+1]);
//		System.out.println();
		writeToMemory(input, memory[head+1]);
		
		return head+2;
	}
	
	private int opcode4(int head, Mode paramModeA) {
		int value = getData(memory[head+1], paramModeA);
		System.out.println("OUTPUT: "+value);
//		System.out.println();
		
		return head+2;
	}
}

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
	//private ParameterMode parameterModes;

	public IntCodeCPU() {
		//parameterModes = new ParameterMode();
		scanner = new Scanner(System.in);
	}

	public void loadProgram() throws UnsupportedEncodingException, IOException {

		//String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/day05/testinput2")), "UTF-8");
		//String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/day05/testinput")), "UTF-8");
		String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/day05/testinput3")), "UTF-8");
		//String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/day02/day02Input.txt")), "UTF-8");
		//String input = new String(Files.readAllBytes(Paths.get("src/y2019/day05Input.txt")), "UTF-8");
		String[] substrings = instructions.split(",");

		int[] intArray = new int[substrings.length];
		for (int i = 0; i < substrings.length; i++) {
			intArray[i] = Integer.parseInt(substrings[i]);
		}
		this.memory = intArray;
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
	
	public int executeProgram() {
		for(int head = 0;head < memory.length;) {
			displayMemory();
			//opcodes ska läsa parametermodes från en attributarray av modes(param12&2)
			//dessa ska sättas här när man parsar ut koden
			if(memory[head] == 1) {
				System.out.println("opcode 1 because value "+memory[head]+" was read index: "+head);
				head = opcode1(head);
			}else if(memory[head] == 2) {
				System.out.println("opcode 2 because value "+memory[head]+" was read index: "+head);
				head = opcode2(head);
			}else if(memory[head] == 3) {
				head = opcode3(head);
			}else if(memory[head] == 4) {
				head = opcode4(head);
			}else if(memory[head] == 99){
				break;
			}else {
				head++;
			}
		}
		return memory[0];
	}
	
	public void displayMemory() {
		for (Integer integer : memory) {
			System.out.print(integer+",");
		}
		System.out.println();
	}
	
	private static enum Mode{
		position,
		intermidiate;
	}
	/*public static class ParameterMode {
		private static enum Modes{
			position,
			intermidiate;
		}
		private Modes[] modes = new Modes[3];

		private void setModes(int opCode) {
			
		}
	
	}*/
	
	/*private int readParameter(int head, int parameter) {
		switch (parameterModes.modes[parameter-1]) {
		case intermidiate:
			return 1;
		case position:
			return 0;
		default:
			return 0;
		}
	}*/

	private int opcode1(int head) {

		int a = getData(memory[head+1], Mode.position);
		int b = getData(memory[head+2], Mode.position);
		
		System.out.println("writing "+(a+b)+" to memory at index "+ memory[head+3]);
		System.out.println(a+" "+b+" read from indexes "+(head+1)+" and "+(head+2));
		System.out.println();
		writeToMemory(a+b, memory[head+3]);

		return head+4;
	}
	
	private int opcode2(int head) {

		int a = getData(memory[head+1], Mode.position);
		int b = getData(memory[head+2], Mode.position);
		
		System.out.println("writing "+(a*b)+" to memory at index "+ memory[head+3]);
		System.out.println(a+" "+b+" read from indexes "+(head+1)+" and "+(head+2));
		System.out.println();
		writeToMemory(a*b, memory[head+3]);
		
		return head+4;
	}
	
	private int opcode3(int head) {
		int input = scanner.nextInt();
		System.out.println("writing "+(input)+" to memory at index "+ memory[head+1]);
		System.out.println();
		writeToMemory(input, memory[head+1]);
		
		return head+1;
	}
	
	private int opcode4(int head) {
		int value = getData(memory[head+1], Mode.position);
		System.out.println("OUTPUT: "+value);
		System.out.println();
		
		return head+1;
	}
	
	public void writeToMemory(int symbol, int index) {
		
		memory[index] = symbol;
	}
}

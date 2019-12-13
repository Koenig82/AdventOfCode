package y2019.day05;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;

public class IntCodeCPU {
	
	private ArrayDeque<Integer> input = new ArrayDeque<>();
	private int[] memory;
	private ParameterMode parameterModes;

	public IntCodeCPU() {
		parameterModes = new ParameterMode();
	}

	public void loadProgram() throws UnsupportedEncodingException, IOException {

		String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/testinput")), "UTF-8");
		//String input = new String(Files.readAllBytes(Paths.get("src/y2019/day05Input.txt")), "UTF-8");
		String[] substrings = instructions.split(",");

		int[] intArray = new int[substrings.length];
		for (int i = 0; i < substrings.length; i++) {
			intArray[i] = Integer.parseInt(substrings[i]);
		}
		this.memory = intArray;
	}
	
	public void addInput(int input) {
		this.input.add(input);
	}
	
	public int executeProgram() {
		for(int head = 0;head < memory.length;) {
			if(memory[head] == 1) {
				head = opcode1(head);
			//}else if(memory[head] == 2) {
				//head = opcode2(head);
			//}else if(memory[head] == 3) {
				//head = opcode3(memory, head);
			}else if(memory[head] == 99){
				break;
			}
		}
		return memory[0];
	}

	public static class ParameterMode {
		private static enum Modes{
			position,
			intermidiate;
		}
		private Modes[] modes = new Modes[3];

		private void setModes(int opCode) {
			
		}
	
	}
	
	private int readParameter(int head, int parameter) {
		switch (parameterModes.modes[parameter-1]) {
		case intermidiate:
			return 1;
		case position:
			return 0;
		default:
			return 0;
		}
	}
	
	private int opcode1(int head) {
		int[] parameters = readParameter(head, 1);

		machineCode[machineCode[head+3]] = parameters[0] + parameters[1];
		return head+4;
	}
	private int opcode2(int head) {
		int[] parameters = modeSwitch(head, 2);

		machineCode[machineCode[head+3]] = parameters[0] * parameters[1];
		return head+4;
	}
	private int opcode3(int[] array, int index) {
		array[array[index+1]] = array[index+1];
		return index;
	}
}

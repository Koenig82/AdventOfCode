package y2019.day05;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;

import adventOfCode.AdventOfCode;

public class Day05 extends AdventOfCode{
	
	private ArrayDeque<Integer> input = new ArrayDeque<>();
	private int[] parameterModes = new int[2];
	private int[] inputBand;

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		
		int result;
		
		IntCodeCPU cpu = new IntCodeCPU();
		cpu.loadProgram();
		//cpu.displayMemory();
		//cpu.writeToMemory(12, 1);
		//cpu.writeToMemory(2, 2);
		result = cpu.executeProgram();
		//cpu.displayMemory();
		System.out.println(result);
		/*getInstructions();
		addInput(1);
		parameterModes = new int[]{0,1};
		System.out.println("Result = "+runMachine());*/
	}

	@Override
	public void part2() throws Exception {
		
		//int[] array = getInput();	
	}
	
	
	
	/*private void addInput(int input) {
		this.input.add(input);
	}*/
	
	/*private int runMachine() {
		for(int head = 0;head < inputBand.length;) {
			if(inputBand[head] == 1) {
				head = opcode1(head);
			}else if(inputBand[head] == 2) {
				head = opcode2(head);
			}else if(inputBand[head] == 3) {
				head = opcode3(inputBand, head);
			}else if(inputBand[head] == 99){
				break;
			}
		}
		return inputBand[0];
	}*/
	
	/*private int opcode1(int head) {
		int[] parameters = modeSwitch(head, 1);

		inputBand[inputBand[head+3]] = parameters[0] + parameters[1];
		return head+4;
	}
	private int opcode2(int head) {
		int[] parameters = modeSwitch(head, 2);

		inputBand[inputBand[head+3]] = parameters[0] * parameters[1];
		return head+4;
	}
	private int opcode3(int[] array, int index) {
		array[array[index+1]] = array[index+1];
		return index;
	}*/
	
	/*private int[] modeSwitch(int head, int opCode) {
		switch (opCode) {
		case 1:
		case 2:
			//fixa att varje parameter har egen parametermode utan 100 ifs
			if(parameterModes[0] == 0) {
				return new int[]{inputBand[inputBand[head+1]],
								 inputBand[inputBand[head+2]],
								 inputBand[inputBand[head+3]]};
			}else if(parameterModes[0] == 1) {
				return new int[]{inputBand[head+1],
						 		 inputBand[head+2],
						 		 inputBand[head+3]};
			}
			break;
		case 3:

			break;
		case 4:

			break;
		default:
			throw new IllegalArgumentException("modeSwitch Error. unknown opcode: " + opCode);
		}
		return new int[] {1,1};
		
	}*/
	
}

package y2019.intcodeComputer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CPUControl {
	
	//private ArrayDeque<Integer> input;
	//private ArrayDeque<Integer> output;
	
	//private Scanner scanner;
	//private Memory memory;
	
	private Core[] cores;
	private Thread[] processes;

	public CPUControl(int nrOfcores) {
		cores = new Core[nrOfcores];
		processes = new Thread[nrOfcores];
		for(int i = 0;i<nrOfcores;i++) {
			cores[i] = new Core(i);
			processes[i] = new Thread(cores[i]);
		}
		//scanner = new Scanner(System.in);
		//input = new ArrayDeque<>();
		//output = new ArrayDeque<>();
		//memory = new Memory();
	}
	public void loadProgramAtCoreId(String path, int coreId) throws UnsupportedEncodingException, IOException {
		cores[coreId].loadProgram(path);
		
	}

	public void writeToCacheIndexAtCoreId(int symbol, int index, int coreId) {
		cores[coreId].writeToMemory(symbol, index);
	}
	
	public void executePrograms() {
		for (Thread process : processes) {
			process.run();
		}
		for(Thread process : processes) {
			try {
				process.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void executeXNrOfCores(int nrOfCores) {
		for(int i = 0; i < nrOfCores; i++) {
			processes[i].run();
		}
		for(int i = 0; i < nrOfCores; i++) {
			try {
				processes[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addInputToCore(int input, int coreId) {
		cores[coreId].io.input.add(input);
	}
	public int getValueFromCoreAtIndex(int coreId, int index) {
		return cores[coreId].getFromMemory(index);
	}

	/*public int executeProgram(Integer input, boolean pipeOutput) {
		if(input != null) {
			this.input.add(input);
		}
		int instruction;
		Mode[] modes = Mode.values();
		for(int head = 0;head < memory.data.length;) {
			
			if(!output.isEmpty()) {
				handleOutput(pipeOutput);
			}
			//displayMemory();
			instruction = memory.data[head];
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
				
				return memory.data[0];
			default:
				head++;
				break;
			}
		}
		return -1;
	}*/
	
	/*private int opcode1(int head, Mode paramModeA, Mode paramModeB) {

		int a = memory.getData(memory.data[head+1], paramModeA);
		int b = memory.getData(memory.data[head+2], paramModeB);

		memory.writeToMemory(a+b, memory.data[head+3]);

		return head+4;
	}
	
	private int opcode2(int head, Mode paramModeA, Mode paramModeB) {

		int a = memory.getData(memory.data[head+1], paramModeA);
		int b = memory.getData(memory.data[head+2], paramModeB);

		memory.writeToMemory(a*b, memory.data[head+3]);
		
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
		memory.writeToMemory(input, memory.data[head+1]);
		
		return head+2;
	}
	
	private int opcode4(int head, Mode paramMode) {
		int value = memory.getData(memory.data[head+1], paramMode);
		output.add(value);

		return head+2;
	}
	
	private int opcode5(int head, Mode paramModeA, Mode paramModeB) {
		
		if(memory.getData(memory.data[head+1], paramModeA) != 0) {
			return memory.getData(memory.data[head+2], paramModeB);
		}	
		return head+3;
	}

	private int opcode6(int head, Mode paramModeA, Mode paramModeB) {

		if(memory.getData(memory.data[head+1], paramModeA) == 0) {
			return memory.getData(memory.data[head+2], paramModeB);
		}	
		return head+3;
	}
	
	private int opcode7(int head, Mode paramModeA, Mode paramModeB) {

		if(memory.getData(memory.data[head+1], paramModeA) < memory.getData(memory.data[head+2], paramModeB)) {
			memory.writeToMemory(1, memory.data[head+3]);
		}else {
			memory.writeToMemory(0, memory.data[head+3]);
		}
		return head+4;
	}
	
	private int opcode8(int head, Mode paramModeA, Mode paramModeB) {

		if(memory.getData(memory.data[head+1], paramModeA) == memory.getData(memory.data[head+2], paramModeB)) {
			memory.writeToMemory(1, memory.data[head+3]);
		}else {
			memory.writeToMemory(0, memory.data[head+3]);
		}
		return head+4;
	}*/
	
	/*private void handleOutput(boolean pipeOutput) {
		if(pipeOutput) {
			memory.writeToMemory(output.pop(), 0);
		}else {
			System.out.println(output.remove());
		}
	}*/
	
	/*public void displayMemory() {
		for (Integer integer : memory.data) {
			System.out.print(", "+integer);
		}
		System.out.println();
	}*/
}

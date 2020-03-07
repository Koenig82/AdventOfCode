package y2019.intcodeComputer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Core implements Runnable{
	
	private int id;
	IO io;
	
	private Memory cache;
	boolean isPiping = false;
	int pipeToIndex = 0;
	
	
	public Core(int id, IO io) {
		this.id = id;
		//io = new IO();
		this.io = io;
		cache = new Memory();
	}
	
	void setPipe(int pipeTo) {
		if(pipeTo < 0) {
			isPiping = false;
			return;
		}else {
			isPiping = true;
			pipeToIndex = pipeTo;
			return;
		}
	}
	public void writeToMemory(int symbol, int index) {
		cache.writeToMemory(symbol, index);
	}
	public int getFromMemory(int index) {
		return cache.getData(index, Mode.position);
	}
	public void loadProgram(String path) throws UnsupportedEncodingException, IOException {
		cache.loadProgram(path);
	}
	
	@Override
	public void run() {
		int instruction;
		Mode[] modes = Mode.values();
		for(int head = 0;head < cache.data.length;) {

			//displayMemory();
			instruction = cache.data[head];
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

				return;
			default:
				head++;
				break;
			}
		}
		return;
		
	}
	
	private int opcode1(int head, Mode paramModeA, Mode paramModeB) {

		int a = cache.getData(cache.data[head+1], paramModeA);
		int b = cache.getData(cache.data[head+2], paramModeB);

		cache.writeToMemory(a+b, cache.data[head+3]);

		return head+4;
	}
	
	private int opcode2(int head, Mode paramModeA, Mode paramModeB) {

		int a = cache.getData(cache.data[head+1], paramModeA);
		int b = cache.getData(cache.data[head+2], paramModeB);

		cache.writeToMemory(a*b, cache.data[head+3]);
		
		return head+4;
	}
	
	private int opcode3(int head) {
		int input;

		try {
			input = io.getInput(id).take();
			cache.writeToMemory(input, cache.data[head+1]);		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return head+2;
	}
	
	private int opcode4(int head, Mode paramMode) {
		int value = cache.getData(cache.data[head+1], paramMode);
		if(!isPiping) {
			try {
				io.getOutput(id).put(value);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				io.getInput(pipeToIndex).put(value);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return head+2;
	}
	
	private int opcode5(int head, Mode paramModeA, Mode paramModeB) {
		
		if(cache.getData(cache.data[head+1], paramModeA) != 0) {
			return cache.getData(cache.data[head+2], paramModeB);
		}	
		return head+3;
	}

	private int opcode6(int head, Mode paramModeA, Mode paramModeB) {

		if(cache.getData(cache.data[head+1], paramModeA) == 0) {
			return cache.getData(cache.data[head+2], paramModeB);
		}	
		return head+3;
	}
	
	private int opcode7(int head, Mode paramModeA, Mode paramModeB) {

		if(cache.getData(cache.data[head+1], paramModeA) < cache.getData(cache.data[head+2], paramModeB)) {
			cache.writeToMemory(1, cache.data[head+3]);
		}else {
			cache.writeToMemory(0, cache.data[head+3]);
		}
		return head+4;
	}
	
	private int opcode8(int head, Mode paramModeA, Mode paramModeB) {

		if(cache.getData(cache.data[head+1], paramModeA) == cache.getData(cache.data[head+2], paramModeB)) {
			cache.writeToMemory(1, cache.data[head+3]);
		}else {
			cache.writeToMemory(0, cache.data[head+3]);
		}
		return head+4;
	}
}

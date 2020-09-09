package y2019.intcodeComputer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class IntCPU implements Runnable {

	private int id;

	private Memory memory;
	boolean isPiping = false;
	int pipeToIndex = 0;

	public IntCPU(int id, IO io) {
		this.id = id;
		// cache = new Memory();
	}

	public IntCPU(List<Long> program) {
		memory = new Memory(program);
	}

	void setPipe(int pipeTo) {
		if (pipeTo < 0) {
			isPiping = false;
			return;
		} else {
			isPiping = true;
			pipeToIndex = pipeTo;
			return;
		}
	}

	public void writeToMemory(int symbol, int index) {
		memory.writeToMemory(symbol, index);
	}

	public int getFromMemory(int index) {
		return memory.getData(index, Mode.position);
	}

	public void loadProgram(String path) throws UnsupportedEncodingException, IOException {
		memory.loadProgram(path);
	}
	
	public void addInput(Long input) {
		memory.input.offer(input);
	}
	public Long getOutput() {
		return memory.output.remove();
	}
	public boolean hasOutput() {
		return !memory.output.isEmpty();
	}

	@Override
	public void run() {
		// if(id != 0) {
		// System.out.println("started core "+id+" piping: "+isPiping+ " to core
		// "+pipeToIndex);
		// }
		int instruction;
		Mode[] modes = Mode.values();
		for (int head = 0; head < memory.data.length;) {

			// displayMemory();
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

				return;
			default:
				head++;
				break;
			}
		}
		return;

	}

	private int opcode1(int head, Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode1");
		int a = memory.getData(memory.data[head + 1], paramModeA);
		int b = memory.getData(memory.data[head + 2], paramModeB);

		memory.writeToMemory(a + b, memory.data[head + 3]);

		return head + 4;
	}

	private int opcode2(int head, Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode2");
		int a = memory.getData(memory.data[head + 1], paramModeA);
		int b = memory.getData(memory.data[head + 2], paramModeB);

		memory.writeToMemory(a * b, memory.data[head + 3]);

		return head + 4;
	}

	private int opcode3(int head) {
		// System.out.println("Core "+id+" opcode3");
		Long input = memory.input.poll();

		memory.writeToMemory(input.intValue(), memory.data[head + 1]);

		return head + 2;
	}

	private int opcode4(int head, Mode paramMode) {
		long value = memory.getData(memory.data[head + 1], paramMode);
		memory.output.offer(value);
		//System.out.println("Core " + id + " outputed " + value);

		return head + 2;
	}

	private int opcode5(int head, Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode5");
		if (memory.getData(memory.data[head + 1], paramModeA) != 0) {
			return memory.getData(memory.data[head + 2], paramModeB);
		}
		return head + 3;
	}

	private int opcode6(int head, Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode6");
		if (memory.getData(memory.data[head + 1], paramModeA) == 0) {
			return memory.getData(memory.data[head + 2], paramModeB);
		}
		return head + 3;
	}

	private int opcode7(int head, Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode7");
		if (memory.getData(memory.data[head + 1], paramModeA) < memory.getData(memory.data[head + 2], paramModeB)) {
			memory.writeToMemory(1, memory.data[head + 3]);
		} else {
			memory.writeToMemory(0, memory.data[head + 3]);
		}
		return head + 4;
	}

	private int opcode8(int head, Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode8");
		if (memory.getData(memory.data[head + 1], paramModeA) == memory.getData(memory.data[head + 2], paramModeB)) {
			memory.writeToMemory(1, memory.data[head + 3]);
		} else {
			memory.writeToMemory(0, memory.data[head + 3]);
		}
		return head + 4;
	}
}

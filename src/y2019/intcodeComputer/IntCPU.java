package y2019.intcodeComputer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class IntCPU implements Runnable {

	private boolean halted = false;
	private Memory memory;
	int pipeToIndex = 0;
	int head = 0;
	private static final Mode[] modes = Mode.values();

	public IntCPU(List<Long> program) {
		memory = new Memory(program);
	}

	public void writeToMemory(int symbol, int index) {
		memory.writeToMemory(symbol, index);
	}

	public int getFromMemory(int index) {
		return memory.getData(index, Mode.position);
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
	public boolean isHalted() {
		return halted;
	}

	@Override
	public void run() {

		while (!halted) {
			// displayMemory();
			int instruction = memory.data[head];
			int opCode = instruction % 100;
			Mode paramModeA = modes[instruction / 100 % 10];
			Mode paramModeB = modes[instruction / 1000 % 10];
			switch (opCode) {
			case 1:
				opcode1(paramModeA, paramModeB);
				break;
			case 2:
				opcode2(paramModeA, paramModeB);
				break;
			case 3:
				if(!opcode3()) {
					return;
				}
				break;
			case 4:
				opcode4(paramModeA);
				break;
			case 5:
				opcode5(paramModeA, paramModeB);
				break;
			case 6:
				opcode6(paramModeA, paramModeB);
				break;
			case 7:
				opcode7(paramModeA, paramModeB);
				break;
			case 8:
				opcode8(paramModeA, paramModeB);
				break;
			case 99:
				halted = true;
				return;
			default:
				throw new RuntimeException("Unknown instruction " + opCode);
			}
		}
	}

	private void opcode1( Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode1");
		int a = memory.getData(memory.data[head + 1], paramModeA);
		int b = memory.getData(memory.data[head + 2], paramModeB);

		memory.writeToMemory(a + b, memory.data[head + 3]);
		head+=4;
	}

	private void opcode2(Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode2");
		int a = memory.getData(memory.data[head + 1], paramModeA);
		int b = memory.getData(memory.data[head + 2], paramModeB);

		memory.writeToMemory(a * b, memory.data[head + 3]);
		head+=4;
	}

	private boolean opcode3() {
		// System.out.println("Core "+id+" opcode3");
		Long input = memory.input.poll();
		if (input != null) {
			memory.writeToMemory(input.intValue(), memory.data[head + 1]);
			head+=2;
			return true;
		}else {
			return false;
		}
	}

	private void opcode4(Mode paramMode) {
		long value = memory.getData(memory.data[head + 1], paramMode);
		memory.output.offer(value);
		// System.out.println("Core " + id + " outputed " + value);

		head += 2;
	}

	private void opcode5(Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode5");
		if (memory.getData(memory.data[head + 1], paramModeA) != 0) {
			head = memory.getData(memory.data[head + 2], paramModeB);
		}else {
			head += 3;
		}
	}

	private void opcode6(Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode6");
		if (memory.getData(memory.data[head + 1], paramModeA) == 0) {
			head = memory.getData(memory.data[head + 2], paramModeB);
		}else {
			head += 3;
		}
	}

	private void opcode7(Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode7");
		if (memory.getData(memory.data[head + 1], paramModeA) < memory.getData(memory.data[head + 2], paramModeB)) {
			memory.writeToMemory(1, memory.data[head + 3]);
		} else {
			memory.writeToMemory(0, memory.data[head + 3]);
		}
		head += 4;
	}

	private void opcode8(Mode paramModeA, Mode paramModeB) {
		// System.out.println("Core "+id+" opcode8");
		if (memory.getData(memory.data[head + 1], paramModeA) == memory.getData(memory.data[head + 2], paramModeB)) {
			memory.writeToMemory(1, memory.data[head + 3]);
		} else {
			memory.writeToMemory(0, memory.data[head + 3]);
		}
		head += 4;
	}
}

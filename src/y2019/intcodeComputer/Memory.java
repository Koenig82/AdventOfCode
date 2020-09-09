package y2019.intcodeComputer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Memory {
	
	int[] data;
	
	Queue<Long> input = new ArrayDeque<>();
	Queue<Long> output = new ArrayDeque<>();
	
	public Memory(List<Long> program) {
		data = new int[program.size()];
		for(int i = 0;i < program.size(); i++) {
			data[i] = program.get(i).intValue();
		}
	}
	//sluta läsa in filen varje gång man startar en core, kan ge tillbaka en lista
	public static List<Long> loadProgram(String path) throws UnsupportedEncodingException, IOException {

		String instructions = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
		String[] substrings = instructions.split(",");
		
		List<Long> memory = new ArrayList<Long>(substrings.length);
		for (String instruction : substrings) {
			memory.add(Long.parseLong(instruction));
		}
		return Collections.unmodifiableList(memory);
	}

	int getData(int index, Mode parameterMode) {
		switch (parameterMode) {
		case position:
			return data[index];
		case intermidiate:
			return index;
		default:
			throw new IllegalArgumentException("Unexpected value: " + parameterMode);
		}

	}

	void writeToMemory(int symbol, int index) {
		data[index] = symbol;
	}
}

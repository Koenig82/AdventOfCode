package y2019.intcodeComputer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Memory {
	
	int[] data;
	
	public Memory() {
		// TODO Auto-generated constructor stub
	}
	//sluta läsa in filen varje gång man startar en core, kan ge tillbaka en lista
	void loadProgram(String path) throws UnsupportedEncodingException, IOException {

		String instructions = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
		String[] substrings = instructions.split(",");

		int[] intArray = new int[substrings.length];
		for (int i = 0; i < substrings.length; i++) {
			intArray[i] = Integer.parseInt(substrings[i]);
		}
		data = intArray;
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

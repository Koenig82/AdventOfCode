package y2018;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import adventOfCode.AdventOfCode;

public class Day05 extends AdventOfCode{

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		
		char[] input = getInput();
		boolean done = false;
		int removeCount = 0;
		while(!done) {
			done = true;
			removeCount = 0;
			for(int i = 0; i < input.length-1; i++) {
				if((int)input[i] == (int)input[i+1]-32 ||
						(int)input[i] == (int)input[i+1]+32) {
					done = false;
					input[i] = ' ';
					input[i+1] = ' ';
					removeCount+=2;
				}
			}
			input = removeBlanks(input, removeCount);
		}
		System.out.println("Result = "+(input.length-1));
	}

	@Override
	public void part2() throws Exception {
		char[] input = getInput();
		//input = removeAllElementsOfType()
		
	}

	private char[] getInput() {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		//try (Stream<String> stream = Files.lines( Paths.get("src/y2018/testinput.txt"), StandardCharsets.UTF_8)) {
        try (Stream<String> stream = Files.lines( Paths.get("src/y2018/Day05Input.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s).append("\n"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }      
        String string = stringBuilder.toString();
		
		return string.toCharArray();
	}
	
	private char[] removeBlanks(char[] array, int removeCount) {
		
		char[] newArray = new char[array.length-removeCount];
		for(int i = 0, j = 0; i< array.length; i++) {
			if(array[i] == ' ') {
				
			}else {
				newArray[j++] = array[i];
			}
		}
		return newArray;
	}
	
	
	private char[] removeTwoElements(char[] array, int index) { 
		if (array == null
				|| index < 0
				|| index+1 >= array.length) { 

			return array; 
		} 
		char[] returnArray = new char[array.length - 2]; 

		for (int i = 0, j = 0; i < array.length; i++) { 

			if (i == index || i == index+1) { 
				continue; 
			} 
			returnArray[j++] = array[i]; 
		} 
		return returnArray;
	}
	
	private char[] removeOneElement(char[] array, int index) { 

		if (array == null
				|| index < 0
				|| index >= array.length) { 

			return array; 
		} 
		char[] returnArray = new char[array.length - 1]; 

		for (int i = 0, j = 0; i < array.length; i++) { 

			if (i == index) { 
				continue; 
			} 
			returnArray[j++] = array[i]; 
		} 
		return returnArray;
	}
	
	private char[] removeAllElementsOfType(char[] array, char lowerCase) { 
		
		for(int i = array.length-1; i > -1; i--) {
			if((int)array[i] == lowerCase ||
					(int)array[i] == (int)array[i+1]-32) {
				array = removeOneElement(array,i);
			}
		}
		return array;
	}
}

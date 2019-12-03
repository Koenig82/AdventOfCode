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
		System.out.println("Result = "+reactPolymers(input));
	}

	@Override
	public void part2() throws Exception {
		
		char[] input = getInput();
		int lowestResult = input.length;
		int tempResult;
		for(int i = 97; i <= 122; i++) {
			input = getInput();
			input = removeAllElementsOfType(input, i);
			tempResult = reactPolymers(input);
			if(tempResult < lowestResult) {
				lowestResult = tempResult;
			}
		}
		System.out.println("Result = "+lowestResult);
	}

	private char[] getInput() {
		
		StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get("src/y2018/day05Input.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s));
        }
        catch (IOException e) {
            e.printStackTrace();
        }      
        String string = stringBuilder.toString();
		
		return string.toCharArray();
	}
	
	private int reactPolymers(char[] array) {
		
		boolean done = false;
		int removeCount = 0;
		while(!done) {
			done = true;
			removeCount = 0;
			for(int i = 0; i < array.length-1; i++) {
				if((int)array[i] == (int)array[i+1]-32 ||
						(int)array[i] == (int)array[i+1]+32) {
					done = false;
					array[i] = ' ';
					array[i+1] = ' ';
					removeCount+=2;
				}
			}
			array = removeBlanks(array, removeCount);
		}
		return array.length;
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
	
	private char[] removeAllElementsOfType(char[] array, int lowerCase) { 
		
		int removeCount = 0;
		for(int i = 0; i < array.length-1; i++) {
			if((int)array[i] == lowerCase ||
					(int)array[i] == lowerCase-32) {
				array[i] = ' ';
				removeCount++;
			}
		}
		return removeBlanks(array, removeCount);
	}
}

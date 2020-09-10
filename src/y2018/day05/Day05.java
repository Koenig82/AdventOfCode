package y2018.day05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import adventOfCode.AdventOfCode;

public class Day05 extends AdventOfCode{

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		
		char[] input = this.input.get(0).toCharArray();
		System.out.println("Result = "+reactPolymers(input));
	}

	@Override
	public void part2() throws Exception {
		
		char[] input = this.input.get(0).toCharArray();
		int lowestResult = input.length;
		int tempResult;
		for(int i = 97; i <= 122; i++) {
			input = this.input.get(0).toCharArray();
			input = removeAllElementsOfType(input, i);
			tempResult = reactPolymers(input);
			if(tempResult < lowestResult) {
				lowestResult = tempResult;
			}
		}
		System.out.println("Result = "+lowestResult);
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

	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2018/day05/day05Input.txt");
	}
}

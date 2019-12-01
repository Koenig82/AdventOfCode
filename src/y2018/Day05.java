package y2018;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import adventOfCode.AdventOfCode;

public class Day05 extends AdventOfCode{

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		char[] input = getInput();
		for(int i = 0; i < input.length-1; i++) {
			if((int)input[i] == (int)input[i+1]-32) {
				System.out.println("Char at index "+i+" = "+input[i]+", Char at index "+(i+1)+" = "+input[i+1]);
				System.out.println("Int at index "+i+" = "+(int)input[i]+", Int at index "+(i+1)+" = "+(int)input[i+1]);
				System.out.println("samma char");
			}
		}
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}

	private char[] getInput() {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		try (Stream<String> stream = Files.lines( Paths.get("src/y2018/testinput.txt"), StandardCharsets.UTF_8)) {
        //try (Stream<String> stream = Files.lines( Paths.get("src/y2018/Day05Input.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s).append("\n"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }      
        String string = stringBuilder.toString();
		
		return string.toCharArray();
	}
}

package y2018;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adventOfCode.AdventOfCode;

public class Day05 extends AdventOfCode{

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		char[] input = getInput();
		for(int i = 0; i < input.length-1; i++) {
			if(input[i] == input[i+1]) {
				System.out.println("samma char");
			}
		}
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}

	private char[] getInput() {
		
		File file = new File("src/y2018/Day05Input.txt");
		String string = file.toString();
		return string.toCharArray();
	}
}

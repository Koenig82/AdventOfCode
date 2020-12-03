package y2020.day02;

import java.util.List;
import java.util.regex.Pattern;

import adventOfCode.AdventOfCode;

public class Day02 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day02().run();
	}
	
	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2020/day02/Day02Input.txt");
	}

	@Override
	public void part1() throws Exception {
		int counter = 0;
		Pattern pattern = 
		for (String string : input) {
			if(checkValidity(Integer.parseInt(string.valueOf(string.charAt(0))), 
					      Integer.parseInt(string.valueOf(string.charAt(2))),
					      string.charAt(4),
					      string.substring(7))) {
				counter++;
			}
		}
		System.out.println(counter);
		
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}
	^([0-9]+)-(\d+) (\w): (.+)
	boolean checkValidity(int a,int b, char x, String input){
		String newstring = input.replaceAll("[^"+x+"]", "");
		//System.out.println(newstring);
		//System.out.println(input.chars().filter(c -> c==x).count());
		
		return (newstring.length()>=a && newstring.length()<=b);
	}

}

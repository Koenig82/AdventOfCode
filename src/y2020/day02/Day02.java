package y2020.day02;

import java.util.List;
import java.util.regex.Matcher;
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
		
		for (String string : input) {
			Matcher matcher = Pattern.compile("^([0-9]+)-(\\d+) (\\w): (.+)").matcher(string);
			matcher.find();
			
			if(checkValidity(Integer.parseInt(matcher.group(1)), 
					      Integer.parseInt(matcher.group(2)),
					      matcher.group(3).charAt(0),
					      matcher.group(4))) {
				counter++;
			}
		}
		System.out.println(counter);
		
	}

	@Override
	public void part2() throws Exception {
		int counter = 0;
		
		for (String string : input) {
			Matcher matcher = Pattern.compile("^([0-9]+)-(\\d+) (\\w): (.+)").matcher(string);
			matcher.find();
			
			if(checkValidity2(Integer.parseInt(matcher.group(1)), 
					      Integer.parseInt(matcher.group(2)),
					      matcher.group(3).charAt(0),
					      matcher.group(4))) {
				counter++;
			}
		}
		System.out.println(counter);
		
	}
	
	boolean checkValidity(int a,int b, char x, String input){	
		String newstring = input.replaceAll("[^"+x+"]", "");		
		return (newstring.length()>=a && newstring.length()<=b);
	}
	boolean checkValidity2(int a,int b, char x, String input){	
		if(input.regionMatches((a-1), String.valueOf(x), 0, 1) ||
		   input.regionMatches((b-1), String.valueOf(x), 0, 1)){
			if(input.regionMatches((a-1), String.valueOf(x), 0, 1) &&
			   input.regionMatches((b-1), String.valueOf(x), 0, 1)) {
				return false;
			}
			return true;
		}
		return false;
	}

}

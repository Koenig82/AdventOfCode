package y2018.d03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

import adventOfCode.AdventOfCode;
import y2018.d02.Day2;

public class Day3 extends AdventOfCode{

	public static void main(String[] args) {
		new Day3().run();

	}

	@Override
	public void part1() throws Exception {
		int[][] cloth = new int[1000][1000];
		int overlapcount= 0;
		
		BufferedReader reader = new BufferedReader(new FileReader("src/y2018/d03/day03Input"));
		try {
		    String line;
		    while ((line = reader.readLine()) != null) {

		    	String[] substrings = line.split(" ");
		    	
		    	int id = Integer.parseInt(substrings[0].substring(1));
		    	
		    	String[] coordsubs = substrings[2].split(",");
		    	int startFromX = Integer.parseInt(coordsubs[0]);
		    	int startFromY = Integer.parseInt(coordsubs[1].substring(0, coordsubs[1].indexOf(":")));

		    	String[] sizesubs = substrings[3].split("x");
		    	int sizeX = Integer.parseInt(sizesubs[0]);
		    	int sizeY = Integer.parseInt(sizesubs[1]);

		    	for(int x = startFromX; x < sizeX+startFromX; x++){
		    		for(int y = startFromY; y < sizeY + startFromY; y++) {
		    			if(cloth[x][y] == 0) {
		    				cloth[x][y] = id;
		    			}else if(cloth[x][y] != -1){
		    				cloth[x][y] = -1;
		    				overlapcount++;	
		    			}
		    		}
		    	}
		    }
		} finally {
		    reader.close();
		    System.out.println("doubleinserts counted: " + overlapcount);
		}
		
	}

	@Override
	public void part2() throws Exception {
		int[][] cloth = new int[1000][1000];
		
		HashSet<Integer> candidates = new HashSet<>();
		
		BufferedReader reader = new BufferedReader(new FileReader("src/y2018/d03/day03Input"));
		try {
		    String line;
		    while ((line = reader.readLine()) != null) {

		    	String[] substrings = line.split(" ");
		    	
		    	int id = Integer.parseInt(substrings[0].substring(1));
		    	candidates.add(id);
		    	
		    	String[] coordsubs = substrings[2].split(",");
		    	int startFromX = Integer.parseInt(coordsubs[0]);
		    	int startFromY = Integer.parseInt(coordsubs[1].substring(0, coordsubs[1].indexOf(":")));

		    	String[] sizesubs = substrings[3].split("x");
		    	int sizeX = Integer.parseInt(sizesubs[0]);
		    	int sizeY = Integer.parseInt(sizesubs[1]);

		    	for(int x = startFromX; x < sizeX+startFromX; x++){
		    		for(int y = startFromY; y < sizeY + startFromY; y++) {
		    			if(cloth[x][y] == 0) {
		    				cloth[x][y] = id;
		    			}else if(cloth[x][y] != -1){
		    				candidates.remove(cloth[x][y]);
		    				candidates.remove(id);
		    				cloth[x][y] = -1;	
		    			}else {
		    				candidates.remove(id);
		    				candidates.remove(cloth[x][y]);
		    			}
		    		}
		    	}
		    }
		} finally {
		    reader.close();
		    for (Integer s : candidates) {
		        System.out.println(s);
		    }
		}
		
	}

}

package y2019.day08;

import java.util.ArrayList;
import java.util.List;


import adventOfCode.AdventOfCode;

public class Day08  extends AdventOfCode{

	public static void main(String[] args) {
		new Day08().run();
	}

	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2019/day08/day08Input.txt");
		//return readFile("src/y2019/day08/testInput");
	}

	@Override
	public void part1() throws Exception {
		
		List<String[]> layers = new ArrayList<>();
		int pictureLength = 6;
		int pictureWidth = 25;
		long lowestCount = Long.MAX_VALUE;
		
		String[] images = splitStringEvery(input.get(0), (pictureLength*pictureWidth));
		for (String string : images) {
			layers.add(splitStringEvery(string, pictureWidth));
		}
		int currentIndex = 0;
		int indexOfLowest = 0;
		for (String[] layer : layers) {
			long number = getNumberCountFromLayer(0, layer);
			if(number < lowestCount) {
				lowestCount = number;
				indexOfLowest = currentIndex;
			}
			currentIndex++;
		}
		System.out.println(lowestCount);
		long ones = getNumberCountFromLayer(1, layers.get(indexOfLowest));
		long twos = getNumberCountFromLayer(2, layers.get(indexOfLowest));
		System.out.println(ones*twos);
	}

	@Override
	public void part2() throws Exception {
		
	}
	
	private long getNumberCountFromLayer(int number, String[] layer) {
		long layerCount = 0;
		for (String string : layer) {
			layerCount += string.chars().filter(ch -> ch == String.valueOf(number).charAt(0)).count();
		}
		return layerCount;
	}
	
	public String[] splitStringEvery(String s, int interval) {
	    int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
	    String[] result = new String[arrayLength];

	    int j = 0;
	    int lastIndex = result.length - 1;
	    for (int i = 0; i < lastIndex; i++) {
	        result[i] = s.substring(j, j + interval);
	        j += interval;
	    } //Add the last bit
	    result[lastIndex] = s.substring(j);

	    return result;
	}
}

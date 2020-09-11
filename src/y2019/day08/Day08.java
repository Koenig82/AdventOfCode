package y2019.day08;

import java.util.ArrayList;
import java.util.List;


import adventOfCode.AdventOfCode;
import sun.tools.tree.NewArrayExpression;

public class Day08  extends AdventOfCode{

	public static void main(String[] args) {
		new Day08().run();
	}

	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2019/day08/day08Input.txt");
	}

	@Override
	public void part1() throws Exception {
		
		List<String[]> layers = getPicture(25,6);
		long lowestCount = Long.MAX_VALUE;
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
		long ones = getNumberCountFromLayer(1, layers.get(indexOfLowest));
		long twos = getNumberCountFromLayer(2, layers.get(indexOfLowest));

		System.out.println("Result = " + ones*twos);
	}

	@Override
	public void part2() throws Exception {
		
		List<String[]> layers = getPicture(25,6);
		List<char[]> fullpicture = new ArrayList<>();
		String empty = "2222222222222222222222222";
		for(int i = 0;i<6;i++) {
			fullpicture.add(empty.toCharArray());
		}
		for (String[] layer : layers) {
			for(int i = 0; i < 6;i++) {
				for(int j = 0;j < 25; j++) {
					if(fullpicture.get(i)[j] == '2') {
						fullpicture.get(i)[j] = layer[i].charAt(j);
					}
				}
			}
		}
		for (char[] row : fullpicture) {
			for (char symbol : row) {
				System.out.print(symbol);
			}
			System.out.println();
		}
	}
	
	private List<String[]> getPicture(int width, int length){
		List<String[]> layers = new ArrayList<>();
		
		String[] images = splitStringEvery(input.get(0), (length*width));
		for (String string : images) {
			layers.add(splitStringEvery(string, width));
		}
		return layers;
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
	    }
	    result[lastIndex] = s.substring(j);

	    return result;
	}
}

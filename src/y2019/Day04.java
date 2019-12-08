package y2019;

import adventOfCode.AdventOfCode;

public class Day04 extends AdventOfCode{

	public static void main(String[] args) {
		new Day04().run();

	}

	@Override
	public void part1() throws Exception {
		int possibilities = 0;
		boolean hasDouble;
		boolean isAscending;
		for(int i = 158126; i <= 624574; i++) {
			if(checkNumber(i)) {
				possibilities++;
			}
		}
		System.out.println(possibilities);	
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private boolean checkNumber(int num) {
		String number = Integer.toString(num);
		boolean hasDouble = false;
		for(int symbolIndex = 0; symbolIndex < 5;symbolIndex++) {
			
			if(Integer.parseInt(number.substring(symbolIndex, symbolIndex+1)) < Integer.parseInt(number.substring(symbolIndex+1, symbolIndex+2))){
				return false;
			}
			if(Integer.parseInt(number.substring(symbolIndex, symbolIndex+1)) == Integer.parseInt(number.substring(symbolIndex+1, symbolIndex+2))) {
				hasDouble = true;
			}
		}
		if(hasDouble) {
			return true;
		}else {
			return false;
		}
	}

}

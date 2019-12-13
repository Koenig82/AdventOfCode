package y2019.day04;

import adventOfCode.AdventOfCode;

public class Day04 extends AdventOfCode{

	public static void main(String[] args) {
		new Day04().run();

	}

	@Override
	public void part1() throws Exception {
		int possibilities = 0;
		for(int i = 158127; i < 624576; i++) {
			if(checkNumber(i)) {
				possibilities++;
			}
		}
		System.out.println("Result = "+possibilities);	
	}

	@Override
	public void part2() throws Exception {
		int possibilities = 0;
		for(int i = 158127; i < 624576; i++) {
			if(checkNumber2(i)) {
				possibilities++;
			}
		}
		System.out.println("Result = "+possibilities);	
		
	}
	
	private boolean checkNumber(int num) {
		String number = Integer.toString(num);
		boolean hasDouble = false;
		for(int symbolIndex = 0; symbolIndex < 5;symbolIndex++) {
			
			if(Integer.parseInt(number.substring(symbolIndex, symbolIndex+1)) > Integer.parseInt(number.substring(symbolIndex+1, symbolIndex+2))){
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

	private boolean checkNumber2(int num) {
		String number = Integer.toString(num);
		boolean hasDouble = false;
		int numberSeriesOf = Integer.MAX_VALUE;
		for(int symbolIndex = 0; symbolIndex < 5; symbolIndex++) {
			if(Integer.parseInt(number.substring(symbolIndex, symbolIndex+1)) > Integer.parseInt(number.substring(symbolIndex+1, symbolIndex+2))){
				return false;
			}
			//if current and next match
			if(Integer.parseInt(number.substring(symbolIndex, symbolIndex+1)) == Integer.parseInt(number.substring(symbolIndex+1, symbolIndex+2))){
				//if also within range for longer check
				if(symbolIndex < 4) {
					//if there is not three in a row
					if(Integer.parseInt(number.substring(symbolIndex, symbolIndex+1)) != Integer.parseInt(number.substring(symbolIndex+2, symbolIndex+3))) {
						//if the match is not a series
						if(Integer.parseInt(number.substring(symbolIndex, symbolIndex+1)) != numberSeriesOf){
							hasDouble = true;
							numberSeriesOf = Integer.MAX_VALUE;
						}
					//if there is three in a row
					}else {
						numberSeriesOf = Integer.parseInt(number.substring(symbolIndex, symbolIndex+1));
					}
				//if theyre the last two in the sequence and not matching a series
				}else if(Integer.parseInt(number.substring(symbolIndex, symbolIndex+1)) != numberSeriesOf){
					hasDouble = true;
				}
			//if its not two in a row
			}else {
				numberSeriesOf = Integer.MAX_VALUE;
			}
		}


		if(hasDouble) {
			return true;
		}else {
			return false;
		}
	}
}

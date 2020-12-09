package y2020.day04;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import adventOfCode.AdventOfCode;

public class Day04 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day04().run();
	}
	
	@Override
	public List<String> readInput() throws Exception {
		
		return readFile("src/y2020/day04/testinput");
	}

	@Override
	public void part1() throws Exception {
		List<Passport> passports = input.stream().map(Passport::fromString).collect(Collectors.toList());		
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public static class Passport {

		String byr = "";
		String iyr = "";
		String hcl = "";
		String ecl = "";
		String pid = "";
		String cid = "";
		
		public Passport(String byr, String iyr, String hcl, String ecl, String pid, String cid) {
			this.byr = byr;
			this.iyr = iyr;
			this.hcl = hcl;
			this.ecl = ecl;
			this.pid = pid;
			this.cid = cid;
		}
		
		boolean validate() {
			return false;
			
		}
		
		public static Passport fromString(String line) {
			return new Passport(null, null, null, null, null, null);
		}
	}


}

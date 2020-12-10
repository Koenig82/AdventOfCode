package y2020.day04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import adventOfCode.AdventOfCode;

public class Day04 extends AdventOfCode{
	
	private static final Pattern reqByr = Pattern.compile("byr:(\\d+)");
	private static final Pattern regIyr = Pattern.compile("iyr:(\\d+)");
	private static final Pattern regHcl = Pattern.compile("(?:hcl:(\\w+)|hcl:(#\\w+))");
	private static final Pattern regEcl = Pattern.compile("(?:ecl:(\\w+)|ecl:(#\\w+))");
	private static final Pattern regPid = Pattern.compile("pid:(\\d+)");
	private static final Pattern regCid = Pattern.compile("cid:(\\d+)");
	private static final Pattern regEyr = Pattern.compile("eyr:(\\d+)");
	private static final Pattern regHgt = Pattern.compile("hgt:(\\d+)");
	
	public static void main(String[] args) {
		new Day04().run();
	}
	
	@Override
	public List<String> readInput() throws Exception {
		//return readFile("src/y2020/day04/testinput");
		return readFile("src/y2020/day04/Day04Input.txt");
	}

	@Override
	public void part1() throws Exception {
		List<Passport> passports = parseInput();
		int counter = 0;
		for (Passport passport : passports) {
			if(passport.validate()) {
				counter++;
			}
		}
		System.out.println(counter);
	}


	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private List<Passport> parseInput() {

		List<Passport> passports = new ArrayList<>();
		String byr = "";
		String iyr = "";
		String hcl = "";
		String ecl = "";
		String pid = "";
		String cid = "";
		String eyr = "";
		String hgt = "";
		Matcher matcher;
		for (String string : input) {
			if(!string.isEmpty()) {
				
				matcher = reqByr.matcher(string);
				if(matcher.find()) {
					byr = matcher.group(1);
				}
				matcher = regIyr.matcher(string);
				if(matcher.find()) {
					iyr = matcher.group(1);
				}
				matcher = regHcl.matcher(string);
				if(matcher.find()) {
					if(matcher.group(2) == null) {
						hcl = matcher.group(1);
					}else {
						hcl = matcher.group(2);
					}
				}
				matcher = regEcl.matcher(string);
				if(matcher.find()) {
					if(matcher.group(2) == null) {
						ecl = matcher.group(1);
					}else {
						ecl = matcher.group(2);
					}
				}
				matcher = regPid.matcher(string);
				if(matcher.find()) {
					pid = matcher.group(1);
				}
				matcher = regCid.matcher(string);
				if(matcher.find()) {
					cid = matcher.group(1);
				}
				matcher = regEyr.matcher(string);
				if(matcher.find()) {
					eyr = matcher.group(1);
				}
				matcher = regHgt.matcher(string);
				if(matcher.find()) {
					hgt = matcher.group(1);
				}
			}else {
				passports.add(new Passport(byr,iyr,hcl,ecl,pid,cid,eyr,hgt));
				byr = "";
				iyr = "";
				hcl = "";
				ecl = "";
				pid = "";
				cid = "";
				eyr = "";
				hgt = "";
			}
		}
		passports.add(new Passport(byr,iyr,hcl,ecl,pid,cid,eyr,hgt));
		
		return passports;
	}
	
	private static class Passport {

		String byr = "";
		String iyr = "";
		String hcl = "";
		String ecl = "";
		String pid = "";
		String cid = "";
		String eyr = "";
		String hgt = "";
		
		public Passport(String byr, String iyr, String hcl, String ecl, String pid, String cid, String eyr, String hgt) {
			this.byr = byr;
			this.iyr = iyr;
			this.hcl = hcl;
			this.ecl = ecl;
			this.pid = pid;
			this.cid = cid;
			this.eyr = eyr;
			this.hgt = hgt;
		}
		
		boolean validate() {
			System.out.println("byr:"+byr);
			System.out.println("iyr:"+iyr);
			System.out.println("hcl:"+hcl);
			System.out.println("ecl:"+ecl);
			System.out.println("pid:"+pid);
			System.out.println("cid:"+cid);
			System.out.println("eyr:"+eyr);
			System.out.println("hgt:"+hgt);
			if(!byr.equals("") &&
			   !iyr.equals("") &&
			   !hcl.equals("") &&
			   !ecl.equals("") &&
			   !pid.equals("") &&
			   !eyr.equals("") &&
			   !hgt.equals("")) {
				System.out.println("returning true");
				System.out.println();
				return true;
			}
			System.out.println("returning false");
			System.out.println();
			return false;
			
		}
	}
}

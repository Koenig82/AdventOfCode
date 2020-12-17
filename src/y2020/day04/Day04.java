package y2020.day04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import adventOfCode.AdventOfCode;

public class Day04 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day04().run();
	}
	
	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2020/day04/Day04Input.txt");
	}

	@Override
	public void part1() throws Exception {
		List<Passport> passports = parseInput();
		int counter = 0;
		for (Passport passport : passports) {
			if(passport.validatePart1()) {
				counter++;
			}
		}
		System.out.println(counter);
	}


	@Override
	public void part2() throws Exception {
		List<Passport> passports = parseInput();
		int counter = 0;
		for (Passport passport : passports) {
			if(passport.validatePart2()) {
				counter++;
			}
		}
		System.out.println(counter);
	}
	
	private List<Passport> parseInput() {

		List<Passport> passports = new ArrayList<>();
		Passport passport = new Passport();
		
		for (String line : input) {
			if(!line.isEmpty()) {
				for (String keyValue : line.split(" ")) {
					String[] split = keyValue.split(":");
					String key = split[0];
					String value = split[1];
					switch (key) {
					case "byr":
						passport.byr = value;
						break;
					case "iyr":
						passport.iyr = value;
						break;
					case "hcl":
						passport.hcl = value;
						break;
					case "ecl":
						passport.ecl = value;
						break;
					case "pid":
						passport.pid = value;
						break;
					case "cid":
						break;
					case "eyr":
						passport.eyr = value;
						break;
					case "hgt":
						passport.hgt = value;
						break;
					default:
						throw new RuntimeException();
					}
				}
			}else {
				passports.add(passport);
				passport = new Passport();
			}
		}
		passports.add(passport);
		return passports;
	}
	
	private static class Passport {

		String byr;
		String iyr;
		String hcl;
		String ecl;
		String pid;
		String eyr;
		String hgt;
		
		private static final Pattern yearPattern = Pattern.compile("(\\d+)");
		private static final Pattern regHcl = Pattern.compile("(#[0-9a-f]{6})");
		private static final Pattern regEcl = Pattern.compile("(amb|blu|brn|gry|grn|hzl|oth)");
		private static final Pattern regPid = Pattern.compile("(\\d{9})");
		private static final Pattern regHgt = Pattern.compile("(\\d+)(in|cm)");
	
		boolean validatePart1() {
			if(byr != null &&
			   iyr != null &&
			   hcl != null &&
			   ecl != null &&
			   pid != null &&
			   eyr != null &&
			   hgt != null) {
				return true;
			}
			return false;
		}
		boolean validatePart2() {
			
			if(!validatePart1()) {
				return false;
			}
			//byr
			Matcher matcher = yearPattern.matcher(byr);
			if(!matcher.matches()) {
				return false;
			}
			int value = Integer.parseInt(matcher.group(1));
			if(value < 1920 || value > 2002) {
				return false;
			}
			//iyr
			matcher = yearPattern.matcher(iyr);
			if(!matcher.matches()) {
				return false;
			}
			value = Integer.parseInt(matcher.group(1));
			if(value < 2010 || value > 2020) {
				return false;
			}
			//eyr
			matcher = yearPattern.matcher(eyr);
			if(!matcher.matches()) {
				return false;
			}
			value = Integer.parseInt(matcher.group(1));
			if(value < 2020 || value > 2030) {
				return false;
			}
			//hgt
			matcher = regHgt.matcher(hgt);
			if(!matcher.matches()) {
				return false;
			}
			value = Integer.parseInt(matcher.group(1));
			if(matcher.group(2).equals("cm")) {
				if(value < 150 || value > 193) {
					return false;
				}	
			}else if(matcher.group(2).equals("in")) {
				if(value < 59 || value > 76) {
					return false;
				}
			}
			//hcl
			matcher = regHcl.matcher(hcl);
			if(!matcher.matches()) {
				return false;
			}
			//ecl
			matcher = regEcl.matcher(ecl);
			if(!matcher.matches()) {
				return false;
			}
			//pid
			matcher = regPid.matcher(pid);
			if(!matcher.matches()) {
				return false;
			}
			return true;
		}
	}
}

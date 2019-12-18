package y2019.day18;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MapState {
	char[][] map;
	Position player;
	Map<Position, Integer> targets;

	public MapState() throws Exception {
		
		targets = new HashMap<>();
//		List<String> reader = Files.readAllLines(Paths.get("src/y2019/day18/Day18Input.txt"));
		List<String> reader = Files.readAllLines(Paths.get("src/y2019/day18/testinput.txt"));

		map = new char[reader.size()][];

		for (int y = 0; y < map.length; y++) {

			String line = reader.get(y);
			map[y] = new char[line.length()];

			for (int x = 0; x < map[y].length; x++) {
				map[y][x] = line.charAt(x);

				if (map[y][x] == '@') {
					player = new Position(x, y);
				}
			}
		}
	}

	public void displayState() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println("");
	}

	public void traverseMaze(Position start) {
		Set<Position> visited = new HashSet<>();
		Queue<Position> path = new ArrayDeque<>();

		path.add(start);
		visited.add(start);
		int tempPathLength = 0;
		int pathLength = 0;
		boolean isIntersection = false;
		boolean intersectionCheck = false;
		while (!path.isEmpty()) {
			isIntersection = false;
			intersectionCheck = false;
			Position pos = path.remove();
			set(pos, '+');
			for (Direction d : Direction.values()) {
				if(intersectionCheck) {
					//save pathlength
					//perhaps as a graphnode
				}
				Position candidate = pos.move(d, 1);
				if (!visited.contains(candidate)) {
					visited.add(candidate);
					char c = check(candidate);
					if (c != '#') {
						
						//fixa så att path längd kapas om vägar slängs bort och
						//den fortsätter på annat håll
						//varje pos ska ha pathlenght?
						
						//när man stöter på en POI, skapa target med path längd
						
						//kanske varje pos kan ha length till allt man vet?(POIs till int dict)
						//borde lösa problem 1 men låter krävande
						
						//
						intersectionCheck = true;
						path.add(candidate);
						tempPathLength++;
					}
				}
				
			}
			intersectionCheck = false;
			pathLength += tempPathLength;
			tempPathLength = 0;
			displayState();
			System.out.println(pathLength);
		}
	}
	
	

	private char check(Position pos) {
		return map[pos.y][pos.x];
	}

	private void set(Position pos, char c) {
		map[pos.y][pos.x] = c;
	}
	
	
}